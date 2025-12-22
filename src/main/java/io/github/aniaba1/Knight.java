package io.github.aniaba1;

public class Knight extends Enemy{
    Knight() {
        setName("Knight"); 
        setLevel(5);  
        setHealth(50);
        setAttack(10);
        setDefense( 15);
        setAura(100) ;   
        setSpeed(15);
        setExp(20);      
    }

    public String slash() {
        return "Knight uses Slash!\n";
    }

   /*  public int getExp() {
        if (user.health)
    }*/
}
