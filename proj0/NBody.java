public class NBody {
    private static String imageBackground = "images/starfield.jpg";
    /**
     * Get radius
     * @param file input file
     * @return radius
     */
    public static double readRadius(String file){
        In input = new In(file);
        double radius = 0.00;
        for (int i = 0; i < 2; i++){
            radius = input.readDouble();
        }
        return radius;
    }

    /**
     * Get array of Planets from input file
     * @param file
     * @return
     */
    public static Planet[] readPlanets(String file){
        In input = new In(file);
        int nums = input.readInt();
        input.readDouble();
        Planet[] planets = new Planet[nums];
        for (int i = 0 ; i < nums; i++){
            planets[i] = new Planet(input.readDouble(), input.readDouble(), input.readDouble(), input.readDouble(), input.readDouble(), input.readString());
        }
        return planets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
//        String filename = "data/planets.txt";
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // Draw the background
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0.0D, 0.0D, imageBackground);
        StdDraw.show();

        // Draw all of the Planets
        for(int i = 0; i < planets.length; i++){
            planets[i].draw();
        }

        StdDraw.enableDoubleBuffering();

        for (int time = 0; time <= T; time += dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // Draw the background
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0.0D, 0.0D, imageBackground);


            // Draw all of the Planets
            for(int i = 0; i < planets.length; i++){
                planets[i].draw();
            }

            // Show th offscreen buffer
            StdDraw.show();

            // Pause 10 milliseconds
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
