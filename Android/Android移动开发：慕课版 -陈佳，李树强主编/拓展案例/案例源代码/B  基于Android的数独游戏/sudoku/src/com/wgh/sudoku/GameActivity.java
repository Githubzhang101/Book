package com.wgh.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class GameActivity extends Activity {
	private static final String TAG = "sudoku";
	public static final String KEY_DIFFICULTY = "difficulty";
	public static final int DIFFICULTY_EASY = 0;
	public static final int DIFFICULTY_MEDIUM = 1;
	public static final int DIFFICULTY_HARD = 2;
	private int puzzle[] = new int[9 * 9];
	private PuzzleView puzzleView;
	private final int used[][][] = new int[9][9][];
	private final String easyPuzzle  
			= "360000000004230800000004200"
			+ "070460003820000014500013020"
			+ "001900000007048300000000045";
	private final String mediumPuzzle 
			= "650000070000506000014000005"
			+ "007009000002314700000700800" 
			+ "500000630000201000030000097";
	private final String hardPuzzle 
			= "009000000080605020501078000"
			+ "000000700706040102004000000" 
			+ "000720903090301080000000600";
	boolean success = false; // 判断是否成功
	//继续前一游戏
	private static final String PREF_PUZZLE="puzzle";
	protected static final int DIFFICULTY_CONTINUE=-1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		int diff = getIntent().getIntExtra(KEY_DIFFICULTY, DIFFICULTY_EASY);
		puzzle = getPuzzle(diff); // 接收难度级别并返回一次数独游戏
		Log.d(TAG, "onCreate11" + diff);
		calculateUsedTiles(); // 实现真正的游戏逻辑
		Log.d(TAG, "onCreate22" + diff);

		puzzleView = new PuzzleView(this);
		setContentView(puzzleView);
		puzzleView.requestFocus();
		 getIntent().putExtra(KEY_DIFFICULTY, DIFFICULTY_CONTINUE);//恢复已保存的游戏
	}
	//获取游戏的难易程序
	private int[] getPuzzle(int diff) {
		String puz;
		switch (diff) {
		case DIFFICULTY_CONTINUE:
			puz=getPreferences(MODE_PRIVATE).getString(PREF_PUZZLE,easyPuzzle);
			break;
		case DIFFICULTY_HARD:
			puz = hardPuzzle;
			break;
		case DIFFICULTY_MEDIUM:
			puz = mediumPuzzle;
			break;
		case DIFFICULTY_EASY:
		default:
			puz = easyPuzzle;
			break;
		}
		return fromPuzzleString(puz);
	}

	public void showKeyPadOrError(int x, int y) {
		int tiles[] = getUsedTiles(x, y);
		if (tiles.length == 9) {
			Toast toast = Toast.makeText(this, R.string.no_moves_label,
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else {
			Log.d(TAG, "showKeyPad:used=" + toPuzzleString(tiles));
			Dialog v = new KeyPad(this, tiles, puzzleView);
			v.show();
		}
	}

	private String toPuzzleString(int[] puz) {
		StringBuilder buf = new StringBuilder();
		for (int element : puz) {
			buf.append(element);
		}
		return buf.toString();
	}

	public boolean setTileIfValid(int x, int y, int value) {
		int tiles[] = getUsedTiles(x, y);
		if (value != 0) {
			for (int tile : tiles) {
				if (tile == value) {
					return false;
				}
			}
		}
		setTile(x, y, value);
		calculateUsedTiles(); // 实现真正的游戏逻辑
		/**************** 判断游戏是否成功 *************************/
		success = true;
		label: for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (getTile(i, j) == 0) {
					success = false;
					break label;
				}
			}
		}
		if (success) {
			Log.d(TAG, "数独游戏成功！");
			// 弹出带确定按钮的提示对话框
			new AlertDialog.Builder(GameActivity.this)
					.setTitle(TAG)
					.setMessage("恭喜您，成功！")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									finish(); // 返回游戏主界面
								}
							}).show();
		}
		/********************************************************/
		return true;
	}

	private void calculateUsedTiles() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				used[i][j] = calculateUsedTiles(i, j);
			}
		}
	}

	private int[] calculateUsedTiles(int x, int y) {
		int c[] = new int[9];
		// 水平方向
		for (int i = 0; i < 9; i++) {
			if (i == y) {
				continue;
			}
			int t = getTile(x, i);
			if (t != 0) {
				c[t - 1] = t;
			}
		}
		// 垂直方向
		for (int i = 0; i < 9; i++) {
			if (i == x) {
				continue;
			}
			int t = getTile(i, y);
			if (t != 0) {
				c[t - 1] = t;
			}
		}
		int startx = (x / 3) * 3;
		int starty = (y / 3) * 3;
		for (int i = startx; i < startx + 3; i++) {
			for (int j = starty; j < starty + 3; j++) {
				if (i == x && j == y) {
					continue;
				}
				int t = getTile(i, j);
				if (t != 0) {
					c[t - 1] = t;
				}
			}
		}
		int nused = 0;
		for (int t : c) {
			if (t != 0) {
				nused++;
			}
		}
		int cl[] = new int[nused];
		nused = 0;
		for (int t : c) {
			if (t != 0) {
				cl[nused++] = t;
			}
		}
		return cl;
	}

	/**
	 * 功能：获取指定单元格中的数字
	 * @param x
	 * @param y
	 * @return
	 */
	private int getTile(int x, int y) {
		// TODO Auto-generated method stub
		return puzzle[y * 9 + x];
	}

	/**
	 * 功能：设置指定单元格中的数字
	 * @param x
	 * @param y
	 * @param value
	 */
	private void setTile(int x, int y, int value) {
		puzzle[y * 9 + x] = value;

	}

	protected int[] getUsedTiles(int x, int y) {
		return used[x][y];
	}

	public String getTileString(int x, int y) {

		int v = getTile(x, y);
		if (v == 0) {
			return "";
		} else {
			return String.valueOf(v);
		}
	}

	static protected int[] fromPuzzleString(String string) {
		int[] puz = new int[string.length()];
		for (int i = 0; i < puz.length; i++) {
			puz[i] = string.charAt(i) - '0';
		}
		return puz;
	}

	@Override
	protected void onPause() {	//暂停游戏
		super.onPause();
		Music.stop(this);
		getPreferences(MODE_PRIVATE).edit()
		.putString(PREF_PUZZLE, toPuzzleString(puzzle)).commit();	//保存游戏当前状态
	}

	@Override
	protected void onResume() {	//恢复游戏
		super.onResume();
		Music.play(this,R.raw.lhydd);
	}
	
}
