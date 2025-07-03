package cineapp.controlador;

import cineapp.modelo.Cliente;
import cineapp.vista.LoginView;
import cineapp.vista.SalasView;
import javafx.application.Application;
import javafx.stage.Stage;

//Controlador de la navegación del programa, cambiando entre vistas y generando el primaryStage del login. Acá se ejecuta el programa, y se gestiona el guardado de datos al cerrarlo.

public class NavegacionControlador extends Application {
    private static Cliente clienteActual;
    private CineControlador controlador = new CineControlador();
    private Stage primaryStage;

    public static Cliente getClienteActual() {
        return clienteActual;
    }

    public static void setClienteActual(Cliente cliente) {
        clienteActual = cliente;
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        mostrarLogin();
        stage.setTitle("CineApp");
        stage.setOnCloseRequest(e -> controlador.guardar());
        stage.show();
    }

    public void mostrarLogin() {
        LoginView loginView = new LoginView(primaryStage, controlador, this);
        loginView.mostrar();
    }

    public void mostrarSalas() {
        SalasView salasView = new SalasView(primaryStage, controlador, this);
        salasView.mostrar();
    }
}
