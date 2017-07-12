/**     
* @author 李安迪
* @date 2017年7月11日
* @description 测试类
*/
public class test
{
public static void main(String[]args){
	String path = "src/mean_and_standard_deviation.java";
	Count count = new Count();
	count.count_all(path);
	count.count_method(path);
	
	System.out.println();
	path = "src/Count.java";
	count = new Count();
	count.count_all(path);
	count.count_method(path);
//
//	System.out.println();
//	path = "src/Correlation.java";
//	count = new Count();
//	count.count_all(path);
//	count.count_method(path);
////	
//	System.out.println();
//	String path1 = "src/3_test1.txt";
//	String path2 = "src/3_test2.txt";
//	String path3 = "src/3_test3.txt";
//	String path4 = "src/3_test4.txt";
//	Correlation c = new Correlation();
//	c.go(path1, path3);
//	c.go(path1, path4);
//	c.go(path2, path3);
//	c.go(path2, path4);
	
}
}
