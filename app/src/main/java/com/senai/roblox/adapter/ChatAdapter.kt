package com.senai.roblox.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.senai.roblox.R
import com.senai.roblox.model.ChatMessage
import com.senai.roblox.model.MessageType

class ChatAdapter(
    private val messages: List<ChatMessage>,
    private val onCopyClick: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 2
        private const val VIEW_TYPE_CODE = 3
    }
    
    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        return when {
            message.isCode -> VIEW_TYPE_CODE
            message.type == MessageType.SENT -> VIEW_TYPE_SENT
            else -> VIEW_TYPE_RECEIVED
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SENT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_sent, parent, false)
                SentMessageViewHolder(view)
            }
            VIEW_TYPE_CODE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_code, parent, false)
                CodeMessageViewHolder(view, onCopyClick)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_received, parent, false)
                ReceivedMessageViewHolder(view)
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder) {
            is SentMessageViewHolder -> holder.bind(message)
            is ReceivedMessageViewHolder -> holder.bind(message)
            is CodeMessageViewHolder -> holder.bind(message)
        }
    }
    
    override fun getItemCount() = messages.size
    
    class SentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textMessage: TextView = itemView.findViewById(R.id.textMessage)
        
        fun bind(message: ChatMessage) {
            textMessage.text = message.content
        }
    }
    
    class ReceivedMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textMessage: TextView = itemView.findViewById(R.id.textMessage)
        
        fun bind(message: ChatMessage) {
            textMessage.text = message.content
            
            if (message.isTyping) {
                textMessage.alpha = 0.7f
            } else {
                textMessage.alpha = 1.0f
            }
        }
    }
    
    class CodeMessageViewHolder(
        itemView: View,
        private val onCopyClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val textCode: TextView = itemView.findViewById(R.id.textCode)
        private val buttonCopy: Button = itemView.findViewById(R.id.buttonCopy)
        
        fun bind(message: ChatMessage) {
            textCode.text = message.content
            textCode.typeface = Typeface.MONOSPACE
            
            buttonCopy.setOnClickListener {
                onCopyClick(message.content)
            }
        }
    }
}
