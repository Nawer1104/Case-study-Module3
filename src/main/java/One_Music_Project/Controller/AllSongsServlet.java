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
        int count = projectDao.getTotalSongNumbers();
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int endPage = count / 6;
        if (count % 6 != 0) {
            endPage++;
        }
        int index = Integer.parseInt(indexPage);

        List<Song> list = projectDao.pagingSongs(index);
        request.setAttribute("pageTag", index);
        request.setAttribute("endP", endPage);
        request.setAttribute("listSongs", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/allSongs.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
