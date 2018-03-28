package com.stbank.model; 

public class Cell {
	public int row;
	public int col;

	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public Cell getLeft() {
		return new Cell(row, col - 1);
	}
	public Cell getRight() {
		return new Cell(row, col + 1);
	}
	public Cell getBottom() {
		return new Cell(row + 1, col);
	}
	public Cell getUp() {
		return new Cell(row - 1, col);
	}
	public String toString() {
		return "<" + row + "," + col + ">";
	}
}