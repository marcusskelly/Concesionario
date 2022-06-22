package com.marcus.concesionario.modelo;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fechaYHora;

    @OneToMany(mappedBy = "alquiler", cascade = CascadeType.ALL)
    private Set<CocheAlquilado> cocheAlquilados;


    @ManyToOne
    @JoinColumn
    private Usuario cliente;

    public Alquiler(Usuario cliente) {
        this.cliente = cliente;
        this.fechaYHora =obtenerFechaYHoraActual();
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Alquiler() {
        this.fechaYHora =obtenerFechaYHoraActual();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTotal() {
        Float total = 0f;
        for (CocheAlquilado cocheAlquilados: this.cocheAlquilados) {
            total += cocheAlquilados.getTotal();
        }
        return total;
    }

    public String getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(String fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Set<CocheAlquilado> getCocheAlquilados() {
        return cocheAlquilados;
    }

    public void setCocheAlquilados(Set<CocheAlquilado> cocheAlquilados) {
        this.cocheAlquilados = cocheAlquilados;
    }

    static String obtenerFechaYHoraActual() {
        String formato = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }

    public void setPropietario(Usuario cliente) {
        this.cliente = cliente;
    }
}
