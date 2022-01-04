package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;
import One_Music_Project.Model.Category;
import One_Music_Project.Model.UserAccount;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "SignUpServlet", urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userName = request.getParameter("userName");
        String userId = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");
        String userPasswordConfirm = request.getParameter("userPasswordConfirm");

        if (userId.equals("") || userPassword.equals("")) {
            request.setAttribute("mess", "Can't be empty!");
            List<Category> categories = projectDao.selectAllCategory();
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!userPassword.equals(userPasswordConfirm)) {
            List<Category> categories = projectDao.selectAllCategory();
            request.setAttribute("categories", categories);
            request.setAttribute("mess", "Your password doesn't match each other");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            UserAccount userAccount = projectDao.checkUserExist(userId);
            if (userAccount == null) {
                projectDao.createNewAccount(userName, userId, userPassword);
                List<Category> categories = projectDao.selectAllCategory();
                request.setAttribute("categories", categories);
                request.setAttribute("messSuccess", "Account Created Successfully");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("mess", "This email already exists!");
                List<Category> categories = projectDao.selectAllCategory();
                request.setAttribute("categories", categories);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
