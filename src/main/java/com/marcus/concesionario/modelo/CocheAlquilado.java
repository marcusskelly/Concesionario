package com.marcus.concesionario.modelo;

import javax.persistence.*;

@Entity
public class CocheAlquilado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float meses, precio;
    private String marca, matricula;

    @ManyToOne
    @JoinColumn
    private Alquiler alquiler;

    public CocheAlquilado(Float meses, Float precio, String marca, String matricula, Alquiler alquiler) {
        this.meses = meses;
        this.precio = precio;
        this.marca = marca;
        this.matricula = matricula;
        this.alquiler = alquiler;
    }

    public CocheAlquilado() {
    }

    public Float getTotal() {
        return this.meses * this.precio;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getMeses() {
        return meses;
    }

    public void setMeses(Float meses) {
        this.meses = meses;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
