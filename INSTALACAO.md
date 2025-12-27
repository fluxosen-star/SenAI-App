# 🚀 Guia Rápido de Instalação - SenAI

## Para Usuários Finais (Sem Conhecimento Técnico)

### Opção 1: Baixar APK Pronto (Recomendado)
**IMPORTANTE**: O APK final precisa ser gerado primeiro. Veja instruções abaixo.

1. **Baixe o arquivo APK** no seu celular Android
2. **Permita instalação de fontes desconhecidas**:
   - Configurações → Segurança → Fontes Desconhecidas → Ativar
   - OU quando abrir o APK, siga as instruções na tela
3. **Abra o arquivo APK baixado**
4. **Clique em "Instalar"**
5. **Abra o app "SenAI"** após instalação
6. **Pronto!** Comece a usar

### Opção 2: Instalar via Android Studio (Para Desenvolvedores)

#### Pré-requisitos
- Computador Windows/Mac/Linux
- Mínimo 8GB RAM (recomendado 16GB)
- 10GB de espaço livre em disco
- Conexão com internet

#### Passo a Passo

**1. Instalar Android Studio**
- Acesse: https://developer.android.com/studio
- Baixe a versão para seu sistema operacional
- Execute o instalador e siga as instruções
- **Duração**: ~30 minutos

**2. Abrir o Projeto**
- Abra o Android Studio
- Clique em "Open"
- Navegue até a pasta `SenAI-App`
- Clique em "OK"
- **Aguarde o Gradle sincronizar** (primeira vez pode demorar 10-15 minutos)

**3. Configurar Dispositivo**

**Opção A - Dispositivo Físico** (Recomendado):
- Conecte seu celular Android no computador via USB
- No celular: Configurações → Sobre o telefone
- Toque 7 vezes em "Número da compilação"
- Volte e acesse "Opções do desenvolvedor"
- Ative "Depuração USB"
- Autorize o computador quando aparecer a mensagem

**Opção B - Emulador** (Mais lento):
- No Android Studio: Tools → Device Manager
- Clique em "Create Device"
- Escolha "Pixel 5" ou similar
- Clique em "Next" e depois "Finish"
- Aguarde o download (~2GB)

**4. Executar o App**
- Clique no botão ▶️ (Run) ou pressione Shift+F10
- Selecione seu dispositivo na lista
- Aguarde a compilação (~3-5 minutos na primeira vez)
- **O app será instalado automaticamente no dispositivo!**

**5. Usar o App**
- Abra o app "SenAI" no celular
- Comece a interagir com a IA!

---

## Para Gerar APK (Desenvolvedores)

### Gerar APK de Debug (Rápido)

```bash
# No terminal do Android Studio ou terminal do sistema:
cd SenAI-App
./gradlew assembleDebug

# O APK estará em:
# app/build/outputs/apk/debug/app-debug.apk
```

### Gerar APK de Release (Para Distribuição)

**1. Criar Keystore** (primeira vez apenas):
```bash
keytool -genkey -v -keystore senai-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias senai
```

**2. Configurar app/build.gradle**:
Adicione antes de `android {`:
```gradle
android {
    signingConfigs {
        release {
            storeFile file("../senai-release-key.jks")
            storePassword "SUA_SENHA"
            keyAlias "senai"
            keyPassword "SUA_SENHA"
        }
    }
    
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

**3. Gerar APK**:
```bash
./gradlew assembleRelease

# O APK estará em:
# app/build/outputs/apk/release/app-release.apk
```

**4. Distribuir**:
- Copie o `app-release.apk` para um servidor/drive
- Compartilhe o link com os usuários
- Eles podem instalar diretamente no Android

---

## Requisitos do Sistema

### Para Compilar
- **SO**: Windows 10+, macOS 10.14+, ou Linux Ubuntu 18.04+
- **RAM**: 8GB mínimo (16GB recomendado)
- **Espaço**: 10GB livre
- **Internet**: Para download de dependências

### Para Usar (Dispositivo Final)
- **Android**: 7.0 (API 24) ou superior
- **RAM**: 2GB mínimo
- **Espaço**: 50MB
- **Internet**: Necessária para funcionalidade completa
- **Câmera**: Opcional (para OCR de imagens)

---

## Resolução de Problemas Comuns

### "Gradle sync failed"
**Solução**:
1. File → Invalidate Caches → Invalidate and Restart
2. Aguarde reiniciar
3. File → Sync Project with Gradle Files

### "SDK not found"
**Solução**:
1. Tools → SDK Manager
2. Instale Android SDK Platform 34
3. Instale Android SDK Build-Tools 34

### "Device not found"
**Solução**:
1. Verifique se USB debugging está ativado
2. Tente outro cabo USB
3. Reinstale drivers USB do celular no PC

### "Build failed"
**Solução**:
1. Build → Clean Project
2. Build → Rebuild Project
3. Se persistir, delete pasta `.gradle` e `.idea` e reabra o projeto

### App trava ao abrir
**Solução**:
1. Verifique permissões (Câmera, Armazenamento)
2. Reinstale o app
3. Limpe cache: Configurações → Apps → SenAI → Limpar Cache

---

## Permissões Necessárias

O app solicita as seguintes permissões:

- **Internet**: Para comunicação com a API da IA
- **Câmera**: Para tirar fotos de código (opcional)
- **Armazenamento**: Para ler arquivos .lua/.txt (opcional)

**Todas as permissões são opcionais** exceto Internet. O app funciona sem câmera/armazenamento, mas com funcionalidade limitada.

---

## Dicas de Uso

✅ **Use boa iluminação** ao fotografar código
✅ **Formato de texto** é mais rápido que imagens
✅ **Seja específico** nas perguntas
✅ **Teste o código** no Roblox Studio antes de usar em produção
✅ **Limpe o chat** (menu superior) se ficar lento

---

## Suporte

**Problemas técnicos**:
- Abra uma Issue no GitHub (se disponível)
- Descreva o problema em detalhes
- Inclua prints/logs se possível

**Dúvidas sobre uso**:
- Leia o README.md completo
- Consulte a documentação do Roblox
- Pergunte na comunidade de desenvolvedores

---

## Próximos Passos

Após instalar:

1. ✅ Abra o app e leia a mensagem de boas-vindas
2. ✅ Teste enviando uma mensagem simples: "Olá"
3. ✅ Experimente pedir um código: "Crie um script que aumenta a velocidade do jogador"
4. ✅ Teste o OCR enviando uma foto de código
5. ✅ Copie um código e cole no Roblox Studio

---

**🎉 Divirta-se criando com a SenAI!**

*Em caso de dúvidas, consulte o README.md principal ou abra uma Issue.*
