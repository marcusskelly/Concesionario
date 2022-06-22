package com.marcus.concesionario.controladores;


import com.marcus.concesionario.modelo.Usuario;
import com.marcus.concesionario.repositorios.AlquileresRepository;
import com.marcus.concesionario.repositorios.UsuarioRepository;
import com.marcus.concesionario.servicios.UsuarioService;
import com.marcus.concesionario.upload.storage.StorageService;
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

@Controller
@RequestMapping(path = "/usuario")
public class AgregarUsuarioController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlquileresRepository alquileresRepository;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/agregar")
    public String nuevoUsuarioForm(Model model){
        model.addAttribute("usuario", new Usuario());
        return "/coches/agregar_usuario";
    }

    @PostMapping(value = "/agregar")
    public String guardarUsuario(@ModelAttribute("usuario") @Valid Usuario usuario, @RequestParam("file") MultipartFile file,BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "home";
        }
        if (usuarioRepository.findFirstByEmail(usuario.getEmail()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un usuario con este email")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/coches/agregar_usuario";
        }

        if (!file.isEmpty()) {
            String imagen = storageService.store(file);
            usuario.setImagen(MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "serveFile", imagen).build().toUriString());
        }
        usuarioRepository.save(usuario);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");

        return "redirect:/usuario/agregar";
    }

    @GetMapping(value = "/mostrar")
    public String mostrarUsuario(Model model, Pageable pageable) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("usuarios", usuarioService.findAllPaginated(pageable));
        model.addAttribute("alquileres", alquileresRepository.findAll());
        return "usuarios/ver_usuarios";
    }
}
