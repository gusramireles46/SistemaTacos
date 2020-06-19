package sample.Views;

import com.jfoenix.controls.*;
import com.jfoenix.skins.JFXTreeTableViewSkin;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Models.Conexion;
import sample.Models.UsuariosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NuevaOrden extends Stage {

    private Connection con = Conexion.conn;
    private PreparedStatement stmt, stmtPlato, stmtBebida, stmtProducto;
    private ResultSet res, resPlato, resBebida, resProducto;

    private Scene escena;
    private Pane root;

    JFXListView<String> lstPlatos, lstBebidas, lstPedidos;
    JFXTabPane tpMenu, tbPedidos;
    JFXButton[][] btnNumeros;
    JFXButton btnCancelar,btnPedir, btnTicket;
    JFXTextField producto, cantidad, subTotal, total;
    JFXTreeTableView tblPedidos;
    int[][] intValores;
    String tipoProducto, idPlato, idBebida, subplatos, subbebidas;

    ObservableList<String> itemsPlatos = FXCollections.observableArrayList();
    ObservableList<String> itemsBebidas = FXCollections.observableArrayList();
    ObservableList<String> itemsPedidos = FXCollections.observableArrayList();

    Image ico = new Image("sample/image/ico.png");
    Image dishes = new Image("sample/image/dishes.png", 50,50,false,false);
    Image drinks = new Image("sample/image/drinks.png", 50,50,false,false);
    Image check = new Image("sample/image/check.png", 50,50,false,false);

    private Tab tabPlatos, tabBebidas, tabPedidosRealizados;
    private BorderPane brpMenu, brpNumeros;
    public static VBox vBoxPedido, vBoxTicket;
    public static HBox hBox;
    static int noMesa;
    static Stage main;
    private GridPane grpBtnNumeros;
    private final Label jobStatus = new Label();

    public NuevaOrden(){
        CrearGUI(this);
        this.setTitle("Menu Orden A Mesa No :"+noMesa);
        this.setScene(escena);
        this.show();
        this.getIcons().add(ico);
        main = this;
    }

    public void fillPlatos(){
        itemsPlatos.clear();
        try {
            String consulta1 = "SELECT nombrePlato FROM tbl_plato";
            stmtPlato = con.prepareStatement(consulta1);
            resPlato = stmtPlato.executeQuery();
            while (resPlato.next()){
                itemsPlatos.add(resPlato.getString("nombrePlato"));
            }
            stmtPlato.close();
            resPlato.close();
        }catch (SQLException ex){
            Logger.getLogger(NuevaOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillBebidas(){
        itemsBebidas.clear();
        try {
            String consulta2 = "SELECT nombreBebida FROM tbl_bebida";
            stmtBebida = con.prepareStatement(consulta2);
            resBebida = stmtBebida.executeQuery();
            while (resBebida.next()){
                itemsBebidas.add(resBebida.getString("nombreBebida"));
            }
            stmtBebida.close();
            resBebida.close();
        }catch (SQLException ex){
            Logger.getLogger(NuevaOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillPedidos(){
        itemsPedidos.clear();
        try {
            String consultaPlatos = "SELECT tbl_plato.nombrePlato, tbl_pedido.cantidadPlato FROM tbl_pedido INNER JOIN tbl_plato ON tbl_pedido.idPlato = tbl_plato.idPlato WHERE tbl_pedido.idMesa = '"+noMesa+"'";
            stmtProducto = con.prepareStatement(consultaPlatos);
            resProducto = stmtProducto.executeQuery();
            while (resProducto.next()){
                itemsPedidos.add(resProducto.getString("nombrePlato"));
                itemsPedidos.add(resProducto.getString("cantidadPlato"));
                //System.out.println(resProducto.getString("nombrePlato")+" "+resProducto.getString("cantidadPlato"));
            }
            String consultaBebidas = "SELECT tbl_bebida.nombreBebida, tbl_pedido.cantidadBebida FROM `tbl_pedido` INNER JOIN `tbl_bebida` ON tbl_pedido.idBebida = tbl_bebida.idBebida WHERE tbl_pedido.idMesa = '"+noMesa+"'";
            stmtProducto = con.prepareStatement(consultaBebidas);
            resProducto = stmtProducto.executeQuery();
            while (resProducto.next()){
                itemsPedidos.add(resProducto.getString("nombreBebida"));
                itemsPedidos.add(resProducto.getString("cantidadBebida"));
                //System.out.println(resProducto.getString("nombreBebida")+" "+resProducto.getString("cantidadBebida"));
            }
            stmtProducto.close();
            resProducto.close();
        }catch (SQLException ex){
            Logger.getLogger(NuevaOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void subtotalPlatos() {
        try {
            String consultaSubtotalPlatos = "SELECT sum(tbl_plato.precioPlato * tbl_pedido.cantidadPlato) AS SUBTOTAL FROM tbl_pedido INNER JOIN tbl_plato ON tbl_pedido.idPlato = tbl_plato.idPlato WHERE tbl_pedido.idMesa = '"+noMesa+"'";
            stmt = con.prepareStatement(consultaSubtotalPlatos);
            res = stmt.executeQuery();
            while (res.next()){
                subplatos = res.getString("SUBTOTAL");
                System.out.println(""+subplatos);
                //total.setText(Integer.parseInt(subTotal.getText())*1.16);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NuevaOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void subtotalBebidas() {
        try {
            String consultaSubtotalPlatos = "SELECT sum(tbl_bebida.precioBebida * tbl_pedido.cantidadBebida) AS SUBTOTAL FROM tbl_pedido INNER JOIN tbl_bebida ON tbl_pedido.idBebida = tbl_bebida.idBebida WHERE tbl_pedido.idMesa = '"+noMesa+"'";
            stmt = con.prepareStatement(consultaSubtotalPlatos);
            res = stmt.executeQuery();
            while (res.next()){
                subbebidas = res.getString("SUBTOTAL");
                System.out.println(""+subbebidas);
                //total.setText(Integer.parseInt(subTotal.getText())*1.16);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NuevaOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CrearGUI(final Stage stage) {
        brpMenu = new BorderPane();
        brpNumeros = new BorderPane();
        BoxBlur blur = new BoxBlur(5,5,5);
        fillPlatos();
        fillBebidas();
        fillPedidos();
        subtotalPlatos();
        lstPlatos = new JFXListView<String>();
        lstPlatos.setItems(itemsPlatos);
        BorderPane.setMargin(lstPlatos,new Insets(10));

        lstBebidas = new JFXListView<String>();
        lstBebidas.setItems(itemsBebidas);
        BorderPane.setMargin(lstBebidas,new Insets(10));

        JFXTreeTableColumn clmPedidos = new JFXTreeTableColumn("Pedido");
        clmPedidos.setPrefWidth(150);
        clmPedidos.setCellFactory(new Callback<TreeTableColumn, TreeTableCell>() {
            @Override
            public TreeTableCell call(TreeTableColumn param) {
                return null;
            }
        });
        JFXTreeTableColumn clmCantidad = new JFXTreeTableColumn("Cantidad");
        tblPedidos = new JFXTreeTableView();

        tpMenu = new JFXTabPane();
        tbPedidos = new JFXTabPane();
        tabPlatos = new Tab();
        tabPlatos.setText("Platos");
        tabPlatos.setGraphic(new ImageView(dishes));
        tabPlatos.setContent(lstPlatos);
        tabBebidas = new Tab();
        tabBebidas.setText("Bebidas");
        tabBebidas.setGraphic(new ImageView(drinks));
        tabBebidas.setContent(lstBebidas);
        tpMenu.getTabs().addAll(tabPlatos,tabBebidas);
        tabPedidosRealizados = new Tab("Pedidos Realizados");
        tabPedidosRealizados.setGraphic(new ImageView(check));
        tabPedidosRealizados.setStyle("-fx-pref-width: 400;");
        tbPedidos.getTabs().addAll(tabPedidosRealizados);

        vBoxPedido = new VBox();
        producto = new JFXTextField();
        producto.setPromptText("Producto");
        producto.setEditable(false);
        producto.setPrefSize(50,50);
        producto.setAlignment(Pos.CENTER);
        cantidad = new JFXTextField();
        cantidad.setPromptText("Cantidad");
        cantidad.setEditable(false);
        cantidad.setPrefSize(50,50);
        cantidad.setAlignment(Pos.CENTER);
        subTotal = new JFXTextField();
        subTotal.setPromptText("Subtotal");
        subTotal.setEditable(false);
        subTotal.setMaxSize(400,50);
        subTotal.setAlignment(Pos.CENTER);
        subTotal.setText(subplatos);
        total = new JFXTextField();
        total.setPromptText("Total");
        total.setEditable(false);
        total.setMaxSize(400,50);
        total.setAlignment(Pos.CENTER);
        //Probables Errores
        double subt = Double.parseDouble(subTotal.getText());
        double totalt = subt * 1.16;
        total.setText(""+totalt);
        vBoxPedido.getChildren().addAll(producto, cantidad);

        lstPlatos.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            String selectedItem = lstPlatos.getSelectionModel().getSelectedItem();
            producto.setText(selectedItem);
            try {
                String consulta = "SELECT idPlato FROM tbl_plato WHERE nombrePlato ='"+selectedItem+"'";
                stmt = con.prepareStatement(consulta);
                res = stmt.executeQuery();
                //idPlato = res.getString("idPlato");
                while (res.next())
                    idPlato = res.getString("idPlato");
                stmt.close();
                res.close();
            }catch (SQLException ex){
                Logger.getLogger(NuevaOrden.class.getName()).log(Level.SEVERE, null, ex);
            }
            tipoProducto = "Plato";
        });
        lstBebidas.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            String selectedItem = lstBebidas.getSelectionModel().getSelectedItem();
            producto.setText(selectedItem);
            tipoProducto = "Bebida";
            try {
                String consulta = "SELECT idBebida FROM tbl_bebida WHERE nombreBebida ='"+selectedItem+"'";
                stmt = con.prepareStatement(consulta);
                res = stmt.executeQuery();
                while (res.next())
                    idBebida = res.getString("idBebida");
            }catch (SQLException ex){
                Logger.getLogger(NuevaOrden.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnNumeros = new JFXButton[3][3];
        intValores = new int[3][3];
        int cont = 1;
        grpBtnNumeros = new GridPane();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                btnNumeros[i][j] = new JFXButton();
                intValores[i][j] = cont;
                btnNumeros[i][j].setPrefSize(100,100);
                btnNumeros[i][j].setText(""+intValores[i][j]);
                int finalI = i;
                int finalJ = j;
                btnNumeros[i][j].setOnAction(event -> {
                    cantidad.setText(cantidad.getText()+intValores[finalI][finalJ]);
                });
                cont++;
                grpBtnNumeros.add(btnNumeros[i][j], i, j);
            }
        }
        grpBtnNumeros.setAlignment(Pos.CENTER);

        btnCancelar = new JFXButton("Cancelar");
        btnCancelar.setStyle("-fx-background-color: #B83F01");
        btnCancelar.setOnAction(event -> {
            main.close();
        });
        btnPedir = new JFXButton("Pedir");
        btnPedir.setStyle("-fx-background-color: #47C0FE");
        btnPedir.setOnAction(event -> {
            root.setEffect(blur);
            Alert bienvenido = new Alert(Alert.AlertType.CONFIRMATION);
            bienvenido.setTitle("Registar");
            bienvenido.setHeaderText("¿Registrar Producto?");
            bienvenido.setContentText("¿Agregar "+tipoProducto+"?");
            Optional<ButtonType> result = bienvenido.showAndWait();
            if(result.get() == ButtonType.OK){
                int cve = UsuariosDAO.cveUsuario;
                String productoString = producto.getText();
                int cantidadInt = Integer.parseInt(cantidad.getText());
                try {
                    if(tipoProducto.equals("Plato")){
                        String consulta = "INSERT INTO tbl_pedido (idPlato, cantidadPlato, idMesa, cveUsuario) VALUES ("+idPlato+","+cantidadInt+","+noMesa+","+cve+")";
                        stmt = con.prepareStatement(consulta);
                        res = stmt.executeQuery();
                        stmt.close();
                        res.close();
                    }else {
                        if (tipoProducto.equals("Bebida")){
                            String consulta = "INSERT INTO tbl_pedido (idBebida, cantidadBebida, idMesa, cveUsuario) VALUES ("+idBebida+","+cantidadInt+","+noMesa+","+cve+")";
                            stmt = con.prepareStatement(consulta);
                            res = stmt.executeQuery();
                            stmt.close();
                            res.close();
                        }
                    }
                }catch (SQLException ex){
                    Logger.getLogger(NuevaOrden.class.getName()).log(Level.SEVERE, null, ex);
                }
                root.setEffect(null);
            } else {
                root.setEffect(null);
            }
        });
        btnTicket = new JFXButton("Realizar\nTicket");
        TextArea textArea = new TextArea();
        btnTicket.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)
            {
                pageSetup(textArea,stage);

            }
        });

        vBoxTicket = new VBox();
        vBoxTicket.getChildren().addAll(subTotal, total, btnTicket);
        vBoxTicket.setAlignment(Pos.CENTER_RIGHT);

        hBox = new HBox();
        hBox.getChildren().addAll(btnCancelar,btnPedir);
        hBox.setAlignment(Pos.CENTER);

        brpNumeros.setTop(vBoxPedido);
        brpNumeros.setCenter(grpBtnNumeros);
        brpNumeros.setBottom(hBox);

        root = new Pane();
        escena = new Scene(root,1280,720);

        brpMenu.prefHeightProperty().bind(escena.heightProperty());
        brpMenu.prefWidthProperty().bind(escena.widthProperty());
        brpMenu.setLeft(tpMenu);
        brpMenu.setCenter(brpNumeros);
        brpMenu.setRight(tbPedidos);
        VBox vbox1 = new VBox();
        vbox1.getChildren().addAll(vBoxTicket);
        brpMenu.setBottom(vbox1);
        root.getChildren().addAll(brpMenu);

        escena.getStylesheets().add("sample/style/nuevaOrden.css");
    }

    private void pageSetup(Node node, Stage owner)
    {
        // Create the PrinterJob
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job == null)
        {
            return;
        }

        // Show the page setup dialog
        boolean proceed = job.showPageSetupDialog(owner);

        if (proceed)
        {
            print(job, node);
        }
    }

    private void print(PrinterJob job, Node node)
    {
        // Set the Job Status Message
        jobStatus.textProperty().bind(job.jobStatusProperty().asString());

        // Print the node
        boolean printed = job.printPage(node);

        if (printed)
        {
            job.endJob();
        }
    }
    //Impresion De PDF Aun No Reconocible Y Funcional Al 100%
}
