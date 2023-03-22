package tests;

import static org.junit.Assert.*;
import java.util.Random;
import model.BoardCell;
import model.ClearCellGameModel;
import model.GameModel;
import org.junit.Test;

public class StudentTest {

	@Test
	public void testGetRows(){
		int rows = 12, cols = 8;
		GameModel model = new ClearCellGameModel(rows, cols, new Random(1L));
		assertTrue(model.getRows() == 12);
	}

	
	@Test
	public void testGetCols(){
		int rows = 232, cols = 54;
		GameModel model = new ClearCellGameModel(rows, cols, new Random(1L));
		assertFalse(model.getCols() == 12);
		assertTrue(model.getCols() == 54);
	}

	@Test
	public void testGetAndSetBoardContents(){
		int rows = 32, cols = 57;
		GameModel model = new ClearCellGameModel(rows, cols, new Random(1L));
		assertTrue(model.getBoardCell(6, 27) == BoardCell.EMPTY);

		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				model.setBoardCell(r, c, BoardCell.GREEN);
			}
		}
		assertTrue(model.getBoardCell(6 , 28) == BoardCell.GREEN);


		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				if(c % 2 == 0){
					model.setBoardCell(r, c, BoardCell.BLUE);
				}
			}
		}
		assertTrue(model.getBoardCell(6 , 28) == BoardCell.BLUE);
		assertFalse(model.getBoardCell(9 , 32) == BoardCell.GREEN);
		assertTrue(model.getBoardCell(25 ,46) == BoardCell.BLUE);
		assertTrue(model.getBoardCell(7, 47) == BoardCell.GREEN);
	}

	@Test
	public void testIsGameOver(){
		int rows = 32, cols = 57;
		GameModel model = new ClearCellGameModel(rows, cols, new Random(1L));
		assertTrue(model.getBoardCell(6, 27) == BoardCell.EMPTY);

		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				model.setBoardCell(r, c, BoardCell.GREEN);
			}
		}
		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				if(r == model.getRows() - 1){
					model.setBoardCell(r, c, BoardCell.EMPTY);
				}
			}
		}
		assertTrue(model.getBoardCell(31, 25) == BoardCell.EMPTY);
		assertTrue(model.isGameOver() == false);
	}

	@Test
	public void testIsRowEmpty2(){
		int rows = 32, cols = 57;
		GameModel model = new ClearCellGameModel(rows, cols, new Random(1L));
		assertTrue(model.getBoardCell(6, 27) == BoardCell.EMPTY);

		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				model.setBoardCell(r, c, BoardCell.GREEN);
			}
		}
		assertTrue(model.isGameOver() == true);
	}

	@Test
	public void testIsGameOver2(){
		int rows = 32, cols = 57;
		GameModel model = new ClearCellGameModel(rows, cols, new Random(1L));
		assertTrue(model.getBoardCell(6, 27) == BoardCell.EMPTY);

		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				model.setBoardCell(r, c, BoardCell.GREEN);
			}
		}
		assertFalse(model.isGameOver() == false);

		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				model.setBoardCell(r, c, BoardCell.EMPTY);
			}
		}
		assertTrue(model.isGameOver() == false);
	}

	@Test
	public void testGetScore(){
		int rows = 32, cols = 57;
		GameModel model = new ClearCellGameModel(rows, cols, new Random(1L));
		assertTrue(model.getBoardCell(6, 27) == BoardCell.EMPTY);

		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				model.setBoardCell(r, c, BoardCell.GREEN);
			}
		}
		model.processCell(10, 15);
		assertTrue(model.getScore() == 9);

		model.processCell(9, 15);
		assertFalse(model.getScore() == 18);
		assertEquals(9,model.getScore());
	}
	
	@Test
	public void testProcessCell(){
		int rows = 32, cols = 57;
		GameModel model = new ClearCellGameModel(rows, cols, new Random(1L));
		model.processCell(24,50);
		assertTrue(model.getScore() == 0);

		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				model.setBoardCell(r, c, BoardCell.GREEN);
			}
		}
		assertTrue(model.isGameOver() == true);
		
		model.processCell(31, 56);
		assertTrue(model.isGameOver() == true);
		assertEquals(4,model.getScore());
		
		for(int c = 0; c < model.getCols(); c++){
			model.setBoardCell(25, c, BoardCell.EMPTY);
		}
		assertTrue(model.isGameOver() == true);
		model.processCell(15, 17);
		assertTrue(model.isGameOver() == false);
	}

}
