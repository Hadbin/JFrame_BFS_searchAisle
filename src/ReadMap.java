import java.io.FileInputStream;
 
public class ReadMap {
 
 /**
  * 读取地图
  * 工具方法
  */
 public static int[][] readMap() {
	 WriterMap.wirterMap();//随机写入地图信息
  int[][] map = null;
  try {
   FileInputStream fis = new FileInputStream("map/map.txt"); //地图文件 1位障碍物
   // 先定义一个字节数组
   byte[] bytes = new byte[fis.available()];//FileInputStream.available()  返回的实际可读字节数，也就是总大小
   fis.read(bytes);
   // 编程字符串
   String str = new String(bytes).trim();//返回字符串的副本，忽略前导空白和尾部空白。
   // 总行数
   int row = 0;
   int column = 0;
   // 总行数
   String[] strs = str.split("\r\n");//根据匹配给定的正则表达式来拆分此字符串。
   row = strs.length;
   // 确定列数
   if (strs != null && strs.length >= 1) {
    String[] allColumn = strs[0].split(",");
    column = allColumn.length;
   }
   map = new int[row][column];
   // 存数据
   for (int i = 0; i < strs.length; i++) {
    String value = strs[i]; //第i行
    String[] vs = value.split(",");
    for (int j = 0; j < vs.length; j++) {
     String v = vs[j];//第j列
     map[i][j] = Integer.parseInt(v);
    }
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
  return map;
 }
}
