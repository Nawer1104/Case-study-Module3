package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.Category;
import One_Music_Project.Model.Song;
import One_Music_Project.Model.UserAccount;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userAccount = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");

        UserAccount account = projectDao.login(userAccount, userPassword);

        if (userAccount.equals("") || userPassword.equals("")) {
            request.setAttribute("mess", "Can't be empty!");
            List<Category> categories = projectDao.selectAllCategory();
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else if (account == null) {
            List<Category> categories = projectDao.selectAllCategory();
            request.setAttribute("categories", categories);
            request.setAttribute("mess", "Wrong password or email address.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", account);
            response.sendRedirect("/loadData");
        }
    }
}
