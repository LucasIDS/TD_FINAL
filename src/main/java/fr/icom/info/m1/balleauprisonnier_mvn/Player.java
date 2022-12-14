package fr.icom.info.m1.balleauprisonnier_mvn;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


/**
 *
 * Classe gérant un joueur
 *
 */
public class Player
{
	protected double x;       // position horizontale du joueur
	protected final double y; 	  // position verticale du joueur
	private double angle; // rotation du joueur, devrait toujours être en 0 et 180
	double step;    // pas d'un joueur
	protected double xInit;
	double vitesse;
	double feinteMax;
	protected DeplacementStrategy deplacementStrategy;
	String playerColor;


	// On une image globale du joueur
	Image directionArrow;
	protected Team team;
	int vie;
	Ball myBall;
	Sprite sprite;
	Sprite spriteExplo;
	double wait =0;
	ImageView playerDirectionArrow;

	GraphicsContext graphicsContext;

	/**
	 * Constructeur du Joueur
	 *
	 * @param gc ContextGraphic dans lequel on va afficher le joueur
	 * @param color couleur du joueur
	 * @param yInit position verticale
	 */
	Player(GraphicsContext gc, String color, double xInit, double yInit, Ball ball,int pVie,Team team)
	{

		this.team = team;

		// Tous les joueurs commencent au centre du canvas,
		x = xInit;
		y = yInit;
		this.xInit = xInit;

		graphicsContext = gc;
		playerColor=color;

		this.vie = pVie;

		this.angle = 0;

		// Image fleche
		if(this.team.getName().equals("top")){
			directionArrow = new Image("assets/PlayerArrowDown.png");
		}
		else{
			directionArrow = new Image("assets/PlayerArrowUp.png");
		}
		playerDirectionArrow = new ImageView();
		playerDirectionArrow.setImage(directionArrow);
		playerDirectionArrow.setFitWidth(10);
		playerDirectionArrow.setPreserveRatio(true);
		playerDirectionArrow.setSmooth(true);
		playerDirectionArrow.setCache(true);

		//Image du joueur
		Image tilesheetImage = new Image("assets/orc.png");
		sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), this.team.getName());
		sprite.setX(x);
		sprite.setY(y);

		Image imageExplo = new Image("assets/PlayerBlue.png");
		spriteExplo = new Sprite(imageExplo, 0,0, Duration.seconds(0.1), this.team.getName());
		spriteExplo.setX(-1000);
		this.vitesse = 1;
		this.step = 3;
		myBall = ball;
		this.placementBall();

	}
	double getX(){
		return this.x;
	}
	double getY(){
		return this.y;
	}


	/**
	 *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
	 */
	void moveLeft()
	{
		if (x > 5)
		{
			spriteAnimate();
			x -= step;
			if (this.myBall != null){
				this.myBall.x -= step;
			}
		}
	}

	/**
	 *  Deplacement du joueur vers la droite
	 */
	void moveRight()
	{
		if (x < 520)
		{
			spriteAnimate();
			x += step;
			if (this.myBall != null){
			this.myBall.x += step;
			}
		}
	}

	public double getAngle(){
		return this.angle;
	}

	void recieveBall(Ball myBall,Team team2){
		this.team.state.recieveBall(this,myBall,team2);
	}

	void lostVie(int vie){
		this.vie -= vie;
		if (this.vie <= 0){
			spriteExplo.setX(this.x);
			spriteExplo.setY(this.y);
			spriteExplo.playExplosion();
			this.x = 1000;
		}
	}


	/**
	 *  Rotation du joueur vers la gauche
	 */
	void turnLeft()
	{
		if (angle <= 90)
		{
			angle += 1;
		}
	}


	/**
	 *  Rotation du joueur vers la droite
	 */
	void turnRight()
	{
		if (angle >= -90 )
		{
			angle -=1;
		}
	}


	void shoot(){
		this.team.state.shoot(this);
	}

	void initBall(Ball balle){
		this.myBall=balle;
		this.myBall.setVelocityY(0);
		this.myBall.setVelocityX(0);
		this.placementBall();

	}
	private void placementBall(){

		if(myBall != null){
			if (this.team.getName().equals("bottom")){
				myBall.x = this.x+22;
				myBall.y = this.y-22;
			}
			else if (this.team.getName().equals("top")){
				myBall.x = this.x+22;
				myBall.y = this.y+60;
			}
		}
	}
	void spriteAnimate(){
		if(!sprite.isRunning) {sprite.playContinuously();}
		sprite.setX(x);
		sprite.setY(y);
	}

	void deplacement(){}

	void setAngle(double angle){
		this.angle=angle;
	}

}

