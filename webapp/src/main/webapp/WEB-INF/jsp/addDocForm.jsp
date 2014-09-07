<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<!doctype html>
<html class="no-touch">
<head>
    <title>Add document</title>
</head>
<body>



<form action="/documents"
      id="addDoc"
      method="post" class="hidden" enctype="multipart/form-data">
    <label class="filelabel" for="file">File:</label>
    <input class="fileinput" type="file" name="file" id="file" accept="*/*">
    <label for="fileName">Name of file:</label>
    <input type="text" class="form-input-text" name="fileName" autofocus=""
           value=""
           placeholder="file.txt">
    <label for="documentDate">Date of document</label>
    <input type="text" class="form-input-text" name="documentDate" autofocus=""
           value="">
    <input type="submit"
           value="save"
           class="btn-prim small">
</form>
</body>
</html>
