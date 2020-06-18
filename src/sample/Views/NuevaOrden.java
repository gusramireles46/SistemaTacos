package sample.Views;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTabPane;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Main;
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
    JFXTabPane tpMenu;
    Tab tabPlatos, tabBebidas;
    Label lblPlatos, lblBebidas;
    Image ico = new Image("sample/image/ico.png");
    Image dishes = new Image("sample/image/dishes.png", 50,50,false,false);
    Image drinks = new Image("sample/image/drinks.png", 50,50,false,false);
    HBox hBox;
    VBox vBox;
    static int noMesa;
    static Stage main;

    public NuevaOrden(){
        CrearGUI();
        this.setTitle("Menu Orden A Mesa No :"+noMesa);
        //this.setTitle("Menu Orden Mesa");
        this.setScene(escena);
        //this.setMaximized(true);
        this.show();
        this.getIcons().add(ico);
        //System.out.println("Mesa No: "+noMesa);
        main = this;
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
        //lstPlatos.setMaxSize(300,450);
        BorderPane.setMargin(lstPlatos,new Insets(10));

        lstBebidas = new JFXListView<String>();
        lstBebidas.setItems(itemsBebidas);
        //lstBebidas.setMaxSize(300,450);
        BorderPane.setMargin(lstBebidas,new Insets(10));

        tpMenu = new JFXTabPane();
        tabPlatos = new Tab();
        tabPlatos.setText("Platos");
        dishes.widthProperty();
        tabPlatos.setGraphic(new ImageView(dishes));
        tabPlatos.setContent(lstPlatos);
        tabBebidas = new Tab();
        tabBebidas.setText("Bebidas");
        tabBebidas.setGraphic(new ImageView(drinks));
        tabBebidas.setContent(lstBebidas);
        tpMenu.getTabs().addAll(tabPlatos,tabBebidas);

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
        hBox = new HBox();
        hBox.getChildren().addAll(tpMenu,vBox);
        //s
        escena = new Scene(hBox,800,450);
        escena.getStylesheets().add("sample/style/nuevaOrden.css");
    }
}
