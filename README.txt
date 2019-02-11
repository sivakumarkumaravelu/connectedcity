Open the project file from the extracted zip file in eclipse IDE.

Run com.admarketplace.connection.ConnectedCities (contains main()) passing the correct arguments to see if cities are connected.

Following Unit tests (running green) added under src/test/java

com.admarketplace.connection.TestConnectedCities

java -cp target\connection-1.0.0-SNAPSHOT.jar com.admarketplace.connection.ConnectedCities

extract the zip into a folder
go to command prompt and type
mvnw clean
mvnw package
mvnw exec:java
mvnw test




