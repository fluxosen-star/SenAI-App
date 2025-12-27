# SenAI - Inteligência Artificial para Roblox Luau

![SenAI Logo](https://img.shields.io/badge/SenAI-Roblox%20Luau%20Expert-00D4FF?style=for-the-badge)

## 📱 Sobre o Aplicativo

**SenAI** é uma Inteligência Artificial **EXCLUSIVA** para códigos Luau do Roblox Studio. Desenvolvida para criar, corrigir, validar, explicar, otimizar e obfuscar scripts Luau de forma profissional e eficiente.

### 🎯 Características Principais

- ✅ **Especialização Total**: Focada exclusivamente em Luau/Roblox
- ✅ **APIs Oficiais**: Usa SOMENTE APIs oficiais do Roblox
- ✅ **Multimodal**: Aceita texto, imagens (OCR) e arquivos (.lua, .txt)
- ✅ **Validação Automática**: Analisa linha por linha
- ✅ **Otimização Inteligente**: Melhora performance mantendo a lógica
- ✅ **Interface ChatGPT-style**: Design moderno e minimalista
- ✅ **Tema Escuro**: Interface preta e profissional
- ✅ **Gratuito**: Sem custo para os usuários

---

## 🚀 Funcionalidades

### 💬 Chat Inteligente
- Interface estilo ChatGPT
- Bolhas de conversa diferenciadas
- Respostas em tempo real
- Histórico de conversação

### 📸 Suporte a Imagens
- Envio de fotos/prints de código
- OCR (reconhecimento de texto) automático
- Extração e análise de código de imagens
- Suporte para câmera e galeria

### 📎 Arquivos
- Upload de arquivos .lua
- Upload de arquivos .txt
- Análise de código de arquivos
- Leitura automática de conteúdo

### 🔍 Análise de Código
- Detecção de erros lógicos
- Validação de serviços do Roblox
- Validação de eventos e APIs
- Identificação de más práticas

### ⚡ Otimização
- Refatoração de código
- Melhoria de performance
- Aplicação de boas práticas
- Manutenção da lógica original

### 🔐 Obfuscação
- Ofuscação opcional de código
- Mantém funcionalidade
- Dificulta leitura humana
- Nunca quebra APIs do Roblox

---

## 🛠️ Tecnologias Utilizadas

### Frontend (Android)
- **Linguagem**: Kotlin
- **UI**: Material Design 3
- **Arquitetura**: MVVM
- **ViewBinding**: Para manipulação de views

### Bibliotecas
- **Retrofit**: Chamadas de API
- **OkHttp**: Cliente HTTP
- **Coroutines**: Programação assíncrona
- **ML Kit**: OCR (Text Recognition)
- **RecyclerView**: Lista de mensagens
- **Glide**: Carregamento de imagens

### Backend/API
- **Modelo Base**: Claude Sonnet 4 (Anthropic API)
- **Especialização**: Prompt engineering para Luau/Roblox
- **Validação**: Sistema de verificação de APIs oficiais

---

## 📦 Estrutura do Projeto

```
SenAI-App/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/senai/roblox/
│   │       │   ├── MainActivity.kt          # Activity principal
│   │       │   ├── adapter/
│   │       │   │   └── ChatAdapter.kt       # Adaptador do chat
│   │       │   ├── model/
│   │       │   │   ├── ChatMessage.kt       # Modelo de mensagem
│   │       │   │   └── SenAIResponse.kt     # Modelo de resposta
│   │       │   └── network/
│   │       │       └── SenAIService.kt      # Serviço de API
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   ├── activity_main.xml    # Layout principal
│   │       │   │   ├── item_message_sent.xml
│   │       │   │   ├── item_message_received.xml
│   │       │   │   └── item_message_code.xml
│   │       │   ├── drawable/                # Recursos gráficos
│   │       │   ├── values/                  # Strings, cores, temas
│   │       │   └── menu/                    # Menu da toolbar
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🔧 Como Compilar

### Pré-requisitos
- **Android Studio** (versão Flamingo ou superior)
- **JDK 17** ou superior
- **SDK Android** (API 24 ou superior)

### Passos

1. **Clone ou baixe o projeto**
   ```bash
   # Se tiver Git instalado
   git clone [URL_DO_REPOSITORIO]
   ```

2. **Abra no Android Studio**
   - File → Open
   - Selecione a pasta `SenAI-App`
   - Aguarde o Gradle sincronizar

3. **Configure a API Key (opcional)**
   - Abra `SenAIService.kt`
   - Se quiser usar a API real da Anthropic, adicione sua chave
   - Por padrão, o app usa um modo de demonstração

4. **Compile e Execute**
   - Conecte um dispositivo Android ou inicie um emulador
   - Clique em "Run" (▶️) ou pressione Shift+F10
   - O app será instalado automaticamente

---

## 📱 Como Usar

### Primeira Execução
1. Abra o aplicativo **SenAI**
2. Leia a mensagem de boas-vindas
3. Comece a interagir!

### Enviar Mensagem de Texto
1. Digite sua pergunta/código na caixa de texto
2. Pressione o botão de enviar (✉️)
3. Aguarde a resposta da SenAI

### Enviar Imagem de Código
1. Clique no botão de anexo (📎)
2. Escolha "Galeria de Imagens" ou "Tirar Foto"
3. Selecione/tire a foto do código
4. A SenAI extrairá e analisará automaticamente

### Enviar Arquivo
1. Clique no botão de anexo (📎)
2. Escolha "Arquivo (.lua, .txt)"
3. Selecione o arquivo
4. A SenAI lerá e processará o conteúdo

### Copiar Código
1. Toda resposta de código tem um botão "Copiar"
2. Clique para copiar para a área de transferência
3. Cole no Roblox Studio!

---

## 🧠 Sistema de Funcionamento

### Fluxo de Processamento

```
1. Entrada do Usuário
   ├── Texto
   ├── Imagem (OCR)
   └── Arquivo

2. Pré-processamento
   ├── Extração de código (se imagem/arquivo)
   └── Formatação da mensagem

3. Envio para IA
   ├── Sistema de prompt especializado em Luau
   └── Contexto de Roblox APIs

4. Análise pela SenAI
   ├── Validação linha por linha
   ├── Detecção de erros
   ├── Verificação de APIs oficiais
   └── Otimização

5. Resposta
   ├── Explicação textual
   └── Código otimizado (se aplicável)

6. Apresentação ao Usuário
   ├── Bolha de texto
   └── Bloco de código (com botão copiar)
```

### Validação de APIs

A SenAI conhece e valida:

**Serviços Principais:**
- `game:GetService("Players")`
- `game:GetService("Workspace")`
- `game:GetService("ReplicatedStorage")`
- `game:GetService("ServerScriptService")`
- `game:GetService("StarterPlayer")`
- E muitos outros...

**Eventos Comuns:**
- `Instance.Changed`
- `BasePart.Touched`
- `Player.CharacterAdded`
- `Humanoid.Died`
- `RemoteEvent:FireServer()`
- E centenas mais...

---

## 🎨 Interface do Usuário

### Cores do Tema
- **Fundo Principal**: #000000 (Preto puro)
- **Fundo Secundário**: #1a1a1a (Cinza muito escuro)
- **Mensagens Enviadas**: #00D4FF (Ciano)
- **Mensagens Recebidas**: #2a2a2a (Cinza escuro)
- **Texto**: #FFFFFF (Branco)
- **Código**: #E0E0E0 (Cinza claro em fundo #0d0d0d)

### Elementos Visuais
- **Bordas arredondadas**: 16dp para mensagens
- **Fonte de código**: Monospace
- **Ícones**: Material Design
- **Animações**: Suaves e responsivas

---

## 🔐 Privacidade e Segurança

- ✅ **Sem coleta de dados**: Nenhum dado pessoal é armazenado
- ✅ **Processamento seguro**: Comunicação via HTTPS
- ✅ **Sem rastreamento**: Zero analytics ou tracking
- ✅ **Código aberto**: Totalmente auditável

---

## 🐛 Resolução de Problemas

### App não compila
- Verifique se o Android Studio está atualizado
- Sincronize o Gradle: File → Sync Project with Gradle Files
- Limpe o build: Build → Clean Project

### OCR não funciona
- Verifique as permissões de câmera
- Use imagens claras e bem iluminadas
- Certifique-se que o texto está legível

### API não responde
- O app tem modo de demonstração integrado
- Para produção, configure uma chave de API válida
- Verifique sua conexão com a internet

---

## 📈 Roadmap Futuro

### Versão 1.1
- [ ] Suporte para múltiplas conversas
- [ ] Histórico persistente
- [ ] Exportar conversa para PDF
- [ ] Modo claro (opcional)

### Versão 1.2
- [ ] Obfuscação avançada
- [ ] Análise de performance de scripts
- [ ] Sugestões de otimização em tempo real
- [ ] Integração com GitHub

### Versão 2.0
- [ ] Versão iOS
- [ ] Versão Web
- [ ] Modo colaborativo
- [ ] Biblioteca de scripts compartilhados

---

## 👥 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Fork este repositório
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

---

## 📄 Licença

Este projeto é fornecido "como está", sem garantias de qualquer tipo.

---

## 📞 Suporte

Para dúvidas ou sugestões:
- Abra uma Issue no repositório
- Entre em contato através do email (a definir)

---

## 🙏 Agradecimentos

- **Anthropic**: Pela API Claude que possibilita a IA
- **Roblox**: Pela plataforma incrível
- **Comunidade**: Todos os desenvolvedores Luau

---

## 🌟 Por que SenAI?

A SenAI foi criada porque desenvolvedores Roblox merecem uma ferramenta especializada que:

✓ **Entende Luau de verdade** - Não é uma IA genérica adaptada
✓ **Conhece as APIs oficiais** - Nunca sugere código inválido
✓ **Valida antes de responder** - Código testado e funcional
✓ **É gratuita** - Acessível para todos os desenvolvedores
✓ **Aprende continuamente** - Melhora a cada interação validada

---

**Desenvolvido com ❤️ para a comunidade Roblox**

*SenAI - Transformando ideias em código Luau funcional desde 2025*
