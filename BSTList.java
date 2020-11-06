// This class implements the binary search tree (BST)

import java.io.File;
import java.io.PrintStream;

public class BSTList {
	
	public static PrintStream ps;
	public Song root;
	
	public void insert(String title) throws Exception{
		ps = new PrintStream(new File(System.getProperty("user.dir")+"/src/"+
						"ArtistsSorted From 07-2020 T0 09-2020.csv"));
		ps.println("Alphabetized list of all songs from the BST");
		
		Song newSong = new Song(title);
		
		if(root==null) {
			root = newSong;
		} else {
			Song current = root;
			Song parent;
			
			// Trickle-down
			while(true) {
				parent = current;
				
				// double-checking whether the title of the song has been already
				// stored in the binary search tree
				if (title.toLowerCase().compareTo(parent.title.toLowerCase()) == 0)
					return;
				// if the title is not stored yet, place 'newSong' under the correct node
				else if(title.compareTo(parent.title) < 0) {
					current = current.leftChild;
					if(current == null) {
						parent.leftChild = newSong;
						return;
					}
				} else {
					
					current = current.rightChild;
					if(current == null) {
						parent.rightChild = newSong;
						return;
					}
				} // end else go right
				
			} // end while
					
		} // end else not root
				
	} // end insert()
	
	//This method displays the binary search tree using inOrder traversal
	
	public void display(Song localRoot) throws Exception {
		
		if(localRoot != null) {
			display(localRoot.leftChild);
			ps.print(localRoot.title + "\n");
			display(localRoot.rightChild);
		}
		
	}
	
}
