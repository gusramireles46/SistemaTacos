package sample.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Main;
import sample.Models.BebidaDAO;
import sample.Models.PlatoDAO;

public class Administrador extends Stage {
    BebidaDAO objB;
    PlatoDAO objP;
    Scene escena;
    HBox hbox;
    VBox vbox, titulo, stats, frmBebida, frmTaco;
    Label title, addPlato, addDrink;
    PieChart chart;
    HBox actButtons, actButtonP;
    Button btnGrafico, btnAddTaco, btnAddBebida, btnRegistrarBebida, btnCancelBebida, btnCancelTaco, btnRegistrarTaco, btnSalir;
    StackPane root;
    TextField nombreBebida, descBebida, precioBebida, nombrePlato, descPlato, precioPlato;


    public Administrador(){
        CrearGUI();
        objP = new PlatoDAO();
        objB = new BebidaDAO();
        this.setTitle("Administrador");
        this.setMaximized(true);
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        root = new StackPane();
        btnSalir = new Button("Salir");
        ImageView salir = new ImageView("sample/image/cerrar.png");
        btnSalir.setGraphic(salir);
        btnSalir.setOnAction(event1 -> {
            this.close();
            Main.stage.show();
        });
        vbox = new VBox();
        titulo = new VBox();
        stats = new VBox();
        stats.setStyle("-fx-border-color: black; -fx-border-width: 5; -fx-border-radius: 50px;");
        hbox = new HBox();
        title = new Label("Taquería \"El arte del taco\"");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: 900;");
        title.setTextFill(Color.WHITE);
        btnAddTaco = new Button("Añadir plato");
        ImageView add = new ImageView("sample/image/add_taco.png");
        btnAddTaco.setGraphic(add);
        btnAddBebida = new Button("Añadir Bebida");
        ImageView addB = new ImageView("sample/image/bebida.png");
        btnAddBebida.setGraphic(addB);
        btnGrafico = new Button("Mostrar gráfico");
        ImageView chrt = new ImageView("sample/image/pieChart.png");
        btnGrafico.setGraphic(chrt);
        titulo.getChildren().addAll(title);
        titulo.setAlignment(Pos.TOP_CENTER);
        titulo.setSpacing(80);
        hbox.getChildren().addAll(btnAddTaco, btnAddBebida, btnSalir);
        hbox.setSpacing(15);
        hbox.setAlignment(Pos.CENTER);

        frmTaco = new VBox();
        frmTaco.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 15px; -fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 15px;");
        frmTaco.setSpacing(5);
        frmTaco.setPadding(new Insets(15));
        frmTaco.setMaxSize(500, 500);
        frmTaco.setAlignment(Pos.CENTER);
        addPlato = new Label("Agregar Plato");
        addPlato.setStyle("-fx-font-size: 18px; -fx-font-weight: 900;");
        actButtonP = new HBox();
        nombrePlato = new TextField();
        nombrePlato.setPromptText("Nombre del plato");
        nombrePlato.setStyle("-fx-border-width: 1; -fx-border-color: rgba(0,0,0,0.5); -fx-border-radius: 15px; -fx-background-radius: 15px;");
        descPlato = new TextField();
        descPlato.setPromptText("Contenido del plato");
        descPlato.setStyle("-fx-border-width: 1; -fx-border-color: rgba(0,0,0,0.5); -fx-border-radius: 15px; -fx-background-radius: 15px;");
        precioPlato = new TextField();
        precioPlato.setPromptText("Precio en formato \"0.00\"");
        precioPlato.setStyle("-fx-border-width: 1; -fx-border-color: rgba(0,0,0,0.5); -fx-border-radius: 15px; -fx-background-radius: 15px;");

        btnRegistrarTaco = new Button("Registrar");
        btnCancelTaco = new Button("Cancelar");
        actButtonP.getChildren().addAll(btnRegistrarTaco, btnCancelTaco);
        actButtonP.setAlignment(Pos.CENTER);
        actButtonP.setSpacing(10);

        frmTaco.getChildren().addAll(addPlato, nombrePlato, descPlato, precioPlato, actButtonP);
        frmTaco.setVisible(false);

        btnRegistrarTaco.setOnAction(eTacoR -> {
            registrarDatosPlato();
        });

        btnCancelTaco.setOnAction(eTaco -> {
            frmTaco.setVisible(false);
        });

        btnAddTaco.setOnAction(eTaco1 -> {
            frmTaco.setVisible(true);
        });

        frmBebida = new VBox();
        frmBebida.setSpacing(5);
        frmBebida.setPadding(new Insets(15));
        frmBebida.setMaxSize(500,500);
        frmBebida.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 15px; -fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 15px;");
        addDrink = new Label("Agregar bebida");
        addDrink.setStyle("-fx-font-size: 18px; -fx-font-weight: 900;");
        nombreBebida = new TextField();
        nombreBebida.setPromptText("Nombre de la bebida");
        nombreBebida.setStyle("-fx-border-width: 1; -fx-border-color: rgba(0,0,0,0.5); -fx-border-radius: 15px; -fx-background-radius: 15px;");
        descBebida = new TextField();
        descBebida.setPromptText("Descripción breve de la bebida");
        descBebida.setStyle("-fx-border-width: 1; -fx-border-color: rgba(0,0,0,0.5); -fx-border-radius: 15px; -fx-background-radius: 15px;");
        precioBebida = new TextField();
        precioBebida.setPromptText("Precio en formato \"0.00\"");
        precioBebida.setStyle("-fx-border-width: 1; -fx-border-color: rgba(0,0,0,0.5); -fx-border-radius: 15px; -fx-background-radius: 15px;");

        actButtons = new HBox();
        btnRegistrarBebida = new Button("Registrar");
        btnCancelBebida = new Button("Cancelar");
        actButtons.getChildren().addAll(btnRegistrarBebida, btnCancelBebida);
        actButtons.setAlignment(Pos.CENTER);
        actButtons.setSpacing(10);

        frmBebida.getChildren().addAll(addDrink, nombreBebida, descBebida, precioBebida, actButtons);
        frmBebida.setVisible(false);

        btnRegistrarBebida.setOnAction(eBebida -> {
            registrarDatosBebida();
        });

        btnCancelBebida.setOnAction(eBebida -> {
            frmBebida.setVisible(false);
        });

        btnAddBebida.setOnAction(event -> {
            frmBebida.setVisible(true);
        });

        vbox.getChildren().addAll(titulo, hbox, frmTaco, frmBebida);
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(vbox);
        root.setAlignment(Pos.CENTER);


        escena = new Scene(root, 500,500);
        escena.getStylesheets().add("sample/style/estiloAdmin.css");
    }

    private void registrarDatosPlato() {
        objP.setNombrePlato(nombrePlato.getText());
        objP.setDescPlato(descPlato.getText());
        objP.setPrecioPlato(Double.parseDouble(precioPlato.getText()));
        objP.registrarPlato();

        Alert complete = new Alert(Alert.AlertType.INFORMATION);
        complete.setTitle("Mensaje del sistema");
        complete.setHeaderText("Registro completo");
        complete.setContentText("Se ha completado el registro con éxito");
        complete.setGraphic(new ImageView("sample/image/check.png"));
        complete.showAndWait();

        nombrePlato.setText(null);
        descPlato.setText(null);
        precioPlato.setText(null);
        frmTaco.setVisible(false);
    }

    private void registrarDatosBebida() {
        objB.setNombreBebida(nombreBebida.getText());
        objB.setDescBebida(descBebida.getText());
        objB.setPrecioBebida(Double.parseDouble(precioBebida.getText()));
        objB.insBebida();
        Alert complete = new Alert(Alert.AlertType.INFORMATION);
        complete.setTitle("Mensaje del sistema");
        complete.setHeaderText("Registro completo");
        complete.setGraphic(new ImageView("sample/image/check.png"));
        complete.setContentText("Se ha completado el registro con éxito");
        complete.showAndWait();
        nombreBebida.setText(null);
        descBebida.setText(null);
        precioBebida.setText(null);
        frmBebida.setVisible(false);
    }
}
