package student.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws SQLException {
        
        String url = "jdbc:postgresql://localhost/e_shop?user=postgres&password=1234&ssl=false";
        Connection conn = DriverManager.getConnection(url);

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Currency Id, Name, Char Code and the Ration:");
        int id = in.nextInt();
        in.nextLine();
        String name = in.nextLine();
        String charCode = in.next();
        float ratio = in.nextFloat();
        
        PreparedStatement st = conn.prepareStatement("INSERT INTO currencies VALUES (" + id + ", '" + name + "', '" + charCode + "', " + ratio + ");");
        st.executeUpdate(); // or st.execute();
        st.close();

    }
}
