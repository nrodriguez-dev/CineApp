package cineapp.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Modelo del cine con las listas de salas, entradas y clientes con sus respectivos getters.

public class Cine implements Serializable {
    private List<Sala> salas = new ArrayList<>();
    private List<Entrada> entradas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public Cine() {
        salas.add(new Sala(1, "Oppenheimer", 4, 6));
        salas.add(new Sala(2, "Interestelar", 4, 6));
        salas.add(new Sala(3, "The Batman", 4, 6));
    }

    public List<Sala> getSalas(){ 
        return salas;
    }
    public List<Entrada> getEntradas(){ 
        return entradas;
    }
    public List<Cliente> getClientes(){
        return clientes;
    }
}
