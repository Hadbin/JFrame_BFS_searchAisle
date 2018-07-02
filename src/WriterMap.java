import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WriterMap {
	static FileWriter fw = null; 
	/**
	 * 写入地图	
	 */
	public static void wirterMap() {
		try {
			File writername=new File("map\\map.txt");
			writername.createNewFile();//创建新文件
			fw = new FileWriter(writername);
			PrintWriter pw = new PrintWriter(writername); 
			for(int i=0;i<20;i++) {
				for(int j=0;j<29;j++) {
					if(Math.random()*10>=8.0) {
						pw.print("1,");
					}else {
						pw.print("0,");
					}
					if(j==28) {
						pw.println("0");
					}
				}				
			}
			pw.close();
		}catch(Exception e){
			
		}
	}

}
