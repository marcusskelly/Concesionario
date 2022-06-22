package com.marcus.concesionario.controladores;

import com.marcus.concesionario.modelo.Coche;
import com.marcus.concesionario.modelo.Usuario;
import com.marcus.concesionario.servicios.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private static final int NUM_COCHES_ALEATORIOS = 8;

    @Autowired
    private CocheService cocheService;

    @GetMapping("/")
    public String index(Model model,@RequestParam(name = "q", required = false) String consulta) {

        List<Coche> resultado = (consulta == null) ? cocheService.findAll() : cocheService.buscador(consulta);
        model.addAttribute("coches", resultado);
        return "home";
    }

    @GetMapping("/info")
    public String infoPagina()
    {
        return "info";
    }

    @GetMapping("/coche/{id}")
    public String showDetails(@PathVariable("id") Integer id, Model model) {
        Coche coche = cocheService.findById(id);
        if (coche != null) {
            model.addAttribute("coche", coche);
            return "coche";
        }

        return "redirect:/";

    }

}