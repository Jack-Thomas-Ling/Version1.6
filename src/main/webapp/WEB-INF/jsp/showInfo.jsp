<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/20
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
这里将测试显示所有用户信息；

<ul>
    <c:forEach items="${users}" var="users">

        <li>
                ${users.userId},${users.userName},${users.password},${users.userType}
            ,${users.locked},${users.credit}
        </li>
    </c:forEach>
</ul>
</body>
</html>
