package fr.icom.info.m1.balleauprisonnier_mvn;


import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */


/**
 * Classe gerant le terrain de jeu.
 *
 */
public class Field extends Canvas {

	/** Joueurs */
	Player [] team1 = new Player[5];
	Player [] team2 = new Player[5];
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
	ArrayList<String> input = new ArrayList<String>();


	final GraphicsContext gc;
	final int width;
	final int height;

	/**
	 * Canvas dans lequel on va dessiner le jeu.
	 *
	 * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
	 * @param w largeur du canvas
	 * @param h hauteur du canvas
	 */
	public Field(Scene scene, int w, int h)
	{
		super(w, h);
		width = w;
		height = h;

		/* permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);

		gc = this.getGraphicsContext2D();

		/* On crée la balle */




		/* On initialise le terrain de jeu */
		team1[0] = new Player(gc, colorMap[0], w/2, h-50, "bottom");
		team1[0].display();

		team1[1] = new IA(gc, colorMap[0], 1*(w/10), h-50, "bottom");
		team1[1].display();

		team1[2] = new IA(gc, colorMap[0], 3*(w/10), h-50, "bottom");
		team1[2].display();

		team1[3] = new IA(gc, colorMap[0], 7*(w/10), h-50, "bottom");
		team1[3].display();

		team1[4] = new IA(gc, colorMap[0], 9*(w/10), h-50, "bottom");
		team1[4].display();


		team2[0] = new Player(gc, colorMap[1], w/2, 20, "top");
		team2[0].display();

		team2[1] = new IA(gc, colorMap[1], 1*(w/10), 20, "top");
		team2[1].display();

		team2[2] = new IA(gc, colorMap[1], 3*(w/10), 20, "top");
		team2[2].display();

		team2[3] = new IA(gc, colorMap[1], 7*(w/10), 20, "top");
		team2[3].display();

		team2[4] = new IA(gc, colorMap[1], 9*(w/10), 20, "top");
		team2[4].display();

		/**
		 * Event Listener du clavier
		 * quand une touche est pressee on la rajoute a la liste d'input
		 *
		 */
		this.setOnKeyPressed(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e)
					{
						String code = e.getCode().toString();
						// only add once... prevent duplicates
						if ( !input.contains(code) )
							input.add( code );
					}
				});

		/**
		 * Event Listener du clavier
		 * quand une touche est relachee on l'enleve de la liste d'input
		 *
		 */
		this.setOnKeyReleased(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e)
					{
						String code = e.getCode().toString();
						input.remove( code );
					}
				});

		/**
		 *
		 * Boucle principale du jeu
		 *
		 * handle() est appelee a chaque rafraichissement de frame
		 * soit environ 60 fois par seconde.
		 *
		 */
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				// On nettoie le canvas a chaque frame
				gc.setFill( Color.LIGHTGRAY);
				gc.fillRect(0, 0, width, height);

				// Deplacement et affichage des joueurs

				if (input.contains("LEFT"))
				{
					team1[0].moveLeft();
				}
				if ( input.contains("RIGHT"))
				{
					team1[0].moveRight();
				}
				if ( input.contains("UP"))
				{
					team1[0].turnLeft();
				}
				if ( input.contains("DOWN"))
				{
					team1[0].turnRight();
				}

				if ( input.contains("M"))
				{
					team1[0].shoot();
				}

				team1[0].display();


				if (input.contains("Q"))
				{
					team2[0].moveLeft();
				}
				if (input.contains("D"))
				{
					team2[0].moveRight();
				}
				if (input.contains("Z"))
				{
					team2[0].turnLeft();
				}
				if ( input.contains("S"))
				{
					team2[0].turnRight();
				}

				if ( input.contains("SPACE"))
				{
					team2[0].shoot();
				}

				team2[0].display();

				int feinte = 10;
				for (int i=1;i<3;i++) {
					team1[i].deplacement((i-1) * (w / 5), i * (w / 5),  feinte);
					team2[i].deplacement((i-1) * (w / 5), i * (w / 5),  feinte);
				}

				for (int i=3;i<5;i++) {
					team1[i].deplacement(i * (w / 5), (i+1) * (w / 5), feinte);
					team2[i].deplacement(i * (w / 5), (i+1) * (w / 5), feinte);
				}


			}
		}.start(); // On lance la boucle de rafraichissement

	}

	public Player[] getJoueurs(int a) {
		if (a==1)
		{
			return team1;
		}
		else if (a==2)
		{
			return team2;
		}
		else {
			return new Player[0];
		}
	}

	public Ball getBall() {
		return new Ball(gc,30,30,2,2);
	}
}
