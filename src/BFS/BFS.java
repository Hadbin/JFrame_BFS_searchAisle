package BFS;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class BFS {
	private final int INF = 100000000;// 一个不可能的常量来初始化最短距离数组
	private int n, m;// 地图的宽高，即是数组的二维长度
	private char[][] maze;// 二维地图数组 (.表示可以走) ( #表示墙壁)( s表示起点 )(g表示终点)
	private int[][] d;// 到各个位置最短距离的数组
	private int sx, sy;// 起点坐标
	private int gx, gy;// 终点坐标
	private int[] dx = { 1, 0, -1, 0 };// x4个方向的移动向量，右上左下 //窗口的左上角0,0为起点，
	private int[] dy = { 0, 1, 0, -1 };// y4个方向的移动向量
	private List<Point> list = new ArrayList<Point>();// 保存最短路径的坐标

	public static void main(String[] args) {
		new BFS().solve();
	}

	private void solve() {
		/************ 控制台输入数据并new数组 ********************/
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();	
		m = scanner.nextInt();	
		// {{'#','S','.','.','.'},{'#','#','#','#','.'},{'.','.','.','.','.'},
		//{'.','#','#','#','#'},{'.','.','.','G','#'}};
		maze = new char[n][m];
		d = new int[n][m];

		// new Thread() {
		// public void run() {
		// while (true){
		/************* char数组赋值 ********************/

		Random random = new Random();
		int num;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				num = random.nextInt(100);
				if (num % 5 == 0) {
					maze[i][j] = '#';// 墙壁
				} else {
					maze[i][j] = '.';// 路
				}
			}
		}

		/************* 起点终点赋值 ********************/
		sx = 0;
		sy = 0;
		gx = n - 1;
		gy = m - 1;
		maze[sx][sy] = 'S';// 起点
		maze[gx][gy] = 'G';// 终点
		/************ 自动生成的field数组元素输出 *********************/
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}

		Queue<Point> queue = new LinkedList<Point>();
		// 初始化距离INF
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				d[i][j] = INF;
			}
		}
		// 添加起点到队列并使起点距离为0
		Point point = new Point(sx, sy);// 起点
		queue.offer(point); //插入
		d[sx][sy] = 0;		

		while (queue.size() > 0) {
			point = queue.poll();// 拿到队尾元素并删除
			// 如果是终点则结束
			if (point.getX() == gx && point.getY() == gy)
				break;
			// 遍历四个向量
			for (int i = 0; i < 4; i++) {
				int nx = (int) (point.getX() + dx[i]);
				int ny = (int) (point.getY() + dy[i]);
				// 如果点在地图里并且不是墙壁并且从未走过则添加到队列里并且距离加1
				if (0 <= nx && nx < n && 0 <= ny && ny < m && maze[nx][ny] != '#' && d[nx][ny] == INF) {
					queue.offer(new Point(nx, ny));
					d[nx][ny] = d[(int) point.getX()][(int) point.getY()] + 1;
				}
			}
		}
		if (d[gx][gy] == INF) {
			System.out.println("从S到G的路不通");
		}
		// System.out.println("从S到G至少要走" + d[gx][gy] + "步");

		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < m; j++) {
		// if (d[i][j]
		// ==d[gx][gy]-1&&((Math.abs(i-gx)==1&&j==gy)||(Math.abs(j-gy)==1&&i==gx))){
		// System.out.println(""+i+j);
		// }
		// }
		// }
		/*************** 遍历d数组不断的找出每个点然后添加到容器 ********************/
		list.add(new Point(gx, gy));// 添加终点
		int number = d[gx][gy];
		boolean flag;
		int f = -1;
		while (number > 0) {
			number--;
			f++;
			flag = true;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (d[i][j] == number && ((Math.abs(i - list.get(f).getX()) == 1 
							&& j == list.get(f).getY())
							|| (Math.abs(j - list.get(f).getY()) == 1 && i == list.get(f).getX()))) {
						if (flag) {
							list.add(new Point(i, j));
						}
						flag = false;
					}
				}
			}
		}
		System.out.println("至少需要走" + (list.size() - 1) + "步,每一步的情况如下");
		System.out.println("x y");
		for (int i = list.size() - 1; i >= 0; i--) {
			System.out.println("" + (int) list.get(i).getX() + " " + (int) list.get(i).getY());
		}
		list.clear();
		// try {
		// sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		// }.start();

	}

}
