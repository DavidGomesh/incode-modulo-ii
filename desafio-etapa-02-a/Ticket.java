import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {

    private UUID codigo = UUID.randomUUID();
    private Double valor = 4.0;
    private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
    private LocalDateTime dataHoraRegistro = LocalDateTime.now();

    private Ticket() {}

    public static Ticket emitir() {
        return new Ticket();
    }

    public void pagar() {
        statusPagamento = StatusPagamento.PAGO;
    }

    public Boolean isPago() {
        return statusPagamento == StatusPagamento.PAGO;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public Double getValor() {
        return valor;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public LocalDateTime getDataHoraRegistro() {
        return dataHoraRegistro;
    }
}
