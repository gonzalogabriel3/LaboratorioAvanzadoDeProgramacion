package ejercicio1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ejercicio1 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		Class.forName("org.postgresql.Driver");
		
		String url="jdbc:postgresql://127.0.0.1:5432/venta";
		String usuario="postgres";
		String contraseña="postgres";
		
		Connection con=DriverManager.getConnection(url, usuario, contraseña);
		
		Statement stmt=con.createStatement();
		
		String consultaSql="SELECT vendedor.nombre,AVG(total) AS tot,COUNT(total) AS cant FROM vendedor "
				+"INNER JOIN venta ON vendedor.codigo=venta.vendedor GROUP BY vendedor.nombre";
		
		ResultSet rs1=stmt.executeQuery(consultaSql);
		
		System.out.println("VENDEDOR          |PROMEDIO DE VENTAS| TOTAL DE VENTAS");
		while(rs1.next()){
			String nombreVendedor=rs1.getString("nombre");
			float totalVendedor=rs1.getLong("tot");
			int cantidadVendedor=rs1.getInt("cant");
			
			System.out.println(nombreVendedor+"         "+totalVendedor+"     "+"           "+cantidadVendedor);
			
		}
		
		
		
		
	}

}
