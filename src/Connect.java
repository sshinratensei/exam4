import java.sql.*;
import java.util.Scanner;

public class Connect {
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "postgres";
    private final String password = "password";
    Scanner scan = new Scanner(System.in);
    Menu menu = new Menu();
    public Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("db successfully connected");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createStorage() throws SQLException {
        System.out.println("New storage name: ");
        String storageName = scan.next();
        String SQL = "ALTER TABLE exam.sklad ADD "+storageName+" VARCHAR";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(SQL);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        menu.menu();
    }

    public void deleteStorage() throws SQLException {
        System.out.println("Storage name: ");
        String storageName = scan.next();
        String SQL = "ALTER TABLE exam.sklad DROP COLUMN "+storageName+";";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(SQL);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        menu.menu();
    }


    public void addGoods() throws SQLException {
        System.out.println("Storage name: ");
        String storageName =scan.next();
        System.out.println("Goods name: ");
        String title =scan.next();
        String SQL = "INSERT INTO exam.sklad("+storageName+") values ('"+title+"');";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(SQL);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        menu.menu();
    }

    public void deleteByName() throws SQLException {
        System.out.println("Name of storage:");
        String storageName = scan.next();
        System.out.println("Name of goods:");
        String title = scan.next();
        String SQL = "DELETE FROM exam.sklad WHERE "+storageName+"='"+title+"';";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(SQL);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        menu.menu();
    }
//    public  void showData() throws SQLException {
//        String storageName = scan.next();
//        String SQL = "select "+storageName+" from exam.sklad";
//        try (Statement stmt = conn.createStatement()) {
//            ResultSet rs = stmt.executeQuery(SQL);
//            while (rs.next()) {
//                String goodsName = rs.getString(1);
//
//                System.out.println(goodsName);
//            }
//        } catch (SQLException ex){
//            System.out.println(ex.getMessage());
//        }
//    }
}
