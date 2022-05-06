import controller.LoginFruit;
import controller.Logout;
import controller.Action;
import controller.ExitException;
import model.OptionList;
import utility.MyMenu;

public class MainFruitore {
    public static void main(String[] args) {
        OptionList option = new OptionList();
        MyMenu menu = new MyMenu();
        boolean logged = false;
        String titolo;
        do{
            menu.setVoci(option.getFruitOptionList(logged));
            if(logged)
                titolo = "Programma Fruitore Loggato";
            else
                titolo = "Programma fruitore";
            menu.setTitolo(titolo);
            int scelta = menu.scegli();
            try {
                Action action =option.getOption(scelta).getAction();
                if(action instanceof LoginFruit || action instanceof Logout)
                    logged = action.execute();
                else
                    action.execute();
            } catch (ExitException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }while (true);
    }
}
