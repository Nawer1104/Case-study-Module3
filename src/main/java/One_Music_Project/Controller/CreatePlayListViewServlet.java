package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.PlayList;
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

@WebServlet(name = "CreatePlayListViewServlet", urlPatterns = "/createPlaylistView")
public class CreatePlayListViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserAccount user = (UserAccount) session.getAttribute("acc");
        if (user != null) {
            int userId = user.getUid();
            List<PlayList> playListList = projectDao.getPlayListNameByUserId(userId);
            request.setAttribute("playList", playListList);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("createPlaylist.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
