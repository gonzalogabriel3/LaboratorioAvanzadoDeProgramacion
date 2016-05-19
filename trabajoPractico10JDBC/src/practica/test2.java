package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test2 {

	public static void recibeBaseDeDatos(String nombreBaseDeDatos) throws ClassNotFoundException{
		try{
		
		Class.forName("org.postgresql.Driver");
		Connection conexion;
		String usuario;
		String contraseña;
		
		usuario="postgres";
		contraseña="postgres";
		
		conexion=DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/"+nombreBaseDeDatos,usuario,contraseña);
		
		//Medio o transporte por donde se envia la consulta//
		PreparedStatement prstm;
		prstm=conexion.prepareStatement("SELECT cliente.nombre AS nom_cliente,cliente.apellido AS ape_cliente,"
				+ "cliente.numero_documento AS dni_cliente,SUM(total) AS total_compras,COUNT(venta.cliente) AS cant_compras "
				+ "FROM cliente INNER JOIN venta ON cliente.numero_documento=venta.cliente WHERE venta.vendedor=? "
				+ "GROUP BY cliente.apellido,cliente.nombre,cliente.numero_documento ORDER BY cliente.apellido");
		
		prstm.setString(1,"LOP");
		
		
		ResultSet resultadoConsulta=prstm.executeQuery();
		
		while(resultadoConsulta.next()){
			String nombreClienteAux;
			String apellidoClienteAux;
			float totalAux;
			int cantidadAux;
			int dniAux;
			
			nombreClienteAux=resultadoConsulta.getString("nom_cliente");
			totalAux=resultadoConsulta.getFloat("total_compras");
			cantidadAux=resultadoConsulta.getInt("cant_compras");
			apellidoClienteAux=resultadoConsulta.getString("ape_cliente");
			dniAux=resultadoConsulta.getInt("dni_cliente");
					
			System.out.println("Cliente: "+apellidoClienteAux+" "+nombreClienteAux);
			System.out.println("DNI: "+dniAux);
			System.out.println("Cantidad de compras realizadas: "+cantidadAux);
			System.out.println("Monto total en compras realizadas: $"+totalAux);
			System.out.println("------------------------------------------------------------");
		}
		
		conexion.close();
		resultadoConsulta.close();
		prstm.close();

	}catch(SQLException e){
		System.out.println("Ocurrio un error/Codigo de Error: "+e.getErrorCode()+"/Detalle: "+e.getMessage());
	}
	
}
	
	public static void main(String[] args) throws ClassNotFoundException {
		recibeBaseDeDatos("venta");
	}
		
	
}

