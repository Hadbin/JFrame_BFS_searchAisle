import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JOptionPane;

/**
 * 核心类
 */
public class ME extends Thread {
	public int row = 0, cols = 0;// 自己地图数组位置
	public int width = 30, height = 30;// 自己宽高
	public int endRow, endCols;// 终点的高度X和宽度Y
	private final int INF = 100000000;// 一个不可能的常量来初始化最短距离数组
	private int n = 20, m = 30;// 地图的宽高，即是数组的二维长度
	private int[][] d;// 到各个位置最短距离的数组
	private int[] dx = { 1, 0, -1, 0 };// x4个方向的移动向量，右上左下 //窗口的左上角0,0为起点，
	private int[] dy = { 0, 1, 0, -1 };// y4个方向的移动向量
	private MainPanel gp = null;
	Queue<Point> queue = new LinkedList<Point>();//存放方块信息
	List<Point> list = new ArrayList<Point>();// 保存最短路径的坐标
	public ME(MainPanel gp) {//构造方法
		this.gp = gp;
	}
	public void run() {
		while (true) { // 使滑块一直处于可活动状态
			List<Point> list = retList();// 根据队，先进先出，第一次访问的终点是第二次访问的起点
			if (list.size() != 1 && list.size() != 0) {// 终点加上起始点，有2个点。但实际只移动了一步，所以-1
				System.out.println("最短路径为" + (list.size() - 1) + "步");
			}
			for (int i = list.size() - 1; i >= 0; i--) {
				row = (int) list.get(i).getX();// 当前滑块所在的行数
				cols = (int) list.get(i).getY();// 当前滑块所在的列数
				gp.repaint();// 重画滑块
				try {
					sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Point> retList() {
		d = new int[n][m];
		// 清除数据
		list.clear();
		queue.clear();
		// 初始化距离INF
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				d[i][j] = INF;
			}
		}

		// 添加起点到队列并使起点距离为0
		Point point = new Point(row, cols);// 起点
		
		queue.offer(point);
		d[row][cols] = 0;

		while (queue.size() > 0) {// 将所有方块的信息存入队
			point = queue.poll();// 拿到队尾元素并删除
			// 如果是终点则结束
			if (point.getX() == endRow && point.getY() == endCols) {
				break;
			}
			// 遍历四个向量
			for (int i = 0; i < 4; i++) {
				int nx = (int) (point.getX() + dx[i]);
				int ny = (int) (point.getY() + dy[i]);
				// 如果点在地图里并且不是墙壁并且从未走过则添加到队列里并且距离加1
				if (0 <= nx && nx < n && 0 <= ny && ny < m && gp.map[nx][ny] == 0 && d[nx][ny] == INF) {
					queue.offer(new Point(nx, ny));
					d[nx][ny] = d[(int) point.getX()][(int) point.getY()] + 1;
				}
			}
		}

		list.add(new Point(endRow, endCols));// 添加终点
		int number = d[endRow][endCols];

		boolean flag;
		int f = -1;
		while (number > 0) {
			number--;
			f++;
			flag = true;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (d[i][j] == number && ((Math.abs(i - list.get(f).getX()) == 1 && j == list.get(f).getY())
							|| (Math.abs(j - list.get(f).getY()) == 1 && i == list.get(f).getX()))) {
						if (flag) {
							list.add(new Point(i, j));
						}
						flag = false;
					}
				}
			}
		}
		return list;

	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getEndCols() {
		return endCols;
	}

	public void setEndCols(int endCols) {
		this.endCols = endCols;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
