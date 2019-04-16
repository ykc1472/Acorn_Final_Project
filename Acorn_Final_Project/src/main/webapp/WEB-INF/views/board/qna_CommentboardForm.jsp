<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#updateCommentBoard").on("click", function(event){
			
		})
	})
</script>
<div align="center">
	<form action="#" method="post">
		<table border="1">
			<tr>
				<th>제목</th>
				<td colspan="3"><span>${QnABoard.qna_num}. ${QnABoard.qnac_title}</span></td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td width="350px"><span >${QnABoard.nickname}</span></td>
				<c:choose>
					<c:when test="${QnABoard.writedate eq QnABoard.rewritedate}">
						<th>작성일</th>
						<td>${QnABoard.writedate}</td>
					</c:when>
					<c:otherwise>
						<th>마지막수정일</th>
						<td>${QnABoard.rewritedate}</td>
					</c:otherwise>
				</c:choose>
				
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><textarea name=content rows ="15" cols="100" readonly="readonly">${QnABoard.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<c:if test="${loginInfo.grade == 99}">
						<a href="/Final_Project/adminCheck/updateQnaCommentBoardForm?qna_num=${QnABoard.qna_num}">수정</a>&nbsp;
					</c:if>
				</td>
			</tr>
		</table>
	</form>
</div>
