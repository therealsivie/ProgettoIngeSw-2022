import java.sql.*;

public class Connect {
    String url = null;

    public Connect(String url){
        this.url = url;
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

    public void createNewTable(Connection conn, String tableName) {

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "	id integer PRIMARY KEY,\n"
                + "	username text NOT NULL,\n"
                + "	password text NOT NULL,\n"
                + " firstlogin boolean NOT NULL,\n"
                + " usertype boolean NOT NULL\n"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String nomeTabella, String username, String password, boolean firstLogin, boolean userType) {
        String sql = "INSERT INTO "+nomeTabella+ "(username,password,firstlogin,usertype) VALUES(?,?,?,?)";

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

    public void selectAll(String nometabella, String usr, String pwd){
        String sql = "SELECT id, username, password FROM utenti"
                + " WHERE username = ?"
                + " AND password = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, usr);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("username") + "\t" +
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}