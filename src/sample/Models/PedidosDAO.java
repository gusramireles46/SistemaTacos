package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidosDAO {
    int idPedido, idPlato, cantidadPlato, idBebida, cantidadBebida, idMesa;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public int getCantidadPlato() {
        return cantidadPlato;
    }

    public void setCantidadPlato(int cantidadPlato) {
        this.cantidadPlato = cantidadPlato;
    }

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public int getCantidadBebida() {
        return cantidadBebida;
    }

    public void setCantidadBebida(int cantidadBebida) {
        this.cantidadBebida = cantidadBebida;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    private PedidosDAO objetoP = null;

    public ObservableList<PedidosDAO> selAllPedidos(){
        ObservableList<PedidosDAO> listaP = FXCollections.observableArrayList();
        String query = "SELECT * FROM tbl_pedido";
        try {
            PreparedStatement stmt = Conexion.conn.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                setIdPedido(res.getInt("idPedido"));
                setIdPlato(res.getInt("idPlato"));
                setCantidadPlato(res.getInt("cantidadPlato"));
                setIdBebida(res.getInt("idBebida"));
                setCantidadBebida(res.getInt("cantidadBebida"));
                setIdMesa(res.getInt("idMesa"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaP;
    }
}
