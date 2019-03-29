<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
  <title>Document</title>
</head>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/style.css">
<body>
  <table id="table" class="table table-hover">
    <thead class="thead-inverse">
      <tr class="row">
        <th class="col-1 number">번호</th>
        <th class="col-5">제목</th>
        <th class="col-1">작성자</th>
        <th class="col-2">등록일</th>
        <th class="col-1">조회수</th>
        <th class="col-1">댓글수</th>
      </tr>
    </thead>
    <tbody>
        <c:if test="${not empty letters}">
          <c:forEach var="list" items="${letters}">
            <tr class="row">
              <td class="col-1 number" number>${list.rnum}</td>
              <td class="col-5" onclick="javascript:selectRow(this, ${list.idx})"><a href="/detail/${list.idx}">${list.title}</a></td>
              <td class="col-1">${list.userId}</td>
              <td class="col-2">${list.atData}</td>
              <td class="col-1">${list.hits}</td>
              <td class="col-1">${list.commentCnt}</td>
            </tr>
          </c:forEach>
        </c:if>
    </tbody>
  </table>

  <%-- Paging --%>
  <div class="paging-aria" style="margin-left: 2%;">
    <nav aria-label="page navigation example">
      <ul class="pagination">
        <li class="page-item">
          <a class="page-link" href="/${cPage-1}" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>
        <c:forEach var="i" begin="${sPage}" end="${ePage}">
          <li class="page-item"><a class="page-link" href="/${i}">${i}</a></li>
        </c:forEach>

        <li class="page-item">
          <a class="page-link" href="/${cPage+1}" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>

  <div class="float-right btn-group write-config" role="group" aria-label="Write Config">
    <button type="button" class="btn btn-success" id="write">작성하기</button>
    <button type="button" class="btn btn-danger" id="delete">삭제</button>
  </div>
</body>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
    $(function () {

      $('#write').click(function () {
        // Todo Ajax를 사용하면 Cointroller로 가긴가는데 왜 페이지가 바뀌지 않는거지?
        window.location.href = "http://localhost:8080/write";
      });

      $("td[name='enterToLetter']").click(function () {
        console.log($(this));
      });

      $('#delete').click(function () {
        var idx = localStorage.getItem('idx');

        if (idx == null) alert('삭제할 문서를 선택해 주세요');
        $.ajax({
          type: 'get',
          url: '/write/delete/' + idx,
          success: function () {
            location.reload();
          }
        });
      });

    });

    function selectRow(rowIdx ,selectIdx) {
      var row = rowIdx.parentElement.rowIndex;
      var table = document.getElementById('table');
      var tr = table.getElementsByTagName('tr');
      var color = tr[row].style.background;

      table.style.backgroundColor = 'white';
      if (color == 'skyblue'){
        tr[row].style.background = 'white';
        localStorage.removeItem('idx');
      }
      else {
        tr[row].style.background = 'skyblue';
        localStorage.setItem('idx', selectIdx);
      }
    };

</script>
</html>
