package com.marcus.concesionario.servicios;

import com.marcus.concesionario.modelo.Coche;




public class CocheParaAlquilar extends Coche {
    private Float meses;


    public CocheParaAlquilar(String marca, String matricula, Float precio, Float existencia, Integer id, Float meses,String imagen) {
        super(marca, matricula, precio, existencia, id,imagen);
        this.meses = meses;


    }

    public CocheParaAlquilar(String marca, String matricula, Float precio, Float existencia, Float meses) {
        super(marca, matricula, precio, existencia);
        this.meses = meses;


    }

    public void aumentarMeses() {
        this.meses++;
    }

    public Float getMeses() {
        return meses;
    }

    public Float getTotal() {
        return this.getPrecio() * this.meses;
    }

    public Float getDescuento() {
        return this.getPrecio() * this.meses;
    }
}