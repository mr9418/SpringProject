<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석게시판</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
	* {font-size: 10pt;}
	table { width: 800px;margin: auto;border: none;}
	th { border: 1px solid gray; background-color: silver; text-align: center;padding:5px; }
	td { border: 1px solid gray; text-align: center;padding:5px; }
	.title{font-size: 18pt; text-align: center; border: none;}
	.sub_title{font-size: 10pt; text-align: right; border: none;}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/common.js"></script>
<script type="text/javascript">
	$(function() {

	});
	// 수정
	function memoUpdate(idx){
		var name = $("#name"+idx).html();
		var content = $("#content"+idx).html();

		$("#memoForm").attr("action","updateOk");
		$("#idx").val(idx);
		$("#name").val(name);
		$("#name").prop('readonly','readonly');
		$("#content").val(content);
		$("#content").prop('readonly','');
		$("#submitBtn").attr('value','수정');
		$("#cancelBtn").css('display','inline');
		$("#password").focus();
	}
	// 삭제
	function memoDelete(idx){
		var name = $("#name"+idx).html();
		var content = $("#content"+idx).html();
		
		$("#memoForm").attr("action","deleteOk");
		$("#idx").val(idx);
		$("#name").val(name);
		$("#name").prop('readonly','readonly');
		$("#content").val(content);
		$("#content").prop('readonly','readonly');
		$("#submitBtn").attr('value','삭제');
		$("#cancelBtn").css('display','inline');
		$("#password").focus();
	}
	// 취소
	function resetForm(){
		$("#memoForm").attr("action","insertOk");
		$("#idx").val(0);
		$("#name").val("");
		$("#content").val("");
		$("#name").prop('readonly','');
		$("#content").prop('readonly','');
		$("#submitBtn").attr('value','저장');
		$("#cancelBtn").css('display','none');
	}	
</script>
</head>
<body>
	<table>
		<tr>
			<td class="title" colspan="5">출석 게시판</td>
		</tr>
		<tr>
			<td class="sub_title" colspan="5">
			${pagingVO.pageInfo }
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th width="60%">내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>아이피</th>
		</tr>
		<c:if test="${pagingVO.totalCount==0 }">
			<tr>
				<td  align="center" colspan="5">등록된 글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${pagingVO.totalCount>0 }">
			<c:forEach var="vo" items="${pagingVO.list}" varStatus="vs">
				<tr>
					<td>${vo.idx }</td>
					<td style="text-align: left;">
						<span id="content${vo.idx }"><c:out value="${vo.content }"/></span>
						<button class="btn btn-outline-success  btn-sm" title="수정" 
						        onclick="memoUpdate('${vo.idx}')">E</button>
						<button class="btn btn-outline-success  btn-sm" title="삭제" 
						        onclick="memoDelete('${vo.idx}')">D</button>
					</td>
					<td>
						<span id="name${vo.idx }"><c:out value="${vo.name }"/></span>
					</td>
					<td>
						<fmt:formatDate value="${vo.regDate }" pattern="MM-dd"/>
					</td>
					<td>${vo.ip }</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" style="border: none;text-align: center;">
				${pagingVO.pageListPost }
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="5" style="border: none;text-align: left;">
				<form action="insertOk" method="post" id="memoForm">
					<input type="hidden" name="idx" value="0" id="idx">
					<input type="hidden" name="p" value="${commVO.currentPage }">
					<input type="hidden" name="s" value="${commVO.pageSize}">
					<input type="hidden" name="b" value="${commVO.blockSize}">
					<input type="hidden" name="ip" value="${pageContext.request.remoteAddr}">
					<input type="text" name="name" size="" id="name" style="width: 70px;" placeholder="이름" required="required">
					<input type="password" name="password" id="password" style="width: 70px;" placeholder="암호" required="required">
					<input type="text" name="content" id="content" placeholder="내용" required="required" size="70">
					<input type="submit" value="저장" id="submitBtn">		
					<input type="button" value="취소" id="cancelBtn" style="display: none;" onclick="resetForm()">			
				</form>
			</td>
		</tr>	
	</table>
	
</body>
</html>