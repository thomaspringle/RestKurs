Genomgång av projektet
==================
###Embedded Jetty
För att kunna starta applikations-servern utan extra plugins startas servern i en vanlig main-metod: ```se.altran.restkurs.main.AltranREST.java```
  
Det här gör att vi enkelt kan starta upp servern från test och anropa REST API:t på samma sätt som klienterna gör. Vi ser väldigt tydligt HTTP-anropet och svaret, vilket är perfekt i utbildningssyfte.

  
###Guice
Google Guice används som DI-ramverk med två moduler, DomainModule.java för domän-klasser och RESTServletModule.java för REST API-specifika klasser. 
RESTServletModule binder ihop MovieResource- och ActorResource-klasserna med Jersey, ser till att JSON-serialisering och deserialisering hanteras av Jacksson och mappar olika Exceptions mot HTTP-respons. 
  

###Test
JUnit test används för att utföra integrationstest mot REST API:t. 
* För den här kursen startas servern och olika domänobjekt injectas i DomainModule i en metod som annoteras med @Before.
* Servern stoppas sedan i en metod annoterad med @After.

Under ```se.altran.restkurs.webapi.movie``` finns en uppsättning test för att testa GET, PUT, POST och DELETE i API:t - en bra utgångspunkt för att skriva egna tester.  
  
Använd hjälpklassen ```se.altran.restkurs.webapiHttpHelper``` för att exekevera HTTP-Metoder och returnera Responsen:
```java
		// Create POST command to /webapi/movies
		HttpHelper httpHelper = new HttpHelper("127.0.0.1", 8090);
		HttpPost httpPost = new HttpPost("/webapi/movies");
		httpPost.setHeader("Accept", "application/json");

		// Define data to be posted
		String data = "{\"title\": \"Gravity\", \"year\": 2013}";
		httpPost.setEntity(new StringEntity(data, ContentType.create("application/json")));    
```