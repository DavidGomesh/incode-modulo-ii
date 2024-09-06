import java.util.Scanner;

public class Calculadora {

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

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

            imprimirEspacos();
            scanner.nextLine();

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

            if (opcao != 0) {
                System.out.println("Pressione enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);

        scanner.close();
    }

    static void adicao() {
        System.out.println("------- ADICAO -------");
        int opcao = 0;
        int result = 0;

        do {
            int num1 = opcao == 1 ? result : lerNumero();
            int num2 = lerNumero();

            result = num1 + num2;
            System.out.println(
                "\nResultado: " + num1 + " + " + num2 + " = " + result
            );

            do {
                System.out.println("\nContinuar adicionando?");
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

    static void subtracao() {
        System.out.println("------- SUBTRACAO -------");
        int opcao = 0;
        int result = 0;

        do {
            int num1 = opcao == 1 ? result : lerNumero();
            int num2 = lerNumero();

            result = num1 - num2;
            System.out.println(
                "\nResultado: " + num1 + " - " + num2 + " = " + result
            );

            do {
                System.out.println("\nContinuar subtraindo?");
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

    static void multiplicacao() {
        System.out.println("------- MULTIPLICACAO -------");
        int opcao = 0;
        int result = 0;

        do {
            int num1 = opcao == 1 ? result : lerNumero();
            int num2 = lerNumero();

            result = num1 * num2;
            System.out.println(
                "\nResultado: " + num1 + " * " + num2 + " = " + result
            );

            do {
                System.out.println("\nContinuar multiplicando?");
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

    static void modulo() {
        System.out.println("------- MODULO -------");
        int num1 = lerNumero();
        int num2 = lerNumero();
        scanner.nextLine();

        if (num2 == 0) {
            System.out.println("\nERRO: DIVISAO POR ZERO!");
            return;
        }

        int result = num1 % num2;
        System.out.println(
            "\nResultado: " + num1 + " mod " + num2 + " = " + result
        );
    }

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
}
