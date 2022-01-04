package One_Music_Project.DAO;

import One_Music_Project.Model.*;

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
    private static final String SELECT_USER_BY_ACCOUNT_AND_PASSWORD = "SELECT * FROM one_music_project.useraccount where uaccount = ? and upassword = ?";
    private static final String CHECK_ACCOUNT_EXIST = "SELECT * FROM one_music_project.useraccount where uaccount = ?;";
    private static final String CREATE_NEW_ACCOUNT = "INSERT INTO `one_music_project`.`useraccount` (`uname`, `uaccount`, `upassword`) VALUES (?, ?, ?);";
    private static final String GET_USER = "SELECT * FROM one_music_project.useraccount where uid = ?;";
    private static final String EDIT_USER_FREE = "UPDATE `one_music_project`.`useraccount` SET `uname` = ?, `upassword` = ?, `uimg` = ? WHERE (`uid` = ?);";
    private static final String UPDATE_PREMIUM = "UPDATE `one_music_project`.`useraccount` SET `uname` = ?, `upassword` = ?, `uimg` = ?, `ispremium` = 1 WHERE (`uid` = ?);";
    private static final String GET_TOTAL_SONG_NUMBERS = "SELECT count(*) FROM one_music_project.song;";
    private static final String GET_TOTAL_SONG_NUMBERS_BY_CATEGORY = "SELECT count(*) FROM one_music_project.song where cid = ?;";
    private static final String OFFSET_QUERY = "SELECT * FROM  one_music_project.song LIMIT ?, 6;";
    private static final String OFFSET_QUERY_BY_CATEGORIES = "SELECT * FROM  one_music_project.song where cid = ? LIMIT ?, 6;";
    private static final String DELETE_SONG_SQL = "DELETE FROM one_music_project.song WHERE sid = ?;";
    private static final String GET_SONG_BY_SID = "SELECT * from one_music_project.song where sid =?";
    private static final String EDIT_SONG_BY_SID = "UPDATE `one_music_project`.`song` SET `sname` = ?, `slink` = ?, `simg` = ?, `aid` = ? WHERE (`sid` = ?);";
    private static final String ADD_NEW_SONG = "INSERT INTO `one_music_project`.`song` (`sname`, `slink`, `simg`, `aid`) VALUES (?, ?, ?, ?);";
    private static final String TOTAL_SONG_NUMBERS_HAVING_LETTER = "SELECT count(*) FROM  one_music_project.song where sname like ?;";
    private static final String GET_ALL_SONG_HAVING_LETTER = "SELECT * FROM  one_music_project.song where sname like ? LIMIT ?,6;";
    private static final String CREATE_PLAY_LIST = "INSERT INTO `one_music_project`.`playlist` (`pname`, `uid`) VALUES (?, ?);";
    private static final String GET_PLAY_LIST_BY_USER_ID = "SELECT * FROM one_music_project.playlist where uid = ?;";
    private static final String GET_SONG_OF_PLAY_LIST = "SELECT song.sid, song.sname, song.slink, song.simg, song.srepeat, song.aid, song.cid FROM song \n" +
            "join playlistsong on song.sid = playlistsong.sid\n" +
            "join playlist on playlistsong.pid = playlist.pid\n" +
            "where playlist.pid = ?;";
    private static final String GET_PLAY_LIST_NAME_BY_PID = "SELECT playlist.pname from playlist where playlist.pid = ?;";
    private static final String REMOVE_SONG_FROM_PLAYLIST = "DELETE FROM `one_music_project`.`playlistsong` WHERE playlistsong.sid = ? and playlistsong.pid = ?;";
    private static final String ADD_SONG_TO_PLAY_LIST = "INSERT INTO `one_music_project`.`playlistsong` (`pid`, `sid`) VALUES (?, ?);";
    private static final String GET_SID_FROM_PLAY_LIST_SONG = "select playlistsong.sid from playlistsong where playlistsong.pid = ?;";
    private static final String ALL_CATEGORY = "SELECT * FROM one_music_project.category;";
    private static final String SEARCH_SONG_BY_CID = "SELECT * FROM one_music_project.song where cid = ?;";
    private static final String GET_CATEGORY_BY_CID = "SELECT * FROM one_music_project.category where cid = ?;";
    private static final String GET_CATEGORY_NAME_BY_CID = "select cname from one_music_project.category where cid = ?;";


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

    public List<Category> selectAllCategory() {
        List<Category> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( ALL_CATEGORY );) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("cid");
                String name = rs.getString("cname");
                list.add(new Category(id, name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }

    public List<Song> getAllSongByCategory(String categoryId) {
        List<Song> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( SEARCH_SONG_BY_CID );) {
            preparedStatement.setString(1, categoryId);
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

    public Category getCategoryById(String categoryId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_BY_CID);) {
            preparedStatement.setString(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("cid");
                String name = rs.getString("cname");
                return new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Song> getAllSongOfPlayList(String pid) {
        List<Song> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_SONG_OF_PLAY_LIST );) {
            preparedStatement.setString(1, pid);
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

    public List<PlayList> getPlayListNameByUserId(int userId) {
        List<PlayList> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_PLAY_LIST_BY_USER_ID );) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pname = rs.getString("pname");
                int uid = rs.getInt("uid");
                list.add(new PlayList(pid, uid, pname));
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

    public UserAccount login(String userAccount, String userPassword) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( SELECT_USER_BY_ACCOUNT_AND_PASSWORD );) {
            preparedStatement.setString(1, userAccount);
            preparedStatement.setString(2, userPassword);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("uid");
                String name = rs.getString("uname");
                String account = rs.getString("uaccount");
                String password = rs.getString("upassword");
                String img = rs.getString("uimg");
                double balance = rs.getDouble("ubalance");
                int isPremium = rs.getInt("ispremium");
                int isUser = rs.getInt("isuser");
                int isAdmin = rs.getInt("isadmin");
                return new UserAccount(id, name, account, password, img, balance, isPremium, isUser, isAdmin);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public UserAccount checkUserExist(String userName) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT_EXIST);) {
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("uid");
                String name = rs.getString("uname");
                String account = rs.getString("uaccount");
                String password = rs.getString("upassword");
                String img = rs.getString("uimg");
                double balance = rs.getDouble("ubalance");
                int isPremium = rs.getInt("ispremium");
                int isUser = rs.getInt("isuser");
                int isAdmin = rs.getInt("isadmin");
                return new UserAccount(id, name, account, password, img, balance, isPremium, isUser, isAdmin);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public void createNewAccount(String userName, String userAccount, String userPassword) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( CREATE_NEW_ACCOUNT );) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userAccount);
            preparedStatement.setString(3, userPassword);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void createNewPlaylist(String uid, String playListName) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( CREATE_PLAY_LIST );) {
            preparedStatement.setString(1, playListName);
            preparedStatement.setString(2, uid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }


    public UserAccount getUserAccount(String uid) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_USER );) {
            preparedStatement.setString(1, uid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("uid");
                String name = rs.getString("uname");
                String account = rs.getString("uaccount");
                String password = rs.getString("upassword");
                String img = rs.getString("uimg");
                double balance = rs.getDouble("ubalance");
                int isPremium = rs.getInt("ispremium");
                int isUser = rs.getInt("isuser");
                int isAdmin = rs.getInt("isadmin");
                return new UserAccount(id, name, account, password, img, balance, isPremium, isUser, isAdmin);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public void editUser(String name, String pass, String image, String id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( EDIT_USER_FREE );) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, image);
            preparedStatement.setString(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updatePrimeum(String name, String pass, String image, String id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( UPDATE_PREMIUM );) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, image);
            preparedStatement.setString(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public int getTotalSongNumbers() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_TOTAL_SONG_NUMBERS );) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return 0;
    }

    public int getTotalSongNumbersByCategory(String cid) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_TOTAL_SONG_NUMBERS_BY_CATEGORY );) {
            preparedStatement.setString(1, cid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return 0;
    }

    public int getSidFromPlayListSong(String sid) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_SID_FROM_PLAY_LIST_SONG );) {
            preparedStatement.setString(1, sid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return 0;
    }

    public String getPlayListNameByPid(String pid) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_PLAY_LIST_NAME_BY_PID );) {
            preparedStatement.setString(1, pid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public String getCategoryListNameByCid(String cid) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_CATEGORY_NAME_BY_CID );) {
            preparedStatement.setString(1, cid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public int getTotalSongNumbersHavingLetter(String txt) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( TOTAL_SONG_NUMBERS_HAVING_LETTER );) {
            preparedStatement.setString(1, "%" + txt + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return 0;
    }

    public List<Song> pagingSongs(int index) {
        List<Song> songList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( OFFSET_QUERY );) {
            preparedStatement.setInt(1, (index - 1)*6);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("sid");
                String name = rs.getString("sname");
                String link = rs.getString("slink");
                String img = rs.getString("simg");
                int repeat = rs.getInt("srepeat");
                int aid = rs.getInt("aid");
                int cid = rs.getInt("cid");
                songList.add(new Song(id, name, link, img, repeat, aid, cid));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return songList;
    }

    public List<Song> pagingSongsByCategory(String categoryId, int index) {
        List<Song> songList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( OFFSET_QUERY_BY_CATEGORIES );) {
            preparedStatement.setString(1, categoryId);
            preparedStatement.setInt(2, (index - 1)*6);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("sid");
                String name = rs.getString("sname");
                String link = rs.getString("slink");
                String img = rs.getString("simg");
                int repeat = rs.getInt("srepeat");
                int aid = rs.getInt("aid");
                int cid = rs.getInt("cid");
                songList.add(new Song(id, name, link, img, repeat, aid, cid));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return songList;
    }

    public List<Song> pagingSongsWithSearch(String txt, int index) {
        List<Song> songList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_ALL_SONG_HAVING_LETTER );) {
            preparedStatement.setString(1, "%" + txt + "%");
            preparedStatement.setInt(2, (index - 1)*6);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("sid");
                String name = rs.getString("sname");
                String link = rs.getString("slink");
                String img = rs.getString("simg");
                int repeat = rs.getInt("srepeat");
                int aid = rs.getInt("aid");
                int cid = rs.getInt("cid");
                songList.add(new Song(id, name, link, img, repeat, aid, cid));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return songList;
    }

    public void deleteSong(String id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( DELETE_SONG_SQL );) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void removeSongFromPlayList(String sid, String pid) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( REMOVE_SONG_FROM_PLAYLIST );) {
            preparedStatement.setString(1, sid);
            preparedStatement.setString(2, pid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void addSongToPlayList(String pid, String sid) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( ADD_SONG_TO_PLAY_LIST );) {
            preparedStatement.setString(1, pid);
            preparedStatement.setString(2, sid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Song getSongBySid(String tid) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_SONG_BY_SID );) {
            preparedStatement.setString(1, tid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("sid");
                String name = rs.getString("sname");
                String link = rs.getString("slink");
                String img = rs.getString("simg");
                int repeat = rs.getInt("srepeat");
                int aid = rs.getInt("aid");
                int cid = rs.getInt("cid");
                return (new Song(id, name, link, img, repeat, aid, cid));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public void editSong(String name, String link, String img, String aid, String sid) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( EDIT_SONG_BY_SID );) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, link);
            preparedStatement.setString(3, img);
            preparedStatement.setString(4, aid);
            preparedStatement.setString(5, sid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void addSong(String name, String link, String img, String aid) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( ADD_NEW_SONG );) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, link);
            preparedStatement.setString(3, img);
            preparedStatement.setString(4, aid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void main(String[] args) {
      //TEST HERE
        ProjectDao projectDao = new ProjectDao();
        List<PlayList> list = projectDao.getPlayListNameByUserId(5);
        for (PlayList x : list) {
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
