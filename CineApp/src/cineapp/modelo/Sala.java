package cineapp.modelo;

import java.io.Serializable;

//Modelo de las salas donde se inicializa la matriz utilizada para seleccionar las butacas, con sus respectivos getters.

public class Sala implements Serializable {
    private int numero;
    private String pelicula;
    private Butaca[][] butacas;

    public Sala(int numero, String pelicula, int filas, int columnas) {
        this.numero = numero;
        this.pelicula = pelicula;
        this.butacas = new Butaca[filas][columnas];
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                butacas[i][j] = new Butaca(i, j);
    }

    public int getNumero(){ 
        return numero;
    }
    public String getPelicula(){
        return pelicula;
    }
    public Butaca[][] getButacas(){
        return butacas;
    }
}
