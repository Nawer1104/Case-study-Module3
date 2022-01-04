package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.Category;
import One_Music_Project.Model.PlayList;
import One_Music_Project.Model.Song;
import One_Music_Project.Model.UserAccount;

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
        String likeName = request.getParameter("txt");
        int count;
        if (likeName == null || likeName.equals("")) {
            count = projectDao.getTotalSongNumbers();
        } else {
            count = projectDao.getTotalSongNumbersHavingLetter(likeName);
        }

        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int endPage = count / 6;
        if (count % 6 != 0) {
            endPage++;
        }
        int index = Integer.parseInt(indexPage);

        List<Song> list;
        if (likeName == null || likeName.equals("")) {
            list = projectDao.pagingSongs(index);
        } else {
            list = projectDao.pagingSongsWithSearch(likeName, index);
        }
        HttpSession session = request.getSession();
        UserAccount user = (UserAccount) session.getAttribute("acc");
        if (user != null) {
            int userId = user.getUid();
            List<PlayList> playListList = projectDao.getPlayListNameByUserId(userId);
            request.setAttribute("playList", playListList);
        }
        List<Category> categories = projectDao.selectAllCategory();
        request.setAttribute("categories", categories);
        request.setAttribute("likeName", likeName);
        request.setAttribute("count", count);
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
