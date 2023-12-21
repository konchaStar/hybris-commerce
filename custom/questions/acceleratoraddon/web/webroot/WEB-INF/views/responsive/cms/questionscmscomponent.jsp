<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <c:forEach var="question" items="${questions}">
        <div>
            <spring:message code="text.customer"/>${question.questionCustomerName}
            <spring:message code="text.question"/>${question.question}
        </div>
        <c:if test="${not empty question.answer}">
            <div>
                <spring:message code="text.customer"/>${question.answerCustomerName}
                <spring:message code="text.answer"/>${question.answer}
            </div>
        </c:if>
    </c:forEach>
</div>