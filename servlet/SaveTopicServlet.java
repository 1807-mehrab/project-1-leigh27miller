package mb.servlet;

import mb.dao.MessageBoardDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveTopicServlet extends HttpServlet {
    //request object contains form values
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String)session.getAttribute("username");
        //get the form values
        String topicName = request.getParameter("topicName");
        String genre = request.getParameter("genre");
        MessageBoardDAO dao = new MessageBoardDAO();
        dao.saveNewTopic(topicName, genre);
        //todo: addpost.html
        request.getRequestDispatcher("addTopic.html").forward(request, response);
    }
}
