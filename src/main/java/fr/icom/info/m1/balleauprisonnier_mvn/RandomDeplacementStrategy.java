package fr.icom.info.m1.balleauprisonnier_mvn;

public class RandomDeplacementStrategy implements DeplacementStrategy{

    int feinte;
    public RandomDeplacementStrategy(int feinte){
        this.feinte = feinte;
    }

    public void deplacementBot(IA bot){
        bot.changementDirectionBot(bot.getxMin(),bot.getxMax(),this.feinte);
        bot.x += bot.step * bot.vitesse;
        bot.spriteAnimate();
    }


}
