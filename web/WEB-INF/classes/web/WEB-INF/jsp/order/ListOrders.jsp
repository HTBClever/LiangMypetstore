<%@ include file="../common/includeTop.jsp"%>

<h2>My Orders</h2>

<table>
	<tr>
		<th>Order ID</th>
		<th>Date</th>
		<th>Total Price</th>
	</tr>

	<c:forEach var="order" items="${sessionScope.orderList}">
		<tr>
			<td><a href="viewOrderFromList?orderId=${order.orderId}">${order.orderId}</a></td>
			<td>${order.orderDate}</td>
			<td><fmt:formatNumber value="${order.totalPrice}"
				pattern="$#,##0.00" /></td>
		</tr>
	</c:forEach>
</table>

<%@ include file="../common/includeBottom.jsp"%>


