/**     
* @author 李安迪
* @date 2017年7月11日
* @description 测试类
*/
public class test
{
public static void main(String[]args){
//	String path = "src/mean_and_standard_deviation.java";
//	Count count = new Count();
//	count.count_all(path);
//	count.count_method(path);
//	
//	System.out.println();
//	path = "src/Estimation.java";
//	count = new Count();
//	count.count_all(path);
//	count.count_method(path);

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

//	Integration i = new Integration();
//	i.go(1.1, 9);
//	i.go(1.1812,10);
//	i.go(2.750, 30);
//	ValueOfX v = new ValueOfX();
//	v.guess(0.20,6);
//	v.guess(0.45,15);
//	v.guess(0.495,4);
//	String path1 = "src/7_test1_1.txt";
//	String path2 = "src/7_test1_2.txt";
//	String path3 = "src/7_test2_2.txt";
//	String path4 = "src/7_test3_1.txt";
//	String path5 = "src/7_test3_2.txt";
//	String path6 = "src/7_test4_2.txt";
	
//	Estimation e = new Estimation();
////	e.go(path1, path2, 10, 386);
////	e.go(path1, path3,10,386);
//	e.go(path4, path5,4,215);
//	e.go(path4, path6,4,215);
	
	String path1 = "src/8_test_1_1.txt";
	String path2 = "src/8_test_1_2.txt";
	String path3 = "src/8_test_1_3.txt";
	String path4 = "src/8_test_1_4.txt";
	
	String path11 = "src/8_test_2_1.txt";
	String path12 = "src/8_test_2_2.txt";
	String path13 = "src/8_test_2_3.txt";
	String path14 = "src/8_test_2_4.txt";
	
	MultiEstimation m = new MultiEstimation();
	m.go(path11, path12, path13, path14, 185, 150, 45);
	m.go(path1, path2, path3, path4, 650, 3000, 155);

	
}
}
