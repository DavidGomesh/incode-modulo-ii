import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ListaTarefas {

     public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        final var tarefas    = new ArrayList<String>();
        final var concluidas = new ArrayList<Boolean>();

        System.out.println("\n\n\n\n\n");
        System.out.println("Banco de tarefas alimentado!\n\n");
        concluidas.addAll(Arrays.asList(false, false, false));
        tarefas.addAll(Arrays.asList(
            "Estudar para prova de Cálculo",
            "Tirar cópia da chave de casa",
            "Separar roupas velhas"
        ));

        int opcao = 0;
        do {
            System.out.println("\n\n\n\n\n");
            System.out.println("===== MENU =====");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Marcar como concluída");
            System.out.println("3 - Listar");
            System.out.println("0 - Sair");
            
            System.out.print("OPÇÃO: ");
            opcao = scanner.nextInt();

            System.out.println("\n\n\n\n\n");
            scanner.nextLine();

            switch (opcao) {
                case 1: 
                    System.out.println("----- ADICIONAR TAREFA -----");

                    System.out.print("Informe a tarefa: ");
                    tarefas.add(scanner.nextLine());
                    concluidas.add(false);
                    
                    System.out.println("\nTAREFA CADASTRADA!");
                break;
                    
                case 2:
                    System.out.println("----- CONCLUIR TAREFA -----");
                    if (tarefas.isEmpty()) {
                        System.out.println("Não há tarefas cadastradas!");
                        break;
                    }
                    
                    int numTarefa;
                    do {
                        System.out.print("Informe o número da tarefa: ");
                        numTarefa = scanner.nextInt();
                        scanner.nextLine();

                        if (numTarefa < 0 || numTarefa > tarefas.size()) {
                            System.out.println("TAREFA INVÁLIDA!");
                        }

                    } while(numTarefa < 0 || numTarefa > tarefas.size());
                    
                    concluidas.set(numTarefa-1, true);
                    System.out.println("\nTAREFA '" + tarefas.get(numTarefa-1) + "' CONCLUÍDA!");
                break;

                case 3: 
                    System.out.println("----- LISTA DE TAREFAS -----");
                    if (tarefas.isEmpty()) {
                        System.out.println("Não há tarefas cadastradas!");
                        break;
                    }

                    for (int i=0; i<tarefas.size(); i++) {
                        System.out.print("- " + (i+1) + (concluidas.get(i) ? " (x) " : " ( ) "));
                        System.out.println(tarefas.get(i));
                    }

                    System.out.println("----------------------------");
                break;

                case 0: 
                    System.out.println("PROGRAMA ENCERRADO!"); 
                break;
            
                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
                break;
            }

            if (opcao != 0) {
                System.out.println("Pressione enter para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 0);

        scanner.close();
    }
}