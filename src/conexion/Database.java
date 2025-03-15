
/*package conexion;
public class Database {
    
  */  


/*public class ConexionSQLServer {

    // URL de la base de datos
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TuNombreBaseDatos;encrypt=true;trustServerCertificate=true;";

    // Credenciales de la base de datos
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contrasena";

    public static void main(String[] args) {
        Connection conexion = null;
        
        try {
            // Registrar el driver de SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Conectar a la base de datos
            conexion = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos SQL Server.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de SQL Server.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error en la conexión a la base de datos.");
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                    System.out.println("Conexión cerrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

}
*/