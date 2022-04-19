import mylib.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import db.Connect;

public class Administrator {
    final private static String url = "jdbc:sqlite:/home/alessandro/ProgettoIngeSw-2022/Data.db";

    public static void main(String[] args) {
        Connect db = new Connect(args[0]);
        db.createNewTable("Utenti");
        String password = getNewPassword(10);
        String utente = InputDati.leggiStringaNonVuota("Inserisci nome Utente da creare: ");
        System.out.println("password dell'utente "+ utente+ ": " + password);
        writePasswordToFile(utente, password);
        db.insert(utente, password, true, true);
    }

    public static String getNewPassword(int passwordSize) {
        //alphabet contiene tutti i possibili caratteri che comporranno la Password
        String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz?!<>-*[]{}/";
        int alphabetLength = alphabet.length();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passwordSize; i++) {
            // Scelgo una delle lettere dell'alfabeto.
            int randomIndexCharInAlphabet = (int) (Math.random() * alphabetLength);
            password.append(alphabet.charAt(randomIndexCharInAlphabet));
        }
        return password.toString();
    }

    public static void writePasswordToFile(String username, String password){
        try(PrintWriter out = new PrintWriter(username)) {
            out.println(password);
        } catch (FileNotFoundException e) {
            System.out.println("File non creato");
        }
    }
}