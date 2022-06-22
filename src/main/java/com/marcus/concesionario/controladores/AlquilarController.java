package com.marcus.concesionario.controladores;

import com.marcus.concesionario.modelo.*;
import com.marcus.concesionario.repositorios.*;
import com.marcus.concesionario.servicios.AlquilerService;
import com.marcus.concesionario.servicios.CocheParaAlquilar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/alquilar", method = { RequestMethod.GET, RequestMethod.POST })
public class AlquilarController {

    @Autowired
    private CocheRepository cocheRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DescuentoRepository descuentoRepository;

    @Autowired
    private AlquileresRepository alquileresRepository;
    @Autowired
    private CochesAlquiladosRepository cochesAlquiladosRepository;

    @PostMapping(value = "/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<CocheParaAlquilar> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
        return "redirect:/alquilar/";
    }

    private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }

    @GetMapping(value = "/limpiar")
    public String cancelarAlquiler(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Alquiler cancelado")
                .addFlashAttribute("clase", "info");
        return "redirect:/alquilar/";
    }

    @PostMapping(value = "/terminar")
    public String firmarAlquiler(@ModelAttribute("cliente") @Valid Usuario cliente, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<CocheParaAlquilar> carrito = this.obtenerCarrito(request);
        if (carrito == null || carrito.size() <= 0) {
            return "redirect:/alquilar/";
        }

        Usuario clienteBuscadoPorMail = usuarioRepository.findFirstByEmail(cliente.getEmail());

        if(clienteBuscadoPorMail.equals(cliente.getEmail())){
            usuarioRepository.save(cliente);
        }

        Alquiler alquiler = alquileresRepository.save(new Alquiler(clienteBuscadoPorMail));
        for (CocheParaAlquilar cocheParaAlquilar : carrito) {
            Coche coche = cocheRepository.findById(cocheParaAlquilar.getId()).orElse(null);
            if (coche == null) continue;
            coche.restarExistencia(cocheParaAlquilar.getMeses());
            cocheRepository.save(coche);
            CocheAlquilado cocheAlquilado = new CocheAlquilado(cocheParaAlquilar.getMeses(), cocheParaAlquilar.getPrecio(), cocheParaAlquilar.getMarca(), cocheParaAlquilar.getMatricula(), alquiler);
            cochesAlquiladosRepository.save(cocheAlquilado);
        }
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Alquiler realizado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/alquilar/";
    }

    @GetMapping(value = "/")
    public String interfazAlquiler(Model model, HttpServletRequest request) {
        model.addAttribute("coche", new Coche());
        model.addAttribute("cliente", new Usuario());
        //model.addAttribute("descuento", new Descuento());

        float total = 0;
        ArrayList<CocheParaAlquilar> carrito = this.obtenerCarrito(request);
        for (CocheParaAlquilar coche: carrito) {
            total += coche.getTotal();

        }
        model.addAttribute("total", total);
        return "alquilar/alquilar";
    }

    private ArrayList<CocheParaAlquilar> obtenerCarrito(HttpServletRequest request) {
        ArrayList<CocheParaAlquilar> carrito = (ArrayList<CocheParaAlquilar>) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }

    private void guardarCarrito(ArrayList<CocheParaAlquilar> carrito, HttpServletRequest request) {
        request.getSession().setAttribute("carrito", carrito);
    }

    /*
    @PostMapping(value="/descontar")
    public String descontarAlCarrito(@ModelAttribute Descuento descuento,HttpServletRequest request){
        ArrayList<CocheParaAlquilar> carrito = this.obtenerCarrito(request);
        Descuento descuentoBuscadoPorCodigo = descuentoRepository.findFirstByCodigo(descuento.getCodigo());

        if(descuentoBuscadoPorCodigo.equals(descuento.getCodigo())){
            for (CocheParaAlquilar coche: carrito) {
                total = coche.getTotal() * descuentoBuscadoPorCodigo.getCantidad();
            }
        }
        this.guardarCarrito(carrito, request);
        return "redirect:/alquilar/";
    }

     */

    /* aquí o bien una tabla descuento y que los enchufe a través de model, o bien un array con descuentos  */
    @PostMapping(value = "/agregar")
    public String agregarAlCarrito(@ModelAttribute Coche coche, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<CocheParaAlquilar> carrito = this.obtenerCarrito(request);
        Coche cocheBuscadoPorMatricula = cocheRepository.findFirstByMatricula(coche.getMatricula());
        
        if (cocheBuscadoPorMatricula == null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El coche con matricula " + coche.getMatricula() + " no existe")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/alquilar/";
        }
        if (cocheBuscadoPorMatricula.sinExistencia()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El coche no está disponible")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/alquilar/";
        }

        boolean encontrado = false;
        for (CocheParaAlquilar cocheParaAlquilar : carrito) {
            if (cocheParaAlquilar.getMatricula().equals(cocheBuscadoPorMatricula.getMatricula())) {
                cocheParaAlquilar.aumentarMeses();
                encontrado = true;
                break;
            }

        }
        if (!encontrado) {
            carrito.add(new CocheParaAlquilar(cocheBuscadoPorMatricula.getMarca(), cocheBuscadoPorMatricula.getMatricula(), cocheBuscadoPorMatricula.getPrecio(), cocheBuscadoPorMatricula.getExistencia(), cocheBuscadoPorMatricula.getId(), 1f,cocheBuscadoPorMatricula.getImagen()));
        }
        this.guardarCarrito(carrito, request);
        return "redirect:/alquilar/";
    }
}
