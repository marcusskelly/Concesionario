package com.marcus.concesionario.repositorios;

import com.marcus.concesionario.modelo.CocheAlquilado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CochesAlquiladosRepository extends JpaRepository<CocheAlquilado,Integer> {

}
