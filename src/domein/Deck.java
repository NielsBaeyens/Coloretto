package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

	List<Kaart> kaarten;

	public Deck() {
		kaarten = new ArrayList<>();
		voegKaartenToe();
		schudDeKaarten();
	}

	private void voegKaartenToe() {
		String kleuren[] = new String[] { "Oranje", "Blauw", "Bruin", "Geel", "Grijs", "Groen", "Roze" };
		for (String kleur : kleuren) {
			for (int i = 0; i < 9; i++) {
				Kaart kaart = new Kaart(kleur);
				kaarten.add(kaart);
			}

		}
	}

	public void schudDeKaarten() {
		Random rnd = new Random();
		List<Kaart> list = new ArrayList<Kaart>();
		while (!kaarten.isEmpty()) {
			int index = Math.abs(rnd.nextInt()) % kaarten.size();
			Kaart kaart = kaarten.remove(index);
			list.add(kaart);
		}
		kaarten = list;
	}

	public Kaart geefKaart() {
		var kaart = kaarten.remove(kaarten.size() - 1);
		return kaart;
	}

	public static void main(String[] args) {
		Deck deck = new Deck();

		for (int i = 0; i < 5; i++) {
			Kaart kaart = deck.geefKaart();
			System.out.println("Kaart nr " + (i + 1) + " : " + kaart.getKleur());
		}

	}

}