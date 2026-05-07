
import business.UserController;
import core.Helper;
import entity.User;
import view.DashboardUI;
import view.LoginUI;

public class App {
    public static void main(String[] args) throws Exception {
        Helper.setThemes();
        UserController userController = new UserController();
        User user = userController.findByLogin("rmzn@patika.dev", "123123");
        DashboardUI dashboardUI = new DashboardUI(user);
    }
}
