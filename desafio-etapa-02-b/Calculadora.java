import java.util.Scanner;

public class Calculadora {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = 0;
        do {
            System.out.println("\n\n\n\n\n");
            System.out.println("===== MENU DE OPERACOES =====");
            System.out.println("1 - Adicao");
            System.out.println("2 - Subtracao");
            System.out.println("3 - Multiplicacao");
            System.out.println("4 - Divisao");
            System.out.println("5 - Modulo");
            System.out.println("0 - Sair");
            
            System.out.print("OPÇAO: ");
            opcao = scanner.nextInt();
    
            System.out.println("\n\n\n\n\n");
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
        System.out.println("----- ADICAO -----");
        int num1 = lerNumero();
        int num2 = lerNumero();
        scanner.nextLine();
        
        int result = num1 + num2;
        System.out.println(
            "\nResultado: " + num1 + " + " + num2 + " = " + result
        );
    }

    static void subtracao() {
        System.out.println("----- SUBTRACAO -----");
        int num1 = lerNumero();
        int num2 = lerNumero();
        scanner.nextLine();
        
        int result = num1 + num2;
        System.out.println(
            "\nResultado: " + num1 + " - " + num2 + " = " + result
        );
    }

    static void multiplicacao() {
        System.out.println("----- MULTIPLICACAO -----");
        int num1 = lerNumero();
        int num2 = lerNumero();
        scanner.nextLine();
        
        int result = num1 * num2;
        System.out.println(
            "\nResultado: " + num1 + " * " + num2 + " = " + result
        );
    }

    static void divisao() {
        System.out.println("----- DIVISAO -----");
        int num1 = lerNumero();
        int num2 = lerNumero();
        scanner.nextLine();

        if (num2 == 0) {
            System.out.println("\nERRO: DIVISAO POR ZERO!");
            return;
        }
        
        double result = (double) num1 / num2;
        System.out.println(
            "\nResultado: " + num1 + " / " + num2 + " = " + result
        );
    }

    static void modulo() {
        System.out.println("----- MODULO -----");
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

    static int lerNumero() {
        System.out.print("Informe um numero: ");
        return scanner.nextInt();
    }
}
