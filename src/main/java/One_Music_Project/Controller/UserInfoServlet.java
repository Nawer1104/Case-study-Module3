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

@WebServlet(name = "UserInfoServlet", urlPatterns = "/info")
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userAcc");

        UserAccount userAccount = projectDao.getUserAccount(userId);

        request.setAttribute("user", userAccount);
        HttpSession session = request.getSession();
        UserAccount user = (UserAccount) session.getAttribute("acc");
        if (user != null) {
            int userID = user.getUid();
            List<PlayList> playListList = projectDao.getPlayListNameByUserId(userID);
            request.setAttribute("playList", playListList);
        }
        List<Category> categories = projectDao.selectAllCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userInfomation.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
