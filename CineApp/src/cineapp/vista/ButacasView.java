package cineapp.vista;

import cineapp.controlador.NavegacionControlador;
import cineapp.controlador.CineControlador;
import cineapp.modelo.Butaca;
import cineapp.modelo.Cliente;
import cineapp.modelo.Sala;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

//Vista de las butacas, donde se dibujan las butacas de las salas, se realiza seleccion de las butacas del usuario y se solicita confirmación de la misma. 
//Esta vista también usa al controlador de Navegacion para volver a SalasView, el "menú principal", como el resto.

public class ButacasView {
    private Stage stage;
    private CineControlador controlador;
    private Sala sala;
    private NavegacionControlador app;

    public ButacasView(Stage stage, CineControlador controlador, Sala sala, NavegacionControlador app) {
        this.stage = stage;
        this.controlador = controlador;
        this.sala = sala;
        this.app = app;
    }

    public void mostrar() {
        GridPane grid = new GridPane();
        grid.setHgap(6);
        grid.setVgap(4);
        grid.setPadding(new Insets(10));

        Butaca[][] butacas = sala.getButacas();

        for (int i = 0; i < butacas.length; i++) {
            for (int j = 0; j < butacas[i].length; j++) {
                Butaca b = butacas[i][j];
                Button btn = new Button(b.isOcupada() ? "X" : "O");
                btn.setStyle(b.isOcupada() ? "-fx-background-color: red" : "-fx-background-color: blue");
                btn.setDisable(b.isOcupada());

                int finalI = i;
                int finalJ = j;

                btn.setOnAction(e -> {
                    Butaca seleccionada = butacas[finalI][finalJ];
                    Cliente cliente = NavegacionControlador.getClienteActual();

                    Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmacion.setTitle("CineApp - Confirmación de compra");
                    confirmacion.setHeaderText("¿Desea comprar la entrada seleccionada?");
                    confirmacion.setContentText(
                            "Cliente: " + cliente.getNombre() + " " +
                                    "Película: " + sala.getPelicula() + "\n" +
                                    "Sala: " + sala.getNumero() + "," +
                                    " Butaca: Fila " + (seleccionada.getFila() + 1) +
                                    " y Asiento " + (seleccionada.getNumero() + 1)
                    ); //por alguna razón solo se visualiza correctamente en resoluciones altas, muestra todos los campos como corresponde pero en 1080p se abrevia con ...
                        //no encontré forma de ajustarlo al tratarse de una Alert y no una caja de texto normal
                    confirmacion.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            controlador.comprarEntrada(cliente, sala, seleccionada);
                            new Alert(Alert.AlertType.INFORMATION, "Se compró la entrada solicitada.").showAndWait();
                            mostrar();
                        }
                    });
                });
                grid.add(btn, j, i);
            }
        }
        Button volver = new Button("Volver");
        volver.setOnAction(e -> app.mostrarSalas());
        Label pantalla = new Label("      [UBICACIÓN DE LA PANTALLA]");
        VBox root = new VBox(10, new Label("Butacas - Sala " + sala.getNumero()), pantalla, grid, volver);
        root.setPadding(new Insets(10));
        
        stage.setScene(new Scene(root, 350, 300));
    }
}
