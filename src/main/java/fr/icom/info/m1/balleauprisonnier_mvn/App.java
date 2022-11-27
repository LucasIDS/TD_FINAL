package fr.icom.info.m1.balleauprisonnier_mvn;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.stage.Stage;



/**
 * Classe principale de l'application
 * s'appuie sur javafx pour le rendu
 */
public class App extends Application
{

	/**
	 * En javafx start() lance l'application
	 * On cree le SceneGraph de l'application ici
	 * @see <a href="http://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm">...</a>
	 *
	 */
	@Override
	public void start(Stage stage) {
		// Nom de la fenêtre
		stage.setTitle("BalleAuPrisonnier");

		Group root = new Group();
		Scene scene = new Scene( root );


		// On crée le terrain de jeu et on l'ajoute à la racine de la scene
		Field gameField = new Field( 600, 600 );
		root.getChildren().add( gameField );
		root.getChildren().add(gameField.getJoueurs(1)[0].sprite);
		root.getChildren().add(gameField.getJoueurs(2)[0].sprite);
		root.getChildren().add(gameField.getJoueurs(1)[1].sprite);
		root.getChildren().add(gameField.getJoueurs(2)[1].sprite);
		root.getChildren().add(gameField.getJoueurs(1)[2].sprite);
		root.getChildren().add(gameField.getJoueurs(2)[2].sprite);
		root.getChildren().add(gameField.getJoueurs(1)[3].sprite);
		root.getChildren().add(gameField.getJoueurs(2)[3].sprite);
		root.getChildren().add(gameField.getJoueurs(1)[4].sprite);
		root.getChildren().add(gameField.getJoueurs(2)[4].sprite);


		// On ajoute la scene à la fenêtre et on affiche
		stage.setScene( scene );
		stage.show();
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
