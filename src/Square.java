import java.awt.Color;

/**
 * 方块类
 * @author Administrator
 *
 */
public class Square {
	
	public int x, y;// 障碍物的x,y坐标
	public int width, height;// 宽高度
	public Color aisleOrWall; //通道或墙

	public Square(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

}
