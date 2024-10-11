/*
file name:      Exploration.java
Authors:        Benjamin Wintersteen
last modified:  9/18/2022

How to run:     java -ea Exploration
*/
public class Exploration {
    
    /**
     * The main method that explores different dimensions and initial chances in the Life Simulation.
     *
     * @param args Command-line arguments (not utilized in this program).
     * @throws InterruptedException If the simulation thread is interrupted during sleep.
     */
    public static void main(String[] args) throws InterruptedException {
        
        // Explore different dimensions and initial chances
        for (int dimensions = 100; dimensions <= 100; dimensions = dimensions * 2) {
            for (double chance = 0.05; chance <= 1.0; chance = chance + 0.05) {
                // Create a new Landscape with the specified dimensions and initial chance
                Landscape scape = new Landscape(dimensions, dimensions, chance);
                
                // Run the simulation for 1000 steps
                for (int i = 0; i <= 1000; i++) {
                    scape.advance();
                }
                
                // Format the chance to the hundredths place
                String formattedChance = String.format("%.2f", chance);
                
                // Print the results of the simulation
                System.out.println("On a " + dimensions + " x " + dimensions + " square, with " +
                                   formattedChance + " initial chance, the percentAlive was " +
                                   scape.getPercentAlive());
            }
            
            // Separate different dimensions with an empty line
            System.out.println("");
        }
        
         
        
        
    }
}




