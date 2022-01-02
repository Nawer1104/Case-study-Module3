package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.Artist;
import One_Music_Project.Model.Song;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowEditFormServlet", urlPatterns = "/edit")
public class ShowEditFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        List<Artist> artists = projectDao.selectAllArtists();
        Song song = projectDao.getSongBySid(sid);

        request.setAttribute("listArtists", artists);
        request.setAttribute("song", song);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editingSong.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
