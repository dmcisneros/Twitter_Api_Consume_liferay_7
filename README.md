# Codefest_Example

Ejemplo de portlet en Liferay 7, desarrollado en MVC Portlet. 

Puedes encontrar la ponencia en la carpeta raíz del proyecto con el nombre "PonenciaLiferay.pptx"



La funcionalidad del portlet será una consulta sobre la API de Twitter para obtener los twits que tengan un hashtag determinadao, es necesario añadir los siguientes properties al fichero portal-ext.properties:

OAuthConsumerKey=${yourOAuthConsumerKey}
OAuthConsumerSecret=${yourOAuthConsumerSecret}
OAuthAccessTokenSecret=${yourOAuthAccessTokenSecret}
OAuthAccessToken=${yourOAuthAccessToken}
hashtag=Codefest_2016

