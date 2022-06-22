package com.marcus.concesionario.controladores;

import com.marcus.concesionario.modelo.Alquiler;
import com.marcus.concesionario.modelo.Coche;
import com.marcus.concesionario.repositorios.AlquileresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/alquileres")

public class AlquileresController {

    @Autowired
    AlquileresRepository alquileresRepository;

    @GetMapping(value = "/")
    public String mostrarAlquileres(Model model) {
        model.addAttribute("alquileres", alquileresRepository.findAll());
        return "alquileres/ver_alquileres";
    }

    @PostMapping(value = "/eliminarAlquiler")
    public String eliminarAlquiler(@ModelAttribute Alquiler alquiler, RedirectAttributes redirectAttrs) {

        alquileresRepository.deleteById(alquiler.getId());
        return "redirect:/alquileres/";
    }
}
