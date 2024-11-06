package com.incode.ReservaHoteis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incode.ReservaHoteis.model.Hotel;
import com.incode.ReservaHoteis.model.Quarto;
import com.incode.ReservaHoteis.model.enumarate.StatusQuarto;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Integer> {

    List<Quarto> findByHotelAndStatus(Hotel hotel, StatusQuarto status);

    Optional<Quarto> findByHotelAndNumero(Hotel hotel, String numero);
}
