import model.Login;
import model.OptionList;
import mylib.MyMenu;

public class mainProva {

    public static void main(String[] args){
        Login login = new Login();
        OptionList option = new OptionList();
        MyMenu menu = new MyMenu("Prova");
        boolean logged = false;
        menu.setVoci(option.getOptionList(logged));
        do{
            int scelta = menu.scegli();
            logged = option.getOption(scelta).getAction().execute();
            if(!logged){
                System.out.println("Login Fallito: riprova");
            }
        }while(!logged);
        menu.setVoci(option.getOptionList(logged));
        int scelta = menu.scegli();
        option.getOption(scelta).getAction().execute();
    }
}
