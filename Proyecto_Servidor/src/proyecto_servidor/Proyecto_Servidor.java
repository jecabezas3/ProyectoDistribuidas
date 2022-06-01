/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_servidor;

import Clases.Cobrador;
import Conexion.Conexion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author pilli
 */
public class Proyecto_Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while(true){
            ServerSocket servidor = new ServerSocket(1410);
            Socket clienteNuevo = servidor.accept();
            
            ObjectInputStream entrada  = new ObjectInputStream(clienteNuevo.getInputStream());
            String mensaje = (String) entrada.readObject();
            System.out.println(""+mensaje);
            String[] parts = mensaje.split(";");
            String user = parts[0];
            String pass = parts[1];
            String proc = parts[2];
            String sql = parts[3];
            switch(proc)
            {
                case "login":
                    String validacion = validationUser(user, pass);
                    ObjectOutputStream respuesta = new ObjectOutputStream(clienteNuevo.getOutputStream());
                    respuesta.writeObject(validacion);
                    break;
                case "datosCobrador":
                    ArrayList<Cobrador> Cobras = infoCobrador();
                    System.out.println(Cobras.get(2).getNombre_cobrador());
                    for (int i = 0; i <Cobras.size(); i++) {
                        System.out.println(Cobras.get(i).getId_cobrador() + " Id Case");
                        System.out.println(Cobras.get(i).getCedula_cobrador() + " Cedula");
                        System.out.println(Cobras.get(i).getNombre_cobrador() + " Nombre");
                        System.out.println(Cobras.get(i).getDireccion_cobrador() + " Direccion Case");
                    }
                    ObjectOutputStream res = new ObjectOutputStream(clienteNuevo.getOutputStream());
                    res.writeObject(Cobras);
                    break;
                case "agregarCobrador":
                    agregarCobrador(sql);
                    break;
            }
            clienteNuevo.close();
            servidor.close();
        }
    }
    
    public static String validationUser(String user,String pass){
        String result="0";
        Connection conn = Conexion.ConnectDB();
        String sql = "Select * from usuario where user=? and contraseña=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,user);
            pst.setString(2,pass);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                result="1";
            }else{
                result="0";
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return result;
    }
    
     public static ArrayList<Cobrador> infoCobrador(){
        ArrayList<Cobrador> cobradores=new ArrayList();
        Connection conn = Conexion.ConnectDB();
        String sql = "Select * from cobrador";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){  
                Cobrador nuevoCobra = new Cobrador();
                nuevoCobra.setId_cobrador(rs.getInt(1));
                nuevoCobra.setCedula_cobrador(rs.getString(2));
                nuevoCobra.setNombre_cobrador(rs.getString(3));
                nuevoCobra.setDireccion_cobrador(rs.getString(4));
                cobradores.add(nuevoCobra);
            }
    
         } catch (Exception e) {
             System.out.println("Error");

         }
     
          for(int i = 0; i<cobradores.size(); i++)
            {
                System.out.println(cobradores.get(i).getId_cobrador()+ " Id Info");
                System.out.println(cobradores.get(i).getCedula_cobrador()+ " Cedula");
                System.out.println(cobradores.get(i).getNombre_cobrador()+ " Nombre");
                System.out.println(cobradores.get(i).getDireccion_cobrador()+ " Direccion");
            }
         return cobradores;
     }
     
     public static String agregarCobrador(String sql){
        Connection conn = Conexion.ConnectDB();
        String result = "";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            
                result="1";

         } catch (Exception e) {
             System.out.println("Error agregar");

         }
        return result;
     }
     
}
     


        
