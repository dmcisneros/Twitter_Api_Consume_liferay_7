
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@page import="twitter4j.MediaEntity"%>
<%@page import="twitter4j.Status"%>
<%@page import="twitter4j.QueryResult"%>
<%@page import="com.everis.codefest.example.Constants.Constants"%>

<portlet:defineObjects />

<portlet:resourceURL var="resourceURL" />
<aui:container id="tweetsContainer">
	<%
		QueryResult resultados = (QueryResult) request.getAttribute(Constants.RESULTS);

		for (Status resultado : resultados.getTweets()) {
	%>

	<div class="card card-horizontal" style="width: 70%; margin: auto;">
		<div class="card-row">
			<div class="card-col-field" style="vertical-align: top">
				<img alt="thumbnail"
					src="<%=resultado.getUser().getBiggerProfileImageURL()%>"
					style="width: 75px; margin: 7%; border: 2px solid; border-radius: 10%;">
			</div>

			<div class="card-col-content card-col-gutters">
				<div>
					<b><%=resultado.getUser().getName()%></b>
				</div>
				<div>
					<%=resultado.getText()%>
				</div>

				<%
					for (MediaEntity entry : resultado.getMediaEntities()) {
				%>
				<div>
					<img style="width: 80%; margin-bottom: 1%; border-radius: 2%;"
						src="<%=entry.getMediaURL()%>">
				</div>
				<%
					}
				%>

			</div>

		</div>
	</div>

	<%
		}
	%>
</aui:container>

<aui:script use="aui-base,aui-io-request,aui-node"> 

/*setInterval(updateDiv,20000);*/

function updateDiv(){
A.io.request('<%=resourceURL%>', {
	            method: 'POST',
	            dataType: 'text',
	             data: {
	            },
	             sync: true,
	             on: {
	                 success: function() {
	                  var response = this.get('responseData');
	                  var dataRefresh = response + A.one('#<portlet:namespace/>tweetsContainer').html();
	                  
	                  if(dataRefresh != null) {
	                	 console.log(dataRefresh);
	                	 console.log("Entro");
	                  	A.one('#<portlet:namespace/>tweetsContainer').html(dataRefresh);
	                  }
	                  
	                  return;                                        
	                },
	                failure: function() {
	                	console.error('Error al recuperar los tweets');
	                }
	            }
	        });
}

</aui:script>
	



