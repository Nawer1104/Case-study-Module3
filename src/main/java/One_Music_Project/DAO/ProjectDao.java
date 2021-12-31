package One_Music_Project.DAO;

import One_Music_Project.Model.Artist;
import One_Music_Project.Model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/one_music_project?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Hoangnam12@";

    private static final String SELECT_ALL_SONG = "SELECT * FROM one_music_project.song;";
    private static final String SELECT_ALL_ARTIST = "SELECT * FROM one_music_project.artist;";
    private static final String SEARCH_ARTIST_BY_CHAR = "SELECT * FROM one_music_project.artist WHERE aname like ?;";
    private static final String SEARCH_SONG_BY_AID = "SELECT * FROM one_music_project.song where aid = ?;";
    private static final String GET_ARTIST_BY_AID = "SELECT * FROM one_music_project.artist WHERE aid = ?;";


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

    public List<Artist> selectAllArtists() {
        List<Artist> artists = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( SELECT_ALL_ARTIST );) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("aid");
                String description = rs.getString("adescription");
                String name = rs.getString("aname");
                String pic = rs.getString("apic");
                artists.add(new Artist(id,description, name, pic));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return artists;
    }

    public List<Artist> searchArtistByName(String likeName) {
        List<Artist> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( SEARCH_ARTIST_BY_CHAR );) {
            preparedStatement.setString(1, "%" + likeName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("aid");
                String description = rs.getString("adescription");
                String name = rs.getString("aname");
                String img = rs.getString("apic");
                list.add(new Artist(id, description, name, img));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }

    public List<Song> songByAid(String aid) {
        List<Song> songs = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( SEARCH_SONG_BY_AID );) {
            preparedStatement.setString(1, aid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("sid");
                String name = rs.getString("sname");
                String link = rs.getString("slink");
                String img = rs.getString("simg");
                int repeat = rs.getInt("srepeat");
                int aId = rs.getInt("aid");
                int cid = rs.getInt("cid");
                songs.add(new Song(id, name, link, img, repeat, aId, cid));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return songs;
    }

    public Artist getArtistById(String aId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_ARTIST_BY_AID );) {
            preparedStatement.setString(1, aId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("aid");
                String description = rs.getString("adescription");
                String name = rs.getString("aname");
                String pic = rs.getString("apic");
                return new Artist(id,description, name, pic);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public static void main(String[] args) {
      //TEST HERE
        ProjectDao projectDao = new ProjectDao();
        List<Song> list = projectDao.songByAid("9");
        for (Song x : list) {
            System.out.println(x);
        }
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
