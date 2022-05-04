package utility;

import model.user.Configuratore;
import model.user.Fruitore;
import model.user.Utente;

import java.sql.*;

public class DbConnect {
    String url = "jdbc:sqlite:/Data.db";
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

    public void insertUser(String username, String password, boolean firstLogin, boolean userType) {
        String sql = "INSERT INTO utenti(username,password,firstlogin,usertype) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            //se primo login TRUE, altrimenti FALSE
            pstmt.setBoolean(3, firstLogin);
            //se l'utente è un configuratore TRUE, se è un fruitore FALSE
            pstmt.setBoolean(4, userType);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Utente checkLogin(String usr, String pwd) {

        String sql = "SELECT id, username, password, firstlogin, usertype FROM utenti"
                + " WHERE username = ?"
                + " AND password = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usr);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();
            int id = rs.getInt("id");
            String user = rs.getString("username");
            String pass = rs.getString("password");
            boolean firstLogin = rs.getBoolean("firstlogin");
            boolean userType = rs.getBoolean("usertype");
            //conn.close();
            if (rs.getBoolean("usertype")) {
                Configuratore c = new Configuratore(id, user, pass);
                c.setFirstLogin(firstLogin);
                return c;
            } else {
                Fruitore f = new Fruitore(id, user, pass);
                f.setFirstLogin(firstLogin);
                return f;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean updateCredentials(Utente utente, String newUser, String newPass) {
        boolean checked = this.checkNewUser(newUser);
        if (checked) {
            String sql = "UPDATE utenti SET username = ? , "
                    + "password = ? , "
                    + "firstlogin = ? "
                    + "WHERE username = ?";
            utente.setFirstLogin(false);
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, newUser);
                pstmt.setString(2, newPass);
                pstmt.setBoolean(3, utente.getFirstLogin());
                pstmt.setString(4, newUser);
                pstmt.executeUpdate();
                return checked;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return checked;
    }

    public boolean checkNewUser(String newUser) {
        String sql = "SELECT username FROM utenti"
                + " WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newUser);
            ResultSet rs = pstmt.executeQuery();
            rs.getString("username");
            return false;
        } catch (SQLException e) {
            return true;
        }
    }
}