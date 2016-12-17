# ProjetJavaEE
School project JEE

il faut installer un client git sur son poste gitbash ou TurtoiseGit 
ensuite il faut cloner le projet en local
vous faites vos modifications ensuite vous faites : 
pour ceux qui utilisent gitBash :
git add *      ENTRER
git commit     ENTRER  ensuite vous mettez votre commentaire exemple: modification de la classe restaurant

donc la on a commité en local 
une fois vos modifications sont fini et votre projet build bien vous pousser votre code sur le server mais il ne faut pas oublier de faire un git pull avant de faire le push

git pull ENTRER ( pour avoir les modifications des autres => résoudre les conflits s'il ya des conflits)
git push -u origin master ENTRER ( on va tous travailler sur la branche master)

un petit tutoriel sur l'utilisation de git

http://rogerdudler.github.io/git-guide/index.fr.html

// Utilisation de JSF/Primefaces pour la partie Vue , Spring pour la partie controler + une couche DAO avec un accès JDBC ( on peut ajouter de l'hibernate facilement)

!!!!!!!!!!!!!! IMPORTANT !!!!!!!!!!!!!!!!!!!!
il faut créer dans glassfish une dataSource avec le nom jndi suivant : "lacuillere"

l'ihm est juste un petit exemple de test qui affiche tous les user qui sont présent dans la table en cliquant sur le bouton update !!
