package mb.servlet;

import mb.dao.MessageBoardDAO;
import mb.model.MbUser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    //request object contains form values
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //get the form values
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);

        MessageBoardDAO dao = new MessageBoardDAO();
        MbUser user = dao.login(username, password);
        if(user != null){
            session.setAttribute("username", user.getUsername());
            session.setAttribute("admin", user.getAdmin());
        }
        String nextPage = "userProfileAndDashboard.html";
        if(user.getAdmin()){
            nextPage = "adminProfileAndDashboard.html";
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
}
