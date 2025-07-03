package cineapp.vista;

import cineapp.controlador.NavegacionControlador;
import cineapp.controlador.CineControlador;
import cineapp.modelo.Cliente;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Vista de la pantalla de login, agregando los campos y un botón adicional por si el usuario desea Registrarse completando todos los campos de Login más uno nuevo con el nombre. Ingresa directamente y pasa a la vista de Salas.

public class LoginView {
    private CineControlador controlador;
    private Stage stage;
    private NavegacionControlador app;

    public LoginView(Stage stage, CineControlador controlador, NavegacionControlador app) {
        this.stage = stage;
        this.controlador = controlador;
        this.app = app;
    }
    
    public void mostrar() {
        
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        TextField email = new TextField();
        email.setPromptText("E-mail");

        PasswordField pass = new PasswordField();
        pass.setPromptText("Contraseña");

        Button login = new Button("Ingresar");
        Button registro = new Button("Registrarme");
        Label registrarse = new Label("Recuerde que debe completar los campos de Login para\npoder registrarse correctamente.");

        root.getChildren().addAll(new Label("E-mail:"), email, new Label("Contraseña:"), pass, login, registro, registrarse);

        login.setOnAction(e -> {
            Cliente cliente = controlador.login(email.getText(), pass.getText());
            if (cliente != null) {
                NavegacionControlador.setClienteActual(cliente);
                app.mostrarSalas();
            } else {
                new Alert(Alert.AlertType.ERROR, "ERROR: E-mail o contraseña incorrecta. Reingrese sus datos.").showAndWait();
            }
        });

        registro.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("CineApp - Registrarse");
            dialog.setHeaderText("Ingrese su nombre: ");
            

            dialog.showAndWait().ifPresent(nombre -> {
                boolean registrado = controlador.registrar(nombre, email.getText(), pass.getText());
                if (registrado) {
                    Cliente nuevo = controlador.login(email.getText(), pass.getText());
                    NavegacionControlador.setClienteActual(nuevo);
                    new Alert(Alert.AlertType.INFORMATION, "Su cuenta se registró con éxito.").showAndWait();
                    app.mostrarSalas();
                } else {
                    new Alert(Alert.AlertType.ERROR, "ERROR: Campos vacíos o E-mail ya registrado.").showAndWait();
                }
            });
        });

        stage.setScene(new Scene(root, 350, 300));
    }
}
