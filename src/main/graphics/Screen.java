package main.graphics;

import java.util.Random;

public class Screen {
	
	private int width, height;
	public int[] pixels;
	public final int tileSize = 64;
	public final int tileSize_Mask = tileSize - 1;
	
	public int[] tiles = new int[tileSize * tileSize];
	
	private Random random = new Random ();
	
	public Screen (int width, int height) {
		this.width = width;
		this.height  = height;
		pixels = new int[width * height]; // 0 - 48,599 = 48,600
		
		for (int i = 0; i < tileSize * tileSize; i++) {
			tiles[i] = random.nextInt (0xffffff);
			tiles[0] = 0;
		}
		
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void render ( int xoffset, int yoffset) {
		for (int y = 0; y < height; y++) {
			int yy = y + yoffset;
			//if (yy < 0 || yy >= height) break;
			for (int x = 0; x < width; x++) {
				int xx = x + xoffset;
				//if (xx < 0 || xx >= width) break;
				int tileIndex = ((xx >> 4) & tileSize_Mask) + ((yy >> 4) & tileSize_Mask) * tileSize; // the number after & should be one less than tileSize
				pixels[x + y * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
			}
		}
	}
}
