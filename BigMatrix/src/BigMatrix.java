/*
 * Isayiah Lim
 * 3/13/2023
 * Data Structures Period 1
 * Mrs. Kankelborg
 * BigMatrix Project
 * This data structure stores values in a 2-dimensional matrix, increasing/decreasing
 * in size to fit according to the storage needs of each value.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BigMatrix 
{
	//matrix stored both by the rows and the columns
	private HashMap<Integer, HashMap<Integer, Integer>> rows;
	private HashMap<Integer, HashMap<Integer, Integer>> columns;
	
	//initializes both
	public BigMatrix()
	{
		rows = new HashMap<Integer, HashMap<Integer, Integer>>();
		columns = new HashMap<Integer, HashMap<Integer, Integer>>();
	}
	
	//sets values of the matrix
	public void setValue(int row, int col, int value)
	{
		if(value == 0 && getValue(row, col) == 0) return;
		if(value == 0)
		{
			if(rows.containsKey(row))
			{
				rows.get(row).remove(col);
				if(rows.get(row).isEmpty()) 
					rows.remove(row);
			}
			if(columns.containsKey(col))
			{
				columns.get(col).remove(row);
				if(columns.get(col).isEmpty()) 
					columns.remove(col);
			}
		}
		else
		{
			//adds to row, and if row doesn't exists, adds the row itself
			if(rows.containsKey(row))
			{
				rows.get(row).put(col, value);
			}
			else
			{
				HashMap<Integer, Integer> newR = new HashMap<Integer, Integer>();
				newR.put(col, value);
				rows.put(row, newR);
			}
			//adds to column, if column doesn't exist, adds column itself
			if(columns.containsKey(col))
			{
				columns.get(col).put(row, value);
			}			
			else
			{
				HashMap<Integer, Integer> newC = new HashMap<Integer, Integer>();
				newC.put(row, value);
				columns.put(col, newC);
			}
		}
	}
	
	//returns the value at an index
	public int getValue(int row, int col)
	{
		//if the value isn't in the matrix, returns 0
		int returnval = 0;
		if(rows.containsKey(row) && rows.get(row).containsKey(col))
			returnval = rows.get(row).get(col);
		return returnval;
	}
	
	//returns a list of every row that isn't empty
	public List<Integer> getNonEmptyRows()
	{
		//list of rows, checks each row and every item in each row, adds it to the list if not 0
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
	
	//returns a list of all the non-empty rows in a given column
	public List<Integer> getNonEmptyRowsInColumn(int col)
	{
		//list to store all the rows that aren't empty
		List<Integer> nRows = new ArrayList<Integer>();
		if(!columns.containsKey(col)) return nRows;
		//temp column to iterate through - for every item in column, if isn't zero, adds to list
		HashMap<Integer, Integer> colTemp = columns.get(col);
		for(int i : colTemp.keySet())
		{
			int temp = colTemp.get(i);
			if(temp != 0)
				nRows.add(temp);
		}
		return nRows;
	}
	
	//returns every column that isn't empty
	public List<Integer> getNonEmptyCols()
	{
		//list of columns
		List<Integer> nCols = new ArrayList<Integer>();
		//for each column in columns, if any of the indexes aren't 0, add it to the list 
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
	
	//checks non-empty columns in each row
	public List<Integer> getNonEmptyColsInRow(int row)
	{
		//list storing the indexes of each non-empty column
		List<Integer> nCols = new ArrayList<Integer>();
		if(!rows.containsKey(row)) return nCols;
		HashMap<Integer, Integer> rowTemp = rows.get(row);
		//for every column in a row, if the value at the column isn't 0, add it to the list
		for(int i : rowTemp.keySet())
		{
			int temp = rowTemp.get(i);
			if(temp != 0)
				nCols.add(temp);
		}
		return nCols;
	}
	
	//sums every value in a row
	public int getRowSum(int row)
	{
		int sum = 0;
		//stores the row used, returns 0 if it doesn't exist
		HashMap<Integer, Integer> rowTemp = rows.get(row);
		if(rowTemp == null) return 0;
		//adds everything inside a row
		for(int i : rowTemp.keySet())
		{
			sum += rowTemp.get(i);
		}
		return sum;
	}
	
	//sums every item in a column
	public int getColSum(int col)
	{
		int sum = 0;
		//stores the column used, and returns 0 if it doesn't exist
		HashMap<Integer, Integer> colTemp = columns.get(col);
		if(colTemp == null) return 0;
		//adds everything inside the column
		for(int i : colTemp.keySet())
		{
			sum += colTemp.get(i);
		}
		return sum;
	}
	
	//sums every item in the matrix
	public int getTotalSum()
	{
		int sum = 0;
		//for every row
		for(int i : rows.keySet())
		{
			//sets that row to a temp, then adds every element in it to the total sum
			HashMap<Integer, Integer> temp = rows.get(i);
			for(int a : temp.keySet())
			{
				sum += temp.get(a);
			}
		}
		return sum;
	}
	
	//multiplies every value in bigMatrix by a constant
	public BigMatrix multiplyByConstant(int constant)
	{
		BigMatrix temp = new BigMatrix();
		//for every row, for every column in that row
		for(int i : rows.keySet())
		{
			for(int j : rows.get(i).keySet())
			{
				//sets the new matrix's value to the existing matrix value times constant
				temp.setValue(i, j, getValue(i,j) * constant);
			}
		}
		return temp;
	}
	
	
	//Adds one matrix to current matrix and returns resulting matrix
	public BigMatrix addMatrix(BigMatrix other)
	{
		BigMatrix temp = new BigMatrix();
		//for every row, for every column in that row
		for(int i : rows.keySet())
		{
			for(int j : rows.get(i).keySet())
			{
				//adds the sum of the two into the new bigMatrix
				temp.setValue(i, j, getValue(i,j) + other.getValue(i, j));
			}
		}
		for(int i : other.rows.keySet())
		{
			for(int j : other.rows.get(i).keySet())
			{
				if(temp.getValue(i,j) == 0)
				{
					temp.setValue(i, j, other.getValue(i, j));
				}
			}
		}
		return temp;
	}
}
