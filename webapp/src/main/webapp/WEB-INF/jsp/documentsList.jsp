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
<form action="/documents"
      id="addDoc"
      method="post" class="hidden" enctype="multipart/form-data">
    <label class="filelabel" for="file">File:</label>
    <input class="fileinput" type="file" name="file" id="file" accept="*/*">
    <label for="fileName">Name of file:</label>
    <input type="text" class="form-input-text" name="fileName" id="fileName" autofocus=""
           value=""
           placeholder="file.txt">
    <label for="documentDate">Date of document</label>
    <input type="text" class="form-input-text" name="documentDate" id="documentDate" autofocus=""
           value="">
    <input type="submit"
           value="save"
           class="btn-prim small">
</form>

<form action="/documents"
      id="findByUserName"
      method="get" class="hidden">
    <label for="user">userName</label>
    <input type="text" name="user" id="user">
    <input type="submit"
           value="searchByUsername"
           class="btn-prim small">
</form>



<form action="/documents"
      id="findByUserName"
      method="get" class="hidden">
    <label for="from">from</label>
    <input type="text" name="from" id="from">
    <label for="to">to</label>
    <input type="text" name="to" id="to">

    <input type="submit"
           value="searchByDates"
           class="btn-prim small">
</form>

<c:if test="${empty it.data}">
    No Documents!!!!!!!!!!!!!!!!!!!!!!
</c:if>

<c:forEach items="${it.data}" var="document">
        <div class="row">
                ${document.id}
                ${document.name}
                ${document.uploadPerson}
                ${document.documentDate}
                <a href="/documents/${document.id}" rel="documentMetadata">Document Metadata </a>
            </div>
        </div>
</c:forEach>

<a href="/documents" rel="allDocuments">All Documents</a>

</body>
</html>
