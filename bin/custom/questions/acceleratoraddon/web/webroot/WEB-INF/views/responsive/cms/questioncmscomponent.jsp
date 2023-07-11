<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<div style="font-size: ${fontSize}" class="content">
    <h2>Questions:</h2>
    <c:if test="${empty productQuestions.questions}">
        <p>No questions were asked about this product</p>
    </c:if>
    <c:forEach items="${productQuestions.questions}" var="question" end="${questionsNumber}">
        <hr/>
        Question:
        <p>${question.question}</p>
        Question customer:
        <p>${question.questionCustomer}</p>
        <c:if test="${not empty question.answer}">
            Answer:
            <p>${question.answer}</p>
            Answer customer:
            <p>${question.answerCustomer}</p>
        </c:if>
        <hr/>
    </c:forEach>
</div>

</body>
</html>

