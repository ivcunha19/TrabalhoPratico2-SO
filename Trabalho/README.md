# Simulador de Gerenciamento de Memória Virtual - Trabalho Prático 2

Este projeto é um simulador de gerenciamento de memória virtual que avalia o desempenho de diferentes algoritmos de substituição de páginas através de métricas de Faltas de Página (*Page Faults*).

## Instruções de Compilação e Execução

### Pré-requisitos
- JDK (Java Development Kit) 17 ou superior.

### Compilação
Abra o terminal no diretório raiz do projeto e execute:
```bash
javac -d bin src/*.java
```

### Execução
Garanta que o arquivo de entrada `referencias.txt` esteja configurado no diretório raiz e execute:
```bash
java -cp bin App
```

---

## Algoritmo MY: Segunda Chance Randomizada

### 1. Intuição
Algoritmos determinísticos tradicionais baseados em ordem (como FIFO e Segunda Chance clássica) sofrem quando a sequência de acessos realiza um loop repetitivo sobre uma quantidade de páginas superior à capacidade da memória física (ex: loop de 4 páginas em uma memória de 3 quadros). Nesses casos, a taxa de acertos cai para 0%, pois todas as páginas necessárias são inseridas sequencialmente exatamente antes de serem acessadas de novo.

O nosso algoritmo resolve este problema quebrando esse determinismo. Ao introduzir escolhas aleatórias e combiná-las com o bit de referência R, o algoritmo impede esse loop cíclico, evitando que a taxa de acertos seja zerada.

### 2. Mecânica de Funcionamento
1. As páginas são mantidas em um array linear de quadros físicos (`listaQuadros`).
2. **Caso de HIT**:
   - A página já está na memória. O algoritmo simplesmente define o bit de referência R = 1.
3. **Caso de MISS (Page Fault)**:
   - Uma falta de página é contabilizada.
   - Se ainda houver quadros vazios disponíveis na memória, a página é carregada diretamente e recebe os bits iniciais R = 1.
   - Se a memória estiver completamente cheia:
     - Sorteia-se um índice aleatório correspondente aos quadros ocupados.
     - Inspeciona-se o bit de referência R da página sorteada:
       - Se R == 1, a página recebe uma segunda chance: o seu bit é zerado (R = 0) e um novo sorteio de índice é realizado.
       - Se R == 0, a página é a vítima selecionada e é substituída diretamente no quadro pelo novo acesso com R = 1.

### 3. Complexidade Arquitetural
- **Complexidade de Tempo**: Tempo médio de execução O(1). Não há necessidade de reordenar listas encadeadas, fazer inserções ou atualizar ponteiros a cada hit de página.
- **Complexidade de Espaço**: O(N) para armazenar o array simples contendo as referências das páginas.

---

## Relatório Comparativo de Desempenho

### Cenário de Teste (referencias.txt)
Configuramos um cenário de loop cíclico para expor a vulnerabilidade de algoritmos determinísticos e verificar a imunidade do Algoritmo MY:
- **Quantidade de Quadros**: 3
- **Cadeia de Acessos**: `0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3` (Total: 16 acessos em loop cíclico)

### Resultados Obtidos

| Algoritmo | Faltas de Página (*Page Faults*) | Taxa de Faltas (%) |
| :--- | :---: | :---: |
| **Segunda Chance** | 16 | 100,00% |
| **FIFO** | 16 | 100,00% |
| **MY** | 13 | 81,25% |

### Análise de Desempenho
- Tanto o FIFO quanto a Segunda Chance clássico obtiveram 0% de hits (16 Faltas de Página para 16 acessos). A cada passo, o algoritmo removia sequencialmente exatamente a próxima página a ser acessada.
- O Algoritmo MY (Segunda Chance Randomizada) obteve 13 Faltas de Página (uma taxa de falha menor: 81,25%). Ao utilizar o sorteio aleatório aliado à segunda chance, ele quebrou o loop sequencial, resultando em hits no loop, provando ser superior em cenários cíclicos.
