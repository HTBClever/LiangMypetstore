<%@ include file="../common/includeTop.jsp" %>

<div id="BackLink"><a href="main">Return to Main Menu</a>
</div>
<div class="box" id="box">
    <div id="rightBoxDiv">
        <ul id="rightBox">
            <li id="li1" class="active">browse cart</li>
            <li id="li2">Payment Details</li>
            <li id="li3">Billing Address</li>
            <li id="li4">Shipping Address</li>
        </ul>
    </div>
    <div id="leftBox">
                <form action="viewConfirmOrder" method="post">
                <table style="width: 100%;">
                    <tbody id="leftBox1" class="active" style="padding-left: 20%">
                    <tr>
                        <th><b>Item ID</b></th>
                        <th><b>Product ID</b></th>
                        <th><b>Description</b></th>
                        <th><b>In Stock?</b></th>
                        <th><b>Quantity</b></th>
                        <th><b>List Price</b></th>
                        <th><b>Total Cost</b></th>
                        <th>&nbsp;</th>
                    </tr>

                    <c:if test="${sessionScope.numberOfItems == 0}">
                        <tr>
                            <td colspan="8"><b>Your cart is empty.</b></td>
                        </tr>
                    </c:if>

                    <c:forEach var="cartItem" items="${sessionScope.cartItems}">
                        <tr>
                            <td><a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
                            </td>
                            <td>${cartItem.item.product.productId}</td>
                            <td>${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                    ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                    ${cartItem.item.attribute5} ${cartItem.item.product.name}</td>
                            <td>${cartItem.inStock}</td>
                            <td><input  type="text"   id=${cartItem.item.itemId}Quantity  class="inputQuantity"  name="${cartItem.item.itemId}" value="${cartItem.quantity}"/></td>
                            <td id=${cartItem.item.itemId}Price><fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00"/></td>
                            <td id=${cartItem.item.itemId}Total><fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00"/></td>
                            <td>
                                <a class="Button" href="removeItem?cartItem=${cartItem.item.itemId}">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="7" id="itemSubTotal">Sub Total: <fmt:formatNumber
                                value="${sessionScope.subTotal}" pattern="$#,##0.00" /></td>
                        <td></td>
                    </tr>
                    </tbody>
                    <tbody style="padding-left: 30%;padding-top: 5%;" id="leftBox2" class="positive">
                    <tr>
                        <th colspan=2>Payment Details</th>
                    </tr>
                    <tr>
                        <td>Card Type:</td>
                        <td><select name="order.cardType">
                            <c:forEach var="cardType" items="${sessionScope.cardTypes}">
                                <option value="${cardType}">${cardType}</option>
                            </c:forEach>
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Card Number:</td>
                        <td><input type="text" name="order.creditCard"/> * Use a fake
                            number!
                        </td>
                    </tr>
                    <tr>
                        <td>Expiry Date (MM/YYYY):</td>
                        <td><input type="text" name="order.expiryDate"/></td>
                    </tr>
                    </tbody>
                    <tbody style="padding-left: 37%;padding-top: 3%;" id="leftBox3" class="positive">
                    <tr>
                        <th colspan=2>Billing Address</th>
                    </tr>

                    <tr>
                        <td>First name:</td>
                        <td><input type="text" name="order.billToFirstName" value="${sessionScope.account.firstName}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Last name:</td>
                        <td><input type="text" name="order.billToLastName" value="${sessionScope.account.lastName}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Address 1:</td>
                        <td><input type="text" size="40" name="order.billAddress1"
                                   value="${sessionScope.account.address1}"/></td>
                    </tr>
                    <tr>
                        <td>Address 2:</td>
                        <td><input type="text" size="40" name="order.billAddress2"
                                   value="${sessionScope.account.address2}"/></td>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td><input type="text" name="order.billCity" value="${sessionScope.account.city}"/></td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td><input type="text" size="4" name="order.billState" value="${sessionScope.account.state}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Zip:</td>
                        <td><input type="text" size="10" name="order.billZip" value="${sessionScope.account.zip}"/></td>
                    </tr>
                    <tr>
                        <td>Country:</td>
                        <td><input type="text" size="15" name="order.billCountry" value="${sessionScope.account.country}"/>
                        </td>
                    </tr>
                    </tbody>
                    <tbody style="padding-left: 37%;padding-top: 3%;" id="leftBox4" class="positive">
                    <tr>
                        <th colspan=2>Shipping Address</th>
                    </tr>

                    <tr>
                        <td>First name:</td>
                        <td><input type="text" name="order.shipToFirstName" value="${sessionScope.account.firstName}"/></td>
                    </tr>
                    <tr>
                        <td>Last name:</td>
                        <td><input type="text" name="order.shipToLastName" value="${sessionScope.account.lastName}"/></td>
                    </tr>
                    <tr>
                        <td>Address 1:</td>
                        <td><input type="text" size="40" name="order.shipAddress1"
                                   value="${sessionScope.account.address1}"/></td>
                    </tr>
                    <tr>
                        <td>Address 2:</td>
                        <td><input type="text" size="40" name="order.shipAddress2"
                                   value="${sessionScope.account.address2}"/></td>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td><input type="text" name="order.shipCity" value="${sessionScope.account.city}"/></td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td><input type="text" size="4" name="order.shipState" value="${sessionScope.account.state}"/></td>
                    </tr>
                    <tr>
                        <td>Zip:</td>
                        <td><input type="text" name="order.shipZip" size="10" value="${sessionScope.account.zip}"/></td>
                    </tr>
                    <tr>
                        <td>Country:</td>
                        <td><input type="text" name="order.shipCountry" value="${sessionScope.account.country}"/></td>
                    </tr>
                    </tbody>
                </table>
                <c:if test="${sessionScope.numberOfItems > 0}">
                <input type="submit" class="Button" value="go to check" style="margin-left: 50%;"/>
                </c:if>
        </form>
    </div>
</div>
<%@ include file="../common/includeBottom.jsp" %>
<script src="JavaScript/Order.js"></script>