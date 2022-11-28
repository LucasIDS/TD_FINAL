package fr.icom.info.m1.balleauprisonnier_mvn;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


/**
 * Classe gérant le terrain de jeu.
 */
public class Field extends Canvas {

	/** Joueurs */
	Team team1 = new Team("bottom",new DefenseState());
	Team team2 = new Team("top",new AttackState());
	Button button1 = new Button("");
	Display afficheur = Display.getInstance();

	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les événements */

	final GraphicsContext gc;
	final double width;
	final double height;

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

		/* Création d'un afficheur gérant l'affichage */
		Display afficheur = Display.getInstance();

		/* Commencement du jeu */
		bouclePrincipale(afficheur);


	}

	/**
	 *
	 * Boucle principale du jeu
	 * <p>
	 * handle() est appelée à chaque rafraichissement de frame
	 * soit environ 60 fois par seconde.
	 *
	 */

	public void bouclePrincipale(Display afficheur){
		/* Création de la balle*/
		Ball myBall = Ball.getInstance();
		int b = 2;
		/* On initialise le terrain de jeu */
		initialisationJoueurs(myBall);

		afficheur.initLabel(team1.players,team2.players);

		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{

				// On nettoie le canvas à chaque frame
				gc.setFill( Color.LIGHTGRAY);
				gc.fillRect(0, 0, width, height);

				// Affichage
				affichage(afficheur,myBall);

				//Gestion des touches
				gestionTouche(b);

				//Deplacement
				deplacementBot(b);
				myBall.deplacementBall();

				gestionCollision(myBall);

			}
		}.start(); // On lance la boucle de rafraichissement
	}

	public ArrayList<Player> getJoueurs(int a) {
		if (a==1)
		{
			return team1.players;
		}
		else if (a==2)
		{
			return team2.players;
		}
		else {
			return new ArrayList<Player>();
		}
	}

	public ArrayList<LabelGestion> getLabels() {
		return afficheur.labels;
	}


	public Button getBouton(){
		return button1;
	}

	public void gestionCollision(Ball myBall){

		// Collision sur les côtés
		if (myBall.x>=width-40 || myBall.x <= 0){
			myBall.bounceX();
		}

		// Collision en haut
		if (myBall.y<=0 ||  myBall.y>=height){
			team1.state.changeState(myBall);
			team2.state.changeState(myBall);
		}

		for (Player player: team1.players) {
			if(player.sprite.getBoundsInParent().intersects(myBall.x,myBall.y,22,22)){
				myBall.collisionWithPlayer(player,team2);
			}
		}

		for (Player player: team2.players) {
			if(player.sprite.getBoundsInParent().intersects(myBall.x,myBall.y,22,22)){
				myBall.collisionWithPlayer(player,team1);
			}
		}

	}

	public void deplacementBot(int boutton){
		int feinte = 50;
		if (boutton == 1) {
			for (int i = 1; i < team1.players.size(); i++) {
				if (team1.players.get(i)!=null) {
					team1.players.get(i).deplacement(feinte);
				}
			}
			for (int i = 1; i < team2.players.size(); i++) {
				if (team2.players.get(i)!=null) {
					team2.players.get(i).deplacement(feinte);
				}
			}
		}
		else if(boutton==2){
			for (int i = 1; i < team1.players.size(); i++) {
				team1.players.get(i).suivit();
			}
			for (int i = 1; i < team2.players.size(); i++) {
				team2.players.get(i).suivit();
			}
		}
	}



	public void initialisationJoueurs(Ball myBall){

		team1.state.setTeam(team1);
		team2.state.setTeam(team2);



		int vieBaseBot = 1;
		int vieBasePlayer = 1;

		team1.players.add(new Player(gc, colorMap[0], width/2, height-100,null,vieBasePlayer,team1));
		team1.players.add(new IA(gc, colorMap[0], (width / 10), height-100, null,vieBaseBot,team1,width/5 - 40,-10));
		team1.players.add(new IA(gc, colorMap[0], 3*(width/10), height-100, null,vieBaseBot,team1,2 * (width / 5) - 40,(width / 5) - 10));
		team1.players.add(new IA(gc, colorMap[0], 7*(width/10), height-100, null,vieBaseBot,team1,4 * (width / 5) - 40,3 * (width / 5) - 10));
		team1.players.add(new IA(gc, colorMap[0], 9*(width/10), height-100, null,vieBaseBot,team1,5 * (width / 5) - 40,4 * (width / 5) - 10));

		team2.players.add(new Player(gc, colorMap[1], width/2, 20, myBall,vieBasePlayer,team2));
		team2.players.add(new IA(gc, colorMap[1], 1*(width/10), 20, null,vieBaseBot,team2,width/5 - 40,-10));
		team2.players.add(new IA(gc, colorMap[1], 3*(width/10), 20, null,vieBaseBot,team2,2 * (width / 5) - 40,(width / 5) - 10));
		team2.players.add(new IA(gc, colorMap[1], 7*(width/10), 20, null,vieBaseBot,team2,4 * (width / 5) - 40,3 * (width / 5) - 10));
		team2.players.add(new IA(gc, colorMap[1], 9*(width/10), 20, null,vieBaseBot,team2,5 * (width / 5) - 40,4 * (width / 5) - 10));
	}


	public void affichage(Display afficheur,Ball myBall){

		afficheur.displayImg(gc ,myBall.getImgBall(), myBall.x, myBall.y);
		afficheur.displayPlayer(gc,team1.players,team2.players,0,0);
		afficheur.displayVie();
		//afficheur.displayImg(gc,terrain,0,0);
		}


	public void gestionTouche(int boutton){
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
			if (boutton==2) {
				if (team1.players.get(0).getX() >= 2*(width/5)){
					team1.players.get(0).moveLeft();
				}
			}
			else {
				team1.players.get(0).moveLeft();
			}
		}
		if (input.contains("RIGHT"))
		{
			if (boutton==2) {
				if (team1.players.get(0).getX() <= 3*(width/5)-50){
					team1.players.get(0).moveRight();
				}
			}
			else {
				team1.players.get(0).moveRight();
			}
		}
		if ( input.contains("UP"))
		{
			team1.players.get(0).turnLeft();
		}
		if ( input.contains("DOWN"))
		{
			team1.players.get(0).turnRight();
		}

		if ( input.contains("M"))
		{
			team1.players.get(0).shoot();
		}

		if (input.contains("Q"))
		{
			if (boutton==2) {
				if (team2.players.get(0).getX() >= 2*(width/5)){
					team2.players.get(0).moveLeft();
				}
			}
			else {
				team2.players.get(0).moveLeft();
			}
		}
		if (input.contains("D"))
		{
			if (boutton==2) {
				if (team2.players.get(0).getX() <= 3*(width/5)-50){
					team2.players.get(0).moveRight();
				}
			}
			else{
			team2.players.get(0).moveRight();
		}
		}
		if (input.contains("Z"))
		{
			team2.players.get(0).turnLeft();
		}
		if ( input.contains("S"))
		{
			team2.players.get(0).turnRight();
		}

		if ( input.contains("SPACE"))
		{
			team2.players.get(0).shoot();
		}
	}

}