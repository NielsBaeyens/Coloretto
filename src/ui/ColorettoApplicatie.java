package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import domein.Deck;
import domein.Kaart;
import domein.Spel;
import domein.Stapel;

public class ColorettoApplicatie 
{
    private Spel sp;


    public ColorettoApplicatie(Spel sp) 
    {
        this.sp = sp;
    }

    public void startSpel()
    {
        Scanner invoer = new Scanner(System.in);
        int aantalSpelers;
        String naam;

        System.out.println("Welgekomen bij Coloretto!");

        do
        {
            System.out.println("Met hoeveel spelers wilt u spelen?");

            aantalSpelers = invoer.nextInt();

            if(aantalSpelers != 4 && aantalSpelers != 5)
            {
                System.out.println("Het aantal spelers is minimum 4 en maximum 5.");
            }

        }while(aantalSpelers != 4 && aantalSpelers != 5);
            
        for (int teller = 1; teller <= aantalSpelers; teller++) {
            System.out.printf("Geef naam van speler %d:%n", teller);
            naam = invoer.next();

            Deck deck = new Deck();

            Kaart kaart = deck.geefKaart();

            System.out.println("De kaart voor u is"+" : "+kaart.getKleur());

        }

        /*
         *  public Kaart geefKaart() {
        var kaart = kaarten.remove(kaarten.size() - 1);
        return kaart;
            }
         */

        System.out.printf("Om een kaart te trekken, kies 1. Om een stapel te nemen, kies 2.%n");
        int keuze = invoer.nextInt();

        if(keuze <1 || keuze >2)
            throw new IllegalArgumentException("Keuze moet 1 of 2 zijn.%n");
        else if (keuze==1) {

             Deck deck = new Deck();
             deck.geefKaart();

            }
        else if(keuze == 2)
        {
            Stapel stapel = new Stapel();
            stapel.getKaartenVanStapel();
        }

    }//einde main
}//einde body
