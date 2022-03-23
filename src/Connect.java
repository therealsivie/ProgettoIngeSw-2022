import structure.Configuratore;
import structure.Fruitore;
import structure.Utente;

import java.sql.*;

public class Connect {
    String url = null;

    public Connect(){
        this.url = "jdbc:sqlite:/home/alessandro/ProgettoIngeSw-2022/Data.db";
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createNewTable(String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "	id integer PRIMARY KEY,\n"
                + "	username text NOT NULL,\n"
                + "	password text NOT NULL,\n"
                + " firstlogin boolean NOT NULL,\n"
                + " usertype boolean NOT NULL\n"
                + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String username, String password, boolean firstLogin, boolean userType) {
        String sql = "INSERT INTO utenti(username,password,firstlogin,usertype) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setBoolean(3, true);
            //se l'utente è un configuratore TRUE, se è un fruitore FALSE
            pstmt.setBoolean(4, true);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateCredentials(Utente utente){
        String sql = "UPDATE utenti SET username = ? , "
                + "password = ? , "
                + "firstlogin = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, utente.getUsername());
            pstmt.setString(2, utente.getPassword());
            pstmt.setBoolean(3, false);
            pstmt.setInt(4, utente.getId());
            utente.setFirstLogin(false);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Utente checkLogin(String usr, String pwd) {

        String sql = "SELECT id, username, password, firstlogin, usertype  FROM utenti"
                + " WHERE username = ?"
                + " AND password = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, usr);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();
            int id = rs.getInt("id");
            String user = rs.getString("username");
            String pass = rs.getString("password");
            //conn.close();
            if(rs.getBoolean("usertype")) {
                Configuratore c = new Configuratore(id, user, pass);
                c.setFirstLogin(true);
                return c;
            }
            else {
                Fruitore f = new Fruitore(id, user, pass);
                f.setFirstLogin(true);
                return f;
            }
        }
        catch (SQLException e){
            return null;
        }
    }
}