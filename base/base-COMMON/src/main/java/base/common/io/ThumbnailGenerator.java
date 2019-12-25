package base.common.io;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

/**
 * @author yshi
 *
 */
public class ThumbnailGenerator {

	public void transform(String originalFile, String thumbnailFile, int thumbWidth, int thumbHeight)
			throws Exception {

		Image image = javax.imageio.ImageIO.read(new File(originalFile));

		double thumbRatio = (double) thumbWidth / (double) thumbHeight;

		ImageObserver imageObserver = new ImageObserver() {

			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		int imageWidth = image.getWidth(imageObserver);

		int imageHeight = image.getHeight(imageObserver);

		double imageRatio = (double) imageWidth / (double) imageHeight;

		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}

		if (imageWidth < thumbWidth && imageHeight < thumbHeight) {
			thumbWidth = imageWidth;
			thumbHeight = imageHeight;
		} else if (imageWidth < thumbWidth) {
			thumbWidth = imageWidth;
		} else if (imageHeight < thumbHeight) {
			thumbHeight = imageHeight;
		}

		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setBackground(Color.WHITE);
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, thumbWidth, thumbHeight);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

		javax.imageio.ImageIO.write(thumbImage, "PNG", new File(thumbnailFile));
	}
	

}
