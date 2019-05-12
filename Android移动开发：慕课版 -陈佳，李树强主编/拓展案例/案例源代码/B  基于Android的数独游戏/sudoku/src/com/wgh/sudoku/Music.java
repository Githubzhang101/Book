package com.wgh.sudoku;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
	private static MediaPlayer mp = null;

	public static void play(Context context, int resource) {
		stop(context);
		if (SettingsActivity.getMusic(context)) {//判断是否播放背景音乐
			mp = MediaPlayer.create(context, resource);
			mp.setLooping(true); // 是否循环播放
			mp.start(); // 开始播放
		}
	}

	public static void stop(Context context) {
		if (mp != null) {
			mp.stop();
			mp.release();
			mp = null;
		}

	}
}
