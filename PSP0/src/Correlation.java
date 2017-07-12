import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**     
* @author 李安迪
* @date 2017年7月12日
* @description Calculate the regression parameters and correlation coefficients between two sets of real numbers
* 				the size of the two sets are both LENGTH
*/
// class Correlation
public class Correlation {
public static int LENGTH = 10;//两个传入数组的大小
public static Scanner in;//文件的扫描仪
public static double[] array;//数组指针



// method calculate_sum

/**
 * 计算大小为LENGTH的传入数组中各元素的和
 * @param array 要被求和的数组，大小为LENGTH
 * @return 数组中各元素的和
 */
public double calculate_sum(double[] array){
	double result = 0;
	for(int i = 0 ; i < LENGTH; i++){
		result += array[i];
	}
	return result;
}
// end

// method multiply_array
/**
 * 计算两个大小为LENGTH的数组对应项的积组成的新数组
 * @param a 传入的大小为LENGTH的数组
 * @param b 传入的大小为LENGTH的数组
 * @return 大小为LENGTH的新数组，其中每个元素是原数组中对应位置两个元素的乘积
 */
public double[] multiply_array(double[] a, double[] b){
	double[] result = new double[10];
	for(int i = 0 ; i < LENGTH; i++){
		result[i] = a[i]*b[i];
	}
	return result;
}
// end

// method prepare_for_denom

/**
 * 计算公式中分母的一部分
 * @param array 要被处理的数组，大小为LENGTH
 * @return 处理后的分母的一部分
 */
public double prepare_for_denom(double[] array){
	double result = 0;
	double temp = 0 ; 
//	System.out.println("prepare");
	//计算减数
	temp = calculate_sum(array);
//	System.out.println(temp);
	
	temp = Math.pow(temp, 2);
//	System.out.println(temp);
	
	//计算被减数
	double sub = LENGTH*calculate_sum(multiply_array(array,array));
	
//	System.out.println(temp);
//	System.out.println(sub);
	//计算结果
	result = sub - temp;
//	System.out.println(result);
	return result;
}
// end

// method prepare_for_beit
/**
 * 计算beit中的分子或分母形式
 * @param a 传入的第一个数组
 * @param b 传入的第二个数组
 * @param x_avg 第一个数组的平均数
 * @param y_avg 第二个数组的平均数
 * @return beit的分子或分母形式
 */
public double prepare_for_beit(double[]a, double[]b,double x_avg,double y_avg){
	double sub = calculate_sum(multiply_array(a,b));
//	System.out.println(sub);
	double temp = 0 ; 
	temp = LENGTH*x_avg*y_avg;
//	System.out.println(temp);
//	System.out.println(sub - temp);
	return sub - temp;
}
// end
// method calculate_correlation

/**
 * Calculate the regression parameters and correlation coefficients between two sets of real numbers, the size of the two sets are both LENGTH
 * @param a 第一个数组，大小为LENGTH
 * @param b 第二个数组，大小为LENGTH
 */
public void calculate_correlation(double[] a, double[] b){
	double x_avg = calculate_mean(a);
//	System.out.println(x_avg);
	double y_avg = calculate_mean(b);
//	System.out.println(y_avg);
	double result1 = prepare_for_beit(a,b,x_avg,y_avg)/prepare_for_beit(a,a,x_avg,x_avg);
	double result0 = y_avg - result1*x_avg;
	System.out.println();
	System.out.println(result0);
	System.out.println(result1);
	double nom = (LENGTH*calculate_sum(multiply_array(a,b))-calculate_sum(a)*calculate_sum(b));
	double denom = Math.pow(prepare_for_denom(a)*prepare_for_denom(b),0.5);
	double result = nom/denom;
//	System.out.println(nom);
//	System.out.println(denom);
	System.out.println(result);
	System.out.println(result*result);
	System.out.println(result0+result1*386);
}
// end
// method read_file
/**
 * 读取一个存有LENGTH个double型数据的数组
 * 
 * @param in
 *            文件读取需要的扫描仪
 * @param utl
 *            文件地址
 * @return 文件中存储的double型数组
 */
public static double[] read_file(Scanner in, String utl) {
	try {
		in = new Scanner(new BufferedReader(new FileReader(new File(utl))));
		array = new double[LENGTH];
		for (int i = 0; i < LENGTH; i++) {
			array[i] = in.nextDouble();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return array;
}
// end

// method calculate_mean
/**
 * 计算一个存有LENGTH个double型数组的数组的平均数
 * 
 * @param array
 *            需要计算平均数的double型数组，大小为LENGTH
 * @return 数组的平均数
 */
public static double calculate_mean(double[] array) {
	double sum = 0;
	double result;

	for (int i = 0; i < LENGTH; i++) {
		sum += array[i];

	}

	result = sum / LENGTH;
	return result;
}
// end

// method go
/**
 * Calculate the regression parameters and correlation coefficients between two sets of real numbers stored in the two files, the size of the two sets are both LENGTH
 * @param path1 第一个文件路径 
 * @param path2 第二个文件路径
 */
public void go(String path1,String path2){
	double[] a = read_file(in,path1);
	double[] b = read_file(in,path2);
	calculate_correlation(a,b);
}
// end
}
 