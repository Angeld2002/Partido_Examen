package com.politecnicomalaga.NasdaqOilPrices.Model;

public class Partido {

    //Aunque sabemos que precios es un double, lo guardamos en String porque nos llega en String
    //y se muestra como texto, al fin y al cabo

    //POJO CLASS
    private String Equipo1;
    private String Equipo2;
    private String Resultado;
    //Comportamiento
    //Construtor

    public Partido(String Equipo1, String Equipo2, String Resultado) {
        this.Equipo1 = Equipo1;
        this.Equipo2 = Equipo2;
        this.Resultado = Resultado;
    }


    //Getters


    public String getEquipo1() {
        return Equipo1;
    }
    public String getEquipo2() {
        return Equipo2;
    }

    public String getResultado() {
        return Resultado;
    }
}
