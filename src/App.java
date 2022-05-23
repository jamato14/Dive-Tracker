public class App {
    public static void main(String[] args) throws Exception {
        Object obj = new JSONParser().parse(new FileReader("db.json"));
        JSONObject js = (JSONObject) obj;
        String url = (String) js.get("url");
        long port = (long) js.get("port");        
        String dbname = (String) js.get("dbname");        
        String user = (String) js.get("user");        
        String password = (String) js.get("password");        
        PostgresConnection c= new PostgresConnection(url, port, dbname, user, password);
        Connection con = c.createConnection();
        Statement state = con.createStatement();
        ResultSet rs = state.executeQuery("Select * from users;");
        while(rs.next())
        {
            System.out.print("ID: "+ rs.getInt("id"));
        }
    }
}
