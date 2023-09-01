package student.examples.orm.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import student.examples.orm.entities.Product;

public class ProductRepository extends ARepository {
    
    private static ProductRepository instance;

    private ProductRepository() {
        super();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }
    
    public void create(Product product) {
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("INSERT INTO products VALUES ("
            + product.getId() + ", '" + product.getName() + "', '" + product.getImage() + "');");
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }

    public Product readById(int id) {
        Statement st;
        Product product = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products WHERE id=" + id + ";");
            rs.next();
            product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> readByMultipleId(int[] idArray) {
        List<Product> list = new ArrayList<>();
        Statement st;
        try {
            st = conn.createStatement();
            for (int i = 0; i < idArray.length; i++) {
                Product product = readById(idArray[i]);
                list.add(product);
                }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> readByMultipleId(List<Integer> idList) {
        List<Product> list = new ArrayList<>();
        Statement st;
        try {
            st = conn.createStatement();
            for (int i = 0; i < idList.size(); i++) {
                Product product = readById(idList.get(i).intValue());
                list.add(product);
                }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> readAll() {
        List<Product> list = new ArrayList<>();
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products;");
            while (rs.next() == true) {
            Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3));
            list.add(product);
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return list;
    }

    public void update(Product product) {
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("UPDATE products SET name='" + product.getName() + "', image='" + product.getImage() + "' WHERE id=" + product.getId() + ";");
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }

    public void updateName(Product product, String newName) {
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("UPDATE products SET name='" + newName + "' WHERE id=" + product.getId() + ";");
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }

    public void updateImage(Product product, String newImage) {
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("UPDATE products SET image='" + newImage + "' WHERE id=" + product.getId() + ";");
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }

    public void delete(Product product) {
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("DELETE FROM products WHERE id=" + product.getId() + ";");
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }

    public void deleteMultiple(List<Product> productList) {
        Statement st;
        try {
            st = conn.createStatement();
            for (int i = 0; i < productList.size(); i++) {
                st.executeUpdate("DELETE FROM products WHERE id=" + productList.get(i).getId() + ";");
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }
    
}
