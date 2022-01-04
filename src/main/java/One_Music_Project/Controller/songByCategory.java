package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.Category;
import One_Music_Project.Model.PlayList;
import One_Music_Project.Model.Song;
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

@WebServlet(name = "songByCategory", urlPatterns = "/songByCategory")
public class songByCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");

        int count = projectDao.getTotalSongNumbersByCategory(cid);

        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int endPage = count / 6;
        if (count % 6 != 0) {
            endPage++;
        }
        int index = Integer.parseInt(indexPage);

        List<Song> list = projectDao.pagingSongsByCategory(cid, index);

        HttpSession session = request.getSession();
        UserAccount user = (UserAccount) session.getAttribute("acc");
        if (user != null) {
            int userId = user.getUid();
            List<PlayList> playListList = projectDao.getPlayListNameByUserId(userId);
            request.setAttribute("playList", playListList);
        }
        String categoryName = projectDao.getCategoryListNameByCid(cid);
        List<Category> categories = projectDao.selectAllCategory();
        request.setAttribute("cid", cid);
        request.setAttribute("categories", categories);
        request.setAttribute("CategoryName", categoryName);
        request.setAttribute("count", count);
        request.setAttribute("pageTag", index);
        request.setAttribute("endP", endPage);
        request.setAttribute("listSongs", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/songOfCategory.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
