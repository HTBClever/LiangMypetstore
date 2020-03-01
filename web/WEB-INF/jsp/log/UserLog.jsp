<%@ include file="../common/includeTop.jsp"%>


<div id="BackLink">
<a href="viewuserlog"></a>
</div>

<div id="Catalog">

	<table>
		<tr>
			<th>orderDate</th>
			<th>userName</th>
			<th>userBehavior</th>
		</tr>

		<c:forEach var="userLog" items="${sessionScope.userLogList}">
			<tr>
				<td>${userLog.visitDate}</td>
				<td>${userLog.userName}</td>
				<td>${userLog.userBehavior}</td>
			</tr>
		</c:forEach>
	</table>

</div>

<%@ include file="../common/includeBottom.jsp"%>



