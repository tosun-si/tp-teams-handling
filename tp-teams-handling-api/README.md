# TP application gestion d'equipes pour l'Université Sorbonne Paris Nord

> L'objectif de ce TP est de développer une application en `fullstack` qui permet de gérer des équipes de Football  :soccer:
>
> Une partie backend/api en Spring boot permettra de gérer les équipes avec une base de données `in memory` `H2` et d'exposer des `endPoints` en REST.
> Spring data JPA et Hibernate seront utilisé pour la partie accès aux données
>
> Une partie front end permettra de gérer les équipes via le framework >Angular 11.
> NodeJs, TypeScript, RxJs, Material... seront utilisés avec Angular
>
> L'application va permettre de lister une liste d'équipe par défaut
> D'afficher le détail d'une équipe
> D'ajouter une nouvelle équipe en base de donnée via un formulaire Web
> De supprimer une équipe existante
> Et en option d'afficher, ajouter, supprimer une liste de joueurs pour une équipe

## :memo: Partie backend/API

### Installer l'open JDK 15 selon l'OS sur lequel vous travaillez (Windows, Linux, Mac)

https://jdk.java.net/15/

Certains tuto expliquent comment faire selon l'OS :

- Windows : https://java.tutorials24x7.com/blog/how-to-install-openjdk-15-on-windows-10
- Linux/Ubuntu : https://techoral.com/blog/java/install-openjdk-15-ubuntu.html

Les variables d'environnement pour Java ne sont pas obligatoires pour ce TP l'application Java Spring sera lancé par l'IDE IntelliJ Idea.

:rocket:

### Créer la structure du projet Spring Boot

Aller sur le lien suivant : https://start.spring.io/

Dans cet interface `Spring Initializr` nous allons pouvoir initialiser une structure de projet avec les dépendances souhaitées.

Spring Boot est initialisé avec un outil de `build` comme Maven ou Gradle, dans le cadre de ce TP c'est Maven qui a été choisi.
Maven reste à ce jour l'outil de `build` le plus utilisé de l'ecosystème Java.

Spring peut également être initialisé avec plusieurs languages, Java, Kotlin et Groovy.
Dans le cadre de ce TP, c'est Java qui a été choisi avec la version 15.

![](https://i.imgur.com/foRVpoT.png)

Dans la partie droite plusieurs dépendances peuvent être ajoutées au projet avec un champ d'autocomplétion. Les dépendances suivantes doivent être ajoutées :

- Spring web
- Spring data JPA
- H2

![](https://i.imgur.com/WBg8YZP.png)


Dans le bloc `Project Metadata`, vous pouvez mettre :
- Group : fr.sorbonne.paris.nord.university
- Artifact : tp-teams-handling-api
- Name : tp-teams-handling-api
- Description : TP for the Sorbonne Paris Nord University that handles the api part
- Package name : fr.sorbonne.paris.nord.university.api
- Packaging : jar
- Java : 15

Cliquez ensuite sur le bouton "generate".


### Lancer l'IDE

Dans le cadre de ce TP nous préconisons l'utilisons de l'IDE IntelliJ Idea.
Cet IDE ets gratuit pour les étudiants et il est surement le plus populaire dans l'ecosytème Java et aussi le plus utilisé.

Ce qui fait sa force c'est qu'il propose également des supports très complets dans quasi tous les languages, dont ceux utilisés dans la partie front (Angular, ES6, Typescipt, CSS, SCSS...)

- Lancer l'IDE
- Utiliser l'option "Create project from existing source"
- Dans la fenêtre qui va s'ouvrir, sélectionner le fichier `pom.xml` du projet Spring boot généré précédement. Ce fichier pom est à la racine du projet.
- IntelliJ va initialiser le projet et grâce au fichier pom.xml, va automatiquement détécté qu'il s'agit d'un projet Maven. L'IDE propose également beaucoup de supports pour la partie Spring.
- Séléctionnez ensuite l'option `File/Project Structure`
- Séléctionnez `Platform settings/SDK` à gauche
- Cliquez ensuite sur le bouton `+`
- Un explorateur de fichier va s'ouvrir, aller vers le chemin contenant le répertoire d'install du JDK (celui qui a été dezippé suite au téléchargement)
- Si cela a bien fonctionné, vous verrez un 15 dans la liste comme sur l'image ci dessous.

![](https://i.imgur.com/OmP69WU.png)


- Allez ensuite dans `Project settings/Project`
- Choisir le SDK 15 ajouté précédement grâce à la liste déroulante proposée
- Dans project language level, séléctionnez Java 15


![](https://i.imgur.com/tiyF0Ag.png)


### Installer et compiler le projet avec Maven

IntelliJ propose une version de Maven intégré `embedded`, donc pas dans le cadre de cet exercice, pas besoin d'installer Maven en global sur la machine.

L'IDE propose une fenêtre Maven à droite avec tout le cycle de vie d'un projet Maven `Lifecyle` :

![](https://i.imgur.com/OGO0JUo.png)

- Double cliquez sur `clean`, ceci videra le répertoire de travail `target` utilisé par Maven
- Double cliquez sur install, ceci va installer toutes les dépendances proposées dans le fichier `pom.xml`, compiler le projet Java et executer les tests s'il y en a dans le projet.

Si tout se passe bien, la console dans l'IDE affichera un SUCCESS :

![](https://i.imgur.com/V4gmfxq.png)

Si ce n'est pas la cas, vérifiez si le bloc suivant existe dans le fichier pom.xml, dans la section `plugins` :

```xml=
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <argLine>--enable-preview</argLine>
    </configuration>
</plugin>

```

Si ce n'est pas le cas, copier coller ce bloc.
En effet Java 15 propose des features en cours de validation `enable-preview` et lors des tests unitaire, Maven utilise le plugin `maven-surefire-plugin`.

Parfois il y a besoin de configurer ça dans le plugin Maven surefire.

Félicitation, vous venez de compiler et installer pour la première fois votre projet backend/api Spring Boot :i_love_you_hand_sign:


### Ajouter votre premier webservice REST

Dans cette partie nous allons ajouter la classe qui va contenir notre premier webservice REST.

Ce premier Webservice va juste afficher "Hello World" sur une page Web.

- Dans le package fr.sorbonne.paris.nord.university.api, ajouter un package `controller`
- Y ajouter une nouvelle class Java appelé `TeamController`
- Cette classe contient une anotation Spring `@RestController` pour indiquer qu'il s'agit d'un controller pour exposer du Rest
- Elle contient une méthode et le type de Webservice, dans cet exemple un `@Get` car nous souhaitons récupérer une resource
- L'URL associée à la resource `hello` dans cet exemple

```java=
@RestController
public class TeamController {

    @GetMapping("/hello")
    public String getTeams() {
        return "Hello World";
    }
}
```

- Chaque projet Sring boot contient un `main` avec un serveur embarqué, tomcat par défaut pour du Spring Web (Jetty peut aussi être utilisé)
- Ouvrir la classe `TpTeamsHandlingApiApplication` à la racine du package api
- Lancer le main via IntelliJ (bouton lecture en vert sur la classe ou la méthode main)
- Si tout se passe bien Spring n'affichera pas d'erreur dans la console
- :warning: spring utilise par défaut le port `8080` dans son serveur embarqué, si ce port est occupé par un autre process sur votre machine, il va falloir soit arrêté le process en question, ou soit changer le port par défaut utilisé par Spring boot, via le fichier `application.properties` ou `application.yaml`
- ouvrir un navigateur Web et afficher sur la colone l'URL suivante : `http://localhost:8081/hello`
- Normalement le text `hello` devrait s'afficher

Félicitations, vous venez de développer votre premier webservice Rest et d'y afficher le résultat.


Utilisez l'IDE IntelliJ Idea

Apply different styling to this paragraph:
**HackMD gets everyone on the same page with Markdown.** ==Real-time collaborate on any documentation in markdown.== Capture fleeting ideas and formalize tribal knowledge.

- [x] **Bold**
- [ ] *Italic*
- [ ] Super^script^
- [ ] Sub~script~
- [ ] ~~Crossed~~
- [x] ==Highlight==

:::info
:bulb: **Hint:** You can also apply styling from the toolbar at the top :arrow_upper_left: of the editing area.

![](https://i.imgur.com/Cnle9f9.png)
:::

> Drag-n-drop image from your file system to the editor to paste it!

### Step 3: Invite your team to collaborate!

Click on the <i class="fa fa-share-alt"></i> **Sharing** menu :arrow_upper_right: and invite your team to collaborate on this note!

![permalink setting demo](https://i.imgur.com/PjUhQBB.gif)

- [ ] Register and sign-in to HackMD (to use advanced features :tada: )
- [ ] Set Permalink for this note
- [ ] Copy and share the link with your team

:::info
:pushpin: Want to learn more? ➜ [HackMD Tutorials](https://hackmd.io/c/tutorials)
:::

---

## BONUS: More cool ways to HackMD!

- Table

| Features          | Tutorials               |
| ----------------- |:----------------------- |
| GitHub Sync       | [:link:][GitHub-Sync]   |
| Browser Extension | [:link:][HackMD-it]     |
| Book Mode         | [:link:][Book-mode]     |
| Slide Mode        | [:link:][Slide-mode]    | 
| Share & Publish   | [:link:][Share-Publish] |

[GitHub-Sync]: https://hackmd.io/c/tutorials/%2Fs%2Flink-with-github
[HackMD-it]: https://hackmd.io/c/tutorials/%2Fs%2Fhackmd-it
[Book-mode]: https://hackmd.io/c/tutorials/%2Fs%2Fhow-to-create-book
[Slide-mode]: https://hackmd.io/c/tutorials/%2Fs%2Fhow-to-create-slide-deck
[Share-Publish]: https://hackmd.io/c/tutorials/%2Fs%2Fhow-to-publish-note

- LaTeX for formulas

$$
x = {-b \pm \sqrt{b^2-4ac} \over 2a}
$$

- Code block with color and line numbers：
```javascript=16
var s = "JavaScript syntax highlighting";
alert(s);
```

- UML diagrams
```sequence
Alice->Bob: Hello Bob, how are you?
Note right of Bob: Bob thinks
Bob-->Alice: I am good thanks!
Note left of Alice: Alice responds
Alice->Bob: Where have you been?
```
- Auto-generated Table of Content
  [ToC]

> Leave in-line comments! [color=#3b75c6]

- Embed YouTube Videos

{%youtube PJuNmlE74BQ %}

> Put your cursor right behind an empty bracket {} :arrow_left: and see all your choices.

- And MORE ➜ [HackMD Tutorials](https://hackmd.io/c/tutorials)
