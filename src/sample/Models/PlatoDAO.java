package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlatoDAO {

    int idPlato;
    String nombrePlato;
    String descPlato;
    double precioPlato;

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getDescPlato() {
        return descPlato;
    }

    public void setDescPlato(String descPlato) {
        this.descPlato = descPlato;
    }

    public double getPrecioPlato() {
        return precioPlato;
    }

    public void setPrecioPlato(double precioPlato) {
        this.precioPlato = precioPlato;
    }

    private PlatoDAO objP;

    public ObservableList<PlatoDAO> selAllPlatos(){
        ObservableList<PlatoDAO> listaPlato = FXCollections.observableArrayList();
        String query = "SELECT * FROM tbl_plato ORDER BY idPlato;";
        try{
            PreparedStatement stmt = Conexion.conn.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                objP = new PlatoDAO();
                objP.setIdPlato(res.getInt("idPlato"));
                objP.setNombrePlato(res.getString("nombrePlato"));
                objP.setDescPlato(res.getString("descPlato"));
                objP.setPrecioPlato(res.getDouble("precioPlato"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return listaPlato;
    }

    public void getPlatoById(){
        String query = "SELECT * FROM tbl_plato WHERE idPlato = "+idPlato;
        try{
            PreparedStatement stmt = Conexion.conn.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                nombrePlato = res.getString("nombrePlato");
                descPlato = res.getString("descPlato");
                precioPlato = res.getDouble("preciPlato");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return nombrePlato+", "+descPlato;
    }
}
