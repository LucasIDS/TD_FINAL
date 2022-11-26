package fr.icom.info.m1.balleauprisonnier_mvn;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.sql.SQLOutput;
import java.util.Random;


/**
 *
 * Classe gerant un joueur
 *
 */
public class Player
{
	double x;       // position horizontale du joueur
	final double y; 	  // position verticale du joueur
	double angle = 90; // rotation du joueur, devrait toujour être en 0 et 180
	double step;    // pas d'un joueur

	double vitesse;

	double feinteMax;
	String playerColor;
	String side;
	// On une image globale du joueur
	Image directionArrow;

	Ball myBall;
	Sprite sprite;
	ImageView PlayerDirectionArrow;

	GraphicsContext graphicsContext;

	/**
	 * Constructeur du Joueur
	 *
	 * @param gc ContextGraphic dans lequel on va afficher le joueur
	 * @param color couleur du joueur
	 * @param yInit position verticale
	 */
	Player(GraphicsContext gc, String color, double xInit, double yInit, String side, Ball ball)
	{
		// Tous les joueurs commencent au centre du canvas,
		x = xInit;
		y = yInit;
		this.side = side;
		graphicsContext = gc;
		playerColor=color;

		angle = 0;

		// Image fleche
		if(side=="top"){
			directionArrow = new Image("assets/PlayerArrowDown.png");
		}
		else{
			directionArrow = new Image("assets/PlayerArrowUp.png");
		}
		PlayerDirectionArrow = new ImageView();
		PlayerDirectionArrow.setImage(directionArrow);
		PlayerDirectionArrow.setFitWidth(10);
		PlayerDirectionArrow.setPreserveRatio(true);
		PlayerDirectionArrow.setSmooth(true);
		PlayerDirectionArrow.setCache(true);

		//Image du joueur
		Image tilesheetImage = new Image("assets/orc.png");
		sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), side);
		sprite.setX(x);
		sprite.setY(y);

		//directionArrow = sprite.getClip().;

		// Tous les joueurs ont une vitesse aleatoire entre 0.0 et 1.0
		// Random randomGenerator = new Random();
		//step = randomGenerator.nextFloat();
		this.vitesse = 1;
		this.step = 3;

		myBall = ball;
		if(myBall != null){
			if (this.side=="bottom"){
				myBall.x = xInit+30;
				myBall.y = yInit-10;
			}
			else if (this.side=="top"){
				myBall.x = xInit+30;
				myBall.y = yInit-10;
			}
		}


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
		if (myBall != null && this.side=="bottom"){
			this.myBall.setVelocityY(-Math.sin(Math.toRadians(90-this.angle)));
			this.myBall.setVelocityX(+Math.cos(Math.toRadians(90-this.angle)));
		}else if (myBall != null && this.side=="top"){
			this.myBall.setVelocityY(+Math.sin(Math.toRadians(90-this.angle)));
			this.myBall.setVelocityX(-Math.cos(Math.toRadians(90-this.angle)));
		}
		this.myBall = null;
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
		//System.out.println("Animating sprite");
		if(!sprite.isRunning) {sprite.playContinuously();}
		sprite.setX(x);
		sprite.setY(y);
	}

	void deplacement(int w5g,int w5d, int feinte){}

}
