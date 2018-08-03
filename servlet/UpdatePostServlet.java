package mb.servlet;

import mb.dao.MessageBoardDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdatePostServlet extends HttpServlet {
    //request object contains form values
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String)session.getAttribute("username");
        Boolean admin = (Boolean) session.getAttribute("admin");
        //get the form values
        String strPostId = request.getParameter("postId");
        int postId = Integer.parseInt(strPostId);
        String text = request.getParameter("text");


        MessageBoardDAO dao = new MessageBoardDAO();

        if(admin){
            dao.updatePost(text, postId);

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            try (PrintWriter writer = response.getWriter()) {

                writer.println("<!DOCTYPE html><html>");
                writer.println("<head>");
                writer.println("<meta charset=\"UTF-8\" />");
                writer.println("<title>Post Updated</title>");
                writer.println("</head>");
                writer.println("<body>");
                writer.println("Post Updated");
                writer.println("</body>");
                writer.println("</html>");



            }
        } else{

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            try (PrintWriter writer = response.getWriter()) {

                writer.println("<!DOCTYPE html><html>");
                writer.println("<head>");
                writer.println("<meta charset=\"UTF-8\" />");
                writer.println("<title>Post Not Updated</title>");
                writer.println("</head>");
                writer.println("<body>");
                writer.println("You Must Be An Admin");
                writer.println("</body>");
                writer.println("</html>");



            }
        }
    }
}
