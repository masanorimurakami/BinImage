package sample;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ChangeImage {

	/**
	 * 画像をモノクロイメージに変換します。
	 *
	 * @param bufimg
	 * @return
	 */
	public BufferedImage changeToGray(BufferedImage bufimg) {

		// 元画像のサイズを取得
		int width = bufimg.getWidth();
		int height = bufimg.getHeight();

		// モノクロイメージ画像を生成
		BufferedImage monoimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 横幅を走査
		for (int x = 0; x < width; x++) {
			// 縦を走査
			for (int y = 0; y < height; y++) {
				// 現在走査中のピクセルから、カラー情報を取得する。
				Color color = new Color(bufimg.getRGB(x, y));
				// 色成分の取り出し
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				// モノクロ成分を計算
				int d = (g * 6 + r * 3 + b) / 10;
				// カラーをモノクロにセット
				color = new Color(d, d, d);
				// モノクロイメージに格納
				monoimg.setRGB(x, y, color.getRGB());
			}
		}

		// モノクロ画像を返却
		return monoimg;
	}

	/**
	 * モノクロ画像を、二値化する。
	 *
	 * @param monoimg モノクロ画像
	 * @return
	 */
	public BufferedImage changeToBin(BufferedImage monoimg) {

		// 画像サイズの取得
		int width = monoimg.getWidth();
		int height = monoimg.getHeight();

		// 二値化されたイメージ生成
		BufferedImage binimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// ピクセル数を求める
		int size = width * height;

		// ヒストグラムの生成
		int[] histogram = new int[256];

		// ヒストグラムを初期化。
		for (int i = 0; i < 256; i++) {
			histogram[i] = 0;
		}

		// ヒストグラムを取得する。
		// 横走査
		for (int x = 0; x < width; x++) {
			// 縦操作
			for (int y = 0; y < height; y++) {
				// モノクロ画像のカラー情報取得
				Color color = new Color(monoimg.getRGB(x, y));
				// RGBどれも同じなので、どれでも良い。
				int d = color.getBlue();
				// ヒストグラムに格納
				histogram[d]++;
			}
		}

		// 頻度数の中間値を求める
		int i = 0;
		int s = 0;
		while (s < size / 2) {
			s = s + histogram[i];
			i++;
		}
		int threshold = i - 1;

		// 画像の二値化
		// 横走査
		for (int x = 0; x < width; x++) {
			// 縦操作
			for (int y = 0; y < height; y++) {
				// モノクロ画像から色情報を取得
				Color color = new Color(monoimg.getRGB(x, y));
				// 更に色の値を取得
				int d = color.getGreen();
				// 白黒閾値との比較
				if (threshold > d) {
					d = 0;
				} else {
					d = 255;
				}
				// 色情報を生成
				color = new Color(d, d, d);
				// 二値化画像にセット
				binimg.setRGB(x, y, color.getRGB());
			}
		}

		// 二値化された画像を返却する。
		return binimg;
	}
}
