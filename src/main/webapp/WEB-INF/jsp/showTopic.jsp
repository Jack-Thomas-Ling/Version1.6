<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/20
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>显示所有帖子</title>
    <link rel="stylesheet" href="../../statics/css/layui.css">
    <script src="../../statics/layui.js"></script>
    <style>
        td {
            margin: 20px;
        }

        body {

        }
    </style>
</head>
<body>
<h1>Here would be all of the topics</h1>


<table class="layui-table">
    <colgroup>
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>帖子id</th>
        <th>板块号</th>
        <th>标题</th>
        <th>用户id</th>
        <th>创建时间</th>
        <th>阅读量</th>
        <th>回复量</th>
        <th>是否加精</th>
        <th>是否置顶</th>
        <th>操作1(加精)</th>
        <th>取消加精</th>
        <th>操作2(置顶)</th>
        <th>取消置顶</th>


    </tr>
    </thead>
    <tbody>
    <C:forEach items="${topics}" var="topics">
        <tr>
            <td>
                    ${topics.topicId}
            </td>
            <td>
                    ${topics.boardId}
            </td>
            <td>
                    ${topics.topicTitle}
            </td>
            <td>
                    ${topics.userId}
            </td>
            <td>
                    ${topics.createTime}
            </td>
            <td>
                    ${topics.topicViews}
            </td>
            <td>
                    ${topics.topicReplies}
            </td>
            <td>

                <c:if test="${topics.digest==1}">
                    <span class="layui-badge">精</span>

                </c:if>
                <c:if test="${topics.digest==0}">
                    <span>0</span>
                </c:if>


            </td>
            <td>

                <c:if test="${topics.istop==1}">
                    <span class="layui-badge">顶</span>

                </c:if>
                <c:if test="${topics.istop==0}">
                    <span>0</span>
                </c:if>

            </td>
            <td>
                <form action="/test/jiajing">
                    <input type="hidden" name="topic_id" value="${topics.topicId}">
                    <input type="submit" value="加精" id="btn_jiajing"
                           class="layui-btn layui-btn-radius layui-btn-warm"
                           onclick="show()">

                </form>
            </td>

            <td>

                <form action="/test/canceljiajing">
                    <input type="hidden" name="topic_id" value="${topics.topicId}">
                    <input type="submit" value="取消加精" id="btn_canceljiajing"
                           class="layui-btn layui-btn-radius layui-btn-warm"
                           onclick="show()">


                </form>
            </td>


            <td>
                <form action="/test/top">
                    <input type="hidden" name="topic_id" value="${topics.topicId}">
                    <input type="submit" value="置顶" id="btn_top"
                           onclick="show()"
                           class="layui-btn layui-btn-radius layui-btn-danger">


                </form>
            </td>


            <td>
                <form action="/test/canceltop">
                    <input type="hidden" name="topic_id" value="${topics.topicId}">
                    <input type="submit" value="取消置顶" id="btn_canceltop"
                           onclick="show()"
                           class="layui-btn layui-btn-radius layui-btn-danger">

                </form>
            </td>
        </tr>
    </C:forEach>

    </tbody>
</table>


<!--怎样绑定按钮的和topic的id
插件看管理员删帖部分的删除功能是怎样实现的-->

<ol>

    <c:forEach items="${topics}" var="topics">


        <li>
                ${topics.topicId},${topics.boardId},${topics.topicTitle},
                ${topics.userId},${topics.createTime},${topics.topicViews}
            ,${topics.topicReplies},${topics.digest},
        </li>
    </c:forEach>
</ol>


</body>


</html>
<script>
    layui.use(['jquery', 'layer'], function () {
        var $ = layui.$;
        var layer = layui.layer;
        var id_btn =

            $(document).ready(function () {
                $("#" + btn_zhiding).click(function () {
                    layer.msg("ni");
                })
            })
    })

    function show() {
        // layer.msg("操作成功")

    }

    // layer.msg("设置成功");

</script>
