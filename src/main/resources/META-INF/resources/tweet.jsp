<%@page import="com.everis.codefest.example.Constants.Constants"%>
<%@page import="twitter4j.MediaEntity"%>
<%@page import="twitter4j.Status"%>
<%@page import="java.util.List"%>
<%
	List<Status> resultados = (List<Status>) request.getAttribute(Constants.TWEETS_RESULTS);
	for (Status resultado : resultados) {
%>

<div class="card card-horizontal" style="width: 70%;margin: auto;">
    <div class="card-row">
        <div class="card-col-field" style="vertical-align: top">
            <img alt="thumbnail" src="<%=resultado.getUser().getBiggerProfileImageURL() %>" 
            		style="width: 75px; margin: 7%; border: 2px solid; border-radius: 10%;">
        </div>

        <div class="card-col-content card-col-gutters">
            <div> <%=resultado.getUser().getName() %> </div>
            <div> <%=resultado.getText() %> </div>
            
        <%        
        for(MediaEntity entry : resultado.getMediaEntities()) {
        %>
            <div><img style="width: 80%;margin-bottom: 1%;border-radius: 2%;" 
            		  src="<%=entry.getMediaURL() %>">
           	</div>
        <%}%>
       
        </div>

    </div>
</div>
<%
	}
%>