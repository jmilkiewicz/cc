<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="no-js no-touch" lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Documents</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>

                ${it.data.name}
                ${it.data.uploadPerson}
                ${it.data.documentDate}
                ${it.data.uploadDate}
                <a href="${it.data.id}/body" type="application/octet-stream" rel="docContent">Dowload</a>



                <a href="/documents" rel="allDocuments">All Documents</a>
</body>




</html>
