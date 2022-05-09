import controller.Logout;
import controller.Action;
import controller.ExitException;
import controller.LoginConf;
import model.OptionList;
import utility.MyMenu;

public class MainConfiguratore {
    public static void main(String[] args) {
        OptionList option = new OptionList();
        MyMenu menu = new MyMenu();
        boolean logged = false;
        String titolo;
        do {
            menu.setVoci(option.getFruitOptionList(logged));
            if (logged) titolo = "Programma Configuratore Loggato";
            else titolo = "Programma Configuratore";
            menu.setTitolo(titolo);
            menu.setVoci(option.getConfOptionList(logged));
            int scelta = menu.scegli();
            try {
                Action action = option.getOption(scelta).getAction();
                if (action instanceof LoginConf || action instanceof Logout) logged = action.execute();
                else action.execute();
            } catch (ExitException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        } while (true);
    }
}
