Projet Backend - SEN-Bank
Le projet backend SEN-Bank est une application bancaire électronique qui offre une gestion complète des comptes, des transactions, et des fonctionnalités avancées telles que les notifications en temps réel.

Technologies Utilisées
Java avec Spring Boot
Base de données MySQL
JWT pour l'authentification
WebSockets pour les notifications en temps réel
Structure du Projet
Le projet est organisé en modules distincts pour assurer la séparation des préoccupations et la facilité de maintenance.
sen-bank-backend
|-- src
|   |-- main
|       |-- java
|           |-- com.forcen.senbank
|               |-- domain
|                   |-- User.java
|                   |-- Transaction.java
|                   |-- Compte.java
|                   |-- Beneficiaire.java
|                   |-- TypeDeCompte.java
|               |-- security
|                   |-- JwtTokenProvider.java
|               |-- service
|                   |-- UserService.java
|                   |-- TransactionService.java
|                   |-- CompteService.java
|                   |-- BeneficiaireService.java
|                   |-- TypeDeCompteService.java
|               |-- controller
|                   |-- UserController.java
|                   |-- TransactionController.java
|                   |-- CompteController.java
|                   |-- BeneficiaireController.java
|                   |-- TypeDeCompteController.java
|       |-- resources
|           |-- application.properties
|-- pom.xml
|-- README.md

Entités
1. User
Représente un utilisateur de la plateforme bancaire.

Attributs :

Id
Nom
Email
Mot de passe (haché)
Liste de comptes associés
Rôle (Client, Admin)
2. Transaction
Représente une transaction entre deux comptes.

Attributs :

Id
Numéro de transaction unique
Montant
Date de la transaction
Compte émetteur
Compte bénéficiaire
3. Compte
Représente un compte bancaire associé à un utilisateur.

Attributs :

Id
Numéro de compte unique
Solde
Découvert autorisé
Type de compte
Liste de transactions émises
Liste de transactions reçues
4. Beneficiaire
Représente un bénéficiaire ajouté par un utilisateur pour des transferts réguliers.

Attributs :

Id
Nom
Numéro de compte
Utilisateur associé
5. TypeDeCompte
Représente le type de compte, par exemple, compte courant ou compte d'épargne.

Attributs :

Id
Nom du type de compte
Taux d'intérêt (le cas échéant)
Frais de transaction (le cas échéant)
Fonctionnalités
Authentification et Autorisation

JWT est utilisé pour l'authentification et la génération de jetons.
Les rôles (Client, Admin) déterminent les autorisations.
Gestion des Comptes

Création, modification et suppression de comptes.
Consultation des soldes et des historiques.
Transactions

Transferts d'argent entre comptes.
Consultation des transactions.
Gestion des bénéficiaires.
Notifications en Temps Réel

Utilisation de WebSockets pour des notifications instantanées.
Réception en temps réel des mises à jour sur les transactions et les changements de solde.
Alertes instantanées pour les transactions importantes.
Notifications

Notification des transactions.
Alertes de solde.
