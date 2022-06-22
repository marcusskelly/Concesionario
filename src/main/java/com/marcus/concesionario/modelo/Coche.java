package com.marcus.concesionario.modelo;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Debes especificar la marca")
    @Size(min = 1, max = 50)
    private String marca;

    @NotNull(message = "Debes especificar la matricula")
    @Size(min = 1, max = 50)
    private String matricula;

    @NotNull(message = "Debes especificar el precio")
    @Min(value = 0, message = "El precio mínimo es 0")
    private Float precio;

    @NotNull(message = "Debes especificar la existencia")
    @Min(value = 0, message = "La existencia mínima es 0")
    private Float existencia;

    @URL
    private String imagen;


    public Coche(String marca, String matricula,Float precio, Float existencia, Integer id,String imagen) {
        this.marca = marca;
        this.matricula = matricula;
        this.precio = precio;
        this.existencia = existencia;
        this.id = id;
        this.imagen = imagen;
    }

    public Coche(String marca, String matricula, Float precio, Float existencia) {
        this.marca = marca;
        this.matricula = matricula;
        this.precio = precio;
        this.existencia = existencia;
    }

    public Coche(@NotNull(message = "Debes especificar la matricula") @Size(min = 1, max = 50) String matricula) {
        this.matricula = matricula;
    }

    public Coche() {
    }

    public boolean sinExistencia() {
        return this.existencia <= 0;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getExistencia() {
        return existencia;
    }

    public void setExistencia(Float existencia) {
        this.existencia = existencia;
    }

    public void restarExistencia(Float existencia) {
        this.existencia -= existencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}