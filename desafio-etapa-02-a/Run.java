import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Run {

    static final Estacionamento estacionamento = new Estacionamento();
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = -1;
        int indice = -1;
        do {
            imprimirEspacos();
            exibirMenuPrincipal();

            opcao = solicitarOpcao();
            scanner.nextLine();

            imprimirEspacos();

            switch (opcao) {
                case 1:
                    println("---------- EMITIR TICKET ----------");
                    println("Novo ticket gerado!");
                    estacionamento.gerarTicket();
                break;

                case 2:
                    println("---------- LISTAR TICKETS ----------");
                    exibirTickets();

                    println("Saldo total: " + estacionamento.saldoTotal());
                    println("Total arrecadado: " + estacionamento.totalArrecadado());
                break;

                case 3: 
                    println("---------- PAGAR TICKET ----------");

                    if (estacionamento.getTickets().size() == 0) {
                        println("Nao ha tickets gerados!");
                        break;
                    }
    
                    exibirTickets();

                    do {
                        println("Informe o indice a ser pago ou digite -1 pra sair!");
                        indice = solicitarIndice();
                        scanner.nextLine();
    
                        if (indice == -1) {
                            break;
                        }
    
                        if (indice <= 0 || indice > estacionamento.getTickets().size()) {
                            println("INDICE INVALIDO!");
                        } else {
                            estacionamento.getTickets().get(indice-1).pagar();
                            println("Ticket pago!");
                        }

                    } while (indice <= 0 || indice > estacionamento.getTickets().size());
                break;
                case 0:
                    println("APLICACAO ENCERRADA!");
                break;

                default:
                    println("OPCAO INVALIDA!");
            }

            // imprimirSeparador();
            pausarPrograma();
        } while (opcao != 0);

        scanner.close();
    }

    static void exibirMenuPrincipal() {
        println("========== MENU PRINCIPAL ==========");
        println("1 - EMITIR TICKET");
        println("2 - LISTA TICKETS");
        println("3 - PAGAR TICKET");
        println("0 - SAIR");
    }

    static void exibirTickets() {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        for (int i=0; i<estacionamento.getTickets().size(); i++) {
            var ticket = estacionamento.getTickets().get(i);

            println("Indice: " + (i+1));
            println("Codigo: " + ticket.getCodigo());
            println("Data e Hora: " + formatter.format(ticket.getDataHoraRegistro()));
            println("Valor: " + ticket.getValor());
            println("Status: " + ticket.getStatusPagamento());
            imprimirSeparador();
            println("");
        }
    }

    static int solicitarOpcao() {
        try {
            print("OPCAO: ");
            return scanner.nextInt();

        } catch (Exception e) {
            scanner.nextLine();
            println("\nOPCAO INVALIDA!");
            return solicitarOpcao();
        }
    }

    static int solicitarIndice() {
        try {
            print("INDICE: ");
            return scanner.nextInt();

        } catch (Exception e) {
            scanner.nextLine();
            println("\nINDICE INVALIDO!");
            return solicitarOpcao();
        }
    }

    static void imprimirEspacos() {
        print("\n\n\n\n\n");
    }

    static void imprimirSeparador() {
        println("--------------------");
    }

    static void pausarPrograma() {
        println("\nPressione enter para continuar...");
        scanner.nextLine();
    }

    static void println(String str) {
        System.out.println(str);
    }

    static void print(String str) {
        System.out.print(str);
    }
}
