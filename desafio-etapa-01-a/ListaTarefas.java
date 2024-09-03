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
            "Estudar para prova de Calculo",
            "Tirar copia da chave de casa",
            "Separar roupas velhas"
        ));

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

            System.out.println("\n\n\n\n\n");
            scanner.nextLine();

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
}