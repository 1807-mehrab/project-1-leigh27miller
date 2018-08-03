<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <h1> Topics </h1>

        <!--TODO: This will bring in data-->
            <div id = "Topic">
                <table>
                  <tr>
                    <th>Topic Id</th>
                    <th>Name</th>
                    <th>Genre</th>
                  </tr>
                  <c:forEach items="${topics}" var="topic">
                    <tr>
                    <!-- the names of the variables must be the same name as model object -->
                      <td><c:out value="${topic.topicId}" /></td>
                      <td><a href="/messageboard/GetAllPostsByTopicIdServlet?topicId=<c:out value="${topic.topicId}" />"><c:out value="${topic.topicName}" /></a></td>
                      <td><c:out value="${topic.genre}" /></td>
                    </tr>
                  </c:forEach>
                </table>
            <div id="replies">
                <a href="/messageboard/replies.html"
            </div>
            </div>
                <a href="/messageboard/addPost.html">Respond to Topic</a>
            <div id="respond">

            <a href="/messageboard/flagNewPost.html">Flag A Post></a>

            </div>

    </body>
</html>

<!--TODO: Once I click a topic, I want to get to that topic.html page-->