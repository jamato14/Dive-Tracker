import java.sql.Connection;
import java.sql.DriverManager;


public class PostgresConnection {
    Connection conn;
    String url;
    int port;
    String dbname;
    String user;
    String password;

    PostgresConnection(String url, int port, String dbname, String user, String password)
    {
        this.conn = null;
        this.url = "jdbc:postgresql://"+url;
        this.port = port;
        this.dbname = dbname;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection()
    {
        return this.conn;
    }
    public String getDBName()
    {
        return this.dbname;
    }
    public int getPort()
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
    

    public boolean createConnection()
    {
        try 
        {
            Class.forName("org.postgresql.Driver");
            String fullUrl = this.url+":"+Integer.toString(this.port)+"/"+this.dbname;
            this.conn = DriverManager.getConnection(fullUrl, this.user, this.password);
            System.out.println("Database Connection Successful");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        return true;
    }

    public void closeConnection()
        {
            try 
            {
                this.conn.close();
                System.out.println("Closing Database Connection");
            }
            catch (Exception e)
            {
                System.err.println("error could not close database connection");
            }

        }

    public static void main(String args[])
    {
        PostgresConnection c = new PostgresConnection("localhost", 5432, "postgres", "postgres", "postgres");
        c.createConnection();
        Connection con = c.getConnection();
        c.closeConnection();
    }

}