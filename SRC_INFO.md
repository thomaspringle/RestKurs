Genomgång av projektet
==================
###Embedded Jetty
För att kunna starta applikations-servern utan extra plugins startas servern i en vanlig main-metod: ```se.altran.restkurs.main.AltranREST.java```
  
Det här gör att vi enkelt kan starta upp servern från test och anropa REST API:t på samma sätt som klienterna gör. Vi ser väldigt tydligt HTTP-anropet och svaret, vilket är perfekt i utbildningssyfte.

  
###Guice 
Google Guice används som DI-ramverk med två moduler, DomainModule.java för domän-klasser och RESTServletModule.java för REST API-specifika klasser. 
RESTServletModule binder ihop MovieResource- och ActorResource-klasserna med Jersey, ser till att JSON-serialisering och deserialisering hanteras av Jacksson och mappar olika Exceptions mot HTTP-respons. 
  
