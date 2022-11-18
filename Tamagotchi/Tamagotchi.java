package com.mycompany.tamagotchikata;

class Tamagotchi {

    private String name;
    private int fullness;
    private int energy;
    private int happiness;

    public Tamagotchi() {
        this.name = "Manolito";
        this.fullness = 50;
        this.energy = 50;
        this.happiness = 50;
    }

    public Tamagotchi(String name) {
        this();
        this.name = name;
    }

    public void feed() {
        fullness += 10;
        normalizeValues();
        System.out.println("You have fed " + this.name + ". Their fullness is now at " + this.fullness + ".");
    }

    public void play() {
        happiness += 15;
        energy -= 10;
        normalizeValues();
        System.out.println("You have played with " + this.name + ". Their happiness has increased to " + this.happiness + " and their energy has decreased to " + this.energy + ".");
    }

    public void putToBed() {
        energy += 20;
        normalizeValues();
        System.out.println("You have put " + this.name + " to bed. They have recovered " + this.energy + " energy.");
    }

    public void poop() {
        fullness -= 20;
        normalizeValues();
        System.out.println(this.name + " has pooped. Their fullness is now at " + this.energy + ".");
    }

    public void passTime() {
        this.energy -= 5;
        this.fullness -= 10;
        this.happiness -= 10;
        normalizeValues();
        if (this.name.endsWith("s")) {
            System.out.println("Time has passed. " + this.name + "' energy, fullness and happiness have decreased.");
        } else {
            System.out.println("Time has passed. " + this.name + "'s energy, fullness and happiness have decreased.");
        }
    }

    @Override
    public String toString() {
        return this.name + "\n>Fullness: " + this.fullness + "\n>Tiredness: " + this.energy + "\n>Happiness: " + this.happiness;
    }

    private void normalizeValues() {
        if (this.fullness > 100) {
            this.fullness = 100;
        }
        if (this.fullness < 1) {
            this.fullness = 1;
        }
        if (this.energy > 100) {
            this.energy = 100;
        }
        if (this.energy < 1) {
            this.energy = 1;
        }
        if (this.happiness > 100) {
            this.happiness = 100;
        }
        if (this.happiness < 1) {
            this.happiness = 1;
        }
    }

}
