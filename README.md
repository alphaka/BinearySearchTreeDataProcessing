# BinearySearchTreeDataProcessing

The purpose of this project is to read multiple CSV files from spotify.com
and produce a sorted list using a binary search tree (BST) data structure.

This program contains four classes.

 - The first one is the 'Song.java' class which represents the node class.
 - The second class is 'BSTList.java'. This class has an 'insert' and 'display'
   methods that insert a new node and display the binary search tree respectively.
 - The third class is 'SongPlayList.java'. The goal of this class is to produce an
   alphabetized list based on the beginning and the end of a string. For instance,
   returning a list of songs from the BSTList situated between "J" and "W".
 - Lastly, we have the 'MyApp.java'. This class contains the main method and instantiates
   objects of BSTList, SongPlayList, and the java hashmap library to process our data
   from the thirteen CSV files.
