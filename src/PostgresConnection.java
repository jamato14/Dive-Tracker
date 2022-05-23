import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PostgresConnection {
    String url;
    long port;
    String dbname;
    String user;
    String password;

    PostgresConnection(String url, long port, String dbname, String user, String password)
    {
        this.url = "jdbc:postgresql://"+url;
        this.port = port;
        this.dbname = dbname;
        this.user = user;
        this.password = password;
    }

    public String getDBName()
    {
        return this.dbname;
    }
    public long getPort()
    {
        return this.port;
    }
    public String getUrl()
    {
        return this.url;
    }
    public String getUser()
    {
        return this.user;
    }
    

    public Connection createConnection()
    {
        Connection conn = null;
        try 
        {
            Class.forName("org.postgresql.Driver");
            String fullUrl = this.url+":"+Long.toString(this.port)+"/"+this.dbname;
            conn = DriverManager.getConnection(fullUrl, this.user, this.password);
            System.out.println("Database Connection Successful");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return null;
        }
        return conn;
    }

    public void closeConnection(Connection conn)
        {
            try 
            {
                conn.close();
                System.out.println("Closing Database Connection");
            }
            catch (Exception e)
            {
                System.err.println("error could not close database connection");
            }

        }

    public ResultSet query(String query)
    {
        ResultSet results = null;
        try
        {
            Connection conn = createConnection();
            Statement state = conn.createStatement();
            results = state.executeQuery(query);
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error processing SQL QUERY: " +query);
            e.printStackTrace();
        }
        return results;
    }

    public boolean insert(String query)
    {
        int result = 0;
        try
        {
            Connection conn = createConnection();
            Statement state = conn.createStatement();
            result = state.executeUpdate(query);
        }
        catch (SQLException e)
        {
            System.out.println("Error processing SQL INSERT: "+query);
            e.printStackTrace();
        }
        if (result > 0)
            return true;
        return false;
    }

    public static void main(String args[])
    {
        PostgresConnection c = new PostgresConnection("localhost", 5432, "postgres", "postgres", "postgres");
        Connection conn = c.createConnection();
        //c.closeConnection();
    }

}