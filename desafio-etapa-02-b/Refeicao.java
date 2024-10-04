public class Refeicao {

    private String descricao;
    private PratoPrincipal pratoPrincipal;
    private Acompanhamento acompanhamento;
    private Salada salada;

    public Refeicao(String descricao, PratoPrincipal pratoPrincipal, Acompanhamento acompanhamento, Salada salada) {
        this.descricao = descricao;
        this.salada = salada;
        this.acompanhamento = acompanhamento;
        this.pratoPrincipal = pratoPrincipal;
    }

    public String getDescricao() {
        return descricao;
    }

    public Salada getSalada() {
        return salada;
    }

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public PratoPrincipal getPratoPrincipal() {
        return pratoPrincipal;
    }
}
