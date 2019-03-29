<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
  <title>Title</title>
</head>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/style.css">
<body>
  <form:form class="form1" method="post" action="/write/complete" modelAttribute="letter">
    <div class="form-group">
      <label for="writer">작성자</label>
      <form:input path="userId" type="text" class="form-control" id="writer"/>
    </div>
    <div class="form-group">
      <label for="title">제목</label>
      <form:input path="title" type="text" class="form-control" id="title"/>
    </div>
    <div class="form-group">
      <label for="contents">글 내용</label>
      <form:textarea path="contents" class="form-control" id="contents" rows="3"/>
    </div>
    <button class="btn btn-primary l-List" type="button" id="letter-list">글 목록</button>
    <div class="float-right btn-group write-config" role="group" aria-label="Write Config">
      <button type="submit" class="btn btn-success float-right">완료</button>
    </div>
  </form:form>

</body>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
  $(function () {

    $('#letter-list').click(function () {
      window.location.href = "http://localhost:8080";
    });

  })
</script>
</html>
