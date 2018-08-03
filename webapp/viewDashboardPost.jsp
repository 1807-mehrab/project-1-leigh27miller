<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <h1> Dashboard </h1>

        <!--TODO: This will bring in data-->
            <div id = "dashboardPost">
                <table>
                  <tr>
                    <th>Post Id</th>
                    <th>Text</th>
                    <th>Username</th>
                    <th>&nbsp;</th>
                  </tr>
                  <c:forEach items="${posts}" var="post">
                    <tr>
                    <!-- the names of the variables must be the same name as model object -->
                      <td><c:out value="${post.postId}" /></td>
                      <td><c:out value="${post.text}" /></td>
                      <td><c:out value="${post.username}" /></td>
                      <td><a href="/messageboard/SaveFlaggedPostServlet?postId=<c:out value="${post.postId}" />&topicId=<c:out value="${topicId}" />">Flag Post</a></td>
                    </tr>
                  </c:forEach>
                </table>
            </div>

    </body>
</html>
