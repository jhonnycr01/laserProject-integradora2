package model;

import java.util.Random;

public class Matrix {
	private Node first;
	private int numRows;
	private int numCols;
	private int mirrows;
	
	public Matrix(int m, int n, int k) {
		numRows = m;
		numCols = n;
		mirrows = k;
		createMatrix();
	}
	
	public Node getFirst() {
		return first;
	}



	public void setFirst(Node first) {
		this.first = first;
	}



	public int getNumRows() {
		return numRows;
	}



	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}



	public int getNumCols() {
		return numCols;
	}



	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}



	public int getMirrows() {
		return mirrows;
	}



	public void setMirrows(int mirrows) {
		this.mirrows = mirrows;
	}



	private void createMatrix() {
		first = new Node(1,1);
		createRow(1,1,first);
		createMirrows(mirrows);
	}

	private void createRow(int i, int j, Node currentFirstRow) {
		createCol(i,j+1,currentFirstRow,currentFirstRow.getUp());
		if(i<numRows) {
			Node downFirstRow = new Node(i+1,j);
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			createRow(i+1,j,downFirstRow);
		}
	}

	private void createCol(int i, int j, Node prev, Node rowPrev) {
		if(j<=numCols) {
			Node current = new Node(i, j);
			System.out.println(i + " " + j);
			current.setPrev(prev);
			prev.setNext(current);
			
			if(rowPrev!=null) {
				rowPrev = rowPrev.getNext();
				current.setUp(rowPrev);
				rowPrev.setDown(current);
			}
			
			createCol(i,j+1,current,rowPrev);
		}
	}
	
	public String toString() {
		String msg;
		msg = toStringRow(first);
		return msg;
	}

	private String toStringRow(Node firstRow) {
		String msg = "";
		if(firstRow!=null) {
			msg = toStringCol(firstRow) + "\n";
			msg += toStringRow(firstRow.getDown());
		}
		return msg;
	}

	private String toStringCol(Node current) {
		String msg = "";
		if(current!=null) {
			msg = current.toString();
			msg += toStringCol(current.getNext());
		}
		return msg;
	}
	
	public String toString2() {
		String msg = "";
		
		return msg;
	}
	
	public void createMirrows(int k) {
		if (k == 0) {
			return;
		} 
		
		if (createMirrow()) {
			createMirrows(k-1);
		} else {
			createMirrows(k);
		}
	}
	
	public boolean createMirrow() {
		Random r = new Random();
		int i = r.nextInt(numRows) + 1;
		int j = r.nextInt(numCols) + 1;
		System.out.println("Find " + i + " " + j);
		
		Node current = first; 
		System.out.println("First " + current.getRow() + " " + current.getCol());
		current = findByRow(current, i);
		current = findByCol(current, j);
		
		if (current.getLetter() == ' ') {
			int dir = r.nextInt(10);
			current.setLetter(dir < 5 ? '/' : '\\');
			return true;
		} 
		return false;		
	}

	
	public Node findByRow(Node from, int i) {
		if(from.getRow() == i) {
			return from;
		} else {
			return findByRow(from.getDown(), i);
		}
	}
	
	public Node findByCol(Node from, int j) {
		if(from.getCol() == j) {
			return from;
		} else {
			return findByCol(from.getNext(), j);
		}
	}
}