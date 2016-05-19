package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

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
		Statement stm;
		stm=conexion.createStatement();
		
		String consultaSql="SELECT nombre,apellido,numero_documento FROM cliente ORDER BY apellido";
		
		ResultSet resultadoConsulta=stm.executeQuery(consultaSql);
		
		while(resultadoConsulta.next()){
			String nombreaux;
			String apellidoaux;
			int dniaux;
			
			nombreaux=resultadoConsulta.getString("nombre");
			apellidoaux=resultadoConsulta.getString("apellido");
			dniaux=resultadoConsulta.getInt("numero_documento");
			
			System.out.println("Apellido: "+apellidoaux+"[/]Nombre: "+nombreaux+"[/]Dni: "+dniaux);
		}
		
		conexion.close();
		resultadoConsulta.close();
		stm.close();

	}catch(SQLException e){
		System.out.println("Ocurrio un error/Codigo de Error: "+e.getErrorCode()+"/Detalle: "+e.getMessage());
	}
	
}
	
	public static void main(String[] args) throws ClassNotFoundException {
		recibeBaseDeDatos("venta");
	}
	
	
	
	
}
