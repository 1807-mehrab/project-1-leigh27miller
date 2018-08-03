package mb.servlet;

import mb.dao.MessageBoardDAO;
import mb.model.Post;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetFlaggedPostServlet extends HttpServlet {
    //request object contains form values
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        MessageBoardDAO dao = new MessageBoardDAO();
        //only get session if there is one don't create one
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Boolean admin = (Boolean) session.getAttribute("admin");

        if (admin) {
            List<Post> flaggedPosts = dao.getFlaggedPosts();
            //put topics into attribute called topics
            request.setAttribute("flaggedPosts", flaggedPosts);
            //forwards request to the jsp
            request.getRequestDispatcher("viewAllFlaggedPosts.jsp").forward(request, response);
        }
    }
}

