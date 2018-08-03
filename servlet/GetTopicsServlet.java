package mb.servlet;

import mb.dao.MessageBoardDAO;
import mb.model.Topic;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetTopicsServlet extends HttpServlet {
    //request object contains form values
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        MessageBoardDAO dao = new MessageBoardDAO();
        //only get session if there is one don't create one
        HttpSession session = request.getSession(false);
        String username = (String)session.getAttribute("username");
        //call dao
        List<Topic> topics = dao.getAllTopics();
        //put topics into attribute called topics
        request.setAttribute("topics" , topics);
        //forwards request to the jsp
        request.getRequestDispatcher("viewAllTopics.jsp").forward(request, response);
    }
}
