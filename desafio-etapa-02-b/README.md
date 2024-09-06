# Calculadora de Números Inteiros

## Desafio

Você deve criar um programa em Java que funcione como uma calculadora simples para números inteiros. 
O programa deve permitir ao usuário realizar operações matemáticas básicas (adição, subtração, multiplicação, divisão e módulo) em um loop até que o usuário decida sair.

- Mantenha o menu rodando até que o usuário escolha a opção: Sair do sistema.
- Garanta que operações como divisão e módulo não sejam feitas com zero. Em caso de divisão por zero, exiba uma mensagem de erro e volte ao menu principal.
- Adicione validações para garantir que os números inseridos sejam válidos.
- Permita que o usuário continue realizando operações sem reiniciar o programa

## Solução

### Menu Principal

O menu principal da aplicação é controlado por um `do-while`, que fica repetindo enquanto o usuário não digitar a opção de sair. A estrutura `try-catch` assegura que o programa não pare em caso de uma opção inválida ser digitada.

```java
int opcao = 0;
do {
    imprimirEspacos();
    System.out.println("===== MENU DE OPERACOES =====");
    System.out.println("1 - Adicao");
    System.out.println("2 - Subtracao");
    System.out.println("3 - Multiplicacao");
    System.out.println("4 - Divisao");
    System.out.println("5 - Modulo");
    System.out.println("0 - Sair");

    try {
        opcao = lerOpcao();
    } catch (Exception e) {
        opcao = -1;
    }

    // Código omitido

} while (opcao != 0);
```

### Escolha de ação

Após digitar a opção, um `switch-case` controla qual método chamar. Foram mapeadas 6 opções válidas, indo de 0 a 5. Para qualquer coisa diferente disso, o sistema exibe a mensagem de ***"OPCAO INVALIDA!"***.

```java
switch (opcao) {
    case 1: adicao(); break;
    case 2: subtracao(); break;
    case 3: multiplicacao(); break;
    case 4: divisao(); break;
    case 5: modulo(); break;

    case 0:
        System.out.println("PROGRAMA ENCERRADO!");
    break;

    default:
        System.out.println("OPCAO INVALIDA!");
    break;
}
```

### Operações

Todas as operações funcionam praticamente da mesma forma. Usaremos a divisão como exemplo. A aplicação recebe dois números do usuário, faz a operação e exibe o resultado. Na divisão, a aplicação verifica se o segundo número digitado é zero. Caso for, retorna ao menu principal.

Após exibir o resultado, a aplicação pergunta ao usuário se ele quer continuar fazendo operações a partir do último resultado obtido. Esse comportamento só não existe na operação de módulo.

```java
static void divisao() {
    System.out.println("------- DIVISAO -------");
    int opcao = 0;
    int result = 0;

    do {
        int num1 = opcao == 1 ? result : lerNumero();
        int num2 = lerNumero();

        if (num2 == 0) {
            System.out.println("\nERRO: DIVISAO POR ZERO!");
            scanner.nextLine();
            return;
        }

        result = num1 / num2;
        System.out.println(
            "\nResultado: " + num1 + " / " + num2 + " = " + result
        );

        do {
            System.out.println("\nContinuar dividindo?");
            System.out.println("1 - Sim");
            System.out.println("0 - Nao");

            try {
                opcao = lerOpcao();
            } catch (Exception e) {
                scanner.nextLine();
                opcao = -1;
            }

            System.out.println(
                opcao < 0 || opcao > 1 ? "OPCAO INVALIDA!" : ""
            );

        } while(opcao < 0 || opcao > 1);

    } while(opcao != 0);
}
```

### Métodos auxiliares

Foram criadas três métodos auxiliares:

- **imprimirEspacos():** imprime alguns espaços na tela, apenas para facilitar a leitura do usuário.
- **lerOpcao():** apenas para receber as opções do usuário, quando necessário.
- **lerNumero():** faz a leitura de números do usuário, e garante que seja um número válido usando a estrutra `try-catch`.

```java
static void imprimirEspacos() {
    System.out.println("\n\n\n\n\n");
}

static int lerOpcao() {
    System.out.print("OPCAO: ");
    return scanner.nextInt();
}

static int lerNumero() {
    try {
        System.out.print("Informe um numero: ");
        return scanner.nextInt();

    } catch (Exception e) {
        scanner.nextLine();
        System.out.println("NUMERO INVALIDO!");
        return lerNumero();
    }
}
```

## Aplicação rodando

| ![Aplicação rodando](./calculadora.gif "Aplicação rodando") |
|-|
| *Aplicação rodando* |

## Desenvolvedor

| <img alt="@DavidGomesh" src="https://avatars.githubusercontent.com/DavidGomesh?size=64" style="border-radius: 50%;"> |
| :-: |
| *David Gomesh* |
