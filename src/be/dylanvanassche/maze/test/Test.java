// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.test;

import be.dylanvanassche.maze.model.*;

public class Test {
	public static void main(String[] args) throws UnknownMovementDirection, WeHaveAWinner, BadMovementDirection  {
		TileT tilet = new TileT();
		System.out.println(tilet);
		/*Maze maze = new Maze("Jefke");
		System.out.println(maze.getPlayer().getPosition());
		try 
		{
			maze.movePlayer(MovementType.LEFT);
		}
		catch(UnknownMovementDirection unknownDirection) 
		{
			throw new UnknownMovementDirection(unknownDirection.getMessage());
		}
		catch(WeHaveAWinner winner)
		{
			throw new WeHaveAWinner(winner.getMessage());
		}
		catch(BadMovementDirection badDirection) 
		{
			throw new BadMovementDirection(badDirection.getMessage());
		}
		System.out.println("***************************************************************");
		maze.movePlayer(MovementType.UP);
		System.out.println("***************************************************************");
		maze.movePlayer(MovementType.LEFT);
		System.out.println("***************************************************************");
		maze.movePlayer(MovementType.LEFT);*/
	}
}
