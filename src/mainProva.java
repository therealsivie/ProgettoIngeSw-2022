public class mainProva {

    public static void main(String[] args){
        Login login = new Login();
        boolean logged;
        do{
            logged = login.doLogin();
            if(!logged){
                System.out.println("Login Fallito: riprova");
            }
        }while(!logged);
        //prossima cosa da fare
        System.out.println("MENU.....");
    }
}
