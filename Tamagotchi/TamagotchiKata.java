package com.mycompany.tamagotchikata;

public class TamagotchiKata {

    public static void main(String[] args) {
        Tamagotchi tama = new Tamagotchi();
        Tamagotchi tama2 = new Tamagotchi("Marcos");
        System.out.println(tama);
        System.out.println(tama2);
        tama.feed();
        tama2.feed();
        tama.play();
        tama2.play();
        tama.putToBed();
        tama2.putToBed();
        tama.poop();
        tama2.poop();
        tama.passTime();
        tama2.passTime();
    }
}
