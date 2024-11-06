package com.incode.ReservaHoteis.model;

import static com.incode.ReservaHoteis.model.enumarate.StatusQuarto.DISPONIVEL;
import static com.incode.ReservaHoteis.model.enumarate.StatusQuarto.RESERVADO;

import com.incode.ReservaHoteis.model.enumarate.StatusQuarto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuarto;

    @NotBlank
    private String numero;

    @NotBlank
    private String descricao;

    @NotNull
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusQuarto status = DISPONIVEL;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_hotel")
    private Hotel hotel;

    public Boolean isDisponivel() {
        return status == DISPONIVEL;
    }

    public void reservar() {
        this.status = RESERVADO;
    }

    public void liberar() {
        this.status = DISPONIVEL;
    }
}
