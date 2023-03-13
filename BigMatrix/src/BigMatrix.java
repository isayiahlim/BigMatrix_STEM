/*
 * Isayiah Lim
 * 3/13/2023
 * Data Structures Period 1
 * Mrs. Kankelborg
 * BigMatrix Project
 * This data structure stores values in a 2-dimentional matrix, increasing/decreasing
 * in size to fit according to the storage needs of each value.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class BigMatrix 
{
	private HashMap<Integer, HashMap<Integer, Integer>> rows;
	private HashMap<Integer, HashMap<Integer, Integer>> columns;
	
	public BigMatrix()
	{
		rows = new HashMap<Integer, HashMap<Integer, Integer>>();
		columns = new HashMap<Integer, HashMap<Integer, Integer>>();
	}
	
	public void setValue(int row, int col, int value)
	{
		if(value == 0 && getValue(row, col) == 0) return;
	}
	
	public int getValue(int row, int col)
	{
		int returnval = 0;
		if(rows.containsKey(row) && rows.get(row).containsKey(col))
			returnval = rows.get(row).get(col);
		return returnval;
	}
	
	public List<Integer> getNonEmptyRows()
	{
		List<Integer> nRows = new ArrayList<Integer>();
		for(int i : rows.keySet())
		{
			HashMap<Integer, Integer> temp = rows.get(i);
			Boolean t = true;
			for(int a : temp.keySet())
			{
				if(temp.get(a) != 0)
					t = false;
			}
			if(t)
			{
				nRows.add(i);
			}
		}
		return nRows;
	}
	
	public List<Integer> getNonEmptyRowsInColumn(int col)
	{
		throw new UnsupportedOperationException();
	}
	
	public List<Integer> getNonEmptyCols()
	{
		List<Integer> nCols = new ArrayList<Integer>();
		for(int i : columns.keySet())
		{
			HashMap<Integer, Integer> temp = columns.get(i);
			Boolean t = true;
			for(int a : temp.keySet())
			{
				if(temp.get(a) != 0)
					t = false;
			}
			if(t)
			{
				nCols.add(i);
			}
		}
		return nCols;
	}
	
	public List<Integer> getNonEmptyColsInRow(int row)
	{
		throw new UnsupportedOperationException();
	}
	
	public int getRowSum(int row)
	{
		throw new UnsupportedOperationException();
	}
	
	public int getColSum(int col)
	{
		throw new UnsupportedOperationException();
	}
	
	public int getTotalSum()
	{
		throw new UnsupportedOperationException();
	}
	
	public BigMatrix multiplyByConstant(int constant)
	{
		throw new UnsupportedOperationException();
	}
	
	public BigMatrix addMatrix(BigMatrix other)
	{
		throw new UnsupportedOperationException();
	}
}
