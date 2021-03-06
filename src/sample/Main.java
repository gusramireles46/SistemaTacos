package sample;

import com.jfoenix.controls.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.Models.*;

public class Main extends Application {
    JFXTextField _txfUsuario;
    JFXPasswordField _pwdUsuario;
    JFXButton _btnAcceso;
    JFXRippler ripUsuario, ripPWD;
    Image ico = new Image("sample/image/ico.png");
    public static VBox vbox, vboxPwd;
    public static Scene escena;
    public static StackPane pane;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage){
        //Declaración de los componentes.
        ripUsuario = new JFXRippler(_txfUsuario);
        _pwdUsuario = new JFXPasswordField();
        _pwdUsuario.setPrefSize(50,50);
        _pwdUsuario.setPromptText("Contraseña");
        _pwdUsuario.setStyle("-fx-font-size: 20;");
        ripPWD = new JFXRippler(_pwdUsuario);
        ripPWD.setStyle("-fx-background-color: #A79E8F;");
        _btnAcceso = new JFXButton("Acceder".toUpperCase());
            _btnAcceso.setStyle("-fx-background-color:BLACK;-fx-padding:15");
            _btnAcceso.getStyleClass().add("button-raised");
            _btnAcceso.setId("btn-info");
            _btnAcceso.setCursor(Cursor.HAND);

        //Construcción de la interfaz
        vboxPwd = new VBox();

        vboxPwd.getChildren().addAll(ripPWD);
        vboxPwd.setSpacing(15);
        vboxPwd.setAlignment(Pos.CENTER);

        //BorderPane
        VBox vboxUsrPwd = new VBox();
        vboxUsrPwd.getChildren().addAll(vboxPwd);
        vboxUsrPwd.setSpacing(30);
        pane = new StackPane();
        pane.getChildren().add(vboxUsrPwd);
        pane.setMaxSize(450,250);
        pane.setStyle("-fx-background-color:#6D4A36;-fx-padding:15;-fx-opacity: 0.85; -fx-border-color: #A79E8F;-fx-border-width: 5px;" +
                "-fx-border-radius: 20px;-fx-background-radius: 21px");

        vbox = new VBox();
        vbox.setMinWidth(250);
        vbox.getChildren().addAll(pane,_btnAcceso);
        vbox.setSpacing(30);
        vbox.setAlignment(Pos.CENTER);
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(_pwdUsuario.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return (_pwdUsuario.getText().isEmpty());
            }
        };
        _btnAcceso.disableProperty().bind(bb);

        //Conexion a la DB
        Conexion.crearConexionMariaDB();
        //Conexion.crearConexionMySQL();

        // Construccion de la escena
        escena = new Scene(vbox, 1280, 720);
        primaryStage.setTitle("Taqueria \"El arte del taco\"");
        primaryStage.getIcons().add(ico);
        primaryStage.setScene(escena);
        primaryStage.show();
        stage = primaryStage;

        _btnAcceso.setOnAction(e-> {
            //vbox.setEffect(blur);
            new UsuariosDAO().validarUsuario(_pwdUsuario.getText());
            _pwdUsuario.setText("");
            primaryStage.close();
        });
        escena.getStylesheets().add("sample/style/estilos.css");
        escena.getStylesheets().add("sample/style/main.css");
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            //System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
