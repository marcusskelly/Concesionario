package com.marcus.concesionario.servicios;

import com.marcus.concesionario.modelo.Coche;
import com.marcus.concesionario.repositorios.CocheRepository;
import com.marcus.concesionario.upload.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CocheService {

    @Autowired
    private CocheRepository cocheRepository;

    @Autowired
    private StorageService storageService;

    public List<Coche> findAll() {
        return cocheRepository.findAll();
    }

    public Coche findById(Integer id) {
        return cocheRepository.findById(id).orElse(null);
    }

    public List<Coche> obtenerCochesAleatorios(int numero) {
        // Obtenemos los ids de todos los coches
        List<Integer> listaIds = cocheRepository.obtenerIds();
        // Los desordenamos
        Collections.shuffle(listaIds);
        // Nos quedamos con los N primeros, con N = numero.
        listaIds = listaIds.stream().limit(numero).collect(Collectors.toList());
        // Buscamos los coches con esos IDs y devolvemos la lista
        return cocheRepository.findAllById(listaIds);

    }

    public List<Coche> buscador(String cadena) {
        return cocheRepository.findByMarcaContains(cadena);
    }

    public Page<Coche> findAllPaginated(Pageable pageable) {
        return cocheRepository.findAll(pageable);
    }


}
