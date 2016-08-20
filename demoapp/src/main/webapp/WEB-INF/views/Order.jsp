
<%@page import="org.apache.taglibs.standard.tag.common.xml.ForEachTag"%>
<div>
	<%@ include file="header.jsp"%>
</div>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>


<script src="<c:url value="${btstrpjs }/angular.min.js"/>"></script>
<script>
  function delete1(id)
{
	 alert("hi");
	 window.location="http://localhost:8080/autobazaar/delete/"+id;
	 
	}  
</script>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<script>
	/* 	var app = angular.module("page", []).controller("innersection",
	 function($scope) {
	 $scope.products = $
	 {
	 sessionScope.cart
	 }
	 ;
	 // $scope.products = product;

	 }); */
</script>

<div ng-app="page" class="container">
	<div ng-controller="innersection">




		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<sf:form modelAttribute="cart" method="post">

					<c:set var="s" value="0"></c:set>

					<table class="table table-striped">
						<thead>
							<tr>

								<th>Name</th>
								<th>Brand</th>
								<th>Description</th>
								
								<th>Quantity</th>
								<th>Amount</th>
								<th>Delete Order</th>
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
									
									<td><a class="btn btn-danger"
								href="<c:url value='${session.getContextPath()}/delete/${product.p.pid}' />">Delete  <span class="glyphicon glyphicon-remove-sign"></span></a></td>
										 
										<!-- <td><button class="btn btn-danger" name="_eventId_edit">Delete</button></td> --> 
									<%-- 	<sf:input type="hidden" path="product.p.pid"></sf:input> --%>
								</tr>
							</c:forEach>
							
							<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>
							Total Amount:
							</td>
							<td> ${sum}</td>
							
							</tr>

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
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<%-- <sf:input type="hidden" path="pid"></sf:input> --%>
							<input class="btn btn-info" name="_eventId_submit" type="submit"
								value="Next" />
						</div>
					</div>
					<br />

				</sf:form>

			</div>
		</div>

	</div>
</div>

<div>
	<%@ include file="footer.jsp"%>
</div>