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
	path = "src/Range.java";
	count = new Count();
	count.count_all(path);
	count.count_method(path);
//
//	System.out.println();
//	path = "src/Correlation.java";
//	count = new Count();
//	count.count_all(path);
//	count.count_method(path);
//////	
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
	
//	Range range = new Range();
//	String path1_1 = "src/4_test1_1.txt";
//	String path1_2 = "src/4_test1_2.txt";
//	String path2_1 = "src/4_test2_1.txt";
//	String path2_2 = "src/4_test2_2.txt";
//	range.go(path1_1, path1_2, 13);
//	range.go(path2_1, path2_2, 16);

	Integration i = new Integration();
	i.go(1.1, 9);
	i.go(1.1812,10);
	i.go(2.750, 30);
	
	
}
}
