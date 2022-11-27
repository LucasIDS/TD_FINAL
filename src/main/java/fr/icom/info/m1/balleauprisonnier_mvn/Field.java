package fr.icom.info.m1.balleauprisonnier_mvn;


import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * Classe gérant le terrain de jeu.
 */
public class Field extends Canvas {

	/** Joueurs */
	Player [] team1 = new Player[5];
	Player [] team2 = new Player[5];
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les événements */

	final GraphicsContext gc;
	final double width;
	final double height;

	Ball myBall;

	ArrayList<String> input = new ArrayList<>();

	/**
	 * Canvas dans lequel on va dessiner le jeu.
	 * @param w largeur du canvas
	 * @param h hauteur du canvas
	 */
	public Field( double w, double h)
	{
		super(w, h);
		width = w;
		height = h;

		/* Permet de capturer le focus et donc les événements clavier et souris */
		this.setFocusTraversable(true);

		gc = this.getGraphicsContext2D();

		/* Création de la balle*/
		myBall = new Ball(gc,0,0,0,0);

		/* On initialise le terrain de jeu */
		initialisationJoueurs();

		/* Commencement du jeu */
		bouclePrincipale(myBall);
	}

	/**
	 *
	 * Boucle principale du jeu
	 * <p>
	 * handle() est appelée à chaque rafraichissement de frame
	 * soit environ 60 fois par seconde.
	 *
	 */
	public void bouclePrincipale(Ball myBall){
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				// On nettoie le canvas à chaque frame
				gc.setFill( Color.LIGHTGRAY);
				gc.fillRect(0, 0, width, height);

				// Deplacement et affichage des joueurs
				gestionTouche();
				team1[0].display();
				team2[0].display();
				deplacementBot();

				myBall.deplacementBall();
				myBall.display();
				gestionCollision(myBall);
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

	public void gestionCollision(Ball myBall){

		// Collision sur les côtés
		if (myBall.x>=width-40 || myBall.x <= 0){
			myBall.bounceX();
		}
		// Collision en haut
		if (myBall.y<=0){
			team2[0].initBall(myBall);
		}
		// Collision en bas
		if (myBall.y>=height){
			team1[0].initBall(myBall);
		}

		boolean touche;


		for(int i =0 ; i < 5 ; i ++){
			if(team1[i].sprite.getBoundsInParent().intersects(myBall.x,myBall.y,22,22)){
				touche = myBall.collisionWithPlayer(team1[i]);
				if(touche) {
					team2[0].initBall(myBall);
				}
			}
		}

		for (Player player: team1) {

		}

		for (Player player: team2) {
			if(player.sprite.getBoundsInParent().intersects(myBall.x,myBall.y,22,22)){
				touche = myBall.collisionWithPlayer(player);
				if(touche) {
					team1[0].initBall(myBall);
				}
			}
		}



	}

	public void deplacementBot(){
		int feinte = 0;
		for (int i=1;i<3;i++) {
			team1[i].deplacement((i-1) * (width / 5)-10, i * (width / 5)-40,  feinte);
			team2[i].deplacement((i-1) * (width / 5)-10, i * (width / 5)-40,  feinte);
		}

		for (int i=3;i<5;i++) {
			team1[i].deplacement(i * (width / 5)-10, (i+1) * (width / 5)-40, feinte);
			team2[i].deplacement(i * (width / 5)-10, (i+1) * (width / 5)-40, feinte);
		}
	}


	public void initialisationJoueurs(){
		String bottom = "bottom";
		String top = "top";
		int vieBaseBot = 2;
		int vieBasePlayer = 5;

		team1[0] = new Player(gc, colorMap[0], width/2, height-100, bottom,null,vieBasePlayer);
		team1[0].display();


		team1[1] = new IA(gc, colorMap[0], (width / 10), height-100, bottom,null,vieBaseBot);


		team1[2] = new IA(gc, colorMap[0], 3*(width/10), height-100, bottom,null,vieBaseBot);
		team1[2].display();

		team1[3] = new IA(gc, colorMap[0], 7*(width/10), height-100, bottom,null,vieBaseBot);
		team1[3].display();

		team1[4] = new IA(gc, colorMap[0], 9*(width/10), height-100, bottom,null,vieBaseBot);
		team1[4].display();


		team2[0] = new Player(gc, colorMap[1], width/2, 20, top,myBall,vieBasePlayer);
		team2[0].display();

		team2[1] = new IA(gc, colorMap[1], 1*(width/10), 20, top,null,vieBaseBot);
		team2[1].display();

		team2[2] = new IA(gc, colorMap[1], 3*(width/10), 20, top,null,vieBaseBot);
		team2[2].display();

		team2[3] = new IA(gc, colorMap[1], 7*(width/10), 20, top,null,vieBaseBot);
		team2[3].display();

		team2[4] = new IA(gc, colorMap[1], 9*(width/10), 20, top,null,vieBaseBot);
		team2[4].display();

	}




	public void gestionTouche(){
		/*
		 * Event Listener du clavier
		 * quand une touche est pressée on la rajoute à la liste d'input
		 *
		 */
		this.setOnKeyPressed(e -> {
					String code = e.getCode().toString();
					// only add once... prevent duplicates
					if ( !input.contains(code) )
						input.add( code );
				});


		/*
		 * Event Listener du clavier
		 * quand une touche est relâchée on l'enlève de la liste d'input
		 */
		this.setOnKeyReleased(
				e -> {
					String code = e.getCode().toString();
					input.remove( code );
				});

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
	}

}



