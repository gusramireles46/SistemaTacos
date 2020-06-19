package sample.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Grafico extends Stage {
    public Grafico(){
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Taco al pastor", 34),
                new PieChart.Data("Taco de Bistec", 4),
                new PieChart.Data("Taco de Cabeza", 10)
        );

        PieChart pc = new PieChart(pieData);

        Pane root = new Pane(pc);
        Scene escena = new Scene(root, 500, 500);

        this.setTitle("Gráfico de ventas");
        this.setMaximized(true);
        this.setScene(escena);
        this.show();
    }
}
