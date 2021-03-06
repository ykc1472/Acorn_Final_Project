<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#passwd").on("click", function(event){
			/* var passwd = prompt("비밀번호를 입력하세요.", ""); */
			var qna_num = $(this).attr("boardnum-data");
			var boardTitle = $(this).attr("title-data");
			console.log(qna_num);
		    if (confirm("QnA게시판의 제목 : " +qna_num+". "+ boardTitle + "\n을 정말로 삭제하시겠습니까?") == true) {
		    	location.href="/Final_Project/adminCheck/deleteQnaBoard?qna_num="+qna_num;
		    }
		    
		});

		$("#update").on("click", function(event){
			var passwd = prompt("비밀번호를 입력하세요.", "");
			var qna_num = $(this).attr("boardnum-data");
			$.ajax({
				type : "GET",
				url : "/Final_Project/loginCheck/passwordCheck",
				dataType : "text",
				data : {
					userpw : passwd
				},
				success : function(Data, status, xhr) {
					console.log(Data);
					if(Data == "true"){
						location.href="/Final_Project/loginCheck/updateBoardForm?qna_num="+qna_num;
					} else{
						alert("비밀번호가 틀렸습니다.");
					}
				},
				error : function(xhr, status, error) {
					console.log("error");
				}
			})
		});

		$("#delete").on("click", function(event){
			var passwd = prompt("비밀번호를 입력하세요.", "");
			var qna_num = $(this).attr("boardnum-data");
			$.ajax({
				type : "GET",
				url : "/Final_Project/loginCheck/passwordCheck",
				dataType : "text",
				data : {
					userpw : passwd
				},
				success : function(Data, status, xhr) {
					console.log(Data);
					if(Data == "true"){
						location.href="/Final_Project/loginCheck/deleteBoard?qna_num="+qna_num;
					} else{
						alert("비밀번호가 틀렸습니다.");
					}
				},
				error : function(xhr, status, error) {
					console.log("error");
				}
			})
		});
	})
</script>
<div align="center">
	<table border="1">
		<tr>
			<th>제목</th>
			<td colspan="3"><span>${QnABoard.qna_num}.${QnABoard.qna_title}</span></td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td width="350px"><span >${QnABoard.nickname}</span></td>
			<c:choose>
				<c:when test="${QnABoard.writedate eq QnABoard.rewritedate}">
					<th>작성일</th>
					<td>${fn:substring(QnABoard.writedate,0,13)}</td>
				</c:when>
				<c:otherwise>
					<th>마지막수정일</th>
					<td>${fn:substring(QnABoard.rewritedate,0,13)}</td>
				</c:otherwise>
			</c:choose>
			
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3"><textarea name=content rows ="15" cols="100" readonly="readonly">${QnABoard.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="right">
				<c:if test="${loginInfo.userid == QnABoard.userid}">
					<c:if test="${QnABoard.qna_option == 1 || QnABoard.qna_option == 3}">
						<button id="update" boardnum-data="${QnABoard.qna_num}">수정</button>&nbsp;
					</c:if>
					<button id="delete" boardnum-data="${QnABoard.qna_num}">삭제</button>
				</c:if>
				<c:if test="${loginInfo.grade == 99 && QnABoard.qna_option != 0}">
					<a id="passwd" title-data="${QnABoard.qna_title}" boardnum-data="${QnABoard.qna_num}" href="#">관리자 삭제 기능</a>&nbsp;&nbsp;&nbsp;
					<c:if test="${!(QnABoard.qna_option eq 0 || QnABoard.qna_option eq 2 || QnABoard.qna_option eq 4)}">
						<a href="/Final_Project/admenCheck/writeCommentBoardForm?pick=${QnABoard.qna_num}">답변</a>
					</c:if>
				</c:if>
			</td>
		</tr>
	</table>
</div>
