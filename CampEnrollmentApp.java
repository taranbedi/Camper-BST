
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (PO9 Camp Badger)
// Files: (Camper.java, CampTreeNode.java, CamperBST.java, CampManager.java, CampEnrollmentApp.java)
// Course: (CS 300, fall, 2019)
//
// Author: (Taran Bedi)
// Email: (tbedi@wisc.edu)
// Lecturer's Name: (Mouna Kacem)
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (None)
// Online Sources: (None)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

public class CampEnrollmentApp {

  /**
   * Constructs an empty constructor
   */
  public CampEnrollmentApp() {

  }

  /**
   * Driver method for the program
   * 
   * @param args
   * @throws IOException - if file doesn't exist
   */
  public static void main(String[] args) throws IOException {
    CampManager x = new CampManager();

    List<String> fileLines = Files.readAllLines(Paths.get("sim.txt"));
    // create a for each loop to iterate through every line in the file
    for (String line : fileLines) {
      System.out.print(line);
      try {

        String[] array = line.split(" ");
        // checks the first Character in each line to determine what operation to call
        if (array[0].equals("E")) {
          Camper newCamper = new Camper(array[2], array[1], Integer.parseInt(array[3]));
          x.enrollCamper(newCamper);
        } else if (array[0].equals("R")) {
          Camper newCamper = new Camper(array[2], array[1]);
          x.unenrollCamper(newCamper);
        } else if (array[0].equals("T")) {
          String a = array[1];
          x.traverse(a);
        } else if (array[0].equals("S")) {
          x.printStatistics();
        }
      } catch (NoSuchElementException f) {
        // catches NoSuchElement exception if the file tries to remove a camper that doesn't exist
        System.out.print("NoSuchElementException");
      } catch (IllegalArgumentException d) {
        // catches IllegalArgument exception if the file tries add a camper that has an invalid age
        System.out.print("illegal argument");
      }

    }

  }
}

