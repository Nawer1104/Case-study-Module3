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

@WebServlet(name = "EditUserServlet", urlPatterns = "/editUser")
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("userId");
        String name = request.getParameter("userName");
        String pass = request.getParameter("userPass");
        String code = request.getParameter("code");
        String image;

        UserAccount userAccount = projectDao.getUserAccount(id);
        if (request.getParameter("userImg").equals("")) {
            image = userAccount.getUimg();
        } else {
            image = "img/core-img/" + request.getParameter("userImg");
        }

        if (code.equals("namdeptrai")) {
            projectDao.updatePrimeum(name, pass, image, id);

            UserAccount userAccountAfterChange = projectDao.getUserAccount(id);
            HttpSession session = request.getSession();
            UserAccount user = (UserAccount) session.getAttribute("acc");
            if (user != null) {
                int userId = user.getUid();
                List<PlayList> playListList = projectDao.getPlayListNameByUserId(userId);
                request.setAttribute("playList", playListList);
            }
            session.setAttribute("acc", userAccountAfterChange);
            request.setAttribute("user", userAccountAfterChange);
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
        } else {
            projectDao.editUser(name, pass, image, id);

            UserAccount userAccountAfterChange = projectDao.getUserAccount(id);
            HttpSession session = request.getSession();
            UserAccount user = (UserAccount) session.getAttribute("acc");
            if (user != null) {
                int userId = user.getUid();
                List<PlayList> playListList = projectDao.getPlayListNameByUserId(userId);
                request.setAttribute("playList", playListList);
            }
            session.setAttribute("acc", userAccountAfterChange);
            request.setAttribute("user", userAccountAfterChange);
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
}
