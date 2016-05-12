package ejercicio2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class cliente {
	
	//Atributos de un objeto de tipo cliente
	private String nombre;
	private String apellido;
	private int dni;
	private java.sql.Date fechaNacimiento;
	private String sexo;
	private String domicilio;
	private String localidad;
	
	//Constructor
	public cliente(String nombre,String apellido,int dni,java.sql.Date fechaNacimiento,String sexo,
			String domicilio,String localidad){
		this.nombre=nombre;
		this.apellido=apellido;
		this.dni=dni;
		this.fechaNacimiento=fechaNacimiento;
		this.sexo=sexo;
		this.domicilio=domicilio;
		this.localidad=localidad;
		
	}
	
	//Metodos getters
	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public int getDni() {
		return dni;
	}


	public java.sql.Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public String getSexo() {
		return sexo;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public String getLocalidad() {
		return localidad;
	}
	//Fin de metodos getters

	//main
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
			
		LinkedList<cliente> listaClientes=new LinkedList<cliente>();
		Class.forName("org.postgresql.Driver");
		
		String url="jdbc:postgresql://127.0.0.1:5432/venta";
		String usuario="postgres";
		String contraseña="postgres";
		
		Connection con=DriverManager.getConnection(url, usuario, contraseña);
		
		Statement stmt=con.createStatement();
		
		String consultaSql="SELECT c.apellido,c.nombre,c.numero_documento,c.fecha_nacimiento,c.sexo,c.domicilio,"
				+ "localidad.nombre AS nombreLocalidad FROM cliente c INNER JOIN localidad ON c.localidad=localidad.codigo " 
				+"ORDER BY c.apellido";
		
		ResultSet rs1=stmt.executeQuery(consultaSql);
		
		while(rs1.next()){
			String nom=rs1.getString("nombre");
			String ape=rs1.getString("apellido");
			int documento=rs1.getInt("numero_documento");
			java.sql.Date fech=rs1.getDate("fecha_nacimiento");
			String sex=rs1.getString("sexo");
			String dom=rs1.getString("domicilio");
			String loc=rs1.getString("nombreLocalidad");
			
			cliente clienteAux=new cliente(nom, ape, documento, fech, sex, dom, loc);
			listaClientes.add(clienteAux);
			
		}
		
		for (cliente cli : listaClientes) {
			System.out.println("Apellido:"+cli.getApellido()+"|~|Nombre:"+cli.getNombre()+"|~|DNI:"+cli.getDni()+
			"|~|Fecha de nacimiento:"+cli.getFechaNacimiento()+"|~|Sexo:"+cli.getSexo()+"|~|Domicilio:"+cli.getDomicilio()+"|~|Localidad:"+cli.getLocalidad());
		}
	}

}
