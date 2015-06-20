package sample;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class ScreenCaptuer {

	/**
	 * 画面の特定の場所をキャプチャする。
	 *
	 * @param x 画像取得の開始位置
	 * @param y 画像取得の開始位置
	 * @param width 画像の幅
	 * @param height 画像の高さ
	 * @return 取得した画像
	 */
	public BufferedImage getScreenImage(int x, int y, int width, int height) {
		// 取得する画像
		BufferedImage cptimg = null;
		// 画像を取得する矩形を設定
		Rectangle rect = new Rectangle();
		rect.setBounds(x, y, width, height);

		// 画像の取得
		try {
			// 取得用ロボットの生成
			Robot robo = new Robot();
			// スクリーンキャプチャの取得
			cptimg = robo.createScreenCapture(rect);

		} catch (AWTException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return cptimg;
	}

}
