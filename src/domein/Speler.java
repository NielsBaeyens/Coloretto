package domein;

public class Speler 
{
	private String naam;

    public Speler(String naam) {
		super();
		this.naam = naam;
	}

	public String getNaam() 
    {
        return naam;
    }

    public void setNaam(String naam) 
    {
        this.naam = naam;
    }

}