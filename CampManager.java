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
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CampManager {
  private CamperBST campers; // private field of the CamperBST class
  //list of possible cabins a camper can be assigned to
  private final static String[] CABIN_NAMES =
      new String[] {"Otter Overpass", "Wolverine Woodland", "Badger Bunkhouse"}; 

  /**
   * Constructor for the CampManager by initializing the campers field
   */
  public CampManager() {
    campers = new CamperBST();
  }

  /**
   * "Enrolls" a camper by determining their cabin and adding them to the tree.
   * 
   * @param newCamper - the camper to be enrolled into the camp
   */
  public void enrollCamper(Camper newCamper) {
    // assign campers to the proper cabin based on their age
    if (newCamper.getAge() == 9 || newCamper.getAge() == 8) {
      newCamper.assignCabin(CABIN_NAMES[0]);
      campers.insert(newCamper);
    } else if (newCamper.getAge() >= 10 && newCamper.getAge() <= 12) {
      newCamper.assignCabin(CABIN_NAMES[1]);
      campers.insert(newCamper);
    } else if (newCamper.getAge() == 13 || newCamper.getAge() == 14) {
      newCamper.assignCabin(CABIN_NAMES[2]);
      campers.insert(newCamper);
    }

  }

  /**
   * Traverses the tree in the designated order by calling it through the CamperBST class.
   * 
   * @param order - the type of traversal for the tree to perform
   * @return the Iterator of Campers from CampBST.traverse()
   */
  public Iterator traverse(String order) {
    return campers.traverse(order);
  }

  /**
   * Prints statistics based on the current "state" of the camp. The statistics to be printed is the
   * total number of campers.
   */
  public void printStatistics() {
    System.out.print(campers.size());
  }

  /**
   * "Unenrolls" a camper by removing them from the tree.
   * 
   * @param delCamper - the camper to be unenrolled the camp
   * @throws NoSuchElementException - if camperBST.delete throws a NoSuchElementException
   */
  public void unenrollCamper(Camper delCamper) {
    try {
      // calls the delete method in the CamperBST class
      campers.delete(delCamper);
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException();
    }
  }

}
