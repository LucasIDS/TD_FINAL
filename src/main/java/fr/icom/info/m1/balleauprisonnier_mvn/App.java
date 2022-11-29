package fr.icom.info.m1.balleauprisonnier_mvn;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
		root.getChildren().add(gameField.getJoueurs(1).get(0).sprite);
		root.getChildren().add(gameField.getJoueurs(2).get(0).sprite);
		root.getChildren().add(gameField.getJoueurs(1).get(1).sprite);
		root.getChildren().add(gameField.getJoueurs(2).get(1).sprite);
		root.getChildren().add(gameField.getJoueurs(1).get(2).sprite);
		root.getChildren().add(gameField.getJoueurs(2).get(2).sprite);
		root.getChildren().add(gameField.getJoueurs(1).get(3).sprite);
		root.getChildren().add(gameField.getJoueurs(2).get(3).sprite);
		root.getChildren().add(gameField.getJoueurs(1).get(4).sprite);
		root.getChildren().add(gameField.getJoueurs(2).get(4).sprite);
		root.getChildren().add(gameField.getBouton());
		for (LabelGestion Label:gameField.getLabels()){
			root.getChildren().add(Label);
		}
		// On ajoute la scene à la fenêtre et on affiche
		stage.setScene( scene );
		stage.show();
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
