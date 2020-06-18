//package sample.pruebas;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*public class pruebas {
        private Label _lblTitle;
        private Button _mainImg;
        private StackPane _stkMain;
        private Button _btnTest,_btnOrden, _btnLista,_btnSalir, _btnAbout;
        private VBox _vbox;
        private VBox _vboxTitle;
        private HBox _h1, _h2;
        private Image ico = new Image("sample/image/ico.png");
        ImageView _img= new ImageView("sample/image/logo.png");
        ImageView _imgOrden = new ImageView("sample/image/snote.png");
        ImageView _imgLista = new ImageView("sample/image/item.png");
        ImageView _imgInfo = new ImageView("sample/image/information.png");
        ImageView _imgExit = new ImageView("sample/image/salir.png");
        @Override
        public void start(Stage primaryStage) throws Exception{
            // Definición de los elementos principales
            _lblTitle = new Label("Taquería \"El arte del taco\"\n\n");
            _stkMain = new StackPane();
            _mainImg = new Button();
            _mainImg.setGraphic(_img);
            _img.setFitHeight(145);
            _img.setFitWidth(195);
            _mainImg.setPrefSize(195,145);
            _mainImg.setDefaultButton(false);
            _mainImg.setStyle("-fx-background-color: transparent;");
            //_btnTest = new Button("Test A Base De Datos");
            _btnOrden = new Button();
            _btnOrden.setGraphic(_imgOrden);
            _imgOrden.setFitHeight(100);
            _imgOrden.setFitWidth(100);
            _btnOrden.setPrefSize(100, 100);
            _btnLista = new Button();
            _btnLista.setGraphic(_imgLista);
            _imgLista.setFitHeight(100);
            _imgLista.setFitWidth(100);
            _btnLista.setPrefSize(100, 100);
            _btnAbout = new Button();
            _btnAbout.setGraphic(_imgInfo);
            _imgInfo.setFitWidth(100);
            _imgInfo.setFitHeight(100);
            _btnAbout.setPrefSize(100,100);
            _btnSalir = new Button();
            _btnSalir.setGraphic(_imgExit);
            _imgExit.setFitHeight(100);
            _imgExit.setFitWidth(100);
            _btnSalir.setPrefSize(100,100);
            _vbox = new VBox();
            _vboxTitle = new VBox();
            _h1 = new HBox();
            _h2 = new HBox();
            // Accione
            _btnTest.setOnAction(event -> {
            conexion.crearConexion();
        });
            _lblTitle.setStyle("-fx-font-size: 32px; -fx-font-weight: 900;");

            _btnSalir.setOnAction(event -> {System.exit(0);});
            _vboxTitle.getChildren().addAll(_lblTitle, _mainImg);
            _vboxTitle.setAlignment(Pos.TOP_CENTER);
            _h1.getChildren().addAll(_btnOrden, _btnLista);
            _h1.setAlignment(Pos.CENTER);
            _h1.setSpacing(10);
            _h2.getChildren().addAll(_btnAbout, _btnSalir);
            _h2.setAlignment(Pos.CENTER);
            _h2.setSpacing(10);
            _vbox.getChildren().addAll(_vboxTitle, _h1, _h2);
            _vbox.setAlignment(Pos.TOP_CENTER);
            _vbox.setSpacing(10);
            _stkMain.getChildren().addAll(_vbox);
            primaryStage.setTitle("Sistema Taquería");
            primaryStage.setScene(new Scene(_stkMain, 300, 275));
            primaryStage.show();
            primaryStage.setMaximized(true);
            primaryStage.getIcons().add(ico);
            primaryStage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });
        }
}

 */
