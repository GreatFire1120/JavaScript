package book;
 

public class Book {

    int weight;
    double enjoyment;
    Genre genre;
    boolean isChecked = false;	// By Percy
    
    public Book(int weight, double enjoyment, Genre genre) {
        this.weight = weight;
        this.enjoyment = enjoyment;
        this.genre = genre;
    }

    public int getWeight() {
        return weight;
    }

    public double getEnjoyment() {
        return enjoyment;
    }

    public Genre getGenre() {
        return genre;
    }
    
    // By percy
	public boolean getCheck() {
		return this.isChecked;
	}
	
    // By percy
    public void setChecked(boolean ok){
    	this.isChecked = ok;
    }
    
    @Override
    public String toString() {
        return "Book{" + "weight=" + weight + ", enjoyment=" + enjoyment + ", genre=" + genre + '}';
    }

    public enum Genre {

        CLASSICS, FANTASY, SCIENCE_FICTION;
    }
}
