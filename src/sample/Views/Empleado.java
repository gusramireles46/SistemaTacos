package sample.Views;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Models.Conexion;
import sample.Models.UsuariosDAO;

public class Empleado extends Stage {
    private Scene escena;
    private UsuariosDAO user;
    UsuariosDAO usr = new UsuariosDAO();

    private Label _lblTitle,_lblMesas1, _lblMesas2;
    private StackPane _stkMain;
    private JFXButton _mainImg,_btnOrden, _btnLista,_btnSalir, _btnAbout;
    private JFXButton[][] _btnMesas1, _btnMesas2;
    private VBox _vbox, _vboxTitle, _vboxMesas1, _vboxMesas2;
    private HBox _h1, _h2,_hmesas;
    private int clvEmpleado;
    private byte clvPuesto;
    private String nombre, apellidos;
    static int[][] lugar1, lugar2;
    private GridPane gprMesas1, gprMesas2;
    Image ico = new Image("sample/image/ico.png");
    ImageView _img= new ImageView("sample/image/logo.png");
    ImageView _imgOrden = new ImageView("sample/image/snote.png");
    ImageView _imgLista = new ImageView("sample/image/item.png");
    ImageView _imgInfo = new ImageView("sample/image/information.png");
    ImageView _imgExit = new ImageView("sample/image/salir.png");
    Image _imgMesa = new Image("sample/image/table.png",100,100,false,false);

    public Empleado(){
        CrearGUI();
        this.setTitle("Vista empleado");
        this.setScene(escena);
        this.setMaximized(true);
        this.show();
        this.getIcons().add(ico);
        this.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private void CrearGUI() {
        _lblTitle = new Label("Taquería \"El arte del taco\"\n\n");
        _lblMesas1 = new Label("Mesas Adentro");
        _lblMesas2 = new Label("Mesas Afuera");
        _stkMain = new StackPane();
        _mainImg = new JFXButton();
        _mainImg.setGraphic(_img);
        _img.setFitHeight(145);
        _img.setFitWidth(195);
        _mainImg.setPrefSize(195,145);
        _mainImg.setDefaultButton(false);
        _mainImg.setStyle("-fx-background-color:#8B5342;-fx-padding:15;-fx-opacity: 0.65;-fx-border-color:BLACK;-fx-border-radius: 20px;-fx-background-radius: 21px;");
        _mainImg.getStyleClass().add("button-raised");
        _btnOrden = new JFXButton();
        _btnOrden.setGraphic(_imgOrden);
        _imgOrden.setFitHeight(100);
        _imgOrden.setFitWidth(100);
        _btnOrden.setOnAction(event -> {
            new NuevaOrden();
        });
        _btnOrden.setPrefSize(100, 100);
        _btnOrden.getStyleClass().add("button-raised");
        _btnLista = new JFXButton();
        _btnLista.setGraphic(_imgLista);
        _imgLista.setFitHeight(100);
        _imgLista.setFitWidth(100);
        _btnLista.setPrefSize(100, 100);
        _btnLista.getStyleClass().add("button-raised");
        _btnAbout = new JFXButton();
        _btnAbout.setGraphic(_imgInfo);
        _imgInfo.setFitWidth(100);
        _imgInfo.setFitHeight(100);
        _btnAbout.setPrefSize(100,100);
        _btnAbout.getStyleClass().add("button-raised");
        _btnSalir = new JFXButton();
        _btnSalir.setGraphic(_imgExit);
        _imgExit.setFitHeight(100);
        _imgExit.setFitWidth(100);
        _btnSalir.setPrefSize(100,100);
        _btnSalir.getStyleClass().add("button-raised");
        _vbox = new VBox();
        _vboxMesas1 = new VBox();
        _vboxMesas2 = new VBox();
        _vboxTitle = new VBox();
        _h1 = new HBox();
        _h2 = new HBox();
        _hmesas = new HBox();
        gprMesas1 = new GridPane();
        gprMesas1.setPadding(new Insets(15));
        gprMesas2 = new GridPane();
        gprMesas2.setPadding(new Insets(15));

        _btnSalir.setOnAction(event -> {System.exit(0);});
        _vboxTitle.getChildren().addAll(_lblTitle, _mainImg);
        _vboxTitle.setAlignment(Pos.TOP_CENTER);
        _h1.getChildren().addAll(_btnOrden, _btnLista);
        _btnMesas1 = new JFXButton[5][3];
        int cont = 1;
        lugar1 = new int[5][3];
        for(int i = 0; i < 5; i ++){
            for(int j = 0; j < 3; j ++){
                _btnMesas1[i][j] = new JFXButton();
                _btnMesas1[i][j].setPrefSize(100,100);
                _btnMesas1[i][j].getStyleClass().add("button-raised");
                _btnMesas1[i][j].setGraphic(new ImageView(_imgMesa));
                lugar1[i][j] = cont++;
                int finalI = i;
                int finalJ = j;
                _btnMesas1[i][j].setOnMouseClicked(e ->{
                    //System.out.println(lugar1[finalI][finalJ]);
                    new NuevaOrden();
                    NuevaOrden.noMesa = lugar1[finalI][finalJ];
                    NuevaOrden.main.close();
                    new NuevaOrden();
                });
                gprMesas1.add(_btnMesas1[i][j],i,j);
                //System.out.println("Adentro "+ lugar1[i][j]);
            }
        }
        _btnMesas2 = new JFXButton[5][3];
        cont = 21;
        lugar2 = new int[5][3];
        for(int i = 0; i < 5; i ++){
            for(int j = 0; j < 3; j ++){
                _btnMesas2[i][j] = new JFXButton();
                _btnMesas2[i][j].setPrefSize(100,100);
                _btnMesas2[i][j].getStyleClass().add("button-raised");
                _btnMesas2[i][j].setGraphic(new ImageView(_imgMesa));
                lugar2[i][j] = cont++;
                int finalI = i;
                int finalJ = j;
                _btnMesas2[i][j].setOnMouseClicked(e ->{
                    //System.out.println(lugar2[finalI][finalJ]);
                    new NuevaOrden();
                    NuevaOrden.noMesa = lugar2[finalI][finalJ];
                    NuevaOrden.main.close();
                    new NuevaOrden();
                });
                //System.out.println("Afuera "+ lugar2[i][j]);
                gprMesas2.add(_btnMesas2[i][j],i,j);
            }
        }
        _h1.setAlignment(Pos.CENTER);
        _h1.setSpacing(10);
        _h2.getChildren().addAll(_btnAbout, _btnSalir);
        _h2.setAlignment(Pos.CENTER);
        _h2.setSpacing(10);
        _vboxMesas1.getChildren().addAll(_lblMesas1,gprMesas1);
        _vboxMesas1.setAlignment(Pos.CENTER);
        _vboxMesas2.getChildren().addAll(_lblMesas2,gprMesas2);
        _vboxMesas2.setAlignment(Pos.CENTER);
        _hmesas.getChildren().addAll(_vboxMesas1,_vboxMesas2);
        _hmesas.setAlignment(Pos.CENTER);
        _hmesas.setSpacing(250);
        _vbox.getChildren().addAll(_vboxTitle, _h1, _h2,_hmesas);
        _vbox.setAlignment(Pos.TOP_CENTER);
        _vbox.setSpacing(10);
        _stkMain.getChildren().addAll(_vbox);

        escena = new Scene(_stkMain, 1280, 720);
        escena.getStylesheets().add("sample/style/empleado.css");
    }
}
