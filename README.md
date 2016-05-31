# Projet extraction d'appels en mode ligne

Le but de ce projet est de cr�er un programme Java permettant d'exporter les appels d'un service d'urgence au format XML.

Il y a un appel par ligne.

Ce fichier est importable dans Microsoft Excel.

##Utilisation:
```
java ExpCalls [-dbserver db] -u unum [-b d�but] [-e fin] [-o fichier.xml] [-d] [-t] 
```
o� :
* ```-dbserver db``` est la r�f�rence � la base de donn�es, par d�faut d�signe la base de donn�es de d�veloppement. Voir fichier *myDatabases.prop* (optionnel).
* ```-u unum``` est la r�f�rence du service d'urgence (obligatoire).
* ```-b d�but``` : date de d�but de l'extraction � 0h au format JJ/MM/AAAA. Amorc� � hier par d�faut (optionnel).
* ```-e fin``` : date de fin de l'extraction � 0h au format JJ/MM/AAAA. Amorc� � aujourd'hui par d�faut (optionnel).
* ```-o fichier.xml``` est le nom du fichier qui recevra les agences au format XML. Amorc� � *tickets_0000.xml* par d�faut (optionnel).
* ```-d``` le programme s'ex�cute en mode d�bug, il est beaucoup plus verbeux. D�sactiv� par d�faut (optionnel).
* ```-t``` le programme s'ex�cute en mode test, les transcations en base de donn�es ne sont pas faites. D�sactiv� par d�faut (optionnel).

##Pr�-requis :
- Java 6 ou sup�rieur.
- JDBC Informix
- JDBC MySql

##Formats XML reconnus :

Il existe plusieurs types de formats XML reconnus pour d�crire les appels. 

Ils d�pendent du service d'urgence concern�.

Il y a un format par d�faut : *tickets_0000.xsd*.

##R�f�rences:

- Construire une application XML, J.C. Bernadac, F. Knab, Eyrolles.

- [OpenClassroom Java XML](https://openclassrooms.com/courses/structurez-vos-donnees-avec-xml/dom-exemple-d-utilisation-en-java)
- [Syntaxe Markdown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
- [Tuto OpenClassroom sur DTD](https://openclassrooms.com/courses/structurez-vos-donnees-avec-xml/introduction-aux-definitions-et-aux-dtd)
- [Tuto W3C sur DTD (en)](https://www.google.fr/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&sqi=2&ved=0ahUKEwiDrurll-fMAhWHBsAKHYdzAegQFggfMAA&url=http%3A%2F%2Fwww.w3schools.com%2Fxml%2Fxml_dtd_intro.asp&usg=AFQjCNGCt7X2oRyUSkTES1aXf8GljqhekA&bvm=bv.122448493,d.ZGg)
- [Validation fichier XML](http://www.xmlvalidation.com/)
- [Convertisseur DTD/XSD](http://www.freeformatter.com/xsd-generator.html)
- [Tuto XML/XSD](http://www.codeguru.com/java/article.php/c13529/XSD-Tutorial-XML-Schemas-For-Beginners.htm)

##Fichier des param�tres : 

Ce fichier permet de sp�cifier les param�tres d'acc�s aux diff�rentes bases de donn�es.

A adapter selon les impl�mentations locales.

Ce fichier est nomm� : *MyDatabases.prop*.

Le fichier *MyDatabases_Example.prop" est fourni � titre d'exemple.
