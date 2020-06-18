package sample.Views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Administrador extends Stage {
    Scene escena;
    HBox hbox;
    VBox vbox, titulo, stats;
    Label title;
    PieChart chart;
    Button btnGrafico, btnAddProd;
    StackPane root;


    public Administrador(){
        CrearGUI();
        this.setTitle("Administrador");
        this.setMaximized(true);
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        root = new StackPane();
        vbox = new VBox();
        titulo = new VBox();
        stats = new VBox();
        stats.setStyle("-fx-border-color: black; -fx-border-width: 5;");
        hbox = new HBox();
        title = new Label("Taquería \"El arte del taco\"");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: 900;");
        btnAddProd = new Button("Añadir producto");
        ImageView add = new ImageView("sample/image/add_taco.png");
        btnAddProd.setGraphic(add);
        btnGrafico = new Button("Mostrar gráfico");
        ImageView chrt = new ImageView("sample/image/pieChart.png");
        btnGrafico.setGraphic(chrt);
        titulo.getChildren().addAll(title);
        titulo.setAlignment(Pos.TOP_CENTER);
        titulo.setSpacing(80);
        hbox.getChildren().addAll(btnAddProd, btnGrafico);
        hbox.setSpacing(15);
        hbox.setAlignment(Pos.CENTER);

        chart.setTitle("Ventas por producto");

        stats.getChildren().addAll(chart);

        vbox.getChildren().addAll(titulo, hbox, stats);
        vbox.setSpacing(15);
        root.getChildren().addAll(vbox);
        root.setAlignment(Pos.CENTER);


        escena = new Scene(root, 500,500);
    }
}
