package fr.icom.info.m1.balleauprisonnier_mvn;

public class BabyFootDeplacementStrategy implements DeplacementStrategy{
    public void deplacementBot(IA bot){
        double difference = bot.team.players.get(0).xInit - bot.xInit;
        if (bot.vie>0) {
            System.out.println("la HICHEM");
            bot.x = bot.team.players.get(0).x - difference;
            bot.spriteAnimate();
        }
    }
}
