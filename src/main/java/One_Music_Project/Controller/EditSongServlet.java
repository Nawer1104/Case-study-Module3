package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "EditSongServlet", urlPatterns = "/editSong")
public class EditSongServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("songId");
        String name = request.getParameter("songName");
        String img = request.getParameter("songImg");
        String link = request.getParameter("songLink");
        String aid = request.getParameter("songOfArtist");

        Song song = projectDao.getSongBySid(id);
        if (img.equals("")) {
            img = song.getSimg();
        } else {
            img = "img/bg-img/" + img;
        }

        if (link.equals("")) {
            link = song.getSlink();
        } else {
            link = "audio/" + link;
        }

        projectDao.editSong(name, link, img, aid, id);
        Song editedSong = projectDao.getSongBySid(id);
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
        request.setAttribute("messSuccess", "Edited Song Successfully");
        request.setAttribute("song", editedSong);
        request.setAttribute("listArtists", artists);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editingSong.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
