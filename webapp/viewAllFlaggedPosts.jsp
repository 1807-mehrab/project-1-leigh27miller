<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <h1> Flagged Posts </h1>

        <!--TODO: This will bring in data-->
            <div id = "Flagged Posts">
                <table>
                  <tr>
                    <th>Post Id</th>
                    <th>Text</th>
                    <th>Username</th>
                    <th>&nbsp;</th>
                  </tr>
                  <c:forEach items="${flaggedPosts}" var="flaggedPost">
                    <tr>
                    <!-- the names of the variables must be the same name as model object -->
                      <td><c:out value="${flaggedPost.postId}" /></td>
                      <td><c:out value="${flaggedPost.text}" /></td>
                      <td><c:out value="${flaggedPost.username}" /></td>
                      <td><a href="/messageboard/DeletePostServlet?postId=<c:out value="${flaggedPost.postId}" />">Delete Post</a></td>

                    </tr>
                  </c:forEach>
                </table>

            <h1> Delete Post </h1>
                <form action= "/messageboard/DeletePostServlet" method = "POST">
                    <div>
                        <input type= "text" name="postId" placeholder = "Enter Post Id"/><br>
                        <input type="button" name="postId" placeholder="Submit"/>
                    </div>
                </form>

            <h1> Edit Topic </h1>
            <form action= "/messageboard/UpdatePostServlet" method = "POST">
                <div>
                    <textarea>

                    </textarea>

                    <input type= "button" name="postId" placeholder = "submit"/><br>
                </div>
            </form>

        </body>
</html>
