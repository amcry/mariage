## Mariage

Ceci est la projet spring boot dans le contexte d'un mariage.


## Créer le projet

Pour créer un projet spring boot il faut se rendre sur cette page : [https://start.spring.io/](https://start.spring.io/).
Cette page est une aide pour construire le projet. il faut remplir avec le nom de groupe et le nom d'artifact que l'on souhaite. (par example: GROUP : com.dampierre et ARTIFACT: spring-boot-tuto).
Dans la partie *Dépendencies* on y retrouve la securité, et d'autres ressources importantes.

Il faut ensuite ouvrir le dossier avec vscode.


## Configuration

Pour la base de donnée nous avons une mariaDB sur docker.

```properties
# Port de la bdd
server.port=8080

# login & password
login : user
password : test

# lien vers le swagger
http://localhost:8080/swagger-ui/index.html#/
```


## Structure

Pour respecter le MVC design pattern, J'ai créé un fichier *controllers*, un fichier *repository* et un fichier *invite* pour "data".
Les tests sont effectués dans Swagger.


## Schéma

Le json se presente sous cette forme :

```json
{
	"user": "user",
	"password": "test"
}
```


### Model

Création du model, on créé une classe `Repository`

La classe *Repository* représentera le model. dans cette classe on définira les getters et setters pour *invite*.

Pour que spring boot prennet en compte le model il faut ajouter l'annotation **@Entity** à la classe.


#### Controller

J'ai créé le fichier `InviteController.java`, il faut ajouter l'annotation **@RestController**


## Liens
[https://start.spring.io/](https://start.spring.io/)

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

[https://mvnrepository.com/](https://mvnrepository.com/)



