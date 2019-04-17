<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div align="center">
	<form action="/Final_Project/loginCheck/updateBoard" method="get">
		<input type="hidden" name="qna_num" value="${QnABoard.qna_num}">
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="qna_title" id="title" maxlength="50" width="500px" value="${QnABoard.qna_title}"></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows ="15" cols="100">${QnABoard.content}</textarea></td>
			</tr>
			<tr>
				<th>공개여부</th>
				<td><input type="checkbox" name="qna_option" <c:if test='${QnABoard.qna_option eq 1}'>checked </c:if> value="1" > 질문을 비공개로 처리합니다.</td>
			</tr>
			<tr>
				<td colspan="2" align="center"> <input type="submit" value="수정하기">&nbsp;&nbsp;<input type="reset" value="다시 작성하기"></td>
			</tr>
		</table>
	</form>
</div>