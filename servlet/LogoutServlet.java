package mb.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    //request object contains form values
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            //get rid of session
            session.invalidate();
        }
        request.getRequestDispatcher("login.html").forward(request, response);

    }
}
