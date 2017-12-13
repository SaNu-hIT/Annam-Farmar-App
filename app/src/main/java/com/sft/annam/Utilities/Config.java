package com.sft.annam.Utilities;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class Config {

	private static boolean isRunnable = false;
	
	public static void LogError(String tag,String msg){
		if (!isRunnable) {
			Log.e(tag, msg);
		}
	}
	public static void LogDebug(String tag,String msg){
		if (!isRunnable) {
			Log.d(tag, msg);
		}
	}
	/****
	 * show toast
	 */
	public static void showToast(String message,Activity activity){
		Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
	}
	
}
