package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddNewSongFormServlet", urlPatterns = "/addSong")
public class AddNewSongFormServlet extends HttpServlet  {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = projectDao.selectAllCategory();
        request.setAttribute("categories", categories);
        List<Artist> artists = projectDao.selectAllArtists();
        request.setAttribute("listArtists", artists);
        HttpSession session = request.getSession();
        UserAccount user = (UserAccount) session.getAttribute("acc");
        if (user != null) {
            int userId = user.getUid();
            List<PlayList> playListList = projectDao.getPlayListNameByUserId(userId);
            request.setAttribute("playList", playListList);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addNewSong.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
