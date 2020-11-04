package model;

import java.io.Serializable;

//this class
//represents the scores the  
//the game
//or nodes of the tree
public class Leaf implements Serializable, Comparable<Leaf>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double score;
	private String nickname;
	private Leaf left;
	private Leaf right;
	private Leaf father;
	
	public Leaf(double score, String nickname) {
		super();
		this.score = score;
		this.nickname = nickname;
		this.left = null;
		this.right = null;
		this.father = null;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Leaf getLeft() {
		return left;
	}

	public void setLeft(Leaf left) {
		this.left = left;
	}

	public Leaf getRight() {
		return right;
	}

	public void setRight(Leaf right) {
		this.right = right;
	}

	public Leaf getFather() {
		return father;
	}

	public void setFather(Leaf father) {
		this.father = father;
	}

	@Override
	public int compareTo(Leaf o) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
	
	

	
	
}
