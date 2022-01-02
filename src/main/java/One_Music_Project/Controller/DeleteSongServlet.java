package One_Music_Project.Controller;

import One_Music_Project.DAO.ProjectDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteSongServlet", urlPatterns = "/delete")
public class DeleteSongServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        projectDao.deleteSong(sid);
        response.sendRedirect("/allSongs");
    }
}
