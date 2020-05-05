# Architecture
Pour réaliser se projet , j'ai dû créer nombreuses classes et packages et j'ai partager comme suivant :\

### _Package Outils_ 
Pour outiller un peu , j'ai créé quelque classe notament par exemple\
**Structure :** qui gérera ma structure donnée pour représenter la relation *Page* et son *Id*.\
**CLI :** qui comme son nom le porte , c'est une implémentation d'une matrice creuse à la forme *CLI* qu'on vas justement garder\
que les cases qui ont du contenu.\
**Collecteur :** l'une des plus importante classe pour le deploiement de notre application web , c'est une sorte de dictionnaire \
c'est une classe qui représentera d'une maniére la relation  mot à toutes les pages qui contiennent se mot.\
**PageRank :** une classe qui nous permet en faite de calculer la fiabilité le pagerank de chaque page en partant d'un vecteur de propabilité.
### _Package Building_
Cette partie la est didiée plus au moins à la construction du corpus , à l'ecriture dans les différents fichier qu'on aura besoin\
dans le déroulement du prgramme.On donnera comme exemple:
**FileRead :** on lire à partir du disque "un fichier xml"  les mots du dictionnaire ainsi les titres des pages dont ils apparaissent.

### _Package Traitement_
Un package dédier au traitement du texte et la construction , ainsi dans ces classes nous traitons le texte en le nettoyant, en récupérant que ce qu'on l'en a besoin comme les titres d'autres pages qui se trouvent dans le texte passé en paramétre ,créer notre dictionnaire. Comme exemple on prend la classe :\
**Factory :** elle contient les méthodes pour traiter le texte , d'ailleurs elle est nécerssaire pour la création de notre _corpus.xml_  notre _collecteur_. \
**MyXmlHandler :** une classe qui hérite de _defaultHandler_ utilisée par l'_api SAXParser_ pour parser des élement balise par balise , ça nous intéresse car notre fichier est tellement gros, donc on pourrais pas l'ouvrir avec un parseur normal.  

### _Package Main_
Ici finalement on a les classes finales qu'on utilisera pour le deploiement , une classe 
**Main :** qui est un controleur,un point d'entrée du projet web (_Spring_) , en utilisant les annotations de springboot  
```
@Controller
@SpringBootApplication 
public class Main 
```
Elle est ulisé lors de la réception d'une requette _http_ , ainsi que d'autre classe de se même package comme :\
***MyXmlCollect :** elle nous permet de lire le fichier _src/resources/storage/collecteur.xml_ qui contient la relation :
```
<mot>
 <content> <!-- contenu mot --> </content>
 <page>
     <title><--! titre de la page -->  </frequence>
    <frequence> <--! frequence d'apparution du mot dans cette page -->  </frequence>
 </page>
</mot>
<mot>
  ....
</mot>
```
### _Conclusion_
J'ai essayé dans ce projet de faire au mieux la maniére d'implémentation , de respecter quelque régles de bases de programmation objet ainsi quelque patrons de disgn pattern notament _Singleton_ ,_Factory_ ainsi que _Service_ , il restera toujours du travail et de l'effort à fournir pour la suite.

[Ghouas Abdelhak](https://github.com/Belak-Ghouas)