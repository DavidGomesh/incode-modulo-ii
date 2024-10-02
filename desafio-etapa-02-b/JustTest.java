public class JustTest {
    
    public static void test() {
        var p1 = new PratoPrincipal("Prato Principal 01");
        var a1 = new Acompanhamento("Acompanhamento 01");
        var s1 = new Salada("Salada 01");
        var r1 = new Refeicao(s1, a1, p1);

        var p2 = new PratoPrincipal("Prato Principal 02");
        var a2 = new Acompanhamento("Acompanhamento 02");
        var s2 = new Salada("Salada 02");
        var r2 = new Refeicao(s2, a2, p2);

        var p3 = new PratoPrincipal("Prato Principal 03");
        var a3 = new Acompanhamento("Acompanhamento 03");
        var s3 = new Salada("Salada 03");
        var r3 = new Refeicao(s3, a3, p3);

        var p4 = new PratoPrincipal("Prato Principal 04");
        var a4 = new Acompanhamento("Acompanhamento 04");
        var s4 = new Salada("Salada 04");
        var r4 = new Refeicao(s4, a4, p4);

        var p5 = new PratoPrincipal("Prato Principal 05");
        var a5 = new Acompanhamento("Acompanhamento 05");
        var s5 = new Salada("Salada 05");
        var r5 = new Refeicao(s5, a5, p5);

        var p6 = new PratoPrincipal("Prato Principal 06");
        var a6 = new Acompanhamento("Acompanhamento 06");
        var s6 = new Salada("Salada 06");
        var r6 = new Refeicao(s6, a6, p6);

        var p7 = new PratoPrincipal("Prato Principal 07");
        var a7 = new Acompanhamento("Acompanhamento 07");
        var s7 = new Salada("Salada 07");
        var r7 = new Refeicao(s7, a7, p7);

        var c1 = new Cardapio();

        c1.adicionarRefeicao(DiaSemana.DOMINGO, Turno.MATUTINO, r1);
        c1.adicionarRefeicao(DiaSemana.SEGUNDA, Turno.MATUTINO, r2);
        c1.adicionarRefeicao(DiaSemana.TERCA, Turno.MATUTINO, r3);
        c1.adicionarRefeicao(DiaSemana.QUARTA, Turno.MATUTINO, r4);
        c1.adicionarRefeicao(DiaSemana.QUINTA, Turno.MATUTINO, r5);
        c1.adicionarRefeicao(DiaSemana.SEXTA, Turno.MATUTINO, r6);
        c1.adicionarRefeicao(DiaSemana.SABADO, Turno.MATUTINO, r7);
        c1.adicionarRefeicao(DiaSemana.SABADO, Turno.VESPERTINO, r1);
        c1.adicionarRefeicao(DiaSemana.SABADO, Turno.NOTURNO, r2);

        Run.exibirCardapio(c1);
    }

}
