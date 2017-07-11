import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author 李安迪
 * @date 2017年7月11日
 * @description 从文件中读取一组数据，个数为10，计算平均数和标准差
 */

// class mean_and_standard_deviation
public class mean_and_standard_deviation {
	public static Scanner in;
	public static double[] array;
	public static final int LENGTH = 10;
	public static int count;
	public static String utl;
	public static DecimalFormat df = new DecimalFormat(".##");

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

}
