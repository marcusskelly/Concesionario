package com.marcus.concesionario.controladores;

import com.marcus.concesionario.modelo.Coche;
import com.marcus.concesionario.repositorios.CocheRepository;
import com.marcus.concesionario.servicios.CocheService;
import com.marcus.concesionario.upload.storage.StorageService;
import com.marcus.concesionario.upload.storage.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping(path = "/coches")
public class AgregarController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private CocheRepository cocheRepository;

    @Autowired
    private CocheService cocheService;

    @GetMapping(value = "/agregar")
    public String agregarCoche(Model model) {
        model.addAttribute("coche", new Coche());
        return "coches/agregar_coche";
    }

    @GetMapping(value = "/mostrar")
    public String mostrarCoche(Model model, Pageable pageable) {
        model.addAttribute("coches", cocheRepository.findAll());
        model.addAttribute("coches", cocheService.findAllPaginated(pageable));
        return "coches/ver_coches";
    }

    @PostMapping(value = "/eliminar")
    public String eliminarCoche(@ModelAttribute Coche coche, RedirectAttributes redirectAttrs) {

        cocheRepository.deleteById(coche.getId());
        return "redirect:/coches/mostrar";
    }

    @PostMapping(value = "/editar/{id}")
    public String actualizarCoche(@ModelAttribute @Valid Coche coche, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (coche.getId() != null) {
                return "coches/editar_coche";
            }
            return "redirect:/coches/mostrar";
        }
        Coche posibleCocheExistente = cocheRepository.findFirstByMatricula(coche.getMatricula());

        if (posibleCocheExistente != null && !posibleCocheExistente.getId().equals(coche.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un coche con esa matricula")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/coches/agregar";
        }
        cocheRepository.save(coche);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/coches/mostrar";
    }

    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("coche", cocheRepository.findById(id).orElse(null));
        return "coches/editar_coche";
    }

    @PostMapping(value = "/agregar")
    public String guardarCoche(@ModelAttribute("coche") @Valid Coche coche, @RequestParam("file") MultipartFile file, BindingResult bindingResult, RedirectAttributes redirectAttrs) throws IOException {
        if (bindingResult.hasErrors()) {
            return "coches/agregar_coche";
        }
        if (cocheRepository.findFirstByMatricula(coche.getMatricula()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un coche con esa matricula")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/coches/agregar";
        }

        if (!file.isEmpty()) {
            String imagen = storageService.store(file);
            coche.setImagen(MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "serveFile", imagen).build().toUriString());
        }

        cocheRepository.save(coche);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/coches/agregar";
    }
}
