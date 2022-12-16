package database;

import java.sql.Connection; // Part of JavaSE with is installed under plugins
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for manipulating with the database
 */
public class DatabaseManipulator {

    /**
     * Method for connecting to the database. The method will return a
     * connection object if connection is established or null if no connection
     * could be made
     *
     * @return A connection object or null
     */
    public static Connection getConnection() {

        Connection conn = null;

        // Try to load the Driver. This is done for compatibility reasons
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Try to get the connection up and running
            try {
                conn = DriverManager.getConnection(DatabaseDetails.host,
                        DatabaseDetails.username,
                        DatabaseDetails.password);
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("AddOn conn: " + conn);
      
        // Return the connection object
        return conn;
    }

    /**
     * Method for making a SQL query to the database.
     *
     * @param sqlStatement The SQL statement as a String
     * @param queryable An Queryable object that handles the result set from the
     * database (@see {@link Queryable})
     */
    public static void executeQueryWithResultSet(String sqlStatement, Queryable queryable) {
        // Get the connection object
        System.out.println("Begynder AddOn executeQueryWithResultSet");
        Connection conn = getConnection();

        Statement stmt = null;
        ResultSet rs = null;

        if (conn != null) {
            try {

                stmt = conn.createStatement();
                System.out.println("AddOn stmt: " + stmt);
                rs = stmt.executeQuery(sqlStatement);
                System.out.println("AddOn rs: " + rs);

                // Do something about the result
                queryable.processResultSet(rs);

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            } finally {
                // it is a good idea to release
                // resources in a finally{} block
                // in reverse-order of their creation
                // if they are no-longer needed

                // Close result set
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                        // handle any errors
                        System.out.println("SQLException: " + sqlEx.getMessage());
                        System.out.println("SQLState: " + sqlEx.getSQLState());
                        System.out.println("VendorError: " + sqlEx.getErrorCode());
                    }

                    rs = null;
                }

                // Close statement
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) {
                        // handle any errors
                        System.out.println("SQLException: " + sqlEx.getMessage());
                        System.out.println("SQLState: " + sqlEx.getSQLState());
                        System.out.println("VendorError: " + sqlEx.getErrorCode());
                    }

                    stmt = null;
                }
            }
        }
    }

    /**
     * Overloading method for execute query with result set. This method only
     * uses the Queryable object
     *
     * @param queryable A Queryable object
     */
    public static void executeQueryWithResultSet(Queryable queryable) {
        executeQueryWithResultSet(queryable.returnSqlQuery(), queryable);
    }

}
