package mb.servlet;

import mb.dao.MessageBoardDAO;
import mb.model.MbUser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetProfileServlet extends HttpServlet {
    //request object contains form values
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //get the form values
        HttpSession session = request.getSession(true);
        String username = (String)session.getAttribute("username");



        MessageBoardDAO dao = new MessageBoardDAO();
        MbUser user = dao.viewProfile(username);
        request.setAttribute("user", user);

        request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
    }
}
