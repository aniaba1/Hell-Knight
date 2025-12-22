package io.github.aniaba1;

public abstract class Enemy {
    private String name;
    private int level;
    private int health;
    private int attack;
    private int defense;
    private int aura;
    private int speed;
    private int exp;


    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setAura(int aura) {
        this.aura = aura;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getAura() {
        return aura;
    }

    public int getSpeed() {
        return speed;
    }

    public int getExp() {
        return exp;
    }
    
    void Health(int health) {
        if (health <= 0) {
            System.out.println(this.name + " defeated!");
        }
    }
}
