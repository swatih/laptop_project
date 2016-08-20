

<div>
	<%@ include file="header.jsp"%>
</div>

<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<p>
&nbsp;
</p>
<p>
&nbsp;
</p>
<p>
&nbsp;
</p>
<p>
&nbsp;
</p>
<div class="container">
<sf:form modelAttribute="uaddress" method="post">
<h1>Thankyou for Shoping </h1>

Hello ${uaddress.cname}
<h3>Your Order is generated and Order No-ORDAB00</h3>

						<table class="table table-striped">
						<thead>
							<tr>

								<th>Name</th>
								<th>Brand</th>
								<th>Description</th>
								
								<th>Quantity</th>
								<th>Amount</th>
								
							</tr>
						</thead>

						<tbody>


							<c:forEach items="${sessionScope.cart.listitem}" var="product">
								<c:set var="sum" value="${sum+ product.p.pprice* product.quantity}"></c:set>
								<tr>
									
									<td>${product.p.pname}</td>
									<td>${product.p.pbrand}</td>
									<td>${product.p.pdescription}</td>
									<td>${product.quantity}</td>
									<td>${product.p.pprice* product.quantity}</td>
									
									
								</tr>
							</c:forEach>
							<tr>
							<td></td>
							<td></td>
							<td></td>
							<td>
							Total Amount:
							</td>
							<td> ${sum}</td>
							
							</tr>
<!-- <tr><td></td></tr> -->
							<%-- <tr>
								<td>${product.pname}</td>
								<td>${product.pbrand}</td>
								<td>${product.pdescription}</td>
								<td>${product.pprice}</td>
								<td>1</td>
								<td>${product.pprice*1}</td>
							</tr> --%>

						</tbody>

					</table>
					
				
					<input name="_eventId_submit" class="btn btn-info" type="submit" value="Home" /><br />
				
				</sf:form>
</div>
<div>
	<%@ include file="footer.jsp"%>
</div>
