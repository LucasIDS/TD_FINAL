package fr.icom.info.m1.balleauprisonnier_mvn;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
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
	double angle; // rotation du joueur, devrait toujours être en 0 et 180
	double step;    // pas d'un joueur

	double vitesse;

	double feinteMax;
	String playerColor;
	String side;
	// On une image globale du joueur
	Image directionArrow;

	int vie;

	Ball myBall;
	Sprite sprite;
	ImageView playerDirectionArrow;

	GraphicsContext graphicsContext;

	/**
	 * Constructeur du Joueur
	 *
	 * @param gc ContextGraphic dans lequel on va afficher le joueur
	 * @param color couleur du joueur
	 * @param yInit position verticale
	 */
	Player(GraphicsContext gc, String color, double xInit, double yInit, String side, Ball ball,int pVie)
	{
		// Tous les joueurs commencent au centre du canvas,
		x = xInit;
		y = yInit;
		this.side = side;
		graphicsContext = gc;
		playerColor=color;

		this.vie = pVie;

		this.angle = 0;

		// Image fleche
		if(side.equals("top")){
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
		sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), side);
		sprite.setX(x);
		sprite.setY(y);

		//directionArrow = sprite.getClip().;

		// Tous les joueurs ont une vitesse aléatoire entre 0.0 et 1.0
		// Random randomGenerator = new Random();
		//step = randomGenerator.nextFloat();
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
	 *  Affichage du joueur
	 */
	void display()
	{
		graphicsContext.save(); // saves the current state on stack, including the current transform
		rotate(graphicsContext, angle, x + directionArrow.getWidth() / 2, y + directionArrow.getHeight() / 2);
		graphicsContext.drawImage(directionArrow, x, y);
		graphicsContext.restore(); // back to original state (before rotation)
	}

	private void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
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

	boolean lostVie(int nb){
		boolean lostVie = false;
		if (this.vie > 0){
			this.vie -= nb;
			lostVie = true;
		}
		return lostVie;
	}


	/**
	 *  Rotation du joueur vers la gauche
	 */
	void turnLeft()
	{
		if (angle <= 60)
		{
			angle += 1;
		}
	}


	/**
	 *  Rotation du joueur vers la droite
	 */
	void turnRight()
	{
		if (angle >= -70 )
		{
			angle -=1;
		}
	}


	void shoot(){
		sprite.playShoot();
		if (myBall != null && this.side.equals("bottom")){
			this.myBall.setVelocityY(-Math.sin(Math.toRadians(90-this.angle)));
			this.myBall.setVelocityX(+Math.cos(Math.toRadians(90-this.angle)));
		}else if (myBall != null && this.side.equals("top")){
			this.myBall.setVelocityY(+Math.sin(Math.toRadians(90-this.angle)));
			this.myBall.setVelocityX(-Math.cos(Math.toRadians(90-this.angle)));
		}
		this.myBall = null;
	}

	void initBall(Ball balle){
		this.myBall=balle;
		this.myBall.setVelocityY(0);
		this.myBall.setVelocityX(0);
		this.placementBall();

	}
	private void placementBall(){

		if(myBall != null){
			if (this.side.equals("bottom")){
				myBall.x = this.x+22;
				myBall.y = this.y-22;
			}
			else if (this.side.equals("top")){
				myBall.x = this.x+22;
				myBall.y = this.y+60;
			}
		}
	}
	/**
	 *  Deplacement en mode boost
	 */
	void boost()
	{
		x += step*2;
		spriteAnimate();
	}

	void spriteAnimate(){
		if(!sprite.isRunning) {sprite.playContinuously();}
		sprite.setX(x);
		sprite.setY(y);
	}

	void deplacement(double w5g,double w5d, int feinte){}
	void suivit(double xbot){}

}
