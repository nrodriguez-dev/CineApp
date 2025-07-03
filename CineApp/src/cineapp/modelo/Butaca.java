package cineapp.modelo;

import java.io.Serializable;

//Modelo de las butacas con sus respectivos atributos y getters/setters, así marcando si están ocupadas para el ButacasView.

public class Butaca implements Serializable {
    private int fila;
    private int numero;
    private boolean ocupada;

    public Butaca(int fila, int numero) {
        this.fila = fila;
        this.numero = numero;
        this.ocupada = false;
    }

    public int getFila(){ 
        return fila; 
    }
    public int getNumero(){ 
        return numero; 
    }
    public boolean isOcupada(){ 
        return ocupada; 
    }
    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada; 
    }
}
