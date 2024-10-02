import java.util.HashMap;
import java.util.Map;

public class Cardapio {
    private Map<DiaSemana, Map<Turno, Refeicao>> menus = new HashMap<>();

    public void adicionarRefeicao(DiaSemana diaSemana, Turno turno, Refeicao refeicao) {
        try {
            menus.get(diaSemana).put(turno, refeicao);
        }
        catch (Exception e) {
            menus.put(diaSemana, new HashMap<>(
                Map.of(turno, refeicao)
            ));
        }
    }

    public Map<DiaSemana, Map<Turno, Refeicao>> getMenus() {
        return menus;
    }
}