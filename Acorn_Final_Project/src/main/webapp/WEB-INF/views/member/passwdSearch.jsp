<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
	<c:if test="${!empty mesg}">
		alert("${mesg}");
		<c:remove var="mesg" />
	</c:if>
	
	$(document).ready(function(){
	$("#domain").on("change", function(event){
		// 이메일 입력 기능 Start
		var email = $("[name='userEMail']").val();
		if($(this).val){
			if(email.indexOf('@') == -1 ){
				email = email +  '@' + $(this).val();
			} else{
				email = email.substr(0, email.indexOf('@'))+ '@' + $(this).val();
			}
		} else {
				email = email.substr(0, email.indexOf('@'))+ '@';
		}
		$("[name='userEMail']").val(email);
		// 이메일 입력 기능 End
	})
})
</script>        
  
<form action="MemberPasswdSearch" method="get">
<div align="center">    
<table>
<tr>
<td colspan="2"><hr></td>
</tr>
<tr>
	<td>이름</td>
	<td><input type="text" name="username"></td>
</tr>
<tr>
<td colspan="2"><hr></td>
</tr>
<tr>
	<td>아이디</td>
	<td><input type="text" name="userid" maxlength="11" id="userid"></td>
</tr>
<tr>
<td colspan="2"><hr></td>
</tr>

<tr>
	<td>가입 메일주소</td>
	<td><input type="email" name="userEMail">
	<select	id="domain">
		<option value="">직접입력</option>
		<option value="naver.com">naver.com</option>
		<option value="hanmail.net">hanmail.net</option>
		<option value="daum.net">daum.net</option>
		<option value="nate.com">nate.com</option>
		<option value="hotmail.com">hotmail.com</option>
		<option value="gmail.com">gmail.com</option>
		<option value="icloud.com">icloud.com</option>
	</select>
	<span id="emailCheck">&nbsp;</span></td>
</tr>	
<tr>
<td colspan="2"><hr></td>
</tr>
	
<tr>
	<td colspan="2" align="center"><input type="submit" value="이메일 인증하기"></td>
</tr>
<tr>
<td colspan="2"><hr></td>
</tr>

</table>
</div>
</form>