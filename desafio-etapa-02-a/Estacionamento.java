import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private List<Ticket> tickets = new ArrayList<>();

    public void gerarTicket() {
        tickets.add(Ticket.emitir());
    }

    public Double saldoTotal() {
        return (tickets.stream()
            .mapToDouble(Ticket::getValor)
            .sum()
        );
    }

    public Double totalArrecadado() {
        return (tickets.stream()
            .filter(Ticket::isPago)
            .mapToDouble(Ticket::getValor)
            .sum()
        );
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}