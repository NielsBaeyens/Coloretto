package domein;

import java.util.ArrayList;
import java.util.List;

public class Spel 
{
	private Deck deck;
	private List<Speler> spelers;
	private List<Stapel> stapels;
	
	public Spel()
	{
		
	}
	
	public Spel(List<Speler> spelers) {
		this.deck = new Deck();
		this.spelers = spelers;
		this.stapels = new ArrayList<Stapel>();
		for (Speler speler : spelers) {
			stapels.add(new Stapel());
		}
	}

	public void legKaartOpStapel(int stapelIndex) {
		Kaart kaart = deck.geefKaart();
		Stapel stapel = stapels.get(stapelIndex);
		stapel.legKaartOpStapel(kaart);
	}
	
	public Kaart neemKaartenVanStapel(int stapelIndex) {
		Stapel stapel = stapels.get(stapelIndex);
		Kaart kaart = stapel.getKaartenVanStapel();
		return kaart;
	}
	
	public int berekenScore() {
		return 0;
	}
	
	public String geefKaart()
	{
		return null;
	}
	
	public boolean isGenomen()
	{
		return false;
	}
	
	
	
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public List<Speler> getSpelers() {
		return spelers;
	}

	public void setSpelers(List<Speler> spelers) {
		this.spelers = spelers;
	}

	public List<Stapel> getStapels() {
		return stapels;
	}

	public void setStapels(List<Stapel> stapels) {
		this.stapels = stapels;
	}
}
