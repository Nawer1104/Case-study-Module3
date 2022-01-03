package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.PlayList;
import One_Music_Project.Model.Song;
import One_Music_Project.Model.UserAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userPlayListViewServlet", urlPatterns = "/userPlayListView")

public class userPlayListViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");

        List<Song> list = projectDao.getAllSongOfPlayList(pid);
        String playListName = projectDao.getPlayListNameByPid(pid);
        HttpSession session = request.getSession();
        UserAccount user = (UserAccount) session.getAttribute("acc");
        if (user != null) {
            int userId = user.getUid();
            List<PlayList> playListList = projectDao.getPlayListNameByUserId(userId);
            request.setAttribute("playList", playListList);
        }

        request.setAttribute("listSongs", list);
        request.setAttribute("pid", pid);
        request.setAttribute("playListName", playListName);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userPlayList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
