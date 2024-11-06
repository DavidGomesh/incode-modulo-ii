package com.incode.ReservaHoteis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incode.ReservaHoteis.model.Reserva;
import com.incode.ReservaHoteis.model.enumarate.StatusReserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    
    List<Reserva> findAllByStatus(StatusReserva status);
}
