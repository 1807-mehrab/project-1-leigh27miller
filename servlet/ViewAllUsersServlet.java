package mb.servlet;

import mb.dao.MessageBoardDAO;
import mb.model.MbUser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewAllUsersServlet extends HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        MessageBoardDAO dao = new MessageBoardDAO();
        //only get session if there is one don't create one
        HttpSession session = request.getSession(false);
        String username = (String)session.getAttribute("username");
        Boolean admin = (Boolean) session.getAttribute("admin");
        if(admin) {
            List<MbUser> users = dao.getAllUserProfiles();
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");


            //put topics into attribute called topics
            request.setAttribute("Users" , users);
            //forwards request to the jsp
            request.getRequestDispatcher("viewAllUsers.jsp").forward(request, response);

        }
    }
}
