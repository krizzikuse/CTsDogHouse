# Intro
Wie in der Angabe bereits vorgeschlagen basiert die Applikaton auf Quarkus 
und nutzt eine zwischen den Starts nicht persistente h2-Datenbank.

Ich nutzte diese Gelegenheit auch sogleich um mich mit quarkus zu befassen.

# SetUp/Installation
Grundsätzlich sollte es out of the box funktionieren, d.h.:
1. Die Applikation in die IDE der Wahl impotieren, wobei ich selbst Netbeans 19 und das JDK21 verwendete.
2. Bauen der Applikation innerhalb der IDE + herunterladen der Dependencies, was dank Maven automatisch geschieht.
3. Start der Applikation
4. Testen

# Testing
Postman oder, je nach Vorliebe auch ein anderes Tool, wie etwa netcat verwenden um die folgenden oder davon abgeleitete Nachrichten an die Applikation zu senden.
Aktuell erfolgt die Kommunikation über der Port 8082.
Dies kann, sofern nötig/gewünscht im File src/main/resources/application.properties geändert werden.
Der relevante Parameter wäre:
quarkus.http.port
z.B. wie aktuell: quarkus.http.port=8082

Folgende Nachrichten gibt es grundsätzlich:

	Zweck									Request				URL
	- Abfragen aller Hunde: 						GET				<host>:<port>/dogs
		=> z.B. / aktuell: 										localhost:8082/dogs			
	- Abfragen eines Hundes mit bestimmter ID: 				GET				<host>:<port>/dogs/<id>
		=> z.B. / aktuell um Hund #1 abzufragen: 							localhost:8082/1
	- Anlegen / Kreieren eines Hundes: 					POST				<host>:<port>/dogs
		=> z.B. / aktuell: 										localhost:8082/dogs
	- Ändern eines existenten Hundes: 					PUT				<host>:<port>/dogs/<id>
		=> z.B. / aktuell um Hund #1 abzuändern: 							localhost:8082/1
	- Löschen eines Hundes:							DELETE				<host>:<port>/dogs/<id>
		=> z.B. / aktuell um Hund #1 zu löschen: 							localhost:8082/1
	- Abfragen aller Hunde mit bestimmtem 
		Namen geordnet nach Geburtsdatum:				GET 				<host>:<port>/dogs/findByName?name=<name>
														localhost:8082/dogs/findByName?name=Puppy

Bezüglich des Body, was hier nur POST- und PUT-Requests betrifft, gilt nur folgendes:
Wie auch die Daten die per GET empfangen werden, sind die Nachrichten im JSON-Format zu übertragen.
Hier ein komplettes Beispiel einer GET-Antwort:

```json
{
	"id": 3,
	"birthdate": "2012-12-11",
	"gender": "FEMALE",
	"name": "Kali"
}
```
 
Der Vollständigkeit halber hier ein Beispiel für das anlegen eines Hundes, per POST (bzw. das Ändern mittels PUT):
```json
{
  "name": "Kali",
  "birthdate": "2012-12-11",
  "gender": "FEMALE"
}
```
Testnachrichten sind als als Collection für Postman mitgeliefert und hier zu finden:
	DogHouse\DogHouse Testset.postman_collection.json


Nachfolgend noch die automatisch generierte README, zu der ich lediglich hinzugefügt habe.
Wichtig zu erwähnen ist dass das gesamte Projekt lediglich "quick and dirty" umgesetzt wurde,
ich also etwa das bauen eines Gesamt-jar Files nicht probiert habe, sondern lediglich innerhalb der IDE getestet habe.
Bei Bedarf kann ich selbiges jedoch problemlos nachliefern!


########################################################################################################


# code-with-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
