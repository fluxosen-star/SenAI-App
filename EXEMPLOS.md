# 📚 Exemplos de Uso - SenAI

Este documento contém exemplos práticos de como usar a SenAI para diferentes tarefas relacionadas a Luau/Roblox.

---

## 💬 Conversação Básica

### Exemplo 1: Saudação
**Você envia:**
```
Olá!
```

**SenAI responde:**
```
Olá! Sou a SenAI, especialista em Luau para Roblox. 
Como posso ajudar você hoje?
```

---

## 🆕 Criação de Código

### Exemplo 2: Script de Velocidade
**Você envia:**
```
Crie um script que aumenta a velocidade do jogador para 50 quando ele entra no jogo
```

**SenAI responde:**
```
Aqui está o script que aumenta a velocidade do jogador:

[Bloco de código:]
-- Script para aumentar velocidade do jogador
local Players = game:GetService("Players")

Players.PlayerAdded:Connect(function(player)
    player.CharacterAdded:Connect(function(character)
        local humanoid = character:WaitForChild("Humanoid")
        humanoid.WalkSpeed = 50
        print(player.Name .. " entrou com velocidade 50")
    end)
end)

✅ Este código:
• Usa GetService para acessar Players corretamente
• Aguarda o personagem spawnar com CharacterAdded
• Usa WaitForChild para garantir que Humanoid existe
• Define WalkSpeed para 50
• Funciona tanto em servidor quanto em cliente
```

### Exemplo 3: Sistema de Pontos
**Você envia:**
```
Fazer um script que dá 10 pontos quando o jogador toca em uma parte
```

**SenAI responde:**
```
Sistema de pontos ao tocar em uma parte:

[Bloco de código:]
-- Script dentro da Part que dá pontos
local part = script.Parent
local POINTS_REWARD = 10

part.Touched:Connect(function(hit)
    local humanoid = hit.Parent:FindFirstChild("Humanoid")
    if humanoid then
        local player = game.Players:GetPlayerFromCharacter(hit.Parent)
        if player then
            -- Adiciona pontos (assumindo que existe leaderstats)
            local leaderstats = player:FindFirstChild("leaderstats")
            if leaderstats then
                local points = leaderstats:FindFirstChild("Points")
                if points then
                    points.Value = points.Value + POINTS_REWARD
                    print(player.Name .. " ganhou " .. POINTS_REWARD .. " pontos!")
                end
            end
        end
    end
end)

💡 Explicação:
• Detecta quando algo toca na parte
• Verifica se é um jogador (tem Humanoid)
• Busca o objeto Player correspondente
• Adiciona pontos no sistema de leaderstats
• Inclui verificações de segurança (FindFirstChild)
```

---

## 🔧 Correção de Código

### Exemplo 4: Corrigir Erro
**Você envia:**
```
Corrige este código:

local player = game.Players.LocalPlayer
player.CharacterAdded:Wait()
player.Character.Humanoid.WalkSpeed = 100
```

**SenAI responde:**
```
📋 ANÁLISE DO CÓDIGO:

⚠️ Problemas encontrados:
1. CharacterAdded:Wait() é incorreto - deve ser CharacterAdded:Connect()
2. Acesso direto a Character.Humanoid pode falhar se ainda não existir
3. Falta tratamento de erro

✅ CÓDIGO CORRIGIDO:

[Bloco de código:]
local Players = game:GetService("Players")
local player = Players.LocalPlayer

player.CharacterAdded:Connect(function(character)
    local humanoid = character:WaitForChild("Humanoid")
    if humanoid then
        humanoid.WalkSpeed = 100
    end
end)

-- Se o personagem já existir
if player.Character then
    local humanoid = player.Character:FindFirstChild("Humanoid")
    if humanoid then
        humanoid.WalkSpeed = 100
    end
end

🔧 Mudanças realizadas:
• Substituído Wait() por Connect() com função callback
• Adicionado WaitForChild para garantir existência do Humanoid
• Adicionada verificação para personagem já existente
• Usado GetService para acessar Players corretamente
• Adicionada verificação de segurança com if
```

---

## 🖼️ Uso com Imagens (OCR)

### Exemplo 5: Enviar Print de Código
**Você:**
1. Clica no botão 📎
2. Seleciona "Galeria de Imagens"
3. Escolhe uma foto de código na tela

**SenAI responde:**
```
📷 Imagem recebida. Extraindo código...

✓ Código extraído da imagem:

[Análise automática do código extraído...]

[Código corrigido se houver erros...]
```

---

## 📎 Uso com Arquivos

### Exemplo 6: Enviar Arquivo .lua
**Você:**
1. Clica no botão 📎
2. Seleciona "Arquivo (.lua, .txt)"
3. Escolhe um arquivo MeuScript.lua

**SenAI responde:**
```
📄 Arquivo recebido e lido com sucesso!

Analisando seu código Luau...

[Análise completa do arquivo...]

[Sugestões de otimização...]

[Código melhorado...]
```

---

## ⚡ Otimização de Código

### Exemplo 7: Otimizar Performance
**Você envia:**
```
Otimize este código:

while true do
    wait(0.1)
    for i, player in pairs(game.Players:GetPlayers()) do
        if player.Character then
            print(player.Name)
        end
    end
end
```

**SenAI responde:**
```
🚀 CÓDIGO OTIMIZADO:

[Bloco de código:]
local Players = game:GetService("Players")
local RunService = game:GetService("RunService")

-- Usar RunService.Heartbeat ao invés de while true
RunService.Heartbeat:Connect(function()
    -- Cache de GetPlayers para evitar múltiplas chamadas
    local players = Players:GetPlayers()
    
    for _, player in ipairs(players) do
        local character = player.Character
        if character then
            print(player.Name)
        end
    end
    
    -- Adicionar pequeno delay se necessário
    task.wait(0.1)
end)

⚡ Otimizações aplicadas:
1. ✓ Substituído while true por RunService.Heartbeat
2. ✓ Usado ipairs ao invés de pairs (mais rápido)
3. ✓ Cachê de player.Character em variável local
4. ✓ Usado task.wait ao invés de wait (mais preciso)
5. ✓ GetService para acesso aos serviços

📊 Resultado: ~30% mais rápido e mais estável
```

---

## 🔍 Explicação de Código

### Exemplo 8: Explicar Função
**Você envia:**
```
Explique o que faz este código:

local TweenService = game:GetService("TweenService")
local part = workspace.MyPart
local tweenInfo = TweenInfo.new(2, Enum.EasingStyle.Quad, Enum.EasingDirection.Out)
local goal = {Position = part.Position + Vector3.new(0, 10, 0)}
local tween = TweenService:Create(part, tweenInfo, goal)
tween:Play()
```

**SenAI responde:**
```
📖 EXPLICAÇÃO LINHA POR LINHA:

Linha 1: `local TweenService = game:GetService("TweenService")`
→ Acessa o serviço TweenService do Roblox, usado para criar animações suaves

Linha 2: `local part = workspace.MyPart`
→ Referência à parte "MyPart" que está no Workspace

Linha 3: `local tweenInfo = TweenInfo.new(2, Enum.EasingStyle.Quad, Enum.EasingDirection.Out)`
→ Cria configuração da animação:
   • Duração: 2 segundos
   • Estilo: Quadrático (aceleração/desaceleração suave)
   • Direção: Out (começa rápido, termina devagar)

Linha 4: `local goal = {Position = part.Position + Vector3.new(0, 10, 0)}`
→ Define o objetivo: mover a peça 10 studs para cima (eixo Y)

Linha 5: `local tween = TweenService:Create(part, tweenInfo, goal)`
→ Cria a animação combinando a peça, configuração e objetivo

Linha 6: `tween:Play()`
→ Inicia a animação

🎯 RESUMO:
Este código move suavemente uma peça 10 studs para cima em 2 segundos,
com uma animação que começa rápida e termina devagar.
```

---

## 🔐 Obfuscação

### Exemplo 9: Ofuscar Código
**Você envia:**
```
Ofusque este código:

local Players = game:GetService("Players")
local SECRET_CODE = "abc123"

function checkCode(player, code)
    if code == SECRET_CODE then
        return true
    end
    return false
end
```

**SenAI responde:**
```
⚠️ AVISO: Deseja realmente ofuscar este código?

A ofuscação irá:
✓ Dificultar a leitura humana
✓ Manter a funcionalidade
✗ Não tornar 100% seguro
✗ Dificultar manutenção futura

Responda "sim" para confirmar a ofuscação.
```

**Você responde:** `sim`

**SenAI responde:**
```
🔐 CÓDIGO OFUSCADO:

[Bloco de código ofuscado - exemplo conceitual]

⚠️ Importante:
• Guarde o código original em local seguro
• Ofuscação não é criptografia
• Teste antes de usar em produção
```

---

## 🎓 Aprendizado

### Exemplo 10: Aprender sobre APIs
**Você envia:**
```
Como funciona o RemoteEvent no Roblox?
```

**SenAI responde:**
```
📚 REMOTE EVENTS - COMUNICAÇÃO CLIENTE-SERVIDOR

RemoteEvents permitem comunicação entre cliente (LocalScript) e servidor (Script).

🔹 NO SERVIDOR (Script):
[Bloco de código:]
local ReplicatedStorage = game:GetService("ReplicatedStorage")
local remoteEvent = ReplicatedStorage:WaitForChild("MyRemoteEvent")

-- Receber do cliente
remoteEvent.OnServerEvent:Connect(function(player, data)
    print(player.Name .. " enviou: " .. data)
    
    -- Enviar de volta para todos
    remoteEvent:FireAllClients("Mensagem para todos")
    
    -- Ou enviar só para quem enviou
    remoteEvent:FireClient(player, "Mensagem só para você")
end)

🔹 NO CLIENTE (LocalScript):
[Bloco de código:]
local ReplicatedStorage = game:GetService("ReplicatedStorage")
local remoteEvent = ReplicatedStorage:WaitForChild("MyRemoteEvent")

-- Enviar para servidor
remoteEvent:FireServer("Olá servidor!")

-- Receber do servidor
remoteEvent.OnClientEvent:Connect(function(data)
    print("Servidor enviou: " .. data)
end)

📌 RESUMO:
• Cliente → Servidor: FireServer()
• Servidor → Cliente: FireClient(player)
• Servidor → Todos: FireAllClients()
• RemoteEvent deve estar em ReplicatedStorage
```

---

## 🎯 Dicas de Uso

### Para Melhores Resultados:

1. **Seja específico**: "Crie script que teletransporta jogador" → "Crie script que teletransporta o jogador para (0, 50, 0) quando ele toca em uma parte vermelha"

2. **Use contexto**: "Corrija isto: [código]" → "Corrija este código de teleporte que está dando erro quando o jogador não tem Character: [código]"

3. **Peça explicações**: "Explique este código linha por linha"

4. **Otimize progressivamente**: Primeiro crie, depois peça para otimizar

5. **Teste sempre**: Copie o código e teste no Roblox Studio antes de usar em produção

---

## ❓ Perguntas Comuns

### "A SenAI pode criar qualquer tipo de script?"
✅ Sim, qualquer código Luau válido do Roblox usando APIs oficiais

### "Posso enviar códigos grandes?"
✅ Sim, mas preferencialmente use arquivos .lua para códigos >100 linhas

### "A SenAI aprende com minhas conversas?"
❌ Não entre conversas, mas valida padrões internamente para melhorar

### "Posso usar os códigos comercialmente?"
✅ Sim, todos os códigos gerados são de uso livre

### "A SenAI funciona offline?"
⚠️ Modo demonstração sim, mas com funcionalidade limitada

---

**💡 Dica Final**: Explore, experimente e teste! A SenAI está aqui para acelerar seu desenvolvimento Roblox!
