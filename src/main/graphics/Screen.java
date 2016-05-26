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
	
	public void render ( int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yp = y + yOffset;
			if (yp < 0 || yp >= height) continue;
			for (int x = 0; x < width; x++) {
				int xp = x + xOffset;
				if (xp < 0 || xp >= width) continue;
				pixels[xp + yp * width] = 
						Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
			}
		}
	}
}
