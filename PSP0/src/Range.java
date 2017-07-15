import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**     
* @author 李安迪
* @date 2017年7月13日
* @description calculate the relative size ranges for very small, small, medium, large, and very large ranges using standard deviation for the data stored in two files
*/
// class Range
public class Range {
	public static int LENGTH = 10;//两个传入数组的大小
	public static Scanner in;//文件的扫描仪
	public static double[] array;//数组指针
	
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

	// method calculate_deviation
	/**
	 * 计算一个存有LENGTH个double型数组的数组的标准差
	 * 
	 * @param mean
	 *            数组的平均数
	 * @param array
	 *            需要计算标准差的double型数组
	 * @return 数组的标准差
	 */
	public static double calculate_deviation(double mean, double[] array) {
		double sum = 0;
		double result;

		for (int i = 0; i < LENGTH; i++) {
			// System.out.println(array[i]);
			sum += Math.pow(array[i] - mean, 2);
			// System.out.println(sum);
		}

		// System.out.println(sum);
		result = Math.pow(sum / (LENGTH - 1), 0.5);
		return result;
	}
	// end

	// method divide
	/**
	 * a数组中各项分别除b数组中各项的结果组成的新数组，长度为LENGTH
	 * @param a 要处理的第一个数组，长度为LENGTH
	 * @param b 要处理的第二个数组，长度为LENGTH
	 * @return a数组中各项分别除b数组中各项的结果组成的新数组
	 */
	public double[] divide(double[] a, double[]b){
		double[] result = new double[LENGTH] ;
		for(int i = 0 ; i < LENGTH ; i++){
			result[i] = a[i]/b[i];
		}
		return result;
	}
	// end
	
	// method log
	/**
	 * 对传入数组中各项取对数并返回结果
	 * @param a 要取对数处理的数组，长度为LENGTH
	 * @return 对原数组中各项取对数获得的新数组
	 */
	public double[] log(double[]a){
		double[] result = new double[LENGTH];
		for(int i = 0 ; i < LENGTH ; i++){
			result[i] = Math.log(a[i]);
		}
		return result;
	}
	// end
	// method go
	/**
	 * calculate the relative size ranges for very small, small, medium, large, and very large ranges using standard deviation for the data stored in two files
	 * @param path1 第一个文件路径
	 * @param path2 第二个文件路径
	 * @param 数组的长度
	 */
	public void go(String path1,String path2,int length){
		LENGTH = length;
		double[] a = read_file(in,path1);
		double[] b = read_file(in,path2);
		double[] result = log(divide(a,b));
		double avg = calculate_mean(result);
		double dev = calculate_deviation(avg,result);
		for(int i = 0 ; i < 5 ; i ++){
			System.out.println(Math.pow(Math.E, avg-2*dev+i*dev));
		}
	}
	// end
}
