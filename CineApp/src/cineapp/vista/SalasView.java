package cineapp.vista;

import cineapp.controlador.NavegacionControlador;
import cineapp.controlador.CineControlador;
import cineapp.modelo.Sala;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Vista de las salas y algunos botones adicionales, como el Ver entradas que lleva a EntradasView y el de Cerrar Sesión para cambiar de usuario.

public class SalasView {
    private Stage stage;
    private CineControlador controlador;
    private NavegacionControlador app;

    public SalasView(Stage stage, CineControlador controlador, NavegacionControlador app) {
        this.stage = stage;
        this.controlador = controlador;
        this.app = app;
    }

    public void mostrar() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().add(new Label("Seleccione una opción:"));

        for (Sala sala : controlador.getCine().getSalas()) {
            Button btn = new Button("Sala " + sala.getNumero() + " - " + sala.getPelicula());
            btn.setOnAction(e -> {
                ButacasView butacasView = new ButacasView(stage, controlador, sala, app);
                butacasView.mostrar();
            });
            root.getChildren().add(btn);
        }

        Button verEntradas = new Button("Ver entradas");
        verEntradas.setOnAction(e -> {
            EntradasView entradasView = new EntradasView(stage, controlador, app);
            entradasView.mostrar();
        });

        Button cerrarSesion = new Button("Cerrar sesión");
        cerrarSesion.setOnAction(e -> {
            NavegacionControlador.setClienteActual(null);
            app.mostrarLogin();
        });

        root.getChildren().addAll(verEntradas, cerrarSesion);
        stage.setScene(new Scene(root, 350, 300));
    }
}
