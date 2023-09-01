package student.examples.orm.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ARepository {

    private final String url = "jdbc:postgresql://localhost/e_shop?user=postgres&password=1234&ssl=false";
    protected Connection conn;


    public ARepository() {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
