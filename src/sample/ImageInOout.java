package sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageInOout {

	public BufferedImage imageRead(String filename) {
		// 返却するイメージファイル
		BufferedImage bufimg = null;

		try {
			bufimg = ImageIO.read(new File(filename));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return bufimg;

	}

	public boolean imageWrite(BufferedImage bufimg, String filename) {

		boolean result = false;

		try {
			result = ImageIO.write(bufimg, "png", new File(filename));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return result;

	}

}
