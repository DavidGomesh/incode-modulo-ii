public class Menu {

    private Refeicao refeicao;
    private Turno turno;

    public Menu(Refeicao refeicao, Turno turno) {
        this.refeicao = refeicao;
        this.turno = turno;
    }

    public Refeicao getRefeicao() {
        return refeicao;
    }

    public Turno getTurno() {
        return turno;
    }
}