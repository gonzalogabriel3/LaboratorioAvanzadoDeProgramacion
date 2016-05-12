package ejercicio4;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class tp2 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	
	Class.forName("org.postgresql.Driver");
	
	String url="jdbc:postgresql://127.0.0.1:5432/venta";
	String usuario="postgres";
	String contraseña="postgres";
	
	Connection con=DriverManager.getConnection(url, usuario, contraseña);
	
	DatabaseMetaData metaDatos=con.getMetaData();
	
	ResultSet rs=metaDatos.getTables("ventas", null, "%", null);
	
	while(rs.next()){
		String catalogo=rs.getString(1);
		String tabla = rs.getString(3);
		   System.out.println("TABLA=" + catalogo + "." + tabla);
		
	}
	
	}
}
