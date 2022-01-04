package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.Artist;
import One_Music_Project.Model.Category;
import One_Music_Project.Model.PlayList;
import One_Music_Project.Model.UserAccount;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddNewSongServlet", urlPatterns = "/addSongServlet")
public class AddNewSongServlet extends HttpServlet  {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("songName");
        String img = request.getParameter("songImg");
        String link = request.getParameter("songLink");
        String aid = request.getParameter("songOfArtist");

        if (img.equals("")) {
            img = "/img/core-img/defaulpicforsong.jpg";
        } else {
            img = "img/bg-img/" + img;
        }

        if (link.equals("") || name.equals("")) {
            List<Artist> artists = projectDao.selectAllArtists();
            HttpSession session = request.getSession();
            UserAccount user = (UserAccount) session.getAttribute("acc");
            if (user != null) {
                int userId = user.getUid();
                List<PlayList> playListList = projectDao.getPlayListNameByUserId(userId);
                request.setAttribute("playList", playListList);
            }
            List<Category> categories = projectDao.selectAllCategory();
            request.setAttribute("categories", categories);
            request.setAttribute("messError", "Add New Song To The Library Failed");
            request.setAttribute("listArtists", artists);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/addNewSong.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            link = "audio/" + link;
            projectDao.addSong(name, link, img, aid);
            List<Artist> artists = projectDao.selectAllArtists();
            List<Category> categories = projectDao.selectAllCategory();
            request.setAttribute("categories", categories);
            request.setAttribute("messSuccess", "New Song Added To The Library");
            request.setAttribute("listArtists", artists);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/addNewSong.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
