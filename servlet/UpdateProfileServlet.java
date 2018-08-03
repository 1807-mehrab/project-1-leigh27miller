package mb.servlet;

import mb.dao.MessageBoardDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateProfileServlet extends HttpServlet {
    //request object contains form values
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String)session.getAttribute("username");
        //get the form values
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first name");
        String lastName = request.getParameter("Last name");

        MessageBoardDAO dao = new MessageBoardDAO();
        dao.updateProfile(username, password, email, firstName, lastName);


        Boolean admin = (Boolean)session.getAttribute("admin");
        String nextPage = "userProfileAndDashboard.html";
        if(admin){
            nextPage = "adminProfileAndDashboard.html";
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
}
