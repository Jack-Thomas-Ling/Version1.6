<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/21
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShowUserDetailedInfo</title>
</head>
<body>
<h1>Hi， there. This page will show user detailed info by user_id.</h1>
<ul>
    <c:forEach items="userDetail" var="userDetsil">
        <li>${userDetail.userId},${userDetail.imagePath},${userDetail.eMail}</li>
    </c:forEach>
</ul>
</body>
</html>
