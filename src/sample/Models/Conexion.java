package sample.Models;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static int lport = 80;

    private static final String hostdb = "127.0.0.1";
    private static final String userdb = "tacoadmin";
    private static final String passdb = "tacosalpastor";
    private static final String userdbmysql = "usuario_tacosotso";
    private static final String passdbmysql = "tacosotso";
    private static final String dbname = "taqueriadb";
    private static final int dbport = 3306;
    public static Connection conn;

    public static void crearConexionMariaDB(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://"+hostdb+":"+dbport+"/"+dbname+"?useSSL=false", userdb, passdb);
            System.out.println("Conexion establecida");

        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Conexion rechazada");
            e.printStackTrace();
        }
    }

    public static void go() {//Puente Para VM
        String user = "sdf2020";
        String password = "SDFsdfSDF+1";
        String host = "192.168.58.134";
        int port=22;
        try
        {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            int assinged_port=session.setPortForwardingL(lport, hostdb, dbport);
            System.out.println("localhost:"+assinged_port+" -> "+hostdb+":"+dbport);
        }
        catch(Exception e){System.err.println(e);}
    }

    public static void crearConexionMySQL(){
        try{
            go();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + hostdb +":" + lport + "/"+dbname + "?user=" +userdbmysql + "&password=" + passdbmysql + "&useSSL=false");
            System.out.println("Base de datos encontrada");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Conexion rechazada");
        }
    }
}
