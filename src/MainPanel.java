import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	/**
	 * 寻找最短路径的面板
	 */
	public int[][] map;
	private Square square;
	private ME me;
	public static ArrayList<Square> squareList = new ArrayList<Square>();// 障碍物容器
	{// 构造代码块，每实例化对象时就执行一次
		map = ReadMap.readMap();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				int x = j * 30;
				int y = i * 30;
				square = new Square(x, y, 30, 30);//每格子 长 30 ，宽30
				if (map[i][j] == 0) {//通道
					square.aisleOrWall = Color.WHITE;
				} else if (map[i][j] == 1) {// 墙壁
					square.aisleOrWall = Color.ORANGE;
				}
				squareList.add(square);
			}
		}
	}
	public MainPanel() {//构造方法
		me = new ME(this);// 自己对象
		initListener();
		me.start();// 启动线程
	}
	private void initListener() {
		MouseAdapter mouseAdpt = new MouseAdapter() {//因为抽象类不能实例化，所以通过匿名内部类，复写mousePressed方法
			public void mousePressed(MouseEvent e) {
				//getX()返回事件相对于源组件的水平 x 坐标
				int row=e.getY() / me.height;	// 100/30=3		120/30=4
				int cols= e.getX() / me.width;
				if(e.getY() % me.height == 0) { // 100%30=10	120%30=0
					row--;
				}
				if(e.getX() % me.width == 0) {
					cols--;
				}

				System.out.println("row="+row);
				System.out.println("cols="+cols);
				if (map[row][cols] == 0) {//设置下次移动的终点
					me.setEndCols(cols);
					me.setEndRow(row);
				}
			}
		};
		this.addMouseListener(mouseAdpt);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < squareList.size(); i++) {// 画方块
			square = squareList.get(i);
			g.setColor(square.aisleOrWall);
			g.fillRect(square.x, square.y, square.width, square.height);//x,y坐标，方块宽，高
		}

		g.setColor(Color.LIGHT_GRAY);//方块间线条颜色
		for (int i = 0; i <= 600; i += 30) {

			g.drawLine(0, i, 900, i);

		}
		for (int i = 0; i <= 870; i += 30) {
			g.drawLine(i + 30, 0, i + 30, 600);
		}

		g.setColor(Color.GREEN);//移动方块的颜色
		g.fillRect(me.cols * me.width, me.row * me.height, me.width, me.height);

	}
}
