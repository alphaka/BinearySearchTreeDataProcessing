// This class read the 13 csv files from spotify.com and
// contains the binary search tree and the hashtable object

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.HashMap;

public class MyApp {

	private static BSTList list;
	private static HashMap<String, Integer> artist;
	
	public static void main(String[] args) throws Exception {
		
		String[]  myFiles = {"07-03.csv","07-10.csv", "07-17.csv", "07-24.csv",
						   	 "07-31.csv", "08-07.csv", "08-14.csv", "08-21.csv",
							 "08-28.csv", "09-04.csv", "09-11.csv", "09-18.csv",
							 "09-25.csv"};
		
		// Instantiating a binary search tree 'list' that will contain
		// the list of all the 2600 songs
		
		list = new BSTList();
		
		// Instantiating a hashMap 'artist' that will keep track of the
		// number of times their song has been played
		
		artist = new HashMap <String, Integer>();
		
		// Iterating through the 13 csv files
		for(int i=0; i<myFiles.length; i++) {
			try {
			readFile(myFiles[i]);
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			}
		}
		
		// displaying the binary search tree
		list.display(list.root);
		// the output file to 'list.display()' is 'Song Titles.csv'
		
		// displaying the average number of songs per artist
		displayFacts(artist);
		// the output file to 'displayFacts()' is 'Facts.csv'
		
		//calling the subset method after instantiating SongPlayList
		SongPlayList myPlaylist = new SongPlayList("Jingle Bells", "Wasn't me");
		myPlaylist.subSet(list.root);
		
		// the result to 'myPlaylist.subset()' is printed into 'PlayList.csv'
	}

	// The below method read the data from the csv file and collect them inside the
	// 'list' binary tree, then use the 'artist' hashtable to count how much times
	// an artist songs has been played
	
	private static void readFile(String file) throws Exception {
		
		// System.getProperty("user.dir")+"/src/" returns the string of the my current working
		// directory
		Scanner sc = new Scanner(new File(System.getProperty("user.dir")+"/src/"+file));
		
		while(sc.hasNextLine() ) {
			
			String line = sc.nextLine();
			String [] arrOfLine = line.split(",");
			// Take care of unwanted quoted
			

			try {
				for(int i=0; i<arrOfLine.length; i++) {
					if(arrOfLine[i].charAt(0) == '"')
						arrOfLine[i] = arrOfLine[i].substring(1, arrOfLine[i].length()-1);
				}
				
				if(!arrOfLine[0].equals("Position")) {
					// inserting the title of a song into 'list'
					list.insert(arrOfLine[1]);
				
					// inserting the number of streams into 'artist',
					// arrOfLine[2] contains the name of an artist
					if(artist.containsKey(arrOfLine[2])) {
						artist.replace(arrOfLine[2], 
							artist.get(arrOfLine[2])+ Integer.parseInt(arrOfLine[3]));
					} else {
						// inserting the artist name and the number of streams the first time
						artist.put(arrOfLine[2], Integer.parseInt(arrOfLine[3]));
					}
				}
			} catch (IndexOutOfBoundsException e) {
				e.getMessage();
			} catch (NumberFormatException e) {
				e.getMessage();
			}	
						
		} // end while loop
		
	} // end readFile
	
	private static void displayFacts(HashMap<String, Integer> artist) throws Exception {
		
		PrintStream ps = new PrintStream(new File(System.getProperty("user.dir")+"/src/"+"Facts.csv"));
		
		ps.println("Artist,Total Streams,Average Monthly Streams,Average Weekly Streams");
		// We have 13 weekly csv files for a quarter of the year (3 months)
		
		//Below, I am using the lambda function of the forEach method to iterate through the
		//'artist' hashmap
		
		artist.forEach((key, value) -> {
			ps.println(key+ ","+ value +","+ value/3+","+value/13);
		});
	}
}
