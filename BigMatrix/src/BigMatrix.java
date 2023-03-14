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
		if(value == 0) return;
		if(rows.containsKey(row) && columns.containsKey(col))
		{
			rows.get(row).put(col, value);
			columns.get(col).put(row, value);
		}
		else
		{
			if(rows.containsKey(row))
			{
				HashMap<Integer, Integer> newCol = new HashMap<Integer, Integer>();
				columns.put(col, newCol);
				rows.get(row).put(row, value);
			}
			if(columns.containsKey(col))
			{
				HashMap<Integer, Integer> newRow = new HashMap<Integer, Integer>();
				rows.put(col, newRow);
				columns.get(col).put(col, value);
			}
			else
			{
				HashMap<Integer, Integer> newRow = new HashMap<Integer, Integer>();
				rows.put(col, newRow);
				columns.get(col).put(col, value);
				HashMap<Integer, Integer> newCol = new HashMap<Integer, Integer>();
				columns.put(col, newCol);
				rows.get(row).put(row, value);
			}
		}

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
			Boolean t = false;
			for(int a : temp.keySet())
			{
				if(temp.get(a) != 0)
					t = true;
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
		List<Integer> nRows = new ArrayList<Integer>();
		HashMap<Integer, Integer> colTemp = columns.get(col);
		for(int i : colTemp.keySet())
		{
			int temp = colTemp.get(i);
			if(temp != 0)
				nRows.add(temp);
		}
		return nRows;
	}
	
	public List<Integer> getNonEmptyCols()
	{
		List<Integer> nCols = new ArrayList<Integer>();
		for(int i : columns.keySet())
		{
			HashMap<Integer, Integer> temp = columns.get(i);
			Boolean t = false;
			for(int a : temp.keySet())
			{
				if(temp.get(a) != 0)
					t = true;
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
		List<Integer> nCols = new ArrayList<Integer>();
		HashMap<Integer, Integer> rowTemp = rows.get(row);
		for(int i : rowTemp.keySet())
		{
			int temp = rowTemp.get(i);
			if(temp != 0)
				nCols.add(temp);
		}
		return nCols;
	}
	
	public int getRowSum(int row)
	{
		int sum = 0;
		HashMap<Integer, Integer> rowTemp = rows.get(row);
		for(int i : rowTemp.keySet())
		{
			sum += rowTemp.get(i);
		}
		return sum;
	}
	
	public int getColSum(int col)
	{
		int sum = 0;
		HashMap<Integer, Integer> colTemp = columns.get(col);
		for(int i : colTemp.keySet())
		{
			sum += colTemp.get(i);
		}
		return sum;
	}
	
	public int getTotalSum()
	{
		int sum = 0;
		for(int i : rows.keySet())
		{
			HashMap<Integer, Integer> temp = rows.get(i);
			for(int a : temp.keySet())
			{
				sum += temp.get(a);
			}
		}
		return sum;
	}
	
	public BigMatrix multiplyByConstant(int constant)
	{
		BigMatrix temp = new BigMatrix();
		for(int i : rows.keySet())
		{
			for(int j : rows.get(i).keySet())
			{
				temp.setValue(i, j, rows.get(i).get(j) * constant);
			}
		}
		return temp;
	}
	
	public BigMatrix addMatrix(BigMatrix other)
	{
		BigMatrix temp = new BigMatrix();
		for(int i : rows.keySet())
		{
			for(int j : rows.get(i).keySet())
			{
				temp.setValue(i, j, rows.get(i).get(j) + other.getValue(i, j));
			}
		}
		return temp;
	}
}
