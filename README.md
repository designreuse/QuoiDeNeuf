# Quoi De Neuf ?
- Projet Long BDD 2015/2016.
- Binôme : Alexandre VASTRA, Zakaria ZEMMIRI.
- Description : _KoiDe9_ est un système de messagerie WEB permettant aux utilisateurs de s’envoyer des messages et des photos.

## PLAN :
  1. Déploiement
  2. Synthèse
    - Objectif du travail
    - Ce qui a été fait
    - Les améliorations à apporter
    - En quoi ce projet était difﬁcile
    - Conclusion

  3. La description technique du projet
    - Objectif techniques du travail
    - Principe de réalisation
    - Difﬁcultés techniques rencontrées et solutions apportées.

  4. Conclusion
    - La documentation utilisateur (tutorial)
    - Objectif de cette application
    - Du point de vue de l’utilisateur


## 1. DEPLOIEMENT
1. Importer le fichier script.sql dans la base de données afin de créer les tables nécessaires, ainsi que les utilisateurs par defaut.
2. Copier le fichier KoiDe9.war dans le dossier webapps de Tomcat.
3. Configurer l'accès à la base de données dans le fichier META-INF/context.xml.
4. Lancer tomcat.
5. lancer l'application : http://localhost:8080/KoiDe9/


Liste utilisateurs par defaut (pour tester l'application) :

| Login | Mot de passe |
|-------|--------------|
|zakaria|      123        |
|alexandre|       123       |
|philippe|      123        |


## 2. SYNTHESE

#### 2.1 OBJECTIF DU TRAVAIL
Realiser un système de messagerie WEB permettant aux utilisateurs de s’envoyer des messages et des photos.
Un utilisateur doit s'inscrire afin de pouvoir utiliser l'application. Après inscription il dispose :
- D'un avatar par défaut qu'il peut changer ultérieurement.
- De 4 groupes par défaut : Amis, Famille, Pro et Autre.

*Dès lors, l'utilisateur peut* :
 - Personnaliser son proﬁl.
 - Rechercher/Ajouter des nouveaux contacts.
 - Consulter le profil des autres utilisateurs et voir la liste de leurs amis.
 - Ajouter un utilisateur à un ou plusieurs groupes.
 - Créer/Supprimer des groupes d’utilisateurs.
 - Envoyer des messages ou des photos aux contacts ou aux groupes.
 - Visualiser les anciens messages déjà envoyés à un contact ou groupe.
 - Changer le theme de l'application.

#### 2.2 CE QUI A ÉTÉ FAIT
  - Tout !
  - Tests de montée en charge.
  - Refactoring et optimisation du code.

#### 2.3 LES AMÉLIORATIONS À APPORTER
Avec un peu plus de temps,  On aurait pu apporter quelques ameliorations :
- Integerer la creation des tables dans l'application.
- Recuperation des mots de passes par Mail.
- Voir les utilisateurs connecté en temps réel.
- Ameliorer la compatibilité avec certains navigateurs.
- SSL...

#### 2.4 EN QUOI CE PROJET ÉTAIT DIFFICILE
- La difficulté principale été de realiser un MCD correct qui repond à l'ensemble des problematiques de cette appliction.
- Faire un choix d'architecture pour l'application.

#### 2.5 CONCLUSION



## 3. LA DESCRIPTION TECHNIQUE DU PROJET

#### 3.1 OBJECTIF TECHNIQUES DU TRAVAIL
- Utilisé un maximum de technologies pour mener à bien ce projet.

#### 3.2 PRINCIPE DE RÉALISATION

###### 3.2.1 Librairies utilisées
- Gson : librairie google pour la gestion des flux json.
- jQuery : pour les appels Ajax, et autre manipulations du DOM.
- Log4j : pour la génération de logs sur la sortie standard et dans un fichier.
- El expressions + JSTL : pour plus de simplicité d'écriture dans les jsp.
- Bootstap.

###### 3.2.2 Sécurité & Infos
 1. [x] Protection contre les injections SQL (PreparedStatement).
 2. [x] Protection contre les injections XSS.
 3. [x] Vérification des données envoyées cotés client et serveur.
 4. [x] Filtre pour limiter l'accès aux pages privées.
 5. [x] Log de toute activité : Adresse IP, port, date, etc.….  
 6. [x] Hashage des mots de passes.
 7. [x] Gestion des erreurs : ErrorPage.
 8. [x] Validation des formulaires.
 9. [x] Ergonomie du site.

###### 3.2.3 Architecture

 ![Architecture](./Docs/archi.png)

 L'application est construite selon le pattern MVC. Avec deux parties distinctes client (front-end) et serveur (contient l'ensemble des services disponibles).
 Chaque partie est construite selon un ou plusieurs patterns pour facilite le développement et la maintenance.
 Les deux parties communiquent en utilisant un protocole ad ‘hoc écrit en JSon à mi-chemin entre celui des communications REST et l'enveloppe SOAP.

 - La communication avec les services se fait donc via des requêtes HTTP, avec un format prédéfini.

 Format de communication :
 - Requête : ```{nomService : "NomDuService",  data : {} }```
 - Réponse : ```{returnCode : 0, message : "", data : [] }```

 Détail Format Requête :
 ```
 nomService : celui qui est le servicefactory.
 data : données envoyées au services.
 ```

 Détail Format Réponse :
 ```
 Codes Retour :
 0 : OK : Tout s'est bien passé.
 4 : Warning : une remarque ou une notification sur le contenu de la réponse.
 8 : Error : un problème s'est produit.

 Message : Permet de donner des informations complémentaires sur le code retour.
 data : contient les véritables données envoyées par le service.
 ```

Une requête client passe par deux filtres :
 - Le premier filtre vérifie si l'utilisateur dispose des droit d'accès à la page demandée. si l'utilisateur est connecté la requête est autorisée a passée sinon elle est bloquée et l'utilisateur se voit diriger vers la page de connexion.
 - Le deuxième filtre permet de vérifier la validité de la requête. Une requête valide doit respecter le format du protocole précédemment détaillé. Si ce format n'est pas respecté, la requête est bloquée et une réponse est envoyée au client lui indiquant la nature du problème.

 Une autre vérification est réalisée au niveau du service. Elle permet de vérifier si le client a bien envoyé les données requises à l'exécution du service et aussi dans le bon format, si ce n'est pas le cas, la requête est bloquée et une réponse est envoyée au client lui indiquant la nature du problème.

 La servlet agit comme un « contrôleur ». elle permet la redirection des requêtes vers le bon service, mais aussi l'envoie de la réponse au client.

 Remarque : Toute données est vérifié en plus sur le client avant envoie au serveur.

- ### Coté Serveur :

  ![servicelocator pattern](./Docs/servicelocator.jpg)


L'implementation des services est une variante du pattern "Locator" semblable à celui qu'on peut trouver lors de l'utilisation des composant JNDI. l'avantage avec ce type de methode est le fait que tous les appels service se font vers une seule et unique url. Et c'est le contrôleur qui sélectionne le bon service.

- ### Coté Client :

 Pour l'utilisateur, L'application est constituée de deux parties :
 - Une partie publique que tout le monde peut consulter, qui est la page de connexion.
 - une partie privée, accessible uniquement par les utilisateurs deja inscrits. Un filtre est utilisé pour eviter toute tentative d'acces a cette partie si l'utilisateur ne s'est pas connecté.  

Coté developpement, le client ne dispose que deux taches :
 - appeler les services dont il a besoin.
 - faire la mapping entre la réponse du service et l'IHM.

#### 3.3 DIFFICULTÉS TECHNIQUES RENCONTRÉES ET SOLUTIONS APPORTÉES.
- Les appels asynchrones imbriqués, Solution? Utilisation des promesses en javascript.


## 4. CONCLUSION
