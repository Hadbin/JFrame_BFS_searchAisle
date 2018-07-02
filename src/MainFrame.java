import javax.swing.JFrame;

public class MainFrame extends JFrame{
	/**
	 * 主界面
	 */
	public void mainFrame() {
		this.setTitle("最短路径");
		this.setSize(905, 625);
		this.setLocationRelativeTo(null); //默认打开窗口居中显示
		this.setResizable(false);//固定窗口大小
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//使用 System exit 方法退出应用程序。仅在应用程序中使用。 
		MainPanel gp = new MainPanel(); //每次实例化对象都会生成地图
		this.add(gp);//将画板加入框架
		this.setVisible(true);//显示窗口
	}
}
