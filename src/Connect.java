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

    public void createStorage(){
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

    public void deleteStorage(){
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


    public void addGoods(){
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

    public void deleteByName(){
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

    public void showByName(){
        System.out.println("Name of storage");
        String storageName = scan.next();
        String SQL = "SELECT '"+storageName+"' FROM exam.sklad;";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            ResultSet rs = pstmt.executeQuery();
            displayActor(rs);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        menu.menu();
    }

    private void displayActor(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("'+storageName+'") + "\n");
        }
        menu.menu();
    }
}
