<%@taglib prefix="f1" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp" %>
<div class="container" >
<div style="padding-top:40px;"></div>
<p>
Product Details
<f1:form modelAttribute="product" method="post">
<%
//String s=session.getAttribute("userId").toString();
String s1=new String();

s1=request.getParameter("name");;
String s2=new String();
s2=request.getParameter("desc");;
String s3=new String();
s3=request.getParameter("id");;
String s4=new String();
s4=request.getParameter("brand");;
String s5=new String();
s5=request.getParameter("price");;
out.println(s1+"  "+s2+" "+s3+" "+s4+" "+s5);

%>
<div>
						<div class="col-md-5 product-qty">
							Quantity
							 <input class="btn btn-default btn-lg btn-qty" value="1" /> 
								
						</div>
						</div>
<div>
		<a href="${session.getContextPath()}/demoapp/Order/1" role="button"
		name="_eventId_submit"
		class="btn btn-lg btn-primary btn-full-width">Add to Cart  
		</a>
</div>
</f1:form>
 </p>
 
</div>
<%@ include file="footer.jsp" %>