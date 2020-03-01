<%@ include file="../common/includeTop.jsp"%>

<div id="Catalog">

	<form action="editAccount" method="post" onsubmit="return fixPasswordWrong()">

	<h3>User Information</h3>

	<table>
		<tr>
			<td>User ID:</td>
			<td>${sessionScope.account.username}</td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input type="password" name="password" id="fixPassword" /></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input type="password" name="repeatedPassword" id="repeatFixPassword"/></td>
		</tr>
	</table>
	<%@ include file="IncludeAccountFields.jsp"%>
		<input type="submit" value="Save Account Information"/>
		</form><a href="viewListOrder">My Orders</a>
</div>

<%@ include file="../common/includeBottom.jsp"%>
