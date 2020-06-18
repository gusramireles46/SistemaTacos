package sample.Views;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Models.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NuevaOrden extends Stage {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet res;

    private Scene escena;
    private JFXListView<String> lstPlatos, lstBebidas;
    //ObservableList<String> items = FXCollections.observableArrayList("Cricket", "Chess", "Kabaddy", "Badminton","Football", "Golf", "CoCo", "car racing");
    ObservableList<String> itemsPlatos = FXCollections.observableArrayList();
    ObservableList<String> itemsBebidas = FXCollections.observableArrayList();
    Label lblPlatos, lblBebidas;
    Image ico = new Image("sample/image/ico.png");
    HBox hbox;
    VBox vBox;
    public static int[][] noMesa;
    public static int lugarI, lugarJ;

    public NuevaOrden(){
        CrearGUI();
        noMesa = new int[5][3];
        this.setTitle("Menu Orden A Mesa No :"+noMesa[lugarI][lugarJ]);
        this.setTitle("Menu Orden Mesa");
        this.setScene(escena);
        //this.setMaximized(true);
        this.show();
        this.getIcons().add(ico);
        //System.out.println("Mesa No: "+noMesa);
    }

    public void fillPlatos(){
        con = Conexion.conn;
        itemsPlatos.clear();
        try {
            String consulta = "SELECT nombrePlato FROM tbl_plato";
            stmt = con.prepareStatement(consulta);
            res = stmt.executeQuery();
            while (res.next()){
                itemsPlatos.add(res.getString("nombrePlato"));
            }
            stmt.close();
            res.close();
        }catch (SQLException ex){
            Logger.getLogger(NuevaOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillBebidas(){
        con = Conexion.conn;
        itemsBebidas.clear();
        try {
            String consulta = "SELECT nombreBebida FROM tbl_bebida";
            stmt = con.prepareStatement(consulta);
            res = stmt.executeQuery();
            while (res.next()){
                itemsBebidas.add(res.getString("nombreBebida"));
            }
            stmt.close();
            res.close();
        }catch (SQLException ex){
            Logger.getLogger(NuevaOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CrearGUI() {
        fillPlatos();
        fillBebidas();
        ResultSet res;
        lstPlatos = new JFXListView<String>();
        lstPlatos.setItems(itemsPlatos);
        lstPlatos.setMaxSize(300,450);
        /*
        lstPlatos.setOnMouseClicked(event -> {
            try {
                String consulta = "SELECT nombrePlanto FROM tbl_plato WHERE nombrePlato = ?";
                PreparedStatement stmt = con.prepareStatement(consulta);
                res = stmt.executeQuery();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        });
        */
        BorderPane.setMargin(lstPlatos,new Insets(10));

        lstBebidas = new JFXListView<String>();
        lstBebidas.setItems(itemsBebidas);
        lstBebidas.setMaxSize(300,450);
        /*
        lstPlatos.setOnMouseClicked(event -> {
            try {
                String consulta = "SELECT nombrePlanto FROM tbl_plato WHERE nombrePlato = ?";
                PreparedStatement stmt = con.prepareStatement(consulta);
                res = stmt.executeQuery();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        });
        */
        BorderPane.setMargin(lstBebidas,new Insets(10));

        lblPlatos = new Label();
        lblPlatos.setStyle("-fx-font-weight: 900;");
        lstPlatos.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            String selectedItem = lstPlatos.getSelectionModel().getSelectedItem();
            lblPlatos.setText("Item selected : " + selectedItem);
        });
        lblBebidas = new Label();
        lblBebidas.setStyle("-fx-font-weight: 900;");
        lstBebidas.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            String selectedItem = lstBebidas.getSelectionModel().getSelectedItem();
            lblBebidas.setText("Item selected : " + selectedItem);
        });
        vBox = new VBox();
        vBox.getChildren().addAll(lblPlatos, lblBebidas);
        hbox = new HBox();
        hbox.getChildren().addAll(lstPlatos,lstBebidas,vBox);
        //s
        escena = new Scene(hbox,800,450);
        escena.getStylesheets().add("sample/style/nuevaOrden.css");
    }
}
