/*
file name:      ExplorationExtension.java
Authors:        Benjamin Wintersteen
last modified:  9/18/2022

How to run:     java -ea Exploration
*/
public class ExplorationExtension {
    
    /**
     * The main method that explores different dimensions and initial chances in the Life Simulation.
     *
     * @param args Command-line arguments (not utilized in this program).
     * @throws InterruptedException If the simulation thread is interrupted during sleep.
     */
    public static void main(String[] args) {
        // Extension 3
        // Uncomment the following block to explore and print information about the best landscape
        
        for (int dimensions = 5; dimensions <= 100; dimensions = dimensions + 5) {
            // Initialize the bestscape with dummy values
            Landscape bestscape = new Landscape(1, 1, 0.0);
            
            // Explore different initial chances
            for (double chance = 0.5; chance <= 0.75; chance = chance + 0.01) {
                // Create a new Landscape with the specified dimensions and initial chance
                Landscape scape = new Landscape(dimensions, dimensions, chance);
                
                // Run the simulation for 1000 steps
                for (int i = 0; i <= 1000; i++) {
                    scape.advance();
                }
                
                // Check if the current landscape has a higher percentage of alive cells
                if (bestscape.getPercentAlive() < scape.getPercentAlive()) {
                    bestscape = scape;
                }
            }
            
            // Print information about the best landscape
            System.out.println("The best landscape had dimensions of " + bestscape.getRows() +
                               " x " + bestscape.getCols() + ", with " +
                               bestscape.getInitialChance() + " initial chance, and had " +
                               bestscape.getPercentAlive() +
                               "% of cells alive over 1000 iterations.");
        }
        
    }
 }

