# Configuração da API - SenAI

## Como Configurar a API da Anthropic (Claude)

### Passo 1: Obter API Key

1. Acesse: https://console.anthropic.com/
2. Crie uma conta ou faça login
3. Vá em "API Keys"
4. Clique em "Create Key"
5. Copie a chave gerada (começa com "sk-ant-")

### Passo 2: Adicionar no Código

Abra o arquivo: `app/src/main/java/com/senai/roblox/network/SenAIService.kt`

Localize a linha no construtor da classe onde configura o interceptor:

```kotlin
.addInterceptor { chain ->
    val original = chain.request()
    val request = original.newBuilder()
        .header("Content-Type", "application/json")
        .header("anthropic-version", "2023-06-01")
        .header("x-api-key", "SUA_API_KEY_AQUI") // <-- ADICIONE ESTA LINHA
        .method(original.method, original.body)
        .build()
    chain.proceed(request)
}
```

Substitua `"SUA_API_KEY_AQUI"` pela sua chave real.

### Passo 3: Recompilar

Após adicionar a chave, recompile o app:
```bash
./gradlew assembleDebug
```

---

## Modo de Demonstração (Padrão)

Por padrão, o app funciona em **modo de demonstração** (sem necessidade de API key).

Este modo:
- ✅ Funciona offline (após primeira execução)
- ✅ Responde com exemplos pré-programados
- ✅ Demonstra todas as funcionalidades
- ❌ Não tem a inteligência completa da IA
- ❌ Respostas limitadas e genéricas

**Ideal para**: Testes, demonstrações, desenvolvimento inicial

---

## Modo Produção (Com API)

Com API key configurada:
- ✅ Inteligência artificial completa
- ✅ Análise real de código Luau
- ✅ Validação precisa de APIs do Roblox
- ✅ Respostas personalizadas
- ✅ Correções inteligentes
- ⚠️ Requer internet
- ⚠️ Pode ter custos de API

**Ideal para**: Uso profissional, produção, usuários finais

---

## Custos da API Anthropic

### Claude Sonnet 4 (Modelo usado)

- **Input**: ~$3 por milhão de tokens
- **Output**: ~$15 por milhão de tokens

### Estimativa de Uso

**Conversa simples** (envio de 100 palavras, resposta de 300 palavras):
- Input: ~130 tokens
- Output: ~400 tokens
- Custo: ~$0.006 (menos de 1 centavo)

**Análise de código** (envio de 500 linhas, resposta de 600 linhas):
- Input: ~600 tokens
- Output: ~700 tokens
- Custo: ~$0.012 (~1 centavo)

**Para 1000 usuários/dia** fazendo média de 10 conversas cada:
- ~10.000 conversas/dia
- Custo estimado: $60-120/dia
- Custo mensal: ~$1.800-3.600

---

## Alternativas Gratuitas

### 1. Modo de Demonstração
Use o modo padrão sem API key.

### 2. Anthropic Credits
A Anthropic oferece créditos gratuitos iniciais para novos usuários.

### 3. Backend Próprio
Desenvolva um servidor próprio que:
- Recebe requisições do app
- Processa com sua própria lógica/IA
- Retorna respostas no formato esperado

### 4. Outras APIs de IA
Adapte o código para usar:
- OpenAI GPT-4
- Google Gemini
- Modelos open-source locais

---

## Segurança da API Key

⚠️ **NUNCA** commit sua API key no GitHub ou compartilhe publicamente!

### Boas Práticas:

1. **Use variáveis de ambiente**:
```kotlin
val apiKey = System.getenv("ANTHROPIC_API_KEY") ?: "modo_demo"
```

2. **Use BuildConfig**:
```gradle
// No app/build.gradle
android {
    buildTypes {
        release {
            buildConfigField "String", "API_KEY", "\"${System.getenv("API_KEY")}\""
        }
    }
}
```

```kotlin
// No código
.header("x-api-key", BuildConfig.API_KEY)
```

3. **Backend intermediário** (mais seguro):
```
App → Seu Servidor → Anthropic API
```
Assim a API key fica apenas no servidor, não no app.

---

## Verificar se API está Funcionando

Execute o app e envie a mensagem:
```
Olá, você está funcionando?
```

**Com API configurada**:
- Resposta personalizada e inteligente
- Demora 2-5 segundos
- Conexão com internet necessária

**Modo demonstração**:
- Resposta genérica pré-programada
- Resposta instantânea após 2s simulados
- Funciona offline

---

## Problemas Comuns

### "401 Unauthorized"
- **Causa**: API key inválida ou não configurada
- **Solução**: Verifique se adicionou a chave corretamente

### "429 Rate Limit"
- **Causa**: Muitas requisições em pouco tempo
- **Solução**: Implemente rate limiting no app ou aguarde alguns minutos

### "500 Internal Server Error"
- **Causa**: Problema no servidor da Anthropic
- **Solução**: Tente novamente em alguns minutos

### App sempre usa modo demo
- **Causa**: API key não foi adicionada corretamente
- **Solução**: Verifique o código e recompile

---

## Para Desenvolvedores

### Estrutura da Requisição

```kotlin
{
    "model": "claude-sonnet-4-20250514",
    "max_tokens": 4096,
    "messages": [
        {
            "role": "user",
            "content": "Sua mensagem aqui"
        }
    ],
    "system": "Prompt do sistema (especialização Luau)"
}
```

### Estrutura da Resposta

```kotlin
{
    "content": [
        {
            "type": "text",
            "text": "Resposta da IA aqui"
        }
    ],
    "stop_reason": "end_turn"
}
```

---

## Contato Anthropic

- **Website**: https://www.anthropic.com
- **Documentação**: https://docs.anthropic.com
- **Console**: https://console.anthropic.com
- **Suporte**: support@anthropic.com

---

**Lembre-se**: O modo de demonstração já fornece uma experiência completa para testar o app. A API é necessária apenas para funcionalidade de IA real em produção.
