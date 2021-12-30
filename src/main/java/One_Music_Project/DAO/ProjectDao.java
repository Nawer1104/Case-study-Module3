package One_Music_Project.DAO;

import One_Music_Project.Model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/one_music_project?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Hoangnam12@";

    private static final String SELECT_ALL_SONG = "SELECT * FROM one_music_project.song;";

    public ProjectDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public List<Song> selectAllSong() {
        List<Song> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( SELECT_ALL_SONG );) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("sid");
                String name = rs.getString("sname");
                String link = rs.getString("slink");
                String img = rs.getString("simg");
                int repeat = rs.getInt("srepeat");
                int aid = rs.getInt("aid");
                int cid = rs.getInt("cid");
                list.add(new Song(id, name, link, img, repeat, aid, cid));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
