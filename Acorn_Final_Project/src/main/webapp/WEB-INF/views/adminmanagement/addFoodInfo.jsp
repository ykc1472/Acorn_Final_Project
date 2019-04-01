<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form action="productAdd" method="POST" enctype="multipart/form-data">

	<table align="center">
		<tr>
			<td>상품 코드 : <input type="text" name="pCode"></td>
		</tr>
		<tr id="image">
			<td>상품 이미지 :
				<input type="file" name="theFile"> 
				<input type="button" value="이미지 추가" id="AddiBtn">
				<input type="button" value="이미지 삭제" id="DeliBtn">
			</td>
		</tr>

		<tr>

			<td>상품명 : <input type="text" name="pName"></td>

		</tr>

		<tr>

			<td>상품 내용 : <input type="text" name="pContent"></td>

		</tr>

		<tr>

			<td>상품 종류 : <input type="checkbox" name="pCategory" value="top">Top
				<input type="checkbox" name="pCategory" value="dress">Dress
				<input type="checkbox" name="pCategory" value="outer">Outer
				<input type="checkbox" name="pCategory" value="bottom">Bottom
			</td>

		</tr>
		<tr>
			<td>상품 사이즈 : <input type="checkbox" name="pSize" value="L">L
				<input type="checkbox" name="pSize" value="M">M <input
				type="checkbox" name="pSize" value="S">S <input
				type="checkbox" name="pSize" value="FREE">FREE
			</td>
		</tr>

		<tr>
			<td>가격 : <input type="text" name="pPrice"></td>
		</tr>

		<tr id="color">
			<td>상품 색상 : <input type="text" class="colors2" name="pColor">
				<input type="button" value="색상 추가" id="AddcBtn"> <input
				type="button" value="색상 삭제" id="DelcBtn">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="상품등록">
				<input type="reset" value="등록취소"></td>
		</tr>
	</table>

</form>