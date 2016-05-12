package ejercicio3;

/*3) Escriba un método que reciba una fecha y el código de un vendedor y
* liste las ventas indicando en una columna si la venta fue en efectivo con tarjeta.
* */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class tp {
    
    public static void codigoYfecha(String codigos, String fechas)throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver"); // nos sirve para cargar driver correctamente sirve para trabajar con servlets
    //paso1     crear conexion
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/venta", "postgres", "postgres");
        System.out.println(conn.getClass().getCanonicalName());
        //paso2 crear objeto preparestatment
        PreparedStatement pstmt = conn.prepareStatement("select vd.nombre as nombre, v.fecha as fecha, v.tarjeta as tarjeta from vendedor vd "
                + "INNER join venta v "
                + "on vd.codigo = ? "
                + "AND  v.fecha = ?");
        //paso 3 ejecutar sql con los select*from ....
        
        pstmt.setString(1, codigos);
        pstmt.setDate(2, java.sql.Date.valueOf(fechas));
        //paso 4  guardar en el resultset
        ResultSet rs = pstmt.executeQuery();
    
        //paso 5 recorrer
        while (rs.next()){
            System.out.print( rs.getString("nombre") +"     | " + rs.getString("fecha") +"    | ");
        if (rs.getString("tarjeta") == null) {
            System.out.println(" EFECTIVO ");
        }else {
            System.out.println(" TARJETA ");
        }
 
        }
        
        conn.close();
        
        
    }
    
    
    
    public static void main(String[] args)  {
        
        try {
 
            codigoYfecha("LOP", "2011-01-01");    
        } catch (ClassNotFoundException c) {
            
            System.err.println("No encontro la libreria");
            System.out.println(c.getMessage());
        }catch (SQLException e) {
            System.out.println("No encontro la base de datos");
        }
                
    }
}