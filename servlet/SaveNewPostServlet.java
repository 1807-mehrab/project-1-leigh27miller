package mb.servlet;

import mb.dao.MessageBoardDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveNewPostServlet extends HttpServlet {
    //request object contains form values
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String)session.getAttribute("username");
        //get the form values
        String strTopicId = request.getParameter("topicId");
        int topicId = Integer.parseInt(strTopicId);
        String text = request.getParameter("text");
        MessageBoardDAO dao = new MessageBoardDAO();
        dao.saveNewPost(topicId, username, text);

        try (PrintWriter writer = response.getWriter()) {

            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>Created New Post</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
