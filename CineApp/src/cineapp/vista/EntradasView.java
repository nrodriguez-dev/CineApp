package cineapp.vista;

import cineapp.controlador.NavegacionControlador;
import cineapp.controlador.CineControlador;
import cineapp.modelo.Cliente;
import cineapp.modelo.Entrada;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

//Vista de las entradas para que el usuario pueda verificar que reservas hizo, utilizando la información de los getters de los modelos que maneja CineControlador.

public class EntradasView {
    private Stage stage;
    private CineControlador controlador;
    private NavegacionControlador app;

    public EntradasView(Stage stage, CineControlador controlador, NavegacionControlador app) {
        this.stage = stage;
        this.controlador = controlador;
        this.app = app;
    }

    public void mostrar() {
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));

        Cliente cliente = NavegacionControlador.getClienteActual();
        Label titulo = new Label("Entradas compradas por: " + cliente.getNombre());

        List<Entrada> entradasCliente = new ArrayList<>();
        for (Entrada entrada : controlador.getCine().getEntradas()) {
            if (entrada.getCliente().getEmail().equals(cliente.getEmail())) {
                entradasCliente.add(entrada);
            }
        }
        root.getChildren().add(titulo);

        if (entradasCliente.isEmpty()) {
            root.getChildren().add(new Label("No se compró ninguna entrada."));
        } else {
            for (Entrada e : entradasCliente) {
                String texto = "Película: " + e.getSala().getPelicula() +
                        " | Sala: " + e.getSala().getNumero() +
                        " | Butaca: Fila " + (e.getButaca().getFila() + 1) +
                        ", Asiento " + (e.getButaca().getNumero() + 1);
                root.getChildren().add(new Label(texto));
            }
        }

        Button volver = new Button("Volver");
        volver.setOnAction(e -> app.mostrarSalas());
        root.getChildren().add(volver);

        stage.setScene(new Scene(root, 350, 300));
    }
}
