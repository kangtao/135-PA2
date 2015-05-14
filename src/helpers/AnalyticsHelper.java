package helpers;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class AnalyticsHelper 
{
	    public static List<defaultCustomerWithProducts> defaultList() {
	        Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;

	        List<defaultCustomerWithProducts> defaultList = new ArrayList<defaultCustomerWithProducts>();
	        try {
	            try {
	                conn = HelperUtils.connect();
	            } catch (Exception e) {
	                System.err.println("Internal Server Error. This shouldn't happen.");
	                return new ArrayList<defaultCustomerWithProducts>();
	            }
	            stmt = conn.createStatement();
	            String createTemp1 = "CREATE temp table temp1 as SELECT uid,pid, sum(quantity*price) as total FROM sales group by uid,pid";
	            stmt.executeUpdate(createTemp1);
	            String createTemp2 = "Create temp table temp2 as SELECT users.id as uid, users.name as cname, products.id as pid, products.name as pname FROM users,products GROUP BY users.name,products.name,uid,pid";
	            stmt.executeUpdate(createTemp2);
	            String query = "SELECT temp2.cname, temp2.pname, temp1.total FROM temp1 RIGHT JOIN temp2 ON temp1.uid = temp2.uid and temp1.pid = temp2.pid";
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	                String cname = rs.getString(1);
	                String pname = rs.getString(2);
	                Integer totalMoney = rs.getInt(3);
	                defaultList.add(new defaultCustomerWithProducts(cname, pname, totalMoney));
	            }
	            return defaultList;
	        } catch (Exception e) {
	            System.err.println("Some error happened!<br/>" + e.getLocalizedMessage());
	            return new ArrayList<defaultCustomerWithProducts>();
	        } finally {
	            try {
	                stmt.close();
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	


}
