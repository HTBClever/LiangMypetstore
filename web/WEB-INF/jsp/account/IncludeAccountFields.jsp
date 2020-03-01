<h3>Account Information</h3>

<table>
	<c:if test="${sessionScope.account!=null}">
		<tr>
			<td>First name:</td>
			<td><input type="text" name="account.firstName" value="${sessionScope.account.firstName}" autocomplete="off"/></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input type="text" name="account.lastName" value="${sessionScope.account.lastName}" autocomplete="off"/></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="account.email" value="${sessionScope.account.email}" autocomplete="off"/></td>
		</tr>
		<tr>
			<td>Phone:</td>
			<td><input type="text" autocomplete="off" name="account.phone" value="${sessionScope.account.phone}"/></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><input type="text" autocomplete="off" name="account.address1" size="40" value="${sessionScope.account.address1}"/></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><input type="text" autocomplete="off" name="account.address2" size="40" value="${sessionScope.account.address2}"/></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input type="text" autocomplete="off" name="account.city" value="${sessionScope.account.city}"/></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><input type="text" autocomplete="off" name="account.state" size="4" value="${sessionScope.account.state}"/></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><input type="text" autocomplete="off" name="account.zip" size="10" value="${sessionScope.account.zip}"/></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><input type="text" autocomplete="off" name="account.country" size="15" value="${sessionScope.account.country}"/></td>
		</tr>
		</table>

		<h3>Profile Information</h3>

		<table>
		<tr>
			<td>Language Preference:</td>

			<td><select name="account.languagePreference" >
				<c:forEach items="${sessionScope.language}" var="lan">
				<option  value="japanese" <c:if test="${sessionScope.account.languagePreference==lan}">selected="selected"</c:if> >${lan}</option>
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td>Favourite Category:</td>
			<td>
				<select name="account.favouriteCategoryId" >
					<<c:forEach items="${sessionScope.favouriteCategoryId}" var="favouriteCategory">
					<option  value="${favouriteCategory}" <c:if test="${sessionScope.account.favouriteCategoryId==favouriteCategory}">selected="selected"</c:if> >${favouriteCategory}</option>
				</c:forEach>
				</select>
		</tr>
		<tr>
			<td>Enable MyList</td>
			<td><input type="checkbox" name="account.listOption" value="${sessionScope.account.listOption}" <c:if test="${sessionScope.account.listOption}">checked="checked"</c:if>/></td>
		</tr>
		<tr>
			<td>Enable MyBanner</td>
		<td><input type="checkbox" name="account.bannerOption" value="${sessionScope.account.bannerOption}" <c:if test="${sessionScope.account.bannerOption}">checked="checked"</c:if>/></td>
		</tr>

	</c:if>
	<c:if test="${sessionScope.account==null}">
		<tr>
			<td>First name:</td>
			<td><input type="text" autocomplete="off" name="account.firstName" required="required"/></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input type="text" autocomplete="off" name="account.lastName" required="required"/></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" autocomplete="off" name="account.email" required="required"/></td>
		</tr>
		<tr>
			<td>Phone:</td>
			<td><input type="text" autocomplete="off" name="account.phone" required="required"/></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><input type="text" autocomplete="off" name="account.address1" size="40" required="required"/></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><input type="text" autocomplete="off" name="account.address2" size="40" required="required"/></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input type="text" autocomplete="off" name="account.city" required="required"/></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><input type="text" autocomplete="off" name="account.state" size="4" required="required"/></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><input type="text" autocomplete="off" name="account.zip" size="10" required="required"/></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><input type="text" autocomplete="off" name="account.country" size="15" required="required"/></td>
		</tr>
		</table>

		<h3>Profile Information</h3>

		<table>
		<tr>
			<td>Language Preference:</td>
			<td><select name="account.languagePreference">
				<option value="japanese">japanese</option>
				<option value="english">english</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>Favourite Category:</td>
			<td>
				<select name="account.favouriteCategoryId">
					<option value="BIRDS">BIRDS</option>
					<option value="CATS">CATS</option>
					<option value="DOGS">DOGS</option>
					<option value="FISH">FISH</option>
					<option value="REPTILES">REPTILES</option>
				</select>
		</tr>
		<tr>
			<td>Enable MyList</td>
			<td><input type="checkbox" name="account.listOption" value="Enable MyList"></td>
		</tr>
		<tr>
			<td>Enable MyBanner</td>
			<td><input type="checkbox" name="account.bannerOption" value="Enable MyBanner"></td>
		</tr>
	</c:if>

</table>
