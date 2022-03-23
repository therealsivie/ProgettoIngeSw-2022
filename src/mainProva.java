import mylib.*;
public class mainProva {

    public static void main(String[] args) {
        String user = InputDati.leggiStringaNonVuota("user: ");
        String pass = InputDati.leggiStringaNonVuota("Pass: ");
        Login.checkLogin(user, pass);
    }
}
