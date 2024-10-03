import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Run {

    static final Scanner scanner = new Scanner(System.in);

    static final List<PratoPrincipal> pratosPrincipais = new ArrayList<>();
    static final List<Acompanhamento> acompanhamentos = new ArrayList<>();
    static final List<Salada> saladas = new ArrayList<>();

    static final List<Refeicao> refeicoes = new ArrayList<>();
    static final Cardapio cardapio = new Cardapio();

    public static void main(String[] args) {
        inicializarListas();

        int opcao = -1;
        do {
            imprimirEspacos();
            exibirMenuPrincipal();

            opcao = solicitarOpcao();
            scanner.nextLine();

            imprimirEspacos();

            switch (opcao) {
                case 1 -> cadastrarPratoPrincipal();
                case 2 -> cadastrarAcompanhamento();
                case 3 -> cadastrarSalada();
                case 4 -> cadastrarRefeicao();
                case 5 -> exibirItensCadastrados();
                case 6 -> println("[NOT IMPLEMENTED]\nADICIONAR REFEICAO A CARDAPIO");
                case 7 -> exibirCardapio();
                case 0 -> println("[NOT IMPLEMENTED]\nSAIR");
                default -> opcao = -1;
            }

            pausarPrograma();
        } while(opcao != 0);

        scanner.close();
    }

    static void exibirMenuPrincipal() {
        println("========== MENU PRINCIPAL ==========");
        println("1 - CADASTRAR PRATO PRINCIPAL");
        println("2 - CADASTRAR ACOMPANHAMENTO");
        println("3 - CADASTRAR SALADA");
        println("4 - CADASTRAR REFEICAO");
        println("5 - EXIBIR ITENS CADASTRADOS");
        println("6 - ADICIONAR REFEICAO A CARDAPIO");
        println("7 - EXIBIR CARDAPIO");
        println("0 - SAIR");
    }

    static void cadastrarPratoPrincipal() {
        println("\n========== CADASTRAR PRATO PRINCIPAL ==========\n");

        print("Descrição: ");
        var descricao = scanner.nextLine();

        pratosPrincipais.add(new PratoPrincipal(descricao));
        println("\n- Prato principal cadastrado!\n");
    }

    static void cadastrarAcompanhamento() {
        println("\n========== CADASTRAR ACOMPANHAMENTO ==========\n");

        print("Descrição: ");
        var descricao = scanner.nextLine();

        acompanhamentos.add(new Acompanhamento(descricao));
        println("\n- Acompanhamento cadastrado!\n");
    }

    static void cadastrarSalada() {
        println("\n========== CADASTRAR SALADA ==========\n");

        print("Descrição: ");
        var descricao = scanner.nextLine();

        saladas.add(new Salada(descricao));
        println("\n- Salada cadastrado!\n");
    }

    static void cadastrarRefeicao() {
        println("\n========== CADASTRAR REFEICAO ==========\n");

        if (pratosPrincipais.isEmpty()) {
            println("- Cadastre algum Prato Principal antes!");
            return;
        }

        if (acompanhamentos.isEmpty()) {
            println("- Cadastre algum Acompanhamento antes!");
            return;
        }

        if (saladas.isEmpty()) {
            println("- Cadastre alguma Salada antes!");
            return;
        }

        exibirPratosPrincipais();
        exibirAcompanhamentos();
        exibirSaladas();

        println("");

        int idxPratoPrincipal = -1;
        do {
            println("\nSELECIONE O PRATO PRINCIPAL");
            idxPratoPrincipal = solicitarIndice();

            if (idxPratoPrincipal < 1 || idxPratoPrincipal > pratosPrincipais.size()) {
                println("INDICE INVALIDO!");
            }
        } while(idxPratoPrincipal < 1 || idxPratoPrincipal > pratosPrincipais.size());
        
        int idxAcompanhamento = -1;
        do {
            println("\nSELECIONE O ACOMPANHAMENTO");
            idxAcompanhamento = solicitarIndice();

            if (idxAcompanhamento < 1 || idxAcompanhamento > acompanhamentos.size()) {
                println("INDICE INVALIDO!");
            }
        } while(idxAcompanhamento < 1 || idxAcompanhamento > acompanhamentos.size());
        
        int idxSalada = -1;
        do {
            println("\nSELECIONE A SALADA");
            idxSalada = solicitarIndice();

            if (idxSalada < 1 || idxSalada > saladas.size()) {
                println("INDICE INVALIDO!");
            }
        } while(idxSalada < 1 || idxSalada > saladas.size());

        refeicoes.add(new Refeicao(
            pratosPrincipais.get(idxPratoPrincipal-1),
            acompanhamentos.get(idxAcompanhamento-1),
            saladas.get(idxSalada-1)
        ));

        println("\n- Refeicao cadastrada!\n");
        scanner.nextLine();
    }

    static void exibirItensCadastrados() {
        println("\n========== ITENS CADASTRADOS ==========\n");
        exibirPratosPrincipais();
        exibirAcompanhamentos();
        exibirSaladas();
        exibirRefeicoes();
    }

    static void exibirPratosPrincipais() {
        println("\nPRATOS PRINCIPAIS");
        for (int i=0; i<pratosPrincipais.size(); i++) {
            println((i+1) + " - " + pratosPrincipais.get(i).descricao);
        }
    }

    static void exibirAcompanhamentos() {
        println("\nACOMPANHAMENTOS");
        for (int i=0; i<acompanhamentos.size(); i++) {
            println((i+1) + " - " + acompanhamentos.get(i).descricao);
        }
    }

    static void exibirSaladas() {
        println("\nSALADAS");
        for (int i=0; i<saladas.size(); i++) {
            println((i+1) + " - " + saladas.get(i).descricao);
        }
    }

    static void exibirRefeicoes() {
        println("\nREFEICOES");
        for (int i=0; i<refeicoes.size(); i++) {
            println((i+1) + " ----------------------------------------:");
            exibirRefeicao(refeicoes.get(i));
        }
    }

    static void exibirCardapio() {
        println("\n========== CARDAPIO ==========\n");
        cardapio.getMenus().entrySet().stream()
            .sorted(Comparator.comparing(Entry::getKey))
            .forEach(Run::exibirDiaMenu)
        ;
    }

    static void exibirDiaMenu(Entry<DiaSemana, Map<Turno, Refeicao>> diaMenu) {
        println("-----------------------------------------");

        exibirDiaSemana(diaMenu.getKey());
        diaMenu.getValue().entrySet().stream()
            .sorted(Comparator.comparing(Entry::getKey))
            .forEach(Run::exibirTurnoRefeicao)
        ;
    }

    static void exibirTurnoRefeicao(Entry<Turno, Refeicao> turnoRefeicao) {
        exibirTurno(turnoRefeicao.getKey());
        exibirRefeicao(turnoRefeicao.getValue());
    }

    static void exibirDiaSemana(DiaSemana diaSemana) {
        print("DIA: ");
        switch (diaSemana) {
            case DOMINGO -> println("Domingo");
            case SEGUNDA -> println("Segunda");
            case TERCA -> println("Terca");
            case QUARTA -> println("Quarta");
            case QUINTA -> println("Quinta");
            case SEXTA -> println("Sexta");
            case SABADO -> println("Sabado");
        };
    }

    static void exibirTurno(Turno turno) {
        print("TURNO: ");
        switch (turno) {
            case MATUTINO -> println("Matutino");
            case VESPERTINO -> println("Vespertino");
            case NOTURNO -> println("Noturno");
        };
    }

    static void exibirRefeicao(Refeicao refeicao) {
        println("- Prato principal: " + refeicao.gePratoPrincipal().getDescricao());
        println("- Acompanhamento: " + refeicao.getAcompanhamento().getDescricao());
        println("- Salada: " + refeicao.getSalada().getDescricao());
        println("");
    }

    static void pausarPrograma() {
        println("\nPressione enter para continuar...");
        scanner.nextLine();
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

    static void println(String str) {
        System.out.println(str);
    }

    static void print(String str) {
        System.out.print(str);
    }

    static void inicializarListas() {
        pratosPrincipais.addAll(List.of(
            new PratoPrincipal("Frango Grelhado"),
            new PratoPrincipal("File de Salmao"),
            new PratoPrincipal("Carne Assada")
        ));

        acompanhamentos.addAll(List.of(
            new Acompanhamento("Arroz a Grega"),
            new Acompanhamento("Pure de Batatas"),
            new Acompanhamento("Legumes Grelhados")
        ));

        saladas.addAll(List.of(
            new Salada("Salada Caesar"),
            new Salada("Salada de Quinoa"),
            new Salada("Salada Caprese")
        ));

        refeicoes.addAll(List.of(
            new Refeicao(pratosPrincipais.get(0), acompanhamentos.get(0), saladas.get(0)),
            new Refeicao(pratosPrincipais.get(1), acompanhamentos.get(0), saladas.get(0)),
            new Refeicao(pratosPrincipais.get(2), acompanhamentos.get(1), saladas.get(0))
        ));
    }
}
