package com.marcus.concesionario.repositorios;

import com.marcus.concesionario.modelo.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CocheRepository extends JpaRepository<Coche,Integer> {

    Coche findFirstByMatricula(String matricula);


    @Query("select coche.id from Coche coche")
    public List<Integer> obtenerIds();

    List<Coche> findByMarcaContains(String marca);


}
