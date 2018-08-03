package mb.servlet;

import mb.dao.MessageBoardDAO;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SaveFlaggedPostServlet extends HttpServlet {
    //request object contains form values
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        //get the form values
        String strPostId = request.getParameter("postId");
        int postId = Integer.parseInt(strPostId);
        String strTopicId = request.getParameter("topicId");
        int topicId = Integer.parseInt(strTopicId);
        MessageBoardDAO dao = new MessageBoardDAO();
        dao.saveFlaggedPost(postId, username);

        //todo: addpost.html
        request.getRequestDispatcher("GetAllPostsByTopicIdServlet").forward(request, response);
    }
}