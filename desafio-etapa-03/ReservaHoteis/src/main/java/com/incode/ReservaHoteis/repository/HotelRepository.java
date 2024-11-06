package com.incode.ReservaHoteis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incode.ReservaHoteis.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    
}
