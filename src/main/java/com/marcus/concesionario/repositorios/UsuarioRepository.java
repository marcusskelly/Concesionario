package com.marcus.concesionario.repositorios;


import com.marcus.concesionario.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {


    Usuario findFirstByEmail(String email);

    @Query("select usuario.id from Usuario usuario")
    public List<Integer> obtenerIds();
}
