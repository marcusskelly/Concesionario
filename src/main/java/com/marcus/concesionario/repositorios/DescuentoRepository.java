package com.marcus.concesionario.repositorios;

import com.marcus.concesionario.modelo.Descuento;
import com.marcus.concesionario.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DescuentoRepository extends JpaRepository<Descuento,Integer> {

    Descuento findFirstByCodigo(String codigo);

}
