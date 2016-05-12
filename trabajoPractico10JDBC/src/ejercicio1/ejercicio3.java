package ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ejercicio3 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		
		String url="jdbc:postgresql://127.0.0.1:5432/venta";
		String usuario="postgres";
		String contraseña="postgres";
		
		Connection con=DriverManager.getConnection(url, usuario, contraseña);
		
		Statement stm=con.createStatement();
		
		String consultaSql="SELECT localidad.nombre,SUM(venta.total)AS total_localidad FROM localidad INNER JOIN "
				+ "cliente ON localidad.codigo=cliente.localidad INNER JOIN venta ON venta.cliente=cliente.numero_documento "
				+ "GROUP BY localidad.nombre ORDER BY localidad.nombre";
		
		ResultSet rs3=stm.executeQuery(consultaSql);
		
		while(rs3.next()){
			String nombre=rs3.getString("nombre");
			float totalLocalidad=rs3.getFloat("total_localidad");
			
			System.out.println(nombre+"--/$"+totalLocalidad);
			
		}
		
	}

}
