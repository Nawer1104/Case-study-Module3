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

@WebServlet(name = "UserPlayListManagement", urlPatterns = "/userPlayListManagement")
public class UserPlayListManagement extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao projectDao;

    public void init() {
        projectDao = new ProjectDao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                addToPlayList(request, response);
                break;
            case "remove":
                removeFromPlayList(request, response);
                break;
        }
    }

    private void addToPlayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String pid = request.getParameter("pid");

        int check = projectDao.getSidFromPlayListSong(pid);
        if (check == 0) {
            projectDao.addSongToPlayList(pid, sid);

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
            request.setAttribute("likeName", likeName);
            request.setAttribute("count", count);
            request.setAttribute("pageTag", index);
            request.setAttribute("endP", endPage);
            request.setAttribute("listSongs", list);
            request.setAttribute("messSuccess", "Song added in to your play list!");
            List<Category> categories = projectDao.selectAllCategory();
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/allSongs.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
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
            request.setAttribute("likeName", likeName);
            request.setAttribute("count", count);
            request.setAttribute("pageTag", index);
            request.setAttribute("endP", endPage);
            request.setAttribute("listSongs", list);
            request.setAttribute("messError", "Song already in your play list!");
            List<Category> categories = projectDao.selectAllCategory();
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/allSongs.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void removeFromPlayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String pid = request.getParameter("pid");
        projectDao.removeSongFromPlayList(sid, pid);

        List<Song> list = projectDao.getAllSongOfPlayList(pid);
        String playListName = projectDao.getPlayListNameByPid(pid);
        HttpSession session = request.getSession();
        UserAccount user = (UserAccount) session.getAttribute("acc");
        if (user != null) {
            int userId = user.getUid();
            List<PlayList> playListList = projectDao.getPlayListNameByUserId(userId);
            request.setAttribute("playList", playListList);
        }

        request.setAttribute("listSongs", list);
        request.setAttribute("pid", pid);
        request.setAttribute("playListName", playListName);
        List<Category> categories = projectDao.selectAllCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userPlayList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
