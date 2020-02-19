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
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CamperBST {

  public CampTreeNode root; // head node of the BST
  private int size; // size of the BST
  private LinkedList<Camper> traversedLList; // LinkedList to maintain current traversal

  /**
   * Constructs a BST and initializes the fields
   */
  public CamperBST() {
    size = 0;
    root = null;
  }

  /**
   * Finds the size of the BST
   * 
   * @returns the size of the BST
   */
  public int size() {
    return size;
  }

  /**
   * Checks if the BST is empty
   * 
   * @returns true if BST is empty, else false
   */
  public boolean isEmpty() {
    // checks if the head node is empty
    if (root == null) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * starts tree insertion by calling insertHelp() on the root and
   * 
   * @param newCamper - camper to be inserted in the BST
   */
  public void insert(Camper newCamper) {
    // calls the the recursive help method
    root = insertHelp(root, newCamper);
    size++;
  }


  /**
   * Recursive helper method to insert.
   * 
   * @param current, The "root" of the subtree we are inserting into, ie the node we are currently
   *        at.
   * @param newCamper, the camper to be inserted into the tree
   * @return the root of the modified subtree we inserted into
   */
  private CampTreeNode insertHelp(CampTreeNode current, Camper newCamper) {
    // checks if BST is empty
    if (current == null) {
      current = new CampTreeNode();
      current.setData(newCamper);

    } else if (newCamper.compareTo(current.getData()) > 0) {
      // recursively calls the method with current set to the new node on the right
      current.setRightNode(insertHelp(current.getRightNode(), newCamper));
    } else {
      // recursively calls the method with current set to the new node on the left
      current.setLeftNode(insertHelp(current.getLeftNode(), newCamper));
    }

    return current;

  }


  /**
   * Prints the contents of this tree in alphabetical order based on the string "lastName,
   * firstName"
   */
  public void print() {
    printHelp(root);
  }

  /**
   * Recursive helper method that iterates through the BST
   * 
   * @param current
   */
  private void printHelp(CampTreeNode current) {
    if (current == null) {
      return;
    }
    printHelp(current.getLeftNode());
    System.out.println(current.getData());
    printHelp(current.getRightNode());
  }

  /**
   * Deletes a Camper into the binary search tree if it exists.
   * 
   * @param key, the camper to be deleted from the tree
   * @throws NoSuchElementException if it is thrown by deleteHelp
   */
  public void delete(Camper key) throws NoSuchElementException {
    root = deleteHelp(root, key);
    // decrements the size
    size--;
  }

  /**
   * Recursive helper method to delete.
   * 
   * @param current, The "root" of the subtree we are deleting from, * ie the node we are currently
   *        at.
   * @param key, the camper to be deleted from the tree
   * @return the root of the modified subtree we deleted from
   * @throws NoSuchElementException if the camper is not in the tree
   */
  private CampTreeNode deleteHelp(CampTreeNode current, Camper key) {
    // check if BST is empty
    if (current == null) {
      throw new NoSuchElementException();
    }
    if (key.compareTo(current.getData()) < 0) {
      // recursive call to iterate through BST
      current.setLeftNode(deleteHelp(current.getLeftNode(), key));
    } else if (key.compareTo(current.getData()) > 0) {
      // recursive call to iterate through BST
      current.setRightNode(deleteHelp(current.getRightNode(), key));
    } else {
      // Base case when key matches current
      // when the node being removed has no children
      if (current.getRightNode() == null && current.getLeftNode() == null) {
        current = null;
        return null;
      } else if (current.getLeftNode() == null) {
        // when the node being removed has one child
        return current.getRightNode();
      } else if (current.getRightNode() == null) {
        // when the node being removed has one child
        return current.getLeftNode();
      } else {
        // when the node being removed has two children
        current.setData(recursiveTwoChild(current));
        current.setRightNode(deleteHelp(current.getRightNode(), current.getData()));
      }

    }
    return current;
  }

  /**
   * A recursive helper method to find the left most node of the right subtree when a node has two
   * children
   * 
   * @param current- node that has two children
   * @returns a new node which is the left most node of the right subtree of the original node
   */
  public Camper recursiveTwoChild(CampTreeNode current) {
    current = current.getRightNode();
    while (current.getLeftNode() != null) {
      // Continuously changes current to it's left node
      current = current.getLeftNode();
    }
    return current.getData();
  }

  /**
   * returns an iterator of camper in the correct order as designated
   * 
   * @param order - the order to traverse the list
   * @return returns camper iterator
   */
  public Iterator<Camper> traverse(String order) {
    // first time traversing need to initialize LinkedList
    if (traversedLList == null) {
      traversedLList = new LinkedList<Camper>();
    } else {
      // clear the list to start over for a new traversal
      traversedLList.clear();
    }
    traverseHelp(root, order);
    return traversedLList.listIterator();
  }

  /**
   * Recursive helper method to traverse. Will take the current CampTreeNode’s data and add it to
   * traversedLList based on the given order. Then continue to recurse on the correct subtree.
   * 
   * @param current, the root of the current subtree we are traversing
   * @param order, the type of traversal to perform
   */
  private void traverseHelp(CampTreeNode current, String order) {
    // assign String values to see if order matches anyone of the proper commands
    String a = "INORDER";
    String b = "POSTORDER";
    String c = "PREORDER";
    if (order.equals(a)) {
      if (current == null) {
        return;
      } else {
        // recursive calls for a INORDER operation
        traverseHelp(current.getLeftNode(), order);
        traversedLList.add(current.getData());
        traverseHelp(current.getRightNode(), order);
      }
    } else if (order.equals(b)) {
      if (current == null) {
        return;
      } else {
        // recursive calls for a POSTORDER operation
        traverseHelp(current.getLeftNode(), order);
        traverseHelp(current.getRightNode(), order);
        traversedLList.add(current.getData());
      }
    } else if (order.equals(c)) {
      if (current == null) {
        return;
      } else {
        // recursive calls for a PREORDER operation
        traversedLList.add(current.getData());
        traverseHelp(current.getLeftNode(), order);
        traverseHelp(current.getRightNode(), order);
      }
    }

  }

}

