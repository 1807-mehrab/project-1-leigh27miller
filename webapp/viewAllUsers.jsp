<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <h1> Users </h1>

        <!--TODO: This will bring in data-->
            <div id = "users">
                <table>
                  <tr>

                    <th>Name</th>

                  </tr>
                  <c:forEach items="${users}" var="user">
                    <tr>
                    <!-- the names of the variables must be the same name as model object -->
                      <td><c:out value="${MbUser.username}" /></td>
                    </tr>
                  </c:forEach>
                </table>
    </body>
</html>