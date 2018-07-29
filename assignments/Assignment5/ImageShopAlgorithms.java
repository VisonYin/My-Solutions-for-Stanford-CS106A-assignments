/* 
 * Note: these methods are public in order for them to be used by other files
 * in this assignment; DO NOT change them to private.  You may add additional
 * private methods to implement required functionality if you would like.
 * 
 * You should remove the stub lines from each method and replace them with your
 * implementation that returns an updated image.
 */

// TODO: comment this file explaining its behavior


import acm.graphics.GImage;

public class ImageShopAlgorithms implements ImageShopAlgorithmsInterface {

	public GImage flipHorizontal(GImage source) {
		int[][] pixels = source.getPixelArray();
		int m = pixels.length;
		int n = pixels[0].length;
		int[][] flipHorizontalPixels = new int[m][n];
		
		for (int i = 0;i < m;i ++)
			for (int j = 0;j < n;j ++)
				flipHorizontalPixels[i][j] = pixels[i][n-1-j];
		GImage flipHorizontalImage = new GImage(flipHorizontalPixels);
		return flipHorizontalImage;
	}

	public GImage rotateLeft(GImage source) {
		int[][] pixels = source.getPixelArray();
		int m = pixels.length;
		int n = pixels[0].length;
		int[][] rotatedPixels = new int[n][m];
		
		for (int i = 0;i < m;i ++)
			for (int j = 0;j < n;j ++)
				rotatedPixels[j][i] = pixels[i][n-1-j];
		GImage rotateLeftImage = new GImage(rotatedPixels);
		return rotateLeftImage;
	}

	public GImage rotateRight(GImage source) {
		int[][] pixels = source.getPixelArray();
		int m = pixels.length;
		int n = pixels[0].length;
		int[][] rotatedPixels = new int[n][m];
		
		for (int i = 0;i < m;i ++)
			for (int j = 0;j < n;j ++)
				rotatedPixels[j][i] = pixels[n-1-i][j];
		GImage rotateRightImage = new GImage(rotatedPixels);
		return rotateRightImage;
	}

	public GImage greenScreen(GImage source) {
		int[][] pixels = source.getPixelArray();
		for (int i = 0;i < pixels.length;i ++)	{
			for (int j = 0;j < pixels[0].length; j ++)	{
				int rgb = pixels[i][j];
				int r = GImage.getRed(rgb);
				int g = GImage.getGreen(rgb);
				int b = GImage.getBlue(rgb);
				int alpha = GImage.getAlpha(rgb);
				int bigger = Math.max(r, b);
				if (g >= bigger)
					alpha = 0;
				pixels[i][j] = GImage.createRGBPixel(r, g, b, alpha);
			}
		}
		GImage imageAfterGreenScreen = new GImage(pixels);
		
		
		return imageAfterGreenScreen;
	}

	public GImage equalize(GImage source) {
		int[] luminosityHistogram = new int[256];
		int[] cumulativeLuminosityHistogram = new int[256];
		int[][] pixels = source.getPixelArray();
		for (int i = 0;i < pixels.length;i ++)	
			for (int j = 0;j < pixels[0].length; j ++)	{
				int rgb = pixels[i][j];
				int r = GImage.getRed(rgb);
				int g = GImage.getGreen(rgb);
				int b = GImage.getBlue(rgb);
				int luminosity = computeLuminosity(r, g, b);
				luminosityHistogram[luminosity] += 1;
			}
		for (int i = 0;i <256;i ++)
			for (int j = 0;j <= i;j ++)	{
				cumulativeLuminosityHistogram[i] += luminosityHistogram[j];
			}
		int numsPixel = pixels.length * pixels[0].length;
		for (int i = 0;i < pixels.length;i ++)	
			for (int j = 0;j < pixels[0].length; j ++)	{
				int rgb = pixels[i][j];
				int r = GImage.getRed(rgb);
				int g = GImage.getGreen(rgb);
				int b = GImage.getBlue(rgb);
				int luminosity = computeLuminosity(r, g, b);
				r = g = b = 255 * cumulativeLuminosityHistogram[luminosity]/numsPixel;
				pixels[i][j] = GImage.createRGBPixel(r, g, b);
			}
		GImage equalizedImage = new GImage(pixels);
		return equalizedImage;
		
	}

	public GImage negative(GImage source) {
		int[][] pixels = source.getPixelArray();
		for (int i = 0;i < pixels.length;i ++)	{
			for (int j = 0;j < pixels[0].length; j ++)	{
				int rgb = pixels[i][j];
				int r = GImage.getRed(rgb);
				int g = GImage.getGreen(rgb);
				int b = GImage.getBlue(rgb);
				r = 255 - r;
				g = 255 - g;
				b = 255 - b;
				pixels[i][j] = GImage.createRGBPixel(r, g, b);
			}
		}
		GImage negativeImage = new GImage(pixels);
		return negativeImage;
	}

	public GImage translate(GImage source, int dx, int dy) {
		int[][] pixels = source.getPixelArray();
		int m = pixels.length;
		int n = pixels[0].length;
		int[][] translatedPixels = new int[m][n];
		
		for (int i = 0;i < m;i ++)
			for (int j = 0;j < n;j ++)
			{
				int i_index = i - dx;
				int j_index = j - dy;
				if (i_index < 0)
					i_index += m;
				if (i_index >= m)
					i_index -= m;
				if (j_index >= n)
					j_index -= n;
				if (j_index < 0)
					j_index += n;
				translatedPixels[j][i] = pixels[j_index][i_index];
			}
		GImage translatedImage = new GImage(translatedPixels);
		return translatedImage;
	}

	public GImage blur(GImage source) {
		int[][] pixels = source.getPixelArray();
		int m = pixels.length;
		int n = pixels[0].length;
		int[][] bluredPixels = new int[m][n];
		int[][][] rgbMatrix = new int[m][n][3];
		for (int i = 0;i < m;i ++)
			for (int j = 0;j < n;j ++)
			{
				int rgb = pixels[i][j];
				rgbMatrix[i][j][0] = GImage.getRed(rgb);
				rgbMatrix[i][j][1] = GImage.getGreen(rgb);
				rgbMatrix[i][j][2] = GImage.getBlue(rgb);
			}
		for (int i = 0;i < m;i ++)
			for (int j =0;j < n;j ++)	{
				int r=0, g=0, b=0;
				if ((i>0)&&(i<m-1)&&(j>0)&&(j<n-1))	{
					r = (rgbMatrix[i][j][0]+ rgbMatrix[i][j-1][0]+ rgbMatrix[i][j+1][0]+ rgbMatrix[i-1][j][0]
							+ rgbMatrix[i-1][j+1][0]+ rgbMatrix[i-1][j-1][0]+ rgbMatrix[i+1][j][0]+ rgbMatrix[i+1][j-1][0]
									+rgbMatrix[i+1][j+1][0])/9;
					g = (rgbMatrix[i][j][1]+ rgbMatrix[i][j-1][1]+ rgbMatrix[i][j+1][1]+ rgbMatrix[i-1][j][1]
							+ rgbMatrix[i-1][j+1][1]+ rgbMatrix[i-1][j-1][1]+ rgbMatrix[i+1][j][1]+ rgbMatrix[i+1][j-1][1]
									+rgbMatrix[i+1][j+1][1])/9;
					b = (rgbMatrix[i][j][2]+ rgbMatrix[i][j-1][2]+ rgbMatrix[i][j+1][2]+ rgbMatrix[i-1][j][2]
							+ rgbMatrix[i-1][j+1][2]+ rgbMatrix[i-1][j-1][2]+ rgbMatrix[i+1][j][2]+ rgbMatrix[i+1][j-1][2]
									+rgbMatrix[i+1][j+1][2])/9;
				}
				else if ((i==0)&&(j==0))	{
					r = (rgbMatrix[i][j][0]+ rgbMatrix[i][j+1][0]+ rgbMatrix[i+1][j][0]+ rgbMatrix[i+1][j+1][0])/4;
					g = (rgbMatrix[i][j][1]+ rgbMatrix[i][j+1][1]+ rgbMatrix[i+1][j][1]+ rgbMatrix[i+1][j+1][1])/4;
					b = (rgbMatrix[i][j][2]+ rgbMatrix[i][j+1][2]+ rgbMatrix[i+1][j][2]+ rgbMatrix[i+1][j+1][2])/4;
				}
				else if ((i==0)&&(j>0)&&(j<n-1))	{
					r = (rgbMatrix[i][j][0]+ rgbMatrix[i][j-1][0]+ rgbMatrix[i][j+1][0]+ rgbMatrix[i+1][j][0]+ 
							rgbMatrix[i+1][j-1][0]+ rgbMatrix[i+1][j+1][0])/6;
					g = (rgbMatrix[i][j][1]+ rgbMatrix[i][j-1][1]+ rgbMatrix[i][j+1][1]+ rgbMatrix[i+1][j][1]+ 
							rgbMatrix[i+1][j-1][1]+ rgbMatrix[i+1][j+1][1])/6;
					b = (rgbMatrix[i][j][2]+ rgbMatrix[i][j-1][2]+ rgbMatrix[i][j+1][2]+ rgbMatrix[i+1][j][2]+ 
							rgbMatrix[i+1][j-1][2]+ rgbMatrix[i+1][j+1][2])/6;
				}
				else if ((i==0)&&(j==n-1))	{
					r = (rgbMatrix[i][j][0]+ rgbMatrix[i][j-1][0]+ rgbMatrix[i+1][j][0]+ rgbMatrix[i+1][j-1][0])/4;
					g = (rgbMatrix[i][j][1]+ rgbMatrix[i][j-1][1]+ rgbMatrix[i+1][j][1]+ rgbMatrix[i+1][j-1][1])/4;
					b = (rgbMatrix[i][j][2]+ rgbMatrix[i][j-1][2]+ rgbMatrix[i+1][j][2]+ rgbMatrix[i+1][j-1][2])/4;
				}
				else if ((i==m-1)&&(j==0))	{
					r = (rgbMatrix[i][j][0]+ rgbMatrix[i][j+1][0]+ rgbMatrix[i-1][j][0]+ rgbMatrix[i-1][j+1][0])/4;
					g = (rgbMatrix[i][j][1]+ rgbMatrix[i][j+1][1]+ rgbMatrix[i-1][j][1]+ rgbMatrix[i-1][j+1][1])/4;
					b = (rgbMatrix[i][j][2]+ rgbMatrix[i][j+1][2]+ rgbMatrix[i-1][j][2]+ rgbMatrix[i-1][j+1][2])/4;
				}
				else if ((i==m-1)&&(j>0)&&(j<n-1))	{
					r = (rgbMatrix[i][j][0]+ rgbMatrix[i][j-1][0]+ rgbMatrix[i][j+1][0]+ rgbMatrix[i-1][j][0]+ 
							rgbMatrix[i-1][j-1][0]+ rgbMatrix[i-1][j+1][0])/6;
					g = (rgbMatrix[i][j][1]+ rgbMatrix[i][j-1][1]+ rgbMatrix[i][j+1][1]+ rgbMatrix[i-1][j][1]+ 
							rgbMatrix[i-1][j-1][1]+ rgbMatrix[i-1][j+1][1])/6;
					b = (rgbMatrix[i][j][2]+ rgbMatrix[i][j-1][2]+ rgbMatrix[i][j+1][2]+ rgbMatrix[i-1][j][2]+ 
							rgbMatrix[i-1][j-1][2]+ rgbMatrix[i-1][j+1][2])/6;
				}
				else if ((i==m-1)&&(j==n-1))	{
					r = (rgbMatrix[i][j][0]+ rgbMatrix[i][j-1][0]+ rgbMatrix[i-1][j][0]+ rgbMatrix[i-1][j-1][0])/4;
					g = (rgbMatrix[i][j][1]+ rgbMatrix[i][j-1][1]+ rgbMatrix[i-1][j][1]+ rgbMatrix[i-1][j-1][1])/4;
					b = (rgbMatrix[i][j][2]+ rgbMatrix[i][j-1][2]+ rgbMatrix[i-1][j][2]+ rgbMatrix[i-1][j-1][2])/4;
				}
				bluredPixels[i][j] = GImage.createRGBPixel(r, g, b);	
			}
		GImage bluredImage = new GImage(bluredPixels);
		return bluredImage;
	}
}
