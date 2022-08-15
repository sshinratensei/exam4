import java.sql.Connection;
import java.sql.SQLException;

public class Menu {
    public void menu() throws SQLException {
        Connect connect = new Connect();
        System.out.println("what do you want to do? \n" +
                "1. create storage\n" +
                "2. delete storage\n" +
                "3. add goods\n" +
                "4. delete goods\n" +
                "5. show goods");
        int a = connect.scan.nextInt();
        switch (a) {
            case 1:
                connect.createStorage();
                break;
            case 2:
                connect.deleteStorage();
                break;
            case 3:
                connect.addGoods();
                break;
            case 4:
                connect.deleteByName();
                break;
//            case 5:
//                connect.showData();
//                break;
            default:
                break;
        }
    }
}