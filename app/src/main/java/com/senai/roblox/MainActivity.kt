package com.senai.roblox

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.senai.roblox.adapter.ChatAdapter
import com.senai.roblox.databinding.ActivityMainBinding
import com.senai.roblox.model.ChatMessage
import com.senai.roblox.model.MessageType
import com.senai.roblox.network.SenAIService
import kotlinx.coroutines.*
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var chatAdapter: ChatAdapter
    private val messages = mutableListOf<ChatMessage>()
    private val senAIService = SenAIService()
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    
    // Launchers para seleção de arquivos
    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { handleImageSelection(it) }
    }
    
    private val pickFileLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { handleFileSelection(it) }
    }
    
    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmap?.let { handleCameraImage(it) }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "SenAI - Roblox Luau Expert"
        
        setupRecyclerView()
        setupListeners()
        
        // Mensagem de boas-vindas
        addMessage(ChatMessage(
            content = "Olá! Sou a SenAI, sua assistente especializada em códigos Luau para Roblox Studio.\n\n" +
                    "Posso ajudar você a:\n" +
                    "✓ Criar scripts Luau do zero\n" +
                    "✓ Corrigir e otimizar códigos\n" +
                    "✓ Explicar funções e APIs\n" +
                    "✓ Validar scripts\n" +
                    "✓ Obfuscar códigos (quando solicitado)\n\n" +
                    "Você pode me enviar texto, imagens de código ou arquivos .lua!",
            type = MessageType.RECEIVED,
            isCode = false
        ))
    }
    
    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(messages) { code ->
            copyToClipboard(code)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatAdapter
        }
    }
    
    private fun setupListeners() {
        binding.buttonSend.setOnClickListener {
            sendMessage()
        }
        
        binding.buttonAttach.setOnClickListener {
            showAttachmentOptions()
        }
        
        binding.editTextMessage.setOnEditorActionListener { _, _, _ ->
            sendMessage()
            true
        }
    }
    
    private fun sendMessage() {
        val message = binding.editTextMessage.text.toString().trim()
        if (message.isEmpty()) return
        
        binding.editTextMessage.text.clear()
        
        addMessage(ChatMessage(
            content = message,
            type = MessageType.SENT,
            isCode = false
        ))
        
        processWithSenAI(message)
    }
    
    private fun processWithSenAI(userMessage: String) {
        // Mostra indicador de "digitando"
        val typingMessage = ChatMessage(
            content = "Analisando seu código...",
            type = MessageType.RECEIVED,
            isCode = false,
            isTyping = true
        )
        addMessage(typingMessage)
        
        scope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    senAIService.processLuauCode(userMessage)
                }
                
                // Remove mensagem de "digitando"
                messages.removeAt(messages.lastIndex)
                chatAdapter.notifyItemRemoved(messages.size)
                
                // Adiciona resposta da SenAI
                addMessage(ChatMessage(
                    content = response.explanation ?: "Código processado com sucesso!",
                    type = MessageType.RECEIVED,
                    isCode = false
                ))
                
                // Se houver código na resposta, adiciona separadamente
                if (response.code.isNotEmpty()) {
                    addMessage(ChatMessage(
                        content = response.code,
                        type = MessageType.RECEIVED,
                        isCode = true,
                        canObfuscate = response.canObfuscate
                    ))
                }
                
            } catch (e: Exception) {
                messages.removeAt(messages.lastIndex)
                chatAdapter.notifyItemRemoved(messages.size)
                
                addMessage(ChatMessage(
                    content = "Erro ao processar: ${e.message}\n\nPor favor, tente novamente.",
                    type = MessageType.RECEIVED,
                    isCode = false
                ))
            }
        }
    }
    
    private fun showAttachmentOptions() {
        val options = arrayOf("Galeria de Imagens", "Tirar Foto", "Arquivo (.lua, .txt)")
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Anexar")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> pickImageLauncher.launch("image/*")
                1 -> checkCameraPermissionAndTakePicture()
                2 -> pickFileLauncher.launch("*/*")
            }
        }
        builder.show()
    }
    
    private fun checkCameraPermissionAndTakePicture() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, 
                arrayOf(Manifest.permission.CAMERA), 100)
        } else {
            takePictureLauncher.launch(null)
        }
    }
    
    private fun handleImageSelection(uri: Uri) {
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            recognizeTextFromImage(bitmap)
        } catch (e: IOException) {
            Toast.makeText(this, "Erro ao ler imagem: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun handleCameraImage(bitmap: Bitmap) {
        recognizeTextFromImage(bitmap)
    }
    
    private fun recognizeTextFromImage(bitmap: Bitmap) {
        addMessage(ChatMessage(
            content = "📷 Imagem recebida. Extraindo código...",
            type = MessageType.SENT,
            isCode = false
        ))
        
        val image = InputImage.fromBitmap(bitmap, 0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        
        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                val extractedText = visionText.text
                if (extractedText.isNotEmpty()) {
                    addMessage(ChatMessage(
                        content = "✓ Código extraído da imagem:",
                        type = MessageType.RECEIVED,
                        isCode = false
                    ))
                    
                    processWithSenAI("Analise e corrija este código Luau:\n\n$extractedText")
                } else {
                    addMessage(ChatMessage(
                        content = "Não consegui detectar texto na imagem. Tente uma foto mais clara.",
                        type = MessageType.RECEIVED,
                        isCode = false
                    ))
                }
            }
            .addOnFailureListener { e ->
                addMessage(ChatMessage(
                    content = "Erro ao processar imagem: ${e.message}",
                    type = MessageType.RECEIVED,
                    isCode = false
                ))
            }
    }
    
    private fun handleFileSelection(uri: Uri) {
        try {
            val inputStream = contentResolver.openInputStream(uri)
            val fileContent = inputStream?.bufferedReader()?.use { it.readText() }
            
            if (fileContent != null) {
                addMessage(ChatMessage(
                    content = "📄 Arquivo recebido e lido com sucesso!",
                    type = MessageType.SENT,
                    isCode = false
                ))
                
                processWithSenAI("Analise este código Luau:\n\n$fileContent")
            } else {
                Toast.makeText(this, "Erro ao ler arquivo", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun addMessage(message: ChatMessage) {
        messages.add(message)
        chatAdapter.notifyItemInserted(messages.size - 1)
        binding.recyclerView.smoothScrollToPosition(messages.size - 1)
    }
    
    private fun copyToClipboard(text: String) {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = android.content.ClipData.newPlainText("Código Luau", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "✓ Código copiado!", Toast.LENGTH_SHORT).show()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                messages.clear()
                chatAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Conversa limpa", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun showAboutDialog() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Sobre a SenAI")
        builder.setMessage(
            "SenAI v1.0\n\n" +
            "Inteligência Artificial especializada em códigos Luau para Roblox Studio.\n\n" +
            "Desenvolvida para criar, corrigir, validar e otimizar scripts usando apenas APIs oficiais do Roblox.\n\n" +
            "© 2025 - Todos os direitos reservados"
        )
        builder.setPositiveButton("OK", null)
        builder.show()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
