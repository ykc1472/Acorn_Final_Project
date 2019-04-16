<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div align="center">
	<form action="/Final_Project/admenCheck/writeBoard" method="get">
		<input type="hidden" name="qna_num" value="${boardInfo.qna_num}">
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="qna_title" id="title" maxlength="50" width="500px" value="문의하신 [${boardInfo.qna_title}]에 대한 답변입니다."></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows ="15" cols="100" id="content">${boardInfo.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"> <input type="submit" value="답변달기">&nbsp;&nbsp;<input type="reset" value="다시 작성하기"></td>
			</tr>
		</table>
	</form>
</div>