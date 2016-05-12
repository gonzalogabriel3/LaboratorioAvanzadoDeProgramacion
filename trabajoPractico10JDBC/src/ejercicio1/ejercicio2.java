package ejercicio1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ejercicio2 {
	
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			Class.forName("org.postgresql.Driver");
			
			String url="jdbc:postgresql://127.0.0.1:5432/venta";
			String usuario="postgres";
			String contraseña="postgres";
			
			Connection con=DriverManager.getConnection(url, usuario, contraseña);
			
			Statement stm=con.createStatement();
			
			String consultaSql="SELECT cliente.nombre,SUM(venta.total)AS total_compras FROM cliente INNER JOIN venta ON cliente.numero_documento=venta.cliente "
					+ "GROUP BY cliente.nombre ORDER BY total_compras DESC LIMIT 10";
			
			ResultSet rs2=stm.executeQuery(consultaSql);
			
			while(rs2.next()){
				String nombreCliente=rs2.getString("nombre");
				float totalCompras=rs2.getFloat("total_compras");
				
				System.out.println(nombreCliente+"|"+totalCompras);
			}
			
			
		}

}
