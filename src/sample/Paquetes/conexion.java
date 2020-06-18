/*package sample.Paquetes;

import java.sql.*;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class conexion {
    static int lport = 80;
    static String rhost = "localhost";
    static int rport = 3306;

    private static final String user = "usuario_tacosotso"; private static final String pwd = "tacosotso"; private static final String db = "taqueria_otso";
    public static Connection con;

    public static void go() {//Puente Para VM
        String user = "sdf2020";
        String password = "SDFsdfSDF+1";
        String host = "192.168.1.70";
        int port=22;
        try
        {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
        }
        catch(Exception e){System.err.print(e);}
    }

    public static void crearConexion(){
        try{
            go();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + rhost +":" + lport + "/"+db + "?user=" +user + "&password=" + pwd + "&useSSL=false");
            System.out.println("Base de datos encontrada");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Conexion Sin SSL o SSH(Nativo)
    /*
    private static final String host = "localhost";
    public static Connection con;

    public static void crearConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Estamos abriendo el socket hacia el SGBD
            con = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+db+"?useSSL=false",user,pwd);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
*/