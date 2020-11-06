package model;

public class Node {
	private int row;
	private int col;

	private Node next;
	private Node prev;
	private Node up;
	private Node down;
	private char letter;

	private boolean isMirror;
	private boolean isMirrirLocated;

	public Node(int r, int c) {
		row = r;
		col = c;
		letter = ' ';
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public char getNameCol() {
		return (char) ('A' + col);
	}

	public Node getNext() {
		return next;
	}

	public Node getPrev() {
		return prev;
	}

	public Node getUp() {
		return up;
	}

	public Node getDown() {
		return down;
	}

	public void setPrev(Node p) {
		prev = p;
	}

	public void setNext(Node n) {
		next = n;
	}

	public void setUp(Node u) {
		up = u;
	}

	public void setDown(Node d) {
		down = d;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public boolean isMirror() {
		return isMirror;
	}

	public void setMirror(boolean isMirror) {
		this.isMirror = isMirror;
	}

	public boolean isMirrirLocated() {
		return isMirrirLocated;
	}

	public void setMirrirLocated(boolean isMirrirLocated) {
		this.isMirrirLocated = isMirrirLocated;
	}

	public String toString() {
		char toShow = ' ';
		// Show when it is an S, a E or a located mirror
		if ((letter == 'S' || letter == 'E' || letter == 'X') || (isMirror && isMirrirLocated)) {
			toShow = letter;
		}
		
		//TODO delete
		if(isMirror && !isMirrirLocated) {
			toShow = (char)(letter + 3);
		}
		
		return "[" + toShow + "]";// + row + "," + col;
	}

}