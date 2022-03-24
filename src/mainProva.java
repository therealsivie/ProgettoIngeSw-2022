import model.ExitException;
import model.Login;
import model.OptionList;
import mylib.MyMenu;

public class mainProva {
    public static void main(String[] args) {
        Login login = new Login();
        OptionList option = new OptionList();
        MyMenu menu = new MyMenu("Programma Configuratore");
        boolean logged = false;
        boolean exited = false;
        menu.setVoci(option.getOptionList(logged));
        do{
            int scelta = menu.scegli();
            try{
                logged = option.getOption(scelta).getAction().execute();
                if(!logged){
                    System.out.println("Login Fallito...");
                }
            }
            catch (ExitException e) {
                exited = true;
                System.out.println(e.getMessage());
            }
        }while(!logged && !exited);
        while (!exited){
            menu.setVoci(option.getOptionList(logged));
            int scelta = menu.scegli();
            try {
                option.getOption(scelta).getAction().execute();
            } catch (ExitException e) {
                exited = true;
            }
        }
    }
}
