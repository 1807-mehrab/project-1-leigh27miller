package mb.servlet;

import mb.dao.MessageBoardDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeletePostServlet extends HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        MessageBoardDAO dao = new MessageBoardDAO();
        //only get session if there is one don't create one
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Boolean admin = (Boolean) session.getAttribute("admin");

        int postId = Integer.parseInt(request.getParameter("postId"));

        if(admin) {
            dao.deletePost(postId);
            request.getRequestDispatcher("viewAllFlaggedPosts.jsp").forward(request, response);

        }


    }
}
