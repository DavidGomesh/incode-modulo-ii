# Lista de Tarefas

## Desafio

Criar um programa que gerencia uma lista de tarefas. O usuário poderá adicionar novas tarefas, marcar tarefas como concluídas e listar todas as tarefas atuais. O programa deve continuar executando até que o usuário decida sair.

## Solução

### Listas de tarefas e tarefas concluídas

Foi utilizado duas listas, uma para guardar as tarefas e outra para controlar quais tarefas já tinham sido concluídas. Essas listas trabalharão juntas, de forma que a tarefa que está, por exemplo, na posição 1 na lista `tarefas` tem seu status guardado na posição 1 da lista `concluidas`.

```java
final var tarefas    = new ArrayList<String>();
final var concluidas = new ArrayList<Boolean>();
```

### Menu principal

O menu principal da aplicação é controlado por um `do-while`, que fica repetindo enquanto o usuário não digitar a opção de sair. A estrutura `try-catch` assegura que o programa não pare em caso de uma opção inválida ser digitada.

```java
int opcao = 0;
do {
    System.out.println("\n\n\n\n\n");
    System.out.println("========== MENU ==========");
    System.out.println("1 - Adicionar tarefas");
    System.out.println("2 - Marcar como concluida");
    System.out.println("3 - Listar tarefas");
    System.out.println("0 - Sair");

    System.out.print("OPCAO: ");
    try {
        opcao = scanner.nextInt();
    } catch (Exception e) {
        opcao = -1;
    }

    // Código omitido

} while (opcao != 0);
```

### Escolha de ação

Após digitar a opção, um `switch-case` controla qual ação tomar. Foram mapeadas 4 opções válidas, indo de 0 a 3. Para qualquer coisa diferente disso, o sistema exibe a mensagem de ***"OPCAO INVALIDA!"***.

```java
int opcao = 0;
do {
    // Código otimido

    switch (opcao) {
        case 1: /* Código omitido */ break;
        case 2: /* Código omitido */ break;
        case 3: /* Código omitido */ break;

        case 0:
            System.out.println("PROGRAMA ENCERRADO!");
        break;

        default:
            System.out.println("OPCAO INVALIDA!");
        break;
    }

    if (opcao != 0) {
        System.out.println("Pressione enter para continuar...");
        scanner.nextLine();
    }
} while (opcao != 0);
```

### Adicionar tarefa

Para adicionar uma tarefa o usuário precisa apenas informar a descrição da tarefa. A aplicação coloca a tarefa na lista de tarefas e também a define como não conluída. Uma condição de digitar 0 foi adicionada para o caso do usuário entrar nessa seção por engano. Nesse caso, uma tarefa de nome ***"0"*** não seria adicionada.

```java
switch (opcao) {
    case 1:
        System.out.println("----- ADICIONAR TAREFA -----");

        System.out.println("\n* Digite 0 para sair!");
        System.out.print("Informe a tarefa: ");
        var tarefa = scanner.nextLine();

        if(tarefa.equals("0")) {
            break;
        }

        tarefas.add(tarefa);
        concluidas.add(false);

        System.out.println("\nTAREFA CADASTRADA!");
    break;

    case 2: /* Código omitido */ break;
    case 3: /* Código omitido */ break;
    case 0: /* Código omitido */ break;
    default: /* Código omitido */ break;
}
```

### Marcar tarefa como conluída

Ao selecionar a opção de concluir uma tarefa, o sistema inicialmente verifica se há tarefas registradas. Caso não tenha, o usuário é redirecionado ao menu principal. Havendo tarefas, elas são exibidas com seu respectivos índices.

Então é solicitado que o usuário informe o número (índice) da tarefa. As estruturas `do-while` e `try-catch` asseguram que a aplicação quebre se caso o usuário digitar valores inválidos. Se o índice foi digitado corretamente, a tarefa é marcada como concluída. Se o usuário digitar 0, ele volta ao menu principal.

```java
switch (opcao) {
    case 1: /* Código omitido */ break;
    case 2: 
        System.out.println("----- CONCLUIR TAREFA -----");
        if (tarefas.isEmpty()) {
            System.out.println("Nao ha tarefas cadastradas!");
            break;
        }

        for (int i=0; i<tarefas.size(); i++) {
            System.out.print("- " + (i+1) + (concluidas.get(i) ? " (x) " : " ( ) "));
            System.out.println(tarefas.get(i));
        }

        int numTarefa = -1;
        do {
            System.out.println("\n* Digite 0 para sair!");
            System.out.print("Informe o numero da tarefa: ");
            try {
                numTarefa = scanner.nextInt();
            } catch (Exception e) {
                numTarefa = -1;
            }
            scanner.nextLine();

            if (numTarefa < 0 || numTarefa > tarefas.size()) {
                System.out.println("TAREFA INVALIDA!");
            }

        } while(numTarefa < 0 || numTarefa > tarefas.size());

        if(numTarefa == 0) {
            break;
        }

        concluidas.set(numTarefa-1, true);
        System.out.println("\nTAREFA '" + tarefas.get(numTarefa-1) + "' CONCLUIDA!"); 
    break;
    
    case 3: /* Código omitido */ break;
    case 0: /* Código omitido */ break;
    default: /* Código omitido */ break;
}
```

### Listar tarefas

Selecionando a opção de listar tarefas, a aplicação exibe todas as tarefas cadastradas, se houverem, juntamente com seu código e status atual. O status é exibido simplesmente com um ***"(X)"*** em caso da tarefas estar concluída, ou um ***( )*** caso contrário. Essa verificação foi feita utilizando o operador ternário.

```java
switch (opcao) {
    case 1: /* Código omitido */ break;
    case 2: /* Código omitido */ break;
    case 3: 
        System.out.println("----- LISTA DE TAREFAS -----");
        if (tarefas.isEmpty()) {
            System.out.println("Nao ha tarefas cadastradas!");
            break;
        }

        for (int i=0; i<tarefas.size(); i++) {
            System.out.print("- " + (i+1) + (concluidas.get(i) ? " (x) " : " ( ) "));
            System.out.println(tarefas.get(i));
        }

        System.out.println("----------------------------");
    break;
    case 0: /* Código omitido */ break;
    default: /* Código omitido */ break;
}
```

## Aplicação rodando

| ![Aplicação rodando](./lista-tarefas.gif "Aplicação rodando") |
|-|
| *Aplicação rodando* |

## Desenvolvedor

| <img alt="@DavidGomesh" src="https://avatars.githubusercontent.com/DavidGomesh?size=64" style="border-radius: 50%;"> |
| :-: |
| *David Gomesh* |