import controller.Logout;
import model.Action;
import model.ExitException;
import controller.LoginConf;
import model.Option;
import model.OptionList;
import mylib.MyMenu;

public class MainConfiguratore {
    public static void main(String[] args) {
        OptionList option = new OptionList();
        MyMenu menu = new MyMenu("Programma Configuratore");
        boolean logged = false;
        do{
            menu.setVoci(option.getConfOptionList(logged));
            int scelta = menu.scegli();
            try {
                Action action =option.getOption(scelta).getAction();
                if (action instanceof LoginConf || option.getOption(scelta).getAction() instanceof Logout)
                    logged = action.execute();
                else
                    action.execute();
            } catch (ExitException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }while(true);
    }
}
