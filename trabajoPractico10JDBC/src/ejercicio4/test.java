package ejercicio4;

import java.sql.*;
import java.util.logging.*;

public class test {

    static Connection conexion;
    static Statement st;
    
    
    
    static DatabaseMetaData metadatos;
    static ResultSetMetaData rsmetadatos;


    public static void recibeDatabase(String nombreDB){
        

        try {
            //REALIZANDO CONEXON
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/"+nombreDB, "postgres", "postgres");
            
            /*DatabaseMetaData
             * Obteniendo Informacion sobre una base de datos
            */
            st = conexion.createStatement();
            
            System.out.println("Obteniendo Informacion sobre una base de datos");
            metadatos = conexion.getMetaData();
            //Nombre de db
            System.out.println("Nombre de base de datos ventas" + metadatos.getDatabaseProductName());
            //Version de db
            System.out.println("Version de de base de datos: "+metadatos.getDatabaseProductVersion());
            //Nombre de driver
            System.out.println("Nombre de Driver: "+metadatos.getDriverName());
            //Version de driver
            System.out.println("Version de Driver: "+metadatos.getDriverVersion());
            //Tablas
            
            ResultSet rst;
            ResultSet rsc;
            
            rst = metadatos.getTables(null, null, null, null);
            String tabla="";
            while(rst.next()){
                tabla = rst.getObject(3).toString();
                System.out.println("Nombre de Tabla: "+tabla);
                //primary key si existe
                ResultSet rsp = metadatos.getPrimaryKeys(null, null, tabla);
                if(rsp.next())
                    System.out.println("Primary Key: "+rsp.getObject(4));
                rsp.close();
                //columnas y tipos
                rsc = metadatos.getColumns(null, null, tabla, null);
                while(rsc.next()){
                    System.out.println("    Columna "+rsc.getString(4));
                    System.out.println("    Tipo "+rsc.getInt(5));
                }
                rsc.close();
            }
            rst.close();

            
            /*ResultSetMetaData
             * Obteniendo Informacion sobre una consulta con un ResultSet
            */
            
            System.out.println("\nObteniendo Informacion sobre una consulta con un ResultSet");
            ResultSet rs = st.executeQuery("select * from venta");
            rsmetadatos =  rs.getMetaData();
            //obteniendo numero de columnas
            int col = rsmetadatos.getColumnCount();
            System.out.println("Columnas: "+col);
            for(int i=1;i<=col;i++){
                System.out.println("Nombre de Columa: "+rsmetadatos.getColumnName(i));
                System.out.println("Tipo de Dato: "+rsmetadatos.getColumnTypeName(i));
                System.out.println("Pertenece a la tabla: "+rsmetadatos.getTableName(i)+"\n");
            }
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String args[]){
        
    
        recibeDatabase("venta");
        
    }        

}