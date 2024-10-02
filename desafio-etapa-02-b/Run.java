import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Run {

    static final Scanner scanner = new Scanner(System.in);

    static final Set<PratoPrincipal> pratosPrincipais = new HashSet<>();
    static final Set<Acompanhamento> acompanhamentos = new HashSet<>();
    static final Set<Salada> saladas = new HashSet<>();

    public static void main(String[] args) {
        JustTest.test();
        scanner.close();
    }

    static void exibirMenuPrincipal() {
        println("========== MENU PRINCIPAL ==========");
        println("1 - CADASTRAR PRATO PRINCIPAL");
        println("2 - CADASTRAR ACOMPANHAMENTO");
        println("3 - CADASTRAR SALADA");
        println("4 - CADASTRAR REFEICAO");
        println("5 - ADICIONAR REFEICAO A CARDAPIO");
        println("6 - EXIBIR CARDAPIO");
        println("0 - SAIR");
    }

    static void exibirCardapio(Cardapio cardapio) {
        System.out.println("CARDAPIO");
        cardapio.getMenus().entrySet().stream()
            .sorted(Comparator.comparing(Entry::getKey))
            .forEach(Run::exibirDiaMenu)
        ;
    }

    static void exibirDiaMenu(Entry<DiaSemana, Map<Turno, Refeicao>> diaMenu) {
        System.out.println("-----------------------------------------");

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
        System.out.print("DIA: ");
        switch (diaSemana) {
            case DOMINGO -> System.out.println("Domingo");
            case SEGUNDA -> System.out.println("Segunda");
            case TERCA -> System.out.println("Terca");
            case QUARTA -> System.out.println("Quarta");
            case QUINTA -> System.out.println("Quinta");
            case SEXTA -> System.out.println("Sexta");
            case SABADO -> System.out.println("Sabado");
        };
    }

    static void exibirTurno(Turno turno) {
        System.out.print("TURNO: ");
        switch (turno) {
            case MATUTINO -> System.out.println("Matutino");
            case VESPERTINO -> System.out.println("Vespertino");
            case NOTURNO -> System.out.println("Noturno");
        };
    }

    static void exibirRefeicao(Refeicao refeicao) {
        System.out.println("- Prato principal: " + refeicao.gePratoPrincipal().getDescricao());
        System.out.println("- Acompanhamento: " + refeicao.getAcompanhamento().getDescricao());
        System.out.println("- Salada: " + refeicao.getSalada().getDescricao());
        System.out.println();
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
