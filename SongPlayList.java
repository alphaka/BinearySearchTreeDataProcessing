// The SongPlayList implementation

import java.io.File;
import java.io.PrintStream;

public class SongPlayList {
	
  private PrintStream ps;
  private String start;
  private String end;
  
  // SongPlayList's constructor 
  public SongPlayList(String start, String end) throws Exception {
	  ps = new PrintStream(new File(System.getProperty("user.dir")+"/src/"+"Playlist.csv"));
	  this.start = start;
	  this.end = end;
	  
	  ps.println("Alphabetized list from " + start + " to " + end);
  }
   
  
  // 'subSet()' displays the alphabetized list of the song titles between 'start' and 'end'
  
  public void subSet(Song root) {
	
	  try {	
		if(root != null) {
			subSet(root.leftChild);
		
			if(root.leftChild.title.compareToIgnoreCase(start)>=0 && 
			     root.leftChild.title.compareToIgnoreCase(end)<=0) {
					ps.println(root.title);
		}
		
		subSet(root.rightChild);
		}
	
	  }catch (Exception e) {
		e.getMessage();	
	  }
  }

}