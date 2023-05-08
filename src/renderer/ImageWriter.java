package renderer;

import primitives.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.*;

/**
 * Image writer class combines accumulation of pixel color matrix and finally
 * producing a non-optimized jpeg image from this matrix. The class although is
 * responsible of holding image related parameters of View Plane - pixel matrix
 * size and resolution
 *
 * @author Dan
 */
public class ImageWriter {
    private int nX;
    private int nY;

    private static final String FOLDER_PATH = System.getProperty("user.dir") + "/images";

    private BufferedImage image;
    private String imageName;

    private Logger logger = Logger.getLogger("ImageWriter");

    // ***************** Constructors ********************** //

    /**
     * Image Writer constructor accepting image name and View Plane parameters,
     *
     * @param imageName the name of jpeg file
     * @param nX        amount of pixels by Width
     * @param nY        amount of pixels by height
     */
    public ImageWriter(String imageName, int nX, int nY) {
        this.imageName = imageName;
        this.nX = nX;
        this.nY = nY;

        image = new BufferedImage(nX, nY, BufferedImage.TYPE_INT_RGB);
    }

    // ***************** Getters/Setters ********************** //

    /**
     * View Plane Y axis resolution
     *
     * @return the amount of vertical pixels
     */
    public int getNy() {
        return nY;
    }

    /**
     * View Plane X axis resolution
     *
     * @return the amount of horizontal pixels
     */
    public int getNx() {
        return nX;
    }

    // ***************** Operations ******************** //

    /**
     * Function writeToImage produces unoptimized png file of the image according to
     * pixel color matrix in the directory of the project
     */
    public void writeToImage() {
        try {
            File file = new File(FOLDER_PATH + '/' + imageName + ".png");
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "I/O error", e);
            throw new IllegalStateException("I/O error - may be missing directory " + FOLDER_PATH, e);
        }
    }

    /**
     * The function writePixel writes a color of a specific pixel into pixel color
     * matrix
     *
     * @param xIndex X axis index of the pixel
     * @param yIndex Y axis index of the pixel
     * @param color  final color of the pixel
     */
    public void writePixel(int xIndex, int yIndex, Color color) {
        image.setRGB(xIndex, yIndex, color.getColor().getRGB());
    }

    /**
     * Create a grid [over the picture] in the pixel color map given the grid's interval and color.
     *
     * @param interval grid's step
     * @param color    grid's color
     */
    public void printGrid(int interval, Color color) {
        //pass on the all pixels and printing a color at each interval
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * Fills the background of the image with the specified color.
     * @param backgroundColor the color to fill the background with
     */
    public void fillBackground(Color backgroundColor) {
        // Create a new Graphics2D object from the image
        java.awt.Graphics2D g2d = image.createGraphics();

        // Set the color of the Graphics2D object to the specified background color
        g2d.setColor(backgroundColor.getColor());

        // Fill a rectangle with the background color from (0, 0) to (nX, nY)
        g2d.fillRect(0, 0, nX, nY);
    }

}
