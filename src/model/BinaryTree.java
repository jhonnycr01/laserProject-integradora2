package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BinaryTree implements Serializable{

	private Leaf root;
	private int weight;
	
	public BinaryTree() {
		File rest = new File("scores/highscore");
		if(rest.exists()) {
			deserializeTree();
		}
		else {
	                this.root =  null;	
	                weight = 0;
		}
	}
	public void addLeaf(Leaf p) {
		addLeaf(p, root);
	}
	
	private void addLeaf(Leaf a, Leaf current) {
		if(root==null) {
			root = a;
			weight++;
			serializeTree();
		} else {
			if (a.getScore()<current.getScore()) {
				if (current.getLeft() == null) {
					current.setLeft(a);
					weight++;
					serializeTree();
				} else {
					addLeaf(a, current.getLeft());
				}
			} else {
				if (current.getRight() == null) {
					current.setRight(a);
					weight++;
					serializeTree();
				} else {
					addLeaf(a, current.getRight());
				}
			}
		}

		}
		
		public void printInorder(){
			printInorder(this.root);
		}
		
		
		public void printInorder(Leaf node){ 
		        if (node == null) {
		        	System.out.println("the tree is empty");
		        }else {
		            
		  
		        /* first recur on left child */
		        printInorder(node.getLeft()); 
		  
		        /* then print the data of node */
		        System.out.print(node.getNickname() + " "+node.getScore()); 
		  
		        /* now recur on right child */
		        printInorder(node.getRight()); 
		    } 
		
	}
	
	//Cup tiene un arbol de Assistants y una lista de atletas
	// adds an assistant recursively to the BST
		

	
	public void serializeTree() {
		try{
			File fileOrders = new File("scores/highscore");
			ObjectOutputStream ficheroOrd = new ObjectOutputStream(new FileOutputStream(fileOrders));
			ficheroOrd.writeObject(root);
			ficheroOrd.close();
		}
		catch (IOException e){
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializeTree() {
		try{ 
		
			File fileOrders = new File("scores/highscore");
			ObjectInputStream ficheroOrd = new ObjectInputStream(new FileInputStream(fileOrders));
			root = (Leaf) ficheroOrd.readObject();
			System.out.println("loading highscore data ....");
			ficheroOrd.close();
		}
		catch(IOException | ClassNotFoundException cnfe) {
			
		}
	}
		


	
}
