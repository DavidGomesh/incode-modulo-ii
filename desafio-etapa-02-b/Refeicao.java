public class Refeicao {
    
    private Salada salada;
    private Acompanhamento acompanhamento;
    private PratoPrincipal pratoPrincipal;

    public Refeicao(Salada salada, Acompanhamento acompanhamento, PratoPrincipal pratoPrincipal) {
        this.salada = salada;
        this.acompanhamento = acompanhamento;
        this.pratoPrincipal = pratoPrincipal;
    }

    public Salada getSalada() {
        return salada;
    }

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public PratoPrincipal gePratoPrincipal() {
        return pratoPrincipal;
    }
}
