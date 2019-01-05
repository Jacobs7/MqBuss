<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ActivMQ DEMO</title>
</head>
<body>

        <article>ActiveMQ 例子service 中Mq*.java是单独MQ队列 操作  Queue\Topic  两种模式</article>
        Queue： 点对点传输消息回保存在队列中</br>
        Topic： 广播模式消息发送后消费者如果没有启动回丢失消息</br>

    </div>
    <form action="${pageContext.request.contextPath }/manage/index" method="post">

        <input type="submit" value="Ready  Go"/>
    </form>
</body>
</html>
