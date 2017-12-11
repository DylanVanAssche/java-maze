// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.model;

public class SquareIndex {
	private int rowIndex;
	private int columnIndex;
	
	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public SquareIndex(int rowIndex, int columnIndex) {
		this.setRowIndex(rowIndex);
		this.setColumnIndex(columnIndex);
	}
	
	public SquareIndex() {
		
	}

	public String toString() {
		return "(" + this.getRowIndex() + "," + this.getColumnIndex() + ")";
	}
}
