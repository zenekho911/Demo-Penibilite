# Demo-Service-Penibilite

**Disclaimer important**  
Ce dépôt est un **projet personnel de démonstration**.  
Il n'est **pas un projet officiel de quiconque organisme**.  
Il ne contient aucune donnée réelle, ni aucun code utilisé en production dans un environnement institutionnel.  

---

## Objectif

Ce projet a été créé **à titre purement pédagogique** afin de simuler une platefome de gestion de la pénibilité au travail.
les Fonctionnalités simulées sont :

- **Employeur** : déclaration de l’exposition d’un salarié.  
- **Agent (fictif)** : validation/rejet d’une exposition.  
- **Salarié** : consultation de ses expositions.  

---

## Côté Backend :

* l’utilisation d’une **architecture Spring Boot + JPA**
* la mise en place d’**entités métiers simples** (salariés, employeurs, agents fictifs, expositions et facteurs de pénibilité)
* l’usage de **DTO / Mappers (MapStruct)**
* une **API REST sécurisée avec Keycloak** (démonstration)
* l’intégration de **Swagger/OpenAPI** pour la documentation des endpoints

## Côté Frontend :

* une application **Angular (100% standalone components)** structurée en modules
* l’authentification et la gestion des rôles via **Keycloak Angular**
* une **navigation dynamique** redirigeant les utilisateurs selon leur rôle :

  * *Salarié* → consultation de ses expositions
  * *Employeur* → déclaration des expositions
  * *Agent* → validation ou rejet des expositions
* une intégration avec le backend via **HttpClient** et un proxy Nginx configuré pour router les appels `/api/**`
* un design responsive et épuré, centré sur la **simplicité d’usage et la lisibilité des données**.

## Stack technique

- Java 17  
- Spring Boot 3  
- Spring Data JPA (MySQL)  
- MapStruct  
- Spring Security + OAuth2 (Keycloak démo)  
- OpenAPI/Swagger UI  
- Angular
- Angular-Keycloak 
- Bootstrap
- Nginx
- Docker

---



## Sécurité

- Sécurisation des endpoints via Keycloak (client démo configuré).  
- Rôles gérés : `ROLE_AGENT`, `ROLE_EMPLOYEUR`, `ROLE_SALARIE`.  
- Les utilisateurs/roles créés sont **fictifs** et uniquement destinés à la démo technique.


## Mode d'emploi
- installer Docker.
- sous Windows aller dans le fichier "C:\Windows\System32\drivers\etc\hosts" et ajouter cette ligne "127.0.0.1 keycloak-penibilite". Il s'agitde  du nom du serveur d'authentification Keycloak qui va être instancié sur votre propre machine (127.0.0.1). Vous aurez besoin de cela pour lancer la prochaine étape.
- aller sur la racine du projet (où se situe le fichier docker-compose.yml) et lancer en bash cette commande : "docker compose up -d".
- une fois la commande precedente terminée, ouvrez votre navigateur et aller sur "localhost:4200" ( car pour cette démo le serveur Nginx sert le port 4200).
- ci-dessous les credentials pour se connecter à la plateforme :

	3 profils SALARIE : 
		login : user_salarie1, user_salarie2, user_salarie3
		password : salarie123
	3 profils EMPLOYEUR : 
		login : user_employeur1, user_employeur2, user_employeur3
		password : employeur123
	2 profils AGENT : 
		login : user_agent1, user_agent2
		password : agent123


## Statut

- **Projet personnel, non officiel**  
- Usage : démonstration technique uniquement  
- Pas de lien avec des environnements réels des organismes d'assurance.  

---





