package com.marcus.concesionario.modelo;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
public class Usuario {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "nombre")
    private String nombre;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")

    private String password;


    private String fechaAlta;

    @URL
    private String imagen;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Alquiler> alquileres;

    public Usuario(@NotNull(message = "Debes especificar el nombre") @Size(min = 1, max = 50) String nombre,String email, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.email = email;
    }
    public Usuario(){
        this.fechaAlta =obtenerFechaYHoraActual();
    }

    public Set<Alquiler> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(Set<Alquiler> alquileres) {
        this.alquileres = alquileres;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    static String obtenerFechaYHoraActual() {
        String formato = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }

}
