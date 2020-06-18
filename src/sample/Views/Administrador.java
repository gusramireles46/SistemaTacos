package sample.Views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    Label title;
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
        stats.setStyle("-fx-border-color: black; -fx-border-width: 5;");
        hbox = new HBox();
        title = new Label("Taquería \"El arte del taco\"");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: 900;");
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
        actButtonP = new HBox();
        nombrePlato = new TextField();
        nombrePlato.setPromptText("Nombre del plato");
        descPlato = new TextField();
        descPlato.setPromptText("Contenido del plato");
        precioPlato = new TextField();
        precioPlato.setPromptText("Precio en formato \"0.00\"");

        btnRegistrarTaco = new Button("Registrar");
        btnCancelTaco = new Button("Cancelar");
        actButtonP.getChildren().addAll(btnRegistrarTaco, btnCancelTaco);

        frmTaco.getChildren().addAll(nombrePlato, descPlato, precioPlato, actButtonP);
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
        nombreBebida = new TextField();
        nombreBebida.setPromptText("Nombre de la bebida");
        descBebida = new TextField();
        descBebida.setPromptText("Descripción breve de la bebida");
        precioBebida = new TextField();
        precioBebida.setPromptText("Precio en formato \"0.00\"");

        actButtons = new HBox();
        btnRegistrarBebida = new Button("Registrar");
        btnCancelBebida = new Button("Cancelar");
        actButtons.getChildren().addAll(btnRegistrarBebida, btnCancelBebida);

        frmBebida.getChildren().addAll(nombreBebida, descBebida, precioBebida, actButtons);
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
        vbox.setSpacing(15);
        root.getChildren().addAll(vbox);
        root.setAlignment(Pos.CENTER);


        escena = new Scene(root, 500,500);
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
        complete.setContentText("Se ha completado el registro con éxito");
        complete.showAndWait();
        nombreBebida.setText(null);
        descBebida.setText(null);
        precioBebida.setText(null);
        frmBebida.setVisible(false);
    }
}
