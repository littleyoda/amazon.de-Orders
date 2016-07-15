package amazon.data;

public class Artikel {
	public String preis;
	public String name;
	public String url;
	
	public Bestellung bestellung;
	public Zustellung zustellung;
	
	public Artikel() {
		zustellung = new Zustellung();
		bestellung = new Bestellung();
		preis = "";
		name = "";
		url = "";
	}
	
	@Override
	public String toString() {
		return "Artikel [preis=" + preis + ", name=" + name + ", url=" + url + ", bestellung=" + bestellung
				+ ", zustellung=" + zustellung + "]";
	}

}
