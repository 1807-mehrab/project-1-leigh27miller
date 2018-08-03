package mb.servlet;

import mb.dao.MessageBoardDAO;
import mb.model.Post;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetDashboardPostServlet extends HttpServlet {
    //request object contains form values
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        MessageBoardDAO dao = new MessageBoardDAO();
        //only get session if there is one don't create one
        HttpSession session = request.getSession(false);
        String username = (String)session.getAttribute("username");

        List<Post> posts = dao.getDashboardPosts(username);
        request.setAttribute("username" , username);
        //forwards request to the jsp
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("viewDashboardPost.jsp").forward(request, response);

    }



}
