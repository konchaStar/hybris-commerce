<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <h1>Questions</h1>
    <c:forEach var="question" items="${questions}">
        <div>
            <h4><spring:message code="text.customer"/></h4>${question.questionCustomerName}
            <h4><spring:message code="text.question"/></h4>${question.question}
        </div>
        <c:if test="${not empty question.answer}">
            <div>
                <h4><spring:message code="text.customer"/></h4>${question.answerCustomerName}
                <h4><spring:message code="text.answer"/></h4>${question.answer}
            </div>
        </c:if>
    </c:forEach>
</div>