package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ConnectionManager {
	private static final int INITIAL_SIZE_POOL = 10;
    private static List<Connection> connections = new ArrayList<>(INITIAL_SIZE_POOL);
    private static String jdbcUrl = "jdbc:postgresql://localhost/elective";
    private static String user = "postgres";
    private static String password = "oleg";
    
    
    static {
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
    	
    	for (int i = 0; i < INITIAL_SIZE_POOL; i++) {
    		Connection connection;
			try {
				connection = DriverManager.getConnection(jdbcUrl, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
				break;
			}
    		connections.add(connection);
    	}
    }
    
    public static void updateConnections(String url, String usr, String pswd) {
    	user = usr;
    	jdbcUrl = url;
    	password = pswd;
    	
    	for (int i = 0; i < INITIAL_SIZE_POOL; i++) {
    		Connection connection;
			try {
				connection = DriverManager.getConnection(jdbcUrl, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
				break;
			}
    		connections.set(i, connection);
    	}
    }
    
    private static Connection createConnection() throws SQLException {
    	try {
    		return DriverManager.getConnection(jdbcUrl, user, password);
        } catch (SQLException e) {
            throw e;
        }
    }

    public synchronized static Connection getConnection() throws SQLException {
    	if (connections.size() > 0) {
    		return connections.remove(0);
    	}
    	
        try {
			return createConnection();
		} catch (SQLException e) {
			throw e;
		}
    }
    
    public static void releaseConnection(Connection connection) {
    	connections.add(connection);
    }
}
