import model.ExitException;
import controller.Login;
import model.OptionList;
import mylib.MyMenu;

public class mainProva {
    public static void main(String[] args) {
        Login login = new Login();
        OptionList option = new OptionList();
        MyMenu menu = new MyMenu("Programma Configuratore");
        boolean logged = false;
        menu.setVoci(option.getOptionList(logged));
        do {
            int scelta = menu.scegli();
            try {
                logged = option.getOption(scelta).getAction().execute();
                if (!logged) {
                    System.out.println("Login Fallito...");
                }
            } catch (ExitException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        } while (!logged);
        menu.setVoci(option.getOptionList(logged));
        int scelta = menu.scegli();
        try {
            option.getOption(scelta).getAction().execute();
        } catch (ExitException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
