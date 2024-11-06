package com.incode.ReservaHoteis.model;

import static com.incode.ReservaHoteis.model.enumarate.StatusReserva.ATIVA;
import static com.incode.ReservaHoteis.model.enumarate.StatusReserva.FINALIZADA;

import java.time.LocalDate;

import com.incode.ReservaHoteis.model.enumarate.StatusReserva;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_quarto")
    private Quarto quarto;

    @NotNull
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusReserva status = ATIVA;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataPrevistaFim;

    private LocalDate dataFim;

    public void finalizar(LocalDate dataFim) {
        this.dataFim = dataFim;
        status = FINALIZADA;
    }
}
