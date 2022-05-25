import java.io.FileReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class App {
    public static void main(String[] args) throws Exception {
        Object obj = new JSONParser().parse(new FileReader("db.json"));
        JSONObject js = (JSONObject) obj;
        String url = (String) js.get("url");
        long port = (long) js.get("port");        
        String dbname = (String) js.get("dbname");        
        String user = (String) js.get("user");        
        String password = (String) js.get("password");        
        PostgresConnection c = new PostgresConnection(url, port, dbname, user, password);

        //Connection con = c.createConnection();
        //Optional<String> loc = Optional.ofNullable("locate");
        //c.insertDive(loc, null, Optional.ofNullable(86.1), null, null, null, null, null, null);
        ResultSet dives = c.queryDive(1);
        while (dives.next())
        {
            System.out.println(dives.getDouble("air_temp"));
        }
        c.deleteDive(1);
        ArrayList<Integer> n = new ArrayList<Integer>();
        n.add(1);
        n.add(2);
        Optional<ArrayList<Integer>> nums = Optional.of(n);
        c.insertDiveGroup(nums, Optional.of("Sample"));
        c.deleteDiveGroup(1);
    }
}
