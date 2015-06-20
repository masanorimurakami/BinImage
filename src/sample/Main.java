package sample;

import java.awt.image.BufferedImage;

public class Main {

	// スクリーンキャプチャ画像のバッファ
	static BufferedImage bufimg;
	// モノクロ画像のバッファ
	static BufferedImage monoimg;
	// 二値化された画像のバッファ
	static BufferedImage binimg;

	public static void main(String[] args) {

		// スクリーンキャプチャの取得
		// 引数の範囲の画像をキャプチャする。
		// 画像取得座標 x,y
		// 画像の取得範囲
		bufimg = new ScreenCaptuer().getScreenImage(100, 100, 200, 200);

		// グレイスケールに変換
		monoimg = new ChangeImage().changeToGray(bufimg);

		// 二値化
		binimg = new ChangeImage().changeToBin(monoimg);

	}

}
