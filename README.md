Java REST övningar
==================
###Förberedelser
Detta måste vara installerat.  
  
**Java SE Development Kit 7** 
http://www.oracle.com/technetwork/java/javase/downloads  
  
**Maven 3**  
http://maven.apache.org/download.cgi  
  
**Koden**  
https://github.com/thomaspringle/RestKurs
  
**Installera**  
Ladda ner dependencies genom att skriva ```mvn clean install -U``` i projekt-roten.

###Genomgång av projektet
I det befintliga projektet finns Movies-resursen skapad tillsammans med JUnit-test som validerar API:t. Servern körs igång genom att starta main-metoden i ```se.altran.restkurs.main.AltranREST.java``` 
  
Den lyssnar på [http://localhost:8080/webapi/movies](http://localhost:8080/webapi/movies)  
Går du in på ovanstående länk borde ett resultat i JSON-format synas om servern startats.
  
Kör igenom alla JUnit-test under ```/src/test/java``` för att validera att alla test går igenom utan fel. I enhetstesten startas servern upp och ett anrop görs med hjälp av en HTTPClient. Det gör att vi kan testa hur API:t beter sig. 
  
Ta en titt på ```se.altran.restkurs.webapi.movie.MovieResource.java```, det är ett simpelt CRUD-interface och en bra utgångspunkt i övningarna.
  
Vi kommer genomgående att använda JSON som mediatyp för request- och response-meddelanden.

Ett anti-corruption-layer i form av en MovieBean används för att inte blotta domänobjekten rakt ut i Web-API:t. Det är dessa som serialiseras och deserialiseras.

  
----------------
     
###Uppgift 1 - GET
 - Skapa upp GET-metoden i Actor-resursen för att hämta alla Actors.
 - Skapa upp GET-metoden i Actor-resursen för att hämta en specifik Actor.
  
**Arbetsgång:**  
Skapa först upp test på resursen som skall implementeras. Verifiera att testet falerar. Gör implementationen. Verifiera att testet går igenom.	
  
* Utgå ifrån ```ActorResourceTestTemplate.java```. 
* Försök att skapa upp test-metod och resurs på egen hand. 
* Både ```GET /actors``` och ```GET /actors/{actorId}```
* Snegla på befintliga enhetstest och implementation av MovieResource om du fastnar. 
* Använd redan uppskapade ActorService och Actor-domänobjekt för att kunna koncentrera dig på test- och resurs-klasser.

**Hjälp om du fastnar:**
- Kolla på ```MovieResourceGetEmptyTest.java```, ```MovieResourceGetNotEmptyTest.java``` och ```MovieResourceGetSpecificMovieTest.java```.
  
----------------  
  
###Uppgift 2 - POST
Skapa upp POST-metoden i Actor-resursen för att lägga till en Actor.
  
**Arbetsgång:**  
Skapa först upp test på resursen som skall implementeras. Verifiera att testet falerar. Gör implementationen. Verifiera att testet går igenom.	
  
* Använd ```private String getActorJsonFromFile()``` för att läsa in filen ```actor.json``` och POST:a datan.
* POST sker mot ```/actors``` - inte mot ```/actors/{id}``` - servern skapar upp ett id.
* Testa att rätt statuskod returneras när allt går bra: ```201 - Created```.
* Testa att rätt URI returneras när allt går bra: ```/webapi/actors/{nytt id}```.
* Testa att rätt statuskod returneras när felaktig data skickas: ```400 - Bad Request```.

**Hjälp om du fastnar:**
- Kolla på ```MovieResourcePostTest.java```.

  
----------------    
    
###Uppgift 3 - filtrering
Lägg till filtrering: ```GET/actors?firstName=xxx```
  
**Arbetsgång:**  
Lägg till test för att filtrera. Verifiera att testet falerar. Gör implementationen. Verifiera att testet går igenom.
  
* Använd ```ActorResourceGetFilteredTest.java``` som mockar upp ```actorService.getActorsWithFirstName("Rachel")``` vilken returnerar två skådespelare.
  
**Hjälp om du fastnar:**  
- Använd ```@QueryParam("firstName") String firstName``` som parameter i den befintliga metoden som svarar mot ```GET /actors```.
  
----------------  
  
###Uppgift 4 - paginering
Lägg till paginering: ```GET /actors?offset=10&limit=5```
  
**Arbetsgång:**  
Lägg till test för paginering. Verifiera att testet falerar. Gör implementationen. Verifiera att testet går igenom.
  
* Använd ```ActorResourceGetPaginationTest.java``` som mockar upp ```actorService.getActorsWithPagination(10, 5)``` och returnerar fem skådespelare.
  
**Hjälp om du fastnar:**  
- Använd ```@QueryParam("offset") int offset, @QueryParam("limit") int limit``` som parametrar i den befintliga metoden som svarar mot GET /actors.

----------------  
  
###Uppgift 5 - länkar
* Lägg till länkar till filmerna som skådespelare medverkat i: 
```GET /actors/{actorId}/movies ```

**Arbetsgång:** 
Lägg till test för länkar. Verifiera att testet falerar. Gör implementationen. Verifiera att testet går igenom.

* Använd ```ActorResourceGetMovieLinksTest.java``` som mockar upp ```actorService.getActor(uuid)``` och returnerar fyra film-idn.
* Skapa upp en ```List<MovieLinkBean>``` som du returnerar.

**Hjälp om du fastnar:**  
- Bygg upp en URI till filmresursen genom att injecta @Context UriInfo uriInfo som en parameter. Länken till filmresursen kan sedan byggas upp genom uriInfo.getBaseUriBuilder().path(MovieResource.class).path(movieId)
- Skapa upp en ny MovieLinkBean för varje URI, och lägg till i listan som returneras.
- Resultatet bör se ut på följande sätt:
```
[
    {
        "id": "35bc4f81-fc1e-467b-bdcc-8d18010aa346",
        "uri": "/webapi/movies/35bc4f81-fc1e-467b-bdcc-8d18010aa346"
    },
    {
        "id": "c94b8eb3-0c6d-4000-b226-2c77fcafb3d6",
        "uri": "/webapi/movies/c94b8eb3-0c6d-4000-b226-2c77fcafb3d6"
    }
]
```

----------------  
  
###Uppgift 6 - headers
Lägg till autentisering: ```DELETE /actors``` 
  
**Arbetsgång:**  
Lägg till test för autentisering. Verifiera att testet falerar. Gör implementationen. Verifiera att testet går igenom.
  
* Använd ```ActorResourceDeleteTest.java``` som verifierar att rätt userToken parsats ut ur Authorization-headern.
Skicka med "user_token_value" i Authorization-headern.
* Använd ```ActorResourceDeleteTest.java``` som verifierar att rätt userToken parsats ut ur Authorization-headern.
Skicka med "-" i Authorization-headern för att slänga ett UnAuthorized exception.

**Hjälp om du fastnar:**
- Använd ```@HeaderParam("Authorization") String userToken``` som parametrar i den befintliga metoden som svarar mot ```DELETE /actors```.
  

----------------  
  
###Uppgift 7 - extrauppgifter
Redan klar? Här är några förslag på övningar.

**Under-resurser**  
Ta dig en titt på under-resurser, dvs hur resurser kan delas in i mindre delar:
``` @Path("/actors/{id}/movies")
    public Class<ActorMovieResource> getActorMovieResource() {
        return ActorMovieResource.class;
    } 
```
https://jersey.java.net/documentation/latest/jaxrs-resources.html#d0e2146  
  
  
**Partiell respons**  
För att minska storleken på meddelandena kan vi använda partiell respons. Vi kan låta klienten specificera vilka fält i den resulterande JSON-datan som skall exkluderas.
  
Ta en titt på http://wiki.fasterxml.com/JacksonFeatureJsonFilter och försök implementera filtrering på en resurs.
  
**Sökning**  
Sökning kan returnera resultat med olika typer, både film och skådespelare. Fundera på hur detta skulle kunna lösas på bästa sätt och försök göra en implementation.

**Versionering**
Versionering kan skötas genom att antingen lägga på versionsnumret i URI:n eller i Accept-headern.  
När det används i Accept-headern brukar det se ut på följande sätt:  
```Accept: application/vnd.example.v1+json```  
Försök skapa två resurser, en som svarar med version 1 och en för version 2 av API:t.  
(Detta kräver säkert lite googling)  

**Metadata i paginering**  
Ett vanligt mönster är att returnera en metadata-del i JSON-datan för att berätta vilken sida som datan representerar.  
```  
{  
    "movies": [ . . .  ],  
    "__metadata": { "total": 93, "limit": 15, "offset": 30 }  
}  
```
Försök att åstadkomma detta på en resurs med paginering.
