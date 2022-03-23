public class mainProva {

    public static void main(String[] args){
        Login login = new Login();
        boolean logged = false;
        do{
            logged = login.doLogin();
            if(!logged){
                System.out.println("Login Fallito\n");
            }
        }while(!logged);
    }
}
