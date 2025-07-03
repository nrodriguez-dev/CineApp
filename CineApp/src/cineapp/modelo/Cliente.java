package cineapp.modelo;

import java.io.Serializable;

//Modelo de cliente con sus atributos y sus getters.

public class Cliente implements Serializable {
    private String nombre;
    private String email;
    private String contraseña;

    public Cliente(String nombre, String email, String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
    }

    public String getNombre(){ 
        return nombre;
    }
    public String getEmail(){ 
        return email; 
    }
    public String getContraseña(){ 
        return contraseña; 
    }
}
