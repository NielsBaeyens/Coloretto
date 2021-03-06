package domein;

import java.util.ArrayList;
import java.util.List;

import domein.Kaart;

public class Stapel {

    private List<Kaart> kaartenStapel;

    public Stapel() {
    	kaartenStapel = new ArrayList<>();
    }


    public void legKaartOpStapel(Kaart kaart) {
    	kaartenStapel.add(kaart);
    }

    public Kaart getKaartenVanStapel() {
    	Kaart kaart = kaartenStapel.remove(kaartenStapel.size()-1);
        return kaart;
    }

    public void cleanUp() {
        kaartenStapel.clear();
    }


	public List<Kaart> getKaartenStapel() {
		return kaartenStapel;
	}


	public void setKaartenStapel(List<Kaart> kaartenStapel) {
		this.kaartenStapel = kaartenStapel;
	}
}