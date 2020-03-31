package main;

import domein.Spel;
import ui.ColorettoApplicatie;

public class StartUp 
{

    public static void main(String[] args) 
    {
        new ColorettoApplicatie(new Spel() ).startSpel();
    }

}