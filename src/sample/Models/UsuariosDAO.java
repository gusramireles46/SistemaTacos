package sample.Models;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.text.Text;
import sample.Views.Empleado;
import sample.Main;

import java.sql.*;
import java.util.Optional;

public class UsuariosDAO {
    private int cveUsuario;
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private byte puesto;
    private Connection con;
    //private Main pane = new Main();

    public int getCveUsuario() {
        return cveUsuario;
    }

    public void setCveUsuario(int cveUsuario) {
        this.cveUsuario = cveUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getPuesto() {
        return puesto;
    }

    public void setPuesto(byte puesto) {
        this.puesto = puesto;
    }

    public UsuariosDAO(){
        con = Conexion.conn;
    }

    public void validarUsuario(String usr, String pwd){
        UsuariosDAO objU = new UsuariosDAO();
        ResultSet res;
        try {
            String consulta = "SELECT * FROM tbl_usuario WHERE username = '"+usr+"';";
            PreparedStatement stmt = con.prepareStatement(consulta);
            res = stmt.executeQuery();
            BoxBlur blur = new BoxBlur(3,3,3);
            if (res.next()){
                if(res.getString("pwd").equals(pwd)){
                    setCveUsuario((res.getInt("cveUsuario")));
                    setNombre(res.getString("nombre"));
                    setApellido(res.getString("apellido"));
                    setUsername(res.getString("username"));
                    setPuesto(res.getByte("puesto"));
                    //System.out.println("Bienvenido "+getNombre()+" "+getApellido()+", usted es: "+getClvPuesto());
                    if(getPuesto() != 1){
                        Main.vbox.setEffect(blur);
                        Alert bienvenido = new Alert(Alert.AlertType.CONFIRMATION);
                        bienvenido.setTitle("Mensaje de bienvenida");
                        bienvenido.setHeaderText("Bienvenido/a "+getNombre());
                        bienvenido.setContentText("Accesar al sistema");
                        Optional<ButtonType> result = bienvenido.showAndWait();
                        if(result.get() == ButtonType.OK){
                            new Empleado();
                            Main.vbox.setEffect(null);
                        } else {
                            System.out.println("Finalizado");
                            Main.vbox.setEffect(null);
                        }
                    } else {
                        System.out.println("Redirigiendo a admin");
                    }
                } else {
                    JFXDialogLayout content = new JFXDialogLayout();
                    content.setHeading(new Text("Error De Acceso"));
                    content.setBody(new Text("Contraseña Incorrecta"));
                    JFXButton button = new JFXButton("Cerrar");
                    JFXDialog dialog = new JFXDialog(Main.pane, content, JFXDialog.DialogTransition.CENTER);
                    content.getStyleClass().add("");
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            dialog.close();
                            //Main.vbox.setEffect(null);
                        }
                    });
                    content.setActions(button);
                    dialog.show();
                    /*
                    Alert objAlerta = new Alert(Alert.AlertType.ERROR);
                    objAlerta.setTitle("Error de acceso");
                    objAlerta.setHeaderText("Contraseña incorrecta");
                    objAlerta.setContentText("Intente nuevamente");
                    objAlerta.showAndWait();
                    */
                }
            } else {
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Error De Acceso"));
                content.setBody(new Text("Usuario Incorrecto"));
                JFXButton button = new JFXButton("Cerrar");
                JFXDialog dialog = new JFXDialog(Main.pane, content, JFXDialog.DialogTransition.CENTER);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        dialog.close();
                        //Main.vbox.setEffect(null);
                    }
                });
                //Main.vbox.setEffect(blur);
                content.setActions(button);
                dialog.show();

                /*
                Alert objAlerta = new Alert(Alert.AlertType.ERROR);
                objAlerta.setTitle("Error de acceso");
                objAlerta.setHeaderText("El usuario no existe");
                objAlerta.setContentText("Por favor intente nuevamente");
                objAlerta.showAndWait();
                Main.vbox.setEffect(null);

                 */
            }
        } catch (Exception e){

        }
    }
}
