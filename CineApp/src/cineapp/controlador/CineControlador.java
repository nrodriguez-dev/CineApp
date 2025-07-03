package cineapp.controlador;

import cineapp.modelo.*;
import cineapp.persistencia.PersistenciaDatos;

//Controlador del resto de vistas y modelos, haciendo el trabajo de validar logins y registros, sus campos y responsable de cargar/guardar los datos en el cine.ser utilizando PersistenciaDatos.

public class CineControlador {
    private Cine cine;

    public CineControlador() {
        cine = PersistenciaDatos.cargar();
    }

    public Cliente login(String email, String contraseña) {
        for (Cliente c : cine.getClientes()) {
            if (c.getEmail().equals(email) && c.getContraseña().equals(contraseña)) return c;
        }
        return null;
    }

    public boolean registrar(String nombre, String email, String contraseña) {
    if (nombre == null || email == null || contraseña == null) return false;

    nombre = nombre.trim();
    email = email.trim();
    contraseña = contraseña.trim();

    if (nombre.isEmpty() || email.isEmpty() || contraseña.isEmpty()) {
        return false;
    }

    for (Cliente c : cine.getClientes()) {
        if (c.getEmail().equalsIgnoreCase(email)) return false;
    }

    Cliente nuevo = new Cliente(nombre, email, contraseña);
    cine.getClientes().add(nuevo);
    return true;
    }

    public void comprarEntrada(Cliente cliente, Sala sala, Butaca butaca) {
        butaca.setOcupada(true);
        cine.getEntradas().add(new Entrada(cliente, sala, butaca));
    }

    public Cine getCine() {
        return cine;
    }

    public void guardar() {
        PersistenciaDatos.guardar(cine);
    }
}
