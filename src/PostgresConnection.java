import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;


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

    public ResultSet queryUser(String query)
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

    public boolean insertUser(String name, String email, String password)
    {
        int result = 0;
        try
        {
            String query = "insert into users (name, email, password) values (?,?,?)";
            Connection conn = createConnection();
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, name);
            state.setString(2, email);
            state.setString(3, password);
            result = state.executeUpdate();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error processing SQL insert User");
            e.printStackTrace();
        }
        if (result > 0)
            return true;
        return false;
    }

    public boolean deleteUser(int id)
    {
        int result = 0;
        try
        {
            String query = "delete from users where id=?";
            Connection conn = createConnection();
            PreparedStatement state = conn.prepareStatement(query);
            state.setInt(1, id);
            result = state.executeUpdate();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error processing SQL insert User");
            e.printStackTrace();
        }
        if (result > 0)
            return true;
        return false;
    }

    public ResultSet queryDive(int number)
    {
        ResultSet results = null;
        try
        {
            Connection conn = createConnection();
            String query = "select * from dive"; //where id = ?";
            PreparedStatement state = conn.prepareStatement(query);
            //state.setInt(1, number);
            results = state.executeQuery();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error processing SQL QUERY dive");
            e.printStackTrace();
        }
        return results;
    }
    
    public boolean insertDive(Optional<String> location, Optional<String> exposure, Optional<Double> air, Optional<Double> water, 
                            Optional<Double> weight, Optional<String> guide, Optional<Integer> sgas, Optional<Integer> egas, Optional<String> gas_type)
        {
            ArrayList<String> insert = new ArrayList<String>();
            ArrayList<String> val = new ArrayList<String>();
            if (location != null)
            {
                insert.add("location");
                val.add("?");
            }
            if (exposure != null)
            {
                insert.add("exposure");
                val.add("?");
            }
            if (air != null)
            {
                insert.add("air_temp");
                val.add("?");
            }
            if (water != null)
            {
                insert.add("water_temp");
                val.add("?");
            }
            if (weight != null)
            {
                insert.add("weight");
                val.add("?");
            }
            if (guide != null)
            {
                insert.add("guide");
                val.add("?");
            }
            if (sgas != null)
            {
                insert.add("start_gas_pressure");
                val.add("?");
            }
            if (egas != null)
            {
                insert.add("end_gas_pressure");
                val.add("?");
            }
            if (gas_type != null)
            {
                insert.add("gas_type");
                val.add("?");
            }
            String query = "insert into dive (" + String.join(", ", insert) + ") values (" + String.join(", ", val) +")";
            try
            {
                Connection conn = createConnection();
                PreparedStatement state = conn.prepareStatement(query);
                int i = 1;
                if (location != null)
                {
                    state.setString(i, location.get());
                    i++;
                }
                if (exposure != null)
                {
                    state.setString(i, exposure.get());
                    i++;
                }
                if (air != null)
                {
                    state.setDouble(i, air.get());
                    i++;
                }
                if (water != null)
                {
                    state.setDouble(i, water.get());
                    i++;
                }
                if (weight != null)
                {
                    state.setDouble(i, weight.get());
                    i++;
                }
                if (guide != null)
                {
                    state.setString(i, guide.get());
                    i++;
                }
                if (sgas != null)
                {
                    state.setInt(i, sgas.get());
                    i++;
                }
                if (egas != null)
                {
                    state.setInt(i, egas.get());
                    i++;
                }
                if (gas_type != null)
                {
                    state.setString(i, gas_type.get());
                    i++;
                }

                int result = state.executeUpdate();
                if (result > 0)
                    return true;
            }
            catch (SQLException e)
            {
                System.out.println("Error processing SQL insert dive");
                e.printStackTrace();
            }
            
            System.out.println(query);

            return false;
        }

    
    public boolean deleteDive(int number)
    {
        int result = 0;
        try
        {
            String query = "delete from dive where number=?";
            Connection conn = createConnection();
            PreparedStatement state = conn.prepareStatement(query);
            state.setInt(1, number);
            result = state.executeUpdate();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error processing SQL delete dive: "+  number);
            e.printStackTrace();
        }
        if (result > 0)
            return true;
        return false;
    }


    public ResultSet queryDiveGroup(int number)
    {
        ResultSet results = null;
        try
        {
            Connection conn = createConnection();
            String query = "select * from dive_group //where id = ?";
            
            PreparedStatement state = conn.prepareStatement(query);
            state.setInt(1, number);
            results = state.executeQuery();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error processing SQL QUERY dive");
            e.printStackTrace();
        }
        return results;
    }

    public boolean insertDiveGroup(Optional<ArrayList<Integer>> numbers, Optional<String> name)
    {
        try
        {
            Connection conn = createConnection();
            ArrayList<String> insert = new ArrayList<String>();
            ArrayList<String> val = new ArrayList<String>();
            if (numbers != null)
            {
                insert.add("dive_numbers");
                val.add("?");
            }
            if (name != null)
            {
                insert.add("name");
                val.add("?");
            }
            String query = "insert into dive_group (" + String.join(", ", insert) + ") values "+ "( "+String.join(", ", val) + ")";
            PreparedStatement state = conn.prepareStatement(query);
            int i = 1;
            if (numbers != null)
            {
                Array arr = conn.createArrayOf("INT", numbers.get().toArray());
                state.setArray(i, arr);
                i++;
            }
            if (name != null)
            {
                state.setString(i, name.get());;
                i++;
            }
            int result = state.executeUpdate();
            if (result > 0)
                return true;


        }
        catch (SQLException e)
        {
            System.out.println("Error inserting row to DiveGroup");
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDiveGroup(int number)
    {
        int result = 0;
        try
        {
            String query = "delete from dive_group where group_number=?";
            Connection conn = createConnection();
            PreparedStatement state = conn.prepareStatement(query);
            state.setInt(1, number);
            result = state.executeUpdate();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error processing SQL delete dive: "+  number);
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