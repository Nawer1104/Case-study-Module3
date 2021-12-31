package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.Artist;
import One_Music_Project.Model.Song;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AllSongOfArtistServlet", urlPatterns = "/songOfArtist")
public class AllSongOfArtistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String aId = request.getParameter("aid");
        List<Song> list = projectDao.songByAid(aId);
        Artist artist = projectDao.getArtistById(aId);

        request.setAttribute("listSongs", list);
        request.setAttribute("Artist", artist);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/allArtists.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
