package sample;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

public class Main {

	// スクリーンキャプチャ画像のバッファ
	static BufferedImage bufimg;
	// モノクロ画像のバッファ
	static BufferedImage monoimg;
	// 二値化された画像のバッファ
	static BufferedImage binimg;
	// 画面全体のスクショ
	static BufferedImage allsrn;

	public static void main(String[] args) {

		// スクリーンキャプチャクラスを生成
		ScreenCaptuer sc = new ScreenCaptuer();
		// 画像加工クラスを生成
		ChangeImage ci = new ChangeImage();
		// ファイル入出量クラス生成
		ImageInOout ii = new ImageInOout();

		// スクリーンキャプチャの取得
		// 引数の範囲の画像をキャプチャする。
		// 画像取得座標 x,y
		// 画像の取得範囲
		bufimg = sc.getScreenImage(100, 100, 200, 200);
		boolean result = ii.imageWrite(bufimg, "cptimg.png");

		// グレイスケールに変換
		monoimg = ci.changeToGray(bufimg);
		result = ii.imageWrite(monoimg, "mono.png");

		// 二値化
		binimg = ci.changeToBin(monoimg);
		result = ii.imageWrite(binimg, "binimg.png");

		// 画面全体のスクリーンキャプチャを取得
		// 画面解像度の取得
		// 環境変数を取得
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		// デフォルトのディスプレイモードを取得
		DisplayMode displayMode = env.getDefaultScreenDevice().getDisplayMode();
		// ディスプレイの高さと幅を取得
		int srnWidhth = displayMode.getWidth();
		int srnHeight = displayMode.getHeight();
		// 画面全体のスクリーンショットを取得
		allsrn = sc.getScreenImage(0, 0, srnWidhth, srnHeight);
		// スクリーンショットの白黒化
		BufferedImage allmono = ci.changeToGray(allsrn);
		// スクリーンショットの二値化
		BufferedImage allbin = ci.changeToBin(allmono);

		// 比較元の画像サイズを取得する。
		int ix = binimg.getWidth();
		int iy = binimg.getHeight();

		// 比較元の適合率を９０％とする。
		int threshold = (int) (binimg.getWidth() * binimg.getHeight() * 0.9);
		// 適合率
		int s = 0;

		// マッチング箇所を探す
		for (int x = 0; x < srnWidhth - ix; x++) {
			for (int y = 0; y < srnHeight - iy; y++) {
				// ピクセルの比較
				// 比較画像のサイズの範囲で走査
				// 比較値の初期化
				s = 0;
				for (int x1 = x; x1 < x + ix; x1++) {
					for (int y1 = y; y1 < y + ix; y1++) {
						Color motoColor = new Color(binimg.getRGB(x1 - x, y1 - y));
						Color allColor = new Color(allbin.getRGB(x1, y1));
						if (motoColor.getBlue() == allColor.getBlue()) {
							// 合致した
							s++;
						}
					}
				}
				// デバック
				System.out.println("Sの値:" + Integer.toString(s) + " threshold:" + Integer.toString(threshold));
				// 適合率の比較
				if (threshold < s) {
					// 合致した。
					Graphics g = allsrn.getGraphics();
					g.setColor(Color.RED);
					g.drawRect(x, y, ix, iy);
				}
			}
		}

		// ファイル書き出し
		result = ii.imageWrite(allsrn, "resultimg.png");

	}
}
