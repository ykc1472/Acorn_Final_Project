<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
${foodInfoList }
	<form>
	<table border="1">
	<tr>
		<td colspan="10"><hr></td>
	</tr>
		<tr>
			<th>전체선택</th>
			<th>카테고리</th>
			<th>상품명</th>
			<th>기본 가격</th>
			<th>이미지 등록여부</th>
			<th>제품설명</th>
			<th>옵션명</th>
			<th>가격차액</th>
			<th>재고량</th>
			<th>삭제</th>
		</tr>
		<tr>
			<td colspan="10"><hr></td>
		</tr>
		<c:forEach items="${foodInfoList}" var="foodInfo" step="status">
		<tr>
			<td><input type="checkbox" name="selectBox" data-fcode="${foodInfo.fcode}">${status.index}</td>
			<td><input type="text" name="fcategory" data-fcode="${foodInfo.fcode}" value="${fcategoryname }"></td>
			<td><input type="text" name="title" data-fcode="${foodInfo.fcode}" value="${foodInfo.ftitle}"></td>
			<td><input type="text" name="fprice" data-fcode="${foodInfo.fcode}" value="${foodInfo.fprice}"></td>
			<c:choose>
				<c:when test="${foodInfo.fimage eq null}">
					<td><input type="text" name="title" data-fcode="${foodInfo.fcode}" value="N"><input type="button" value="이미지 등록하기"></td>
				</c:when>
				<c:otherwise>
					<td><input type="text" name="title" data-fcode="${foodInfo.fcode}" value="Y"><input type="button" value="이미지 수정하기" data-fcode="${foodInfo.fcode}"></td>
				</c:otherwise>
			</c:choose>
			<td><input type="text" name="image" data-fcode="${foodInfo.fcode}" value="${foodInfo.ftitle}"></td>
			<td><input type="text" name="content" data-fcode="${foodInfo.fcode}" value="${foodInfo.content}"></td>
			<td>
				<table>
					<tr>
						<td></td>
					</tr>
				</table>
			</td>
			
		</tr>
		<tr>
			<td colspan="10"><hr></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="10">
			</td>
		</tr>
		
	</table>
	</form>
</div>