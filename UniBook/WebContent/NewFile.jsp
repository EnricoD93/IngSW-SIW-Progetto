<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.ckeditor.com/4.8.0/standard-all/ckeditor.js"></script>

</head>
<body>
	<textarea name="editor1"></textarea>
	<script>
		CKEDITOR.replace('editor1');
	</script>
</body>
</html>