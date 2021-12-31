package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.Artist;
import One_Music_Project.Model.Song;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchArtistServlet", urlPatterns = "/searchArtist")
public class SearchArtistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String likeName = request.getParameter("name");
        List<Artist> artists = projectDao.searchArtistByName(likeName);

        request.setAttribute("likeName", likeName);
        request.setAttribute("listArtists", artists);
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
