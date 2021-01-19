package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    /**
     * draw hexagon
     * @param world
     * @param size  size of hexagon
     * @param dx  bottom left x-coordinate of the hexagon-square
     * @param dy  bottom left y-coordinate of the hexagon-square
     * @param tile  tile pattern will be filled in 2d-tiles
     */
    public static void addHexagon(TETile[][] world, int size, int dx, int dy, TETile tile){
        if (size < 2){
            throw new IllegalArgumentException("Illegal: size at least 2.");
        }
        int maxCol = 3 * size - 2;
        // draw upper hexa
        int offset = 0;
        for (int x = size - 1; x >= 0; x--){
            for (int y = offset; y < maxCol - offset; y++){
                world[y + dx][x + dy] = TETile.colorVariant(tile, 32, 32, 32, RANDOM);
            }
            offset++;
        }
        // draw lower hexa
        offset = 0;
        for (int x = size; x < size * 2; x++){
            for (int y = offset; y < maxCol - offset; y++){
                world[y + dx][x + dy] = TETile.colorVariant(tile, 32, 32, 32, RANDOM);
            }
            offset++;
        }
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexaTiles = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                hexaTiles[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(hexaTiles, 3, 10, 0, Tileset.WALL);
        addHexagon(hexaTiles, 3, 5, 3, Tileset.GRASS);
        addHexagon(hexaTiles, 3, 0, 6, Tileset.FLOWER);

        addHexagon(hexaTiles, 3, 10, 6, Tileset.WALL);
        addHexagon(hexaTiles, 3, 5, 9, Tileset.GRASS);
        addHexagon(hexaTiles, 3, 0, 12, Tileset.FLOWER);

        ter.renderFrame(hexaTiles);
    }
}
