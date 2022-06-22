package com.marcus.concesionario.repositorios;

import com.marcus.concesionario.modelo.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlquileresRepository extends JpaRepository<Alquiler,Integer> {

}
