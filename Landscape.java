/*
file name:      CellTests.java
Authors:        Max Bender & Naser Al Madi
last modified:  9/18/2022

How to run:     java -ea CellTests
*/
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Landscape {

    /**
     * The underlying grid of Cells for Conway's Game
     */
    private Cell[][] landscape;

    /**
     * The original probability each individual Cell is alive
     */
    private double initialChance;

    /**
     * true if the current and next landscape are equivalent
     */
    private boolean isInfinite = false; 

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * All Cells are initially dead.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     */
    public Landscape(int rows, int columns) {
        landscape = new Cell[rows][columns];
        reset();
    }

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * Each Cell is initially alive with probability specified by chance.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     * @param chance  the probability each individual Cell is initially alive
     */
    public Landscape(int rows, int columns, double chance) {
        landscape = new Cell[rows][columns];
        initialChance = chance;
        reset();
    }

   /**
     * Constructs a Landscape based on the starting prompt.
     * 
     * @param startingPrompt the prompt specifying the initial configuration
     */
    public Landscape(String startingPrompt) {
        landscape = new Cell[100][100];
        if (startingPrompt.equalsIgnoreCase("Gliders")) {
            createGliderGun();
        }
    }

    /**
     * Extension 1
     * Creates a glider gun pattern in the landscape.
     */
    public void createGliderGun(){
        for (int i = 0; i < getRows(); i++){
            for ( int j = 0; j < getCols(); j++){
               if (i == 1 && j == 25){
                landscape[i][j] = new Cell (true);
               }
               else if ((i == 2 && j == 23) || (i == 2 && j == 25)) {
                landscape[i][j] = new Cell (true);
               }
               else if ((i == 3 && j == 13) || (i == 3 && j == 14) || (i == 3 && j == 21) || (i == 3 && j == 22) || (i == 3 && j == 35) || (i == 3 && j == 36)) {
                landscape[i][j] = new Cell (true);
               }
               else if ((i == 4 && j == 12) || (i == 4 && j == 16) || (i == 4 && j == 21) || (i == 4 && j == 22) || (i == 4 && j == 35) || (i == 4 && j == 36)) {
                landscape[i][j] = new Cell (true);
               }
               else if ((i == 5 && j == 1) || (i == 5 && j == 2) || (i == 5 && j == 11) || (i == 5 && j == 17) || (i == 5 && j == 21) || (i == 5 && j == 22)) {
                landscape[i][j] = new Cell (true);
               }
               else if ((i == 6 && j == 1) || (i == 6 && j == 2) || (i == 6 && j == 11) || (i == 6 && j == 17) || (i == 6 && j == 15) || (i == 6 && j == 18) || (i == 6 && j == 23) || (i == 6 && j == 25)) {
                landscape[i][j] = new Cell (true);
               }
               else if ((i == 7 && j == 11) || (i == 7 && j == 17) || (i == 7 && j == 25)) {
                landscape[i][j] = new Cell (true);
               }
               else if ((i == 8 && j == 12) || (i == 8 && j == 16)) {
                landscape[i][j] = new Cell (true);
               }
               else if ((i == 9 && j == 13) || (i == 9 && j == 14)) {
                landscape[i][j] = new Cell (true);
               }
               else{
                landscape[i][j] = new Cell (false);
               }
            }
        }
    }



    /**
     * Recreates the Landscape according to the specifications given
     * in its initial construction.
     */
    public void reset() {
        Random rand = new Random();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                // Initializing cells based on initialChance
                landscape[i][j] = new Cell(rand.nextDouble() < initialChance);
            }
        }
    }

    /**
     * Returns the number of rows in the Landscape.
     * 
     * @return the number of rows in the Landscape
     */
    public int getRows() {
        return landscape.length;
    }

    /**
     * Returns the number of columns in the Landscape.
     * 
     * @return the number of columns in the Landscape
     */
    public int getCols() {
        return landscape[0].length;
    }

    /**
     * Returns the Cell specified by the given row and column.
     * 
     * @param row the row of the desired Cell
     * @param col the column of the desired Cell
     * @return the Cell specified by the given row and column
     */
    public Cell getCell(int row, int col) {
        return landscape[row][col];
    }

    /**
     * Returns a String representation of the Landscape.
     */
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (Cell[] cells : landscape) {
            output.append("[");
            for (Cell cell : cells) {
                output.append(cell.toString() + ", ");
            }
            output.append("]\n");
        }
        return output.toString();
    }

    /**
     * Returns an ArrayList of the neighboring Cells to the specified location.
     * 
     * @param row the row of the specified Cell
     * @param col the column of the specified Cell
     * @return an ArrayList of the neighboring Cells to the specified location
     */
    public ArrayList<Cell> getNeighbors(int row, int col) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        // Horizontal and vertical neighbors
        if (row > 0) {
            neighbors.add(landscape[row - 1][col]);
        }
        if (col > 0) {
            neighbors.add(landscape[row][col - 1]);
        }
        if (col < landscape[0].length - 1) {
            neighbors.add(landscape[row][col + 1]);
        }
        if (row < landscape.length - 1) {
            neighbors.add(landscape[row + 1][col]);
        }

        // Diagonal neighbors
        if (row > 0 && col > 0) {
            neighbors.add(landscape[row - 1][col - 1]);
        }
        if (row > 0 && col < landscape[0].length - 1) {
            neighbors.add(landscape[row - 1][col + 1]);
        }
        if (row < landscape.length - 1 && col > 0) {
            neighbors.add(landscape[row + 1][col - 1]);
        }
        if (row < landscape.length - 1 && col < landscape[0].length - 1) {
            neighbors.add(landscape[row + 1][col + 1]);
        }
        return neighbors;
    }
    /* 
     * Advances the landscape one space into the future
     */
    public void advance() {
        boolean isSame = true;
        
        // Creates a copy of the current Landscape
        Cell[][] landscapeCopy = new Cell[landscape.length][landscape[0].length];
        
        // Iterate through each cell in the Landscape
        for (int i = 0; i < landscape.length; i++) {
            for (int j = 0; j < landscape[0].length; j++) {
                // Create a new cell with the same alive state as the current cell
                landscapeCopy[i][j] = new Cell(landscape[i][j].getAlive());
                
                // Update the state of the new cell based on its neighbors
                landscapeCopy[i][j].updateState(getNeighbors(i, j));
                
                // Check if the new state is different from the current state
                if (landscapeCopy[i][j].getAlive() != landscape[i][j].getAlive()) {
                    isSame = false;
                }
            }
        }
    
        // Update the Landscape with the new copy
        landscape = landscapeCopy;
        isInfinite = isSame;
    }
    
    /**
     * Gets the status of the Landscape's infiniteness.
     *
     * @return true if the Landscape remains the same after advancing, false otherwise.
     */
    public boolean getIsInfinite() {
        return isInfinite;
    }
    
    /**
     * Calculates and returns the percentage of alive cells in the Landscape.
     *
     * @return the percentage of alive cells in the Landscape.
     */
    public double getPercentAlive() {
        double percentAlive = 0;
        
        // Iterate through each cell in the Landscape
        for (Cell[] cells : landscape) {
            for (Cell cell : cells) {
                // Increment the count if the cell is alive
                if (cell.getAlive()) {
                    percentAlive++;
                }
            }
        }
        
        // Calculate the percentage of alive cells
        percentAlive = percentAlive / (getCols() * getRows());
        return percentAlive;
    }
    
    /**
     * Gets the initial chance of a cell being alive in the Landscape.
     *
     * @return the initial chance of a cell being alive.
     */
    public double getInitialChance() {
        return initialChance;
    }
    
    /**
     * Draws the Cell to the given Graphics object at the specified scale.
     * An alive Cell is drawn with a black color; a dead Cell is drawn gray.
     * 
     * @param g     the Graphics object on which to draw
     * @param scale the scale of the representation of this Cell
     */
    public void draw(Graphics g, int scale) {
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getCols(); y++) {
                g.setColor(getCell(x, y).getAlive() ? Color.BLACK : Color.gray);
                g.fillOval(y * scale, x * scale, scale, scale);
            }
        }
    }

    public static void main(String[] args) {
    }
}
