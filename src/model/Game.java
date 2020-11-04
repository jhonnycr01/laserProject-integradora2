package model;

public class Game {
	private Leaf p1;
	private BinaryTree scores;
	private Matrix board;
	public Game(String nick, int n, int m, int k) {
		scores = new BinaryTree();
		Leaf p1 = new Leaf(0, nick);
		board = new Matrix(n, m, k);
	}
	
	//assign the mirrors to the tiles
	//linked listmetodo que genere una matriz 
	// linked list metodo que coja k y aleatoriamente asigne un espejo 
	
	//create the board as a string
	public Matrix getBoard() {
		return board;
	}
	
}
