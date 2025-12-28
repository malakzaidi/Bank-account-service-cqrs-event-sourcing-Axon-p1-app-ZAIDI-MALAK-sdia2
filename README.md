# Service de Compte Bancaire - CQRS & Event Sourcing avec Axon Framework

Une application bancaire complète implémentant les patterns CQRS (Command Query Responsibility Segregation) et Event Sourcing en utilisant le framework Axon. Ce projet démontre les principes modernes d'architecture microservices pour la gestion de comptes bancaires avec un historique complet d'audit et des opérations pilotées par événements.

<img width="1052" height="706" alt="image" src="https://github.com/user-attachments/assets/7996e204-2b99-4974-92e7-a80de12a3b83" />


<img width="1918" height="716" alt="Screenshot 2025-12-28 192841" src="https://github.com/user-attachments/assets/cb78ba47-cf54-4df1-b1e5-b679f4413bce" />



<img width="1855" height="467" alt="image" src="https://github.com/user-attachments/assets/80cff596-fc5c-4afe-a14e-2c527dff921c" />

<img width="1834" height="915" alt="image" src="https://github.com/user-attachments/assets/3ccdb767-efca-44bc-a69b-dcb19b8dcbb2" />



[Captures d'Écran](#captures-décran)

## Aperçu

Ce projet implémente un système de gestion de comptes bancaires en utilisant les patterns architecturaux CQRS et Event Sourcing. L'application sépare les opérations de lecture et d'écriture, maintient un historique complet des événements, et fournit des opérations de compte en temps réel incluant les dépôts, les retraits et les virements.


## Démarrage du docker compsose contenant la base de données et le serveur Axon

<img width="1241" height="323" alt="Screenshot 2025-12-22 032754" src="https://github.com/user-attachments/assets/99d6cd87-6cfa-4ca7-b6dc-96fc93046793" />

## Apercu de la base de données h2 de token-entry

<img width="1686" height="867" alt="image" src="https://github.com/user-attachments/assets/00e0be5a-a8f2-498a-9fc3-3ed69bb320c7" />


### Tableau de Bord Axon Server
Le tableau de bord d'Axon Server montrant le traitement des événements et la gestion des commandes.

## L'interface Axon : 


- Nodes et Applications : l application a aparu dans l'interface Axon

<img width="1890" height="999" alt="image" src="https://github.com/user-attachments/assets/dfb9b1f5-b594-4c1d-a719-f9431beac88a" />

<img width="1912" height="930" alt="image" src="https://github.com/user-attachments/assets/21405d78-feff-44b0-a33e-524442830727" />

<img width="1910" height="1031" alt="image" src="https://github.com/user-attachments/assets/1fd205cb-bc82-438d-90b9-eaeab33b7057" />

- Les événements :

<img width="1904" height="1060" alt="image" src="https://github.com/user-attachments/assets/1368ddf9-f18f-4ca3-bf7b-78c8faa682a6" />

- Détails de l'événement :

<img width="1912" height="1050" alt="image" src="https://github.com/user-attachments/assets/4f926ce4-cd5a-413b-bc64-69216590328c" />

<img width="1913" height="1065" alt="image" src="https://github.com/user-attachments/assets/816c53be-5e46-4683-90b8-5f54d4954c38" />
  


## Architecture

L'application suit le pattern CQRS avec Event Sourcing :

**Côté Commande (Modèle d'Écriture)**
- Gère toutes les opérations de changement d'état
- Valide les règles métier
- Émet des événements de domaine
- Utilise les agrégats du framework Axon

**Côté Requête (Modèle de Lecture)**
- Optimisé pour les opérations de lecture
- Vues matérialisées à partir des événements
- Gestionnaires de projection
- Base de données séparée pour les requêtes

**Event Store**
- Piste d'audit complète
- Capacité de rejeu d'événements
- Requêtes temporelles
- Source de vérité pour l'état du système

## Technologies Utilisées

**Backend**
- Java 
- Spring Boot
- Axon Framework
- Axon Server
- Spring Data JPA
- Base de données H2 / MySQL
- Maven

**Frontend**
- Angular
- TypeScript
- HTML/CSS
- Bootstrap

## Structure du Projet

```
bank-account-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/bankaccount/
│   │   │       ├── commands/
│   │   │       │   ├── aggregates/
│   │   │       │   ├── controllers/
│   │   │       │   └── dto/
│   │   │       ├── query/
│   │   │       │   ├── entities/
│   │   │       │   ├── repositories/
│   │   │       │   ├── controllers/
│   │   │       │   └── projections/
│   │   │       ├── commonapi/
│   │   │       │   ├── commands/
│   │   │       │   ├── events/
│   │   │       │   ├── queries/
│   │   │       │   └── dto/
│   │   │       └── BankAccountApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Fonctionnalités

### Gestion des Comptes
- Créer de nouveaux comptes bancaires
- Consulter les détails des comptes
- Lister tous les comptes
- Mises à jour du solde en temps réel

### Transactions
- Opérations de crédit (dépôts)
- Opérations de débit (retraits)
- Virements entre comptes
- Historique des transactions
- Validation du solde

### Event Sourcing
- Journal complet des événements
- Capacité de rejeu des événements
- Piste d'audit pour toutes les opérations
- Requêtes temporelles

### Surveillance et Visualisation
- Tableau de bord du statut des comptes
- Visualisation des transactions
- Surveillance du flux d'événements
- Mises à jour en temps réel

## Démarrage

### Prérequis

- JDK 11 ou supérieur
- Maven 3.6+
- Node.js et npm (pour le frontend Angular)
- Axon Server (à télécharger depuis AxonIQ)

### Installation

1. Cloner le dépôt :
```bash
git clone https://github.com/malakzaidi/Bank-account-service-cqrs-event-sourcing-Axon-p1-app-ZAIDI-MALAK-sdia2.git
cd Bank-account-service-cqrs-event-sourcing-Axon-p1-app-ZAIDI-MALAK-sdia2
```

2. Démarrer Axon Server :
```bash
java -jar axonserver.jar
```

3. Compiler et exécuter l'application Spring Boot :
```bash
mvn clean install
mvn spring-boot:run
```

4. Installer et exécuter le frontend Angular :
```bash
cd frontend
npm install
ng serve
```

5. Accéder à l'application :
- Frontend : http://localhost:4200
- API Backend : http://localhost:8080
- Tableau de bord Axon Server : http://localhost:8024

## Points de Terminaison API

### Commandes (Opérations d'Écriture)

**Créer un Compte**
```
POST /commands/account/create
Body: {
  "initialBalance": 1000,
  "currency": "USD"
}
```

**Créditer un Compte**
```
PUT /commands/account/credit
Body: {
  "accountId": "uuid",
  "amount": 500,
  "currency": "USD"
}
```

**Débiter un Compte**
```
PUT /commands/account/debit
Body: {
  "accountId": "uuid",
  "amount": 200,
  "currency": "USD"
}
```

### Requêtes (Opérations de Lecture)

**Obtenir Tous les Comptes**
```
GET /query/accounts
```

**Obtenir un Compte par ID**
```
GET /query/accounts/{accountId}
```

**Obtenir les Événements d'un Compte**
```
GET /query/accounts/{accountId}/events
```

## Event Sourcing

L'application implémente l'Event Sourcing avec les événements suivants :

**AccountCreatedEvent**
- Émis lors de la création d'un nouveau compte
- Contient : accountId, initialBalance, currency

**AccountCreditedEvent**
- Émis lors d'un dépôt d'argent
- Contient : accountId, amount, currency

**AccountDebitedEvent**
- Émis lors d'un retrait d'argent
- Contient : accountId, amount, currency

Tous les événements sont stockés dans l'event store d'Axon Server et peuvent être rejoués pour reconstruire l'état du compte.

## Implémentation CQRS

### Côté Commande

**Agrégat : AccountAggregate**
- Gère les commandes
- Valide les règles métier
- Émet des événements
- Maintient la cohérence

**Commandes :**
- CreateAccountCommand
- CreditAccountCommand
- DebitAccountCommand

### Côté Requête

**Entité : Account**
- Modèle optimisé pour la lecture
- Projection à partir des événements
- Stocké dans une base de données relationnelle

**Projections :**
- AccountProjection : Écoute les événements et met à jour le modèle de lecture


## Configuration

Modifier `application.properties` pour configurer :

```properties
# Configuration Axon
axon.axonserver.servers=localhost:8124

# Configuration Base de Données
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update

# Configuration Serveur
server.port=8080
```

## Concepts Clés

### CQRS (Command Query Responsibility Segregation)
Séparation des responsabilités entre les opérations de lecture et d'écriture, permettant une optimisation indépendante de chaque côté.

### Event Sourcing
Stockage de tous les changements d'état comme une séquence d'événements, offrant une traçabilité complète et la possibilité de reconstruire l'état à tout moment.

### Axon Framework
Framework facilitant l'implémentation de CQRS et Event Sourcing avec des abstractions de haut niveau et une infrastructure robuste.

## Auteur

ZAIDI MALAK - SDIA2

