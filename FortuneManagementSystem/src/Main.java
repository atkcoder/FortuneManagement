import jju.soft.Host;
import jju.soft.LoginFrame;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        LoginFrame Login = new LoginFrame();
        Login.login();
        new Host();
    }
}