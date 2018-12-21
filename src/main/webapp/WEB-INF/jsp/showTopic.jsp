<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/20
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示所有帖子</title>
</head>
<body>
<h1>Here would be all of the topics</h1>


<ul>
    <c:forEach items="${topics}" var="topics">

        <li>
                ${topics.topicId},${topics.boardId},${topics.topicTitle}
        </li>
    </c:forEach>
</ul>
</body>
</html>
