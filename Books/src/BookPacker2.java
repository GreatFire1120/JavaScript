import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import book.Book;

public class BookPacker1 {
	int cnt = 0;

	private int getIntValFromEnum(Book.Genre genre) {
		
		if (genre == Book.Genre.CLASSICS)
			return 0;
		else if (genre==Book.Genre.FANTASY)
			return 1;
		if (genre == Book.Genre.SCIENCE_FICTION) 
			return 2;
		
		return -1;
	}
	
    public double computeMaximumEnjoyment(List<Book> books, int weightLimit, int minFromEachGenre) {
        // TODO: Implement

    	// By Percy
		boolean bestGenre[] =  {false, false, false};
		int eachGenreCnt[] = {0, 0, 0};
		double sumRate = 0;
		
		System.out.println("weightLimit: " + weightLimit + ", minFromEachGenre: " + minFromEachGenre);
		// sort
		for (int i = 0; i < books.size(); i++) {
			for (int j = 1; j < books.size(); j++) {
				if (books.get(i).getEnjoyment() < books.get(j).getEnjoyment()) {
					Book tmp1 = books.get(i), tmp2=books.get(j);
					
					books.remove(i);
					books.add(i, tmp2);
					books.remove(j);
					books.add(j, tmp1);
				}
			}
		}
		
		if (cnt == 0)  {
			cnt++;
			for (int i = 0; i < books.size(); i++) {
				System.out.println("1 -> enjoy:" + books.get(i).getEnjoyment() + ", weight: " + books.get(i).getWeight() + ", genre: " + books.get(i).getGenre());
			}
		}
		
		// selection
		if (minFromEachGenre == 0) {
			for (int i = 0; i < books.size(); i++) {
//				if ((books.get(i).getGenre() == Book.Genre.CLASSICS) &&
				if (!bestGenre[getIntValFromEnum(books.get(i).getGenre())]) {
					books.get(i).setChecked(true);
					bestGenre[getIntValFromEnum(books.get(i).getGenre())] = true;
					sumRate += books.get(i).getEnjoyment();
					System.out.println("3 -> enjoy:" + books.get(i).getEnjoyment() + ", weight: " + books.get(i).getWeight() + ", genre: " + books.get(i).getGenre());
				}
				/*
				else if (books.get(i).getGenre() == Book.Genre.FANTASY &&
				!bestGenre[1]) {
					books.get(i).setChecked(true);
					bestGenre[1] = true;
				} else if (books.get(i).getGenre() == Book.Genre.SCIENCE_FICTION&&
				!bestGenre[2]) {
					books.get(i).setChecked(true);
					bestGenre[2] = true;
				}
				*/
			}
		} else if (minFromEachGenre > 0) {
			for (int i = books.size() - 1; i >= 0; i--) {
				if ((int)books.get(i).getWeight() <= weightLimit &&
						eachGenreCnt[getIntValFromEnum(books.get(i).getGenre())] < minFromEachGenre) {
					weightLimit -= (int)books.get(i).getWeight();
					books.get(i).setChecked(true);
					eachGenreCnt[getIntValFromEnum(books.get(i).getGenre())]++;
					
					sumRate += books.get(i).getEnjoyment();
					System.out.println("2 -> enjoy:" + books.get(i).getEnjoyment() + ", weight: " + books.get(i).getWeight() + ", genre: " + books.get(i).getGenre());
				}
			}
		} else {
			return -1;
		}
		
		System.out.println("sum -> " + sumRate);
		
		return sumRate;
    }

    public List<Book> computeMaximumEnjoymentBooks(List<Book> books, int weightLimit, int minFromEachGenre) {
        // TODO: Implement

    	// By percy
    	for (int i = 0; i < books.size(); i++) {
    		if (books.get(i).getCheck() == true) {
    			String resultString = books.toString();
    			System.out.println(resultString);
    		}
    	}

        return Collections.emptyList();
    }

    public static void main(String[] args) {
        // This performs a small test
        System.out.print("Testing basic correctness: ");

        BookPacker1 packer = new BookPacker1();
        List<Book> books = new ArrayList<>(Arrays.asList(new Book(1, 1, Book.Genre.CLASSICS), new Book(1, 1, Book.Genre.FANTASY), new Book(1, 1, Book.Genre.SCIENCE_FICTION), new Book(3, 5, Book.Genre.FANTASY)));

        if (packer.computeMaximumEnjoyment(books, 3, 1) != 3.0 || packer.computeMaximumEnjoyment(books, 3, 0) != 5.0) {
            System.out.println("BookPacker is incorrect!");
        } else {
            System.out.println("Everything seems fine so far.");

            System.out.println(" Best books without restrictions:   " + packer.computeMaximumEnjoymentBooks(books, 3, 0));
            System.out.println(" Best books with 1 from each genre: " + packer.computeMaximumEnjoymentBooks(books, 3, 1));
        }

        System.out.println("Testing basic efficiency: this should take less than a second...");
        long start = System.currentTimeMillis();

        for (int i = 0; i < 20; i++) {
            books.add(new Book(i, Math.sqrt(i), Book.Genre.values()[i % 3]));
        }

        for (int i = 0; i < 80; i++) {
            packer.computeMaximumEnjoyment(books, i, i / 20);
        }

        System.out.println("Done. That took " + ((System.currentTimeMillis() - start) / 1000.0) + " seconds.");
    }
}

