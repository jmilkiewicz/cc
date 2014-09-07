<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="no-js no-touch" lang="en">
<global:htmlHead title="${label.withKey('brandDetails.title')}"/>
<body class="page-branddetails">
<head>
    <meta charset="UTF-8"/>
    <title>Documents</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
<c:if test="${empty it.data}">
    No Documents!!!!!!!!!!!!!!!!!!!!!!
</c:if>

<c:forEach items="${it.data}" var="document">
        <div class="row">
                ${document.id}
                ${document.documentData.name}
                ${document.documentData.uploadPerson}
                ${document.documentData.documentDate}
            </div>
        </div>
</c:forEach>
</body>
</html>
