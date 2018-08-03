package mb.servlet;

import mb.dao.MessageBoardDAO;
import mb.model.Post;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewProfileServlet extends HttpServlet {
    //request object contains form values
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        MessageBoardDAO dao = new MessageBoardDAO();
        //only get session if there is one don't create one
        HttpSession session = request.getSession(true);
        String username = (String)session.getAttribute("username");

        List<Post> posts = dao.getDashboardPosts(username);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {

            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>Profile</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("Profile");

            for(Post post: posts){
                writer.println(post.getPostId());
                writer.println(post.getText());
                writer.println(post.getTopicId());
            }

            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
