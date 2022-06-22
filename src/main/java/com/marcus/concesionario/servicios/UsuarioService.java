package com.marcus.concesionario.servicios;

import com.marcus.concesionario.modelo.Coche;
import com.marcus.concesionario.modelo.Usuario;
import com.marcus.concesionario.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> findAllPaginated(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
}
