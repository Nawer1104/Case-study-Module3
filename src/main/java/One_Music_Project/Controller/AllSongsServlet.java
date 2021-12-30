package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.Song;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AllSongsServlet", urlPatterns = "/allSongs")
public class AllSongsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Song> list = projectDao.selectAllSong();
        request.setAttribute("listSongs", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/allSongs.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
