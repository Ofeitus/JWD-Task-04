<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title><fmt:message key="edit-personal-data.edit-personal-data" /></title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
<div class="container-fluid d-flex">
    <div class="w-100 row justify-content-md-center">
        <div class="col-md-5">
            <div class="form-container">
                <h3 class="title"><fmt:message key="edit-personal-data.edit-personal-data" /></h3>
                <form class="form-horizontal" action="controller" method="get">
                    <input type="hidden" name="command" value="edit-personal-data">
                    <div class="form-group">
                        <label><fmt:message key="edit-personal-data.name" /></label>
                        <input type="text" name="first-name" value="${sessionScope.user_name}" class="form-control" placeholder="<fmt:message key="edit-personal-data.name-placeholder" />" required>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-personal-data.surname" /></label>
                        <input type="text" name="second-name" value="${sessionScope.user_surname}" class="form-control" placeholder="<fmt:message key="edit-personal-data.surname-placeholder" />" required>
                    </div>
                    <div class="form-group" style="width: 100%">
                        <label><fmt:message key="edit-personal-data.phone-number" /></label>
                        <input type="tel" name="phone-number" value="${sessionScope.user_phone_number}" class="form-control" placeholder="<fmt:message key="edit-personal-data.phone-number-placeholder" />">
                    </div>
                    <div class="w-100 row justify-content-end">
                        <button class="h-50 col-4 btn submit"><fmt:message key="edit-personal-data.save-changes" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="tamplate/footer.jsp" />
</body>
</html>
