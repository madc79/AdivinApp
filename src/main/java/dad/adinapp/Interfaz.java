package dad.adinapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class Interfaz extends Application {

    private Label propuestaLabel;
    private TextField numeroTextField;
    private Button comprobarButton;
    private Alert aciertoAlerta;
    private Alert falloAlerta;
    private Alert errorAlerta;
    private Random random = new Random();
    private int numeroAleatorio;  // Número aleatorio a adivinar
    private int intentos = 0;     // Contador de intentos

    @Override
    public void start(Stage stage) throws Exception {

        numeroAleatorio = random.nextInt(100) + 1; // Genera un número entre 1 y 100

        // Interfaz
        propuestaLabel = new Label();
        propuestaLabel.setText("Introduce un número del 1 al 100:");

        HBox propuestaHB = new HBox();
        propuestaHB.setPadding(new Insets(5));
        propuestaHB.setSpacing(5);
        propuestaHB.setAlignment(Pos.CENTER);
        propuestaHB.getChildren().addAll(propuestaLabel);

        numeroTextField = new TextField();
        numeroTextField.setPromptText("Introduce el número");
        numeroTextField.setText("0");
        numeroTextField.setPrefWidth(150);

        HBox numeroHB = new HBox();
        numeroHB.setPadding(new Insets(5));
        numeroHB.setPrefWidth(50);
        numeroHB.setSpacing(5);
        numeroHB.setAlignment(Pos.CENTER);
        numeroHB.getChildren().addAll(numeroTextField);

        comprobarButton = new Button();
        comprobarButton.setText("Comprobar");
        comprobarButton.setDefaultButton(true);
        comprobarButton.setOnAction(this::comprobar);

        HBox comprobarHB = new HBox();
        comprobarHB.setPadding(new Insets(5));
        comprobarHB.setSpacing(5);
        comprobarHB.setAlignment(Pos.CENTER);
        comprobarHB.getChildren().addAll(comprobarButton);

        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);
        root.setFillWidth(false);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(propuestaHB, numeroHB, comprobarHB);

        //Alertas
        aciertoAlerta = new Alert(Alert.AlertType.INFORMATION);
        aciertoAlerta.setTitle("AdivinApp");
        aciertoAlerta.setHeaderText("¡Has acertado!");

        falloAlerta = new Alert(Alert.AlertType.WARNING);
        falloAlerta.setTitle("AdivinApp");
        falloAlerta.setHeaderText("Intento fallido");

        errorAlerta = new Alert(Alert.AlertType.ERROR);
        errorAlerta.setTitle("AdivinApp");
        errorAlerta.setHeaderText("Error");

        Scene scene = new Scene(root, 440, 280);

        stage.setTitle("Adivina el número");
        stage.setScene(scene);
        stage.show();
    }

    //Métodos
    private void comprobar(javafx.event.ActionEvent event) {
        try {
            int numero = Integer.parseInt(numeroTextField.getText());
            intentos++;

            if (numero >= 1 && numero <= 100) {
                if (numero == numeroAleatorio) {
                    aciertoAlerta.setContentText("¡Has acertado en " + intentos + " intentos!");
                    aciertoAlerta.showAndWait();
                    reiniciarJuego();
                } else if (numero < numeroAleatorio) {
                    falloAlerta.setContentText("El número es mayor. Intentos: " + intentos);
                    numeroTextField.setText("");
                    falloAlerta.showAndWait();
                } else {
                    falloAlerta.setContentText("El número es menor. Intentos: " + intentos);
                    numeroTextField.setText("");
                    falloAlerta.showAndWait();
                }
            } else {
                errorAlerta.setContentText("El número debe estar entre 1 y 100.");
                numeroTextField.setText("");
                errorAlerta.showAndWait();
            }

        } catch (NumberFormatException ex) {
            errorAlerta.setContentText("Por favor, introduce un número válido.");
            errorAlerta.showAndWait();
        }
    }


    private void reiniciarJuego() {
        numeroAleatorio = random.nextInt(100) + 1;
        intentos = 0;
        numeroTextField.clear();
    }
}



