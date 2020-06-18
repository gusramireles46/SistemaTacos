package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BebidaDAO {
    int idBebida;
    String nombreBebida;
    String descBebida;
    double precioBebida;

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public String getNombreBebida() {
        return nombreBebida;
    }

    public void setNombreBebida(String nombreBebida) {
        this.nombreBebida = nombreBebida;
    }

    public String getDescBebida() {
        return descBebida;
    }

    public void setDescBebida(String descBebida) {
        this.descBebida = descBebida;
    }

    public double getPrecioBebida() {
        return precioBebida;
    }

    public void setPrecioBebida(double precioBebida) {
        this.precioBebida = precioBebida;
    }

    private BebidaDAO objB;

    public ObservableList<BebidaDAO> selAllBebidas (){
        ObservableList<BebidaDAO> listaBebidas = FXCollections.observableArrayList();
        try{
            String query = "SELECT * FROM tbl_bebidas ORDER BY idBebida";
            PreparedStatement stmt = Conexion.conn.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                objB = new BebidaDAO();
                objB.setIdBebida(res.getInt("idBebida"));
                objB.setNombreBebida(res.getString("nombreBebida"));
                objB.setDescBebida(res.getString("descBebida"));
                objB.setPrecioBebida(res.getDouble("precioBebida"));
            }
        }catch(Exception e){e.printStackTrace();}
        return listaBebidas;
    }

    public void getBebidaById(){

    }
}
