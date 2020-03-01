<%@ include file="../common/includeTop.jsp"%>

<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>
<div id="Catalog">
	<form action="signon" method="post" >
		<table align="center" >
			<tr align="center"><td colspan="2" style="text-align: center">Please enter your username and password.</td></tr>
			<tr align="center">
				<td align="center">Username:</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr align="center">
				<td align="center">Password:</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td align="center">Please enter verification code:</td>
				<td><input type="text" name="checkYan"/><img src="${pageContext.request.contextPath}/yanZheng" alt="验证码看不清，换一张" id="validateCodeImg" onclick="changeImg()"/><c:if test="${sessionScope.yanZhengMessage!=null}"><span style="color: red">${sessionScope.yanZhengMessage}</span></c:if></td>
			</tr>
                <c:if test="${sessionScope.signonMessage != null}">
			<tr>
				<td align="center" colspan="2" style="text-align: center"><span style="color: red">${sessionScope.signonMessage}</span></td>
			</tr>
		  </c:if>
			<tr>
				<td align="center" colspan="2" style="text-align: center"><input type="submit" name="signon" value="Login"/></td>
			</tr>
		</table>
</form>Need a user name and password?
	<a href="viewNewAccount" >Register Now!</a>
</div>
<script>
	function changeImg() {
		document.getElementById("validateCodeImg").src="${pageContext.request.contextPath}/yanZheng?"+Math.random();//在末尾加Math.random()的作用：<br>如果两次请求地址一样，服务器只会处理第一次请求，第二次请求返回内容和第一次一样。或者说如果地址相同，第一次请求时，将自动缓存，导致第二次不会重复请求了。Math.random()是调用javascript语法中的数学函数，能够产生随机数。<br>末尾加Math.random()使每次请求地址不相同，服务器每次都去做不同的响应。也可以使用new date()时间戳的形式作为参数传递。
	}
</script>

<%@ include file="../common/includeBottom.jsp"%>

