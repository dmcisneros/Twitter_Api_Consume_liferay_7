
<%@page import="twitter4j.Status"%>
<%@page import="twitter4j.QueryResult"%>
<%@page import="com.everis.codefest.example.Constants.Constants"%>

<portlet:defineObjects />

<%
QueryResult resultados = (QueryResult) request.getAttribute(Constants.RESULTS);

for (Status resultado : resultados.getTweets()) {
	
%>

<div class="card card-horizontal">
    <div class="card-row">
        <div class="card-col-field">
            <img alt="thumbnail" src="<%=resultado.getUser().getBiggerProfileImageURL() %>" style="width: 150px;">
        </div>

        <div class="card-col-content card-col-gutters">
            <div> <%=resultado.getUser().getName() %> </div>
            <div> <%=resultado.getText() %> </div>
        </div>
    </div>
</div>

<%}%>




