/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.helper;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class ImageUtil {

    private static Logger logger = LogManager.getLogger();

    public static BufferedImage resizeImage(BufferedImage bufferedImage, int width, int height) {

        BufferedImage resizedImage = new BufferedImage(width, height, bufferedImage.getType());
        Graphics2D gd = resizedImage.createGraphics();
        gd.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        gd.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.drawImage(bufferedImage, 0, 0, width, height, null);
        gd.dispose();

        return resizedImage;
    }

    public static byte[] getResizedImage(BufferedImage bufferedImage, int width, int height) {
        int originalWidth = bufferedImage.getWidth();
        int originalHeight = bufferedImage.getHeight();
        float scaleFactor;

        int widthDiff = originalWidth - width;
        int heightDiff = originalHeight - height;

        if (widthDiff > heightDiff) {
            scaleFactor = (float) width / originalWidth;
        } else {
            scaleFactor = (float) height / originalHeight;
        }

        int newWidth = (int) (originalWidth * scaleFactor);
        int newHeight = (int) (originalHeight * scaleFactor);

        BufferedImage resizdImage = resizeImage(bufferedImage, newWidth, newHeight);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(resizdImage, "png", baos);
            baos.flush();
        } catch (IOException ex) {
            logger.catching(ex);
        }

        return baos.toByteArray();

    }
}
