package amazon.data;

public class Bestellung {
	
	public Bestellung() {
		datum = "";
		wert = "";
	}
	@Override
	public String toString() {
		return "Bestellung [datum=" + datum + ", wert=" + wert + "]";
	}
	public String datum;
	public String wert;
}