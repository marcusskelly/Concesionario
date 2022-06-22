package com.marcus.concesionario.servicios;


import com.marcus.concesionario.modelo.Alquiler;
import com.marcus.concesionario.modelo.Usuario;
import com.marcus.concesionario.repositorios.AlquileresRepository;
import com.marcus.concesionario.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlquilerService {

    @Autowired
    AlquileresRepository alquileresRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Alquiler insertar(Alquiler a, Usuario cliente) {
        a.setPropietario(cliente);
        return alquileresRepository.save(a);
    }

    public Alquiler insertar(Alquiler a) {
        return alquileresRepository.save(a);
    }
}
