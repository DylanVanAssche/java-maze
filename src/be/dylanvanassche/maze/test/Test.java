// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.test;

import be.dylanvanassche.maze.model.*;

public class Test {
	public static void main(String[] args) {
		Square square1 = new Square(SquareType.GOLD);
		System.out.println(square1.toString());
		
		Square square2 = new Square(SquareType.WALL);
		System.out.println(square2.toString());
		
		Square square3 = new Square(SquareType.FREE);
		System.out.println(square3.toString());
		
		System.out.println("----------------------------------");
		Tile tilecross = new TileCross();
		System.out.println(tilecross.toString());
		
		System.out.println("----------------------------------");
		Tile tilestraight = new TileStraight();
		System.out.println(tilestraight.toString());
		
		System.out.println("----------------------------------");
		Tile tilebend = new TileBend();
		System.out.println(tilebend.toString());
		
		System.out.println("----------------------------------");
		Tile tilet = new TileT();
		System.out.println(tilet.toString());
		tilet.rotate(1);
		System.out.println("----------------------------------");
		System.out.println(tilet.toString());
		tilet.rotate(2);
		System.out.println("----------------------------------");
		System.out.println(tilet.toString());
		tilet.rotate(1);
		System.out.println("----------------------------------");
		System.out.println(tilet.toString());
	}
}
