<%@ include file="../common/includeTop.jsp"%>
<script src="JavaScript/Account.js"></script>
<div id="Catalog">
	<form action="newAccount" method="post" onsubmit="return accountExist()">
	<h3>User Information</h3>

	<table>
		<tr>
			<td>User ID:</td>
			<td><input type="text" name="username" id="username" onkeyup="checkUserName();" required="required" autocomplete="off"/><span id="isExist"></span></td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input type="password" name="password" id="password" required="required" autocomplete="off"/></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input type="password" name="repeatedPassword" id="repeatedPassword" onkeyup="checkPassword();" required="required" autocomplete="off"/><span id="notEqual"></span></td>
		</tr>
		<tr>
			<td align="center">Please enter verification code:</td>
			<td><input type="text" name="checkYan"/><img src="${pageContext.request.contextPath}/yanZheng" alt="验证码看不清，换一张" id="validateCodeImg" onclick="changeImg()"/><c:if test="${sessionScope.yanZhengMessage!=null}"><span style="color: red">${sessionScope.yanZhengMessage}</span></c:if></td>
		</tr>
	</table>
		<script>
			function changeImg() {
				document.getElementById("validateCodeImg").src="${pageContext.request.contextPath}/yanZheng?"+Math.random();//在末尾加Math.random()的作用：<br>如果两次请求地址一样，服务器只会处理第一次请求，第二次请求返回内容和第一次一样。或者说如果地址相同，第一次请求时，将自动缓存，导致第二次不会重复请求了。Math.random()是调用javascript语法中的数学函数，能够产生随机数。<br>末尾加Math.random()使每次请求地址不相同，服务器每次都去做不同的响应。也可以使用new date()时间戳的形式作为参数传递。
			}
		</script>
	<%@ include file="IncludeAccountFields.jsp"%>
	<input type="submit" value="Save Account Information">
	</form></div>

<%@ include file="../common/includeBottom.jsp"%>