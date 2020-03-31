package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domein.Deck;
import domein.Kaart;
import domein.KaartType;
import domein.Rij;
import domein.Spel;
import domein.Speler;

public class ColorettoApplicatie {

	private static Scanner invoer = new Scanner(System.in);

	private Spel spel;

	public ColorettoApplicatie(Spel spel) {
		this.spel = spel;
	}

	public void start() {
	//public static void main(String[] args) {
		System.out.println("Welkom bij Coloretto!");

		// We gebruiken 1 spel kaarten die door elkaar geschud zijn
		//
		Deck deck = new Deck();
		List<Speler> spelers = new ArrayList<Speler>();

		// We voeren nu de spelers in
		//
		int aantalSpelers;
		String naam;

		aantalSpelers = 0;
		while (aantalSpelers < 4 || aantalSpelers > 5) {
			System.out.println("Met hoeveel spelers wilt u spelen, met 4 of 5?");
			aantalSpelers = vraagIntegerKeuze();
		}

		for (int teller = 1; teller <= aantalSpelers; teller++) {
			System.out.printf("Geef naam van speler %d:%n", teller);
			naam = vraagStringKeuze();

			String spelerKleur = Deck.getKleuren()[teller - 1];
			Kaart kleurKaart = deck.geefKaartMetKleur(spelerKleur);

			System.out.println("De kaart voor u is" + " : " + kleurKaart.getKleur());
			Speler speler = new Speler(naam);
			speler.setKleurKaart(kleurKaart);
			spelers.add(speler);
		}

		// Nu verstoppen we de laatste ronde kaart in het deck
		//
		deck.verstopDeLaatsteRondeKaart();

		// Nu we de spelers kennen kunnen we beginnen met het spel
		// Elke speler in het spel heeft zijn eigen stapel met kaarten
		//
		Spel spel = new Spel(spelers);

		// Nu begint de applicatie
		//
		ColorettoApplicatie app = new ColorettoApplicatie(spel);
	
		//boolean rijGekozen = false;
			
		//if() {
		
		boolean doorlopenSpelers = true;
		int aantalKeerSpelersDoorloopt = 1;
		while (doorlopenSpelers) {

			//System.out.println("Ronde nr " + aantalKeerSpelersDoorloopt + " begint.");
			List<Rij> rondeRijen = new ArrayList<Rij>();

			for (Speler speler : spel.getSpelers()) {
				System.out.println("===== Speler " + speler.getNaam() + " is aan zet ======");

				// Keuze : A. Draw and place a card
				// B. Take a row card
				//
				String keuze = "-";
				while (!keuze.equalsIgnoreCase("a") && !keuze.equalsIgnoreCase("b")) {
					System.out.println("Wil je een kaart trekken of een rij kaart nemen?");
					spel.toonRijen();
					spel.toonSpelersKaarten();
					System.out.println("Kies A om een kaart te trekken");
					System.out.println("Kies B om een rij kaart te nemen");
					System.out.print("Jouw keuze : ");
					keuze = vraagStringKeuze();
				}

				if (keuze.equalsIgnoreCase("a")) {
					// De speler neemt een spelerskaart
					//
					Kaart kaart = spel.getDeck().geefKaart();

					if (kaart.getType() == KaartType.LaatsteRonde) {
						System.out.println("!!! De laatste ronde kaart is getrokken !!!");
						System.out.println("==> Dit is dus de laatste ronde voor het einde van het spel.");
						doorlopenSpelers = false;
						kaart = spel.getDeck().geefKaart();
					}

					boolean rijGekozen = false;
					while (!rijGekozen) {
						System.out.println("Uw kaart is: " + kaart);
						System.out.println("In welke rij wil je de kaart plaatsen?");
						spel.toonRijen();
						int rijNr = vraagIntegerKeuze();
						rijNr = app.controleerRijNr(rijNr);
						if (rijNr > 0) {
							Rij rij = spel.getRijen().get(rijNr - 1);
							if (rij.isVol()) {
								// Rij zit vol, verkeerde keuze
								

								System.err.println("Sorry, deze rij kan maximaal 3 kaarten bevatten");
							} else {
								// Voeg de kaart aan de rij toe
								//
								rij.getKaarten().add(kaart);
								rijGekozen = true;
							}
						}
					}

				} else //keuze b
				{
					// Speler neemt een rij kaart
					//
					boolean rijGekozen = false;
					while (!rijGekozen)
					if (spel.telNietLegeRijen() > 0 && rijGekozen == false) {
						 {
							System.out.println("Welke rij kaarten wil je nemen?");
							spel.toonRijen();
							int rijNr = vraagIntegerKeuze();
							rijNr = app.controleerRijNr(rijNr);
							if (rijNr >0) { //check of je groter getal dan aantal 
								Rij rij = spel.getRijen().get(rijNr - 1);
								if (rij.isLeeg()) {
									// Rij is leeg, verkeerde keuze
									//
									System.err.println("Sorry, deze rij bevat geen kaarten");
								} else {
									// Voeg de kaarten in de rij aan de speler toe
									//
									speler.getKaarten().addAll(rij.getKaarten());
									speler.sorteerKaartenPerKleur();
									System.out
											.println("De kaarten van deze speler (" + speler.getNaam() 
											+ ") zijn nu:");
									speler.toonSpelerKaarten();

									// Verwijder de kaarten uit de rij, zet de rij apart
									//
									rij.getKaarten().clear();
									rondeRijen.add(rij);
									spel.getRijen().remove(rij);

									rijGekozen = true;
									
								}
							}

						}
					} else {
						System.err.println("Sorry, er zijn geen rijen die kaarten bevatten");
					}
				}

			} // Spelers lus

			// Op het einde van de ronde voegen we de ronde kaarten/rijen terug toe aan het
			// spel
			//
			spel.getRijen().addAll(rondeRijen);
			spel.sorteerRondeRijen();

			aantalKeerSpelersDoorloopt++;
		} // ronde lus
		
		
		//}//einde ronde ------------------------------------------------------------------------------------------------------------------
		
		
		// Vraag aan elke speler die een joker heeft in welke kleur ze die willen veranderen
		//
		for (Speler speler : spel.getSpelers()) {
			for (Kaart joker : speler.zoekJokers()) {
				System.out.print("Speler "+speler.getNaam()+" : kies de kleur waar je joker voor telt: ");
				String kleur = vraagStringKeuze();
				speler.vervangJokerKaart(joker, kleur);
			}
		}

		// Evalueer nu het resultaat en toon de scores
		//
		Speler winnaar = null;
		int topScore = 0;
		int aantalWinnaars = 0;
		System.out.println("-------------------------------------------------------");
		System.out.println("Dit zijn de scores van de spelers:");
		for (Speler speler : spel.getSpelers()) {
			int score = speler.berekenScore();
			System.out.println("Speler " + speler.getNaam() + " : " + score);
			
			if (score>=topScore ) {
			/*zorgen dat het herkent wanneer er gelijkspel is
			&& speler.berekenScore() != speler.berekenScore()*/
				winnaar = speler;
				topScore = score;
				//aantalWinnaars +=1;
			}
		}
		System.out.println("-------------------------------------------------------");
		
		//for(int teller = 0; teller < aantalWinnaars; teller ++){
		System.out.println("DE WINNAAR IS : " + winnaar.getNaam() + " met score " + topScore);
		//}
		System.out.println("-------------------------------------------------------");
		System.out.println("Bedankt om te spelen!");
		System.out.println("-------------------------------------------------------");
	}

	private static String vraagStringKeuze() {
		String keuze = invoer.nextLine();
		while (keuze.length() == 0) {
			keuze = invoer.nextLine();
		}
		return keuze;
	}

	private static int vraagIntegerKeuze() {
		int keuze = invoer.nextInt();
		return keuze;
	}

	/**
	 * Controleer de rij en geef de waarde terug als alles OK is of -1 in geval van
	 * een probleem.
	 * 
	 * @param rij
	 * @return
	 */
	private int controleerRijNr(int rij) {
		int rijNr = rij;
		if (rijNr < 1 || rijNr > spel.getRijen().size()) {
			System.err.println("Sorry, kies een rij tussen 1 en " + spel.getRijen().size());
			rijNr = -1;
		}
		return rijNr;
	}
}
