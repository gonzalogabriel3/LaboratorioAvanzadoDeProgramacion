package ejercicio4;

import java.sql.*;
import java.util.logging.*;

import org.apache.log4j.chainsaw.Main;

public class test {

    static Connection conexion;
    static Statement st; 
    static DatabaseMetaData metadatos;
    static ResultSetMetaData rsmetadatos;


    public static void recibeDatabase(String nombreDB) throws ClassNotFoundException{
        

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
            
            String[] tablas={"TABLE"};
            int contador;
            ResultSet rs=metadatos.getTables(null, null, null, tablas);
            
            while(rs.next()){
            	String nombreTabla=rs.getString(3);
            	System.out.print("Nombre de la Tabla: "+nombreTabla);	
            	PreparedStatement stmt=conexion.prepareStatement("SELECT * FROM "+nombreTabla);
            	ResultSet rsaux=stmt.executeQuery();
            	ResultSetMetaData rsmdaux=rsaux.getMetaData();
            	int numeroColumnas=rsmdaux.getColumnCount();
            	System.out.println("|~|Cantidad de Columnas:"+numeroColumnas);
            	
            	for(int i=1;i<=numeroColumnas; i++){
            	System.out.println("Columna:"+rsmdaux.getColumnLabel(i)+"|~|Tipo: "+rsmdaux.getColumnTypeName(i));
            	
            	
            	}
            	
            } 
                  
        }catch(SQLException e){
        	System.out.println(e.getMessage());
        }
     
       
     }
 
    public static void main(String[] args) throws ClassNotFoundException {
		recibeDatabase("venta");
	}
    
} 