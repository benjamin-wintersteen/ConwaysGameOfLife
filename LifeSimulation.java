/*
file name:      LifeSimulation.java
Authors:        Max Bender & Naser Al Madi
last modified:  9/18/2022

How to run:     java -ea LifeSimulation
*/
public class LifeSimulation {
    /**
     * The main method that initializes and runs the Life Simulation.
     *
     * @param args Command-line arguments for configuring the simulation.
     * @throws InterruptedException If the simulation thread is interrupted during sleep.
     */
    public static void main(String[] args) throws InterruptedException {
        // Check and update simulation parameters based on command-line arguments
        checkUsage(args);
        int rows = 100; 
        int cols = 100;
        double chance = 0.25;
        
        // Uses command line arguments to update rows, columns, and initial chance
        if (args.length >= 1 && Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[0]) < 10000) {
            rows = Integer.parseInt(args[0]);
        }
        if (args.length >= 2 && Integer.parseInt(args[1]) > 0 && Integer.parseInt(args[1]) < 10000) {
            cols = Integer.parseInt(args[1]);
        }
        if (args.length >= 3 && Double.parseDouble(args[2]) > 0.0 && Double.parseDouble(args[2]) < 1.0) {
            chance = Double.parseDouble(args[2]);
        }

        // Create a new Landscape with the specified parameters
        Landscape scape = new Landscape(rows, cols, chance);
        
        // Create Gliders pattern if specified in the command line
        if (args.length >= 4 && args[3].equalsIgnoreCase("Gliders")) {
            scape = new Landscape("Gliders");
        }

        // Create a LandscapeDisplay for visualization
        LandscapeDisplay display = new LandscapeDisplay(scape, 6);
        
        // Run the simulation for 50 steps
        for (int i = 0; i <= 50; i++) {
            Thread.sleep(250);

            // Exit loop if the Landscape becomes infinite
            if (scape.getIsInfinite()) {
                i = 1001;
            }

            // Advance the Landscape, repaint the display, and save the frame as an image
            scape.advance();
            display.repaint();
            display.saveImage("data/life_frame_" + String.format("%03d", i) + ".png");
        }
        
    }
    
    /**
     * Checks the usage of the program and prints instructions if needed.
     *
     * @param args Command-line arguments provided to the program.
     */
    public static void checkUsage(String[] args) {
        if (args.length > 0) {
            // Print provided command-line arguments
            for (String str : args) {
                System.out.println(str);
            }
        } else {
            // Print usage instructions if no command-line arguments are provided
            System.out.println("Usage: java LifeSimulation [printed args]" +
                               "\n 1st arg: Updates row length [int: 0-10000]" + 
                               "\n 2nd arg: Updates col length [int: 0-10000]" + 
                               "\n 3rd arg: Updates initial chance [double: 0.0-1.0]" + 
                               "\n 4th arg: Creates the Glider Gun [Gliders]");
        }
    }
}