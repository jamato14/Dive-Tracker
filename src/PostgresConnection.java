import java.sql.Connection;

import java.sql.DriverManager;

public class PostgresConnection {
    public static Connection createConnection(String dbPath)
    {
        Connection c = null;
        try 
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            System.out.println("Database Connection Successful");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return c;
    }

    public static void main(String args[])
    {
        Connection conn = PostgresConnection.createConnection("abc");
    }

}