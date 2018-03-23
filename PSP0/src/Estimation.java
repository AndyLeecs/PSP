import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**     
* @author 李安迪
* @date 2017年7月17日
* @description calculate the required data
*/
// class Estimation
public class Estimation {
	public static Scanner in; //文件扫描仪
	public static double[] array; //数组指针
	public static int LENGTH = 10;//两个传入数组的大小
	public static int xi = 386;//需要依赖的xi值
	final double e = 0.0000001;//permitted error range
	public static double r;//the required r
	public static double yk;//the required yk
	public static double b0;
	public static double b1;
	public static double value;
	public static double[] a;
	public static double[] b;
//	public static double tail_area;

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
//		System.out.println("prepare");
		//计算减数
		temp = calculate_sum(array);
//		System.out.println(temp);
		
		temp = Math.pow(temp, 2);
//		System.out.println(temp);
		
		//计算被减数
		double sub = LENGTH*calculate_sum(multiply_array(array,array));
		
//		System.out.println(temp);
//		System.out.println(sub);
		//计算结果
		result = sub - temp;
//		System.out.println(result);
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
//		System.out.println(sub);
		double temp = 0 ; 
		temp = LENGTH*x_avg*y_avg;
//		System.out.println(temp);
//		System.out.println(sub - temp);
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
//		System.out.println(x_avg);
		double y_avg = calculate_mean(b);
//		System.out.println(y_avg);
		double result1 = prepare_for_beit(a,b,x_avg,y_avg)/prepare_for_beit(a,a,x_avg,x_avg);
		double result0 = y_avg - result1*x_avg;
		System.out.println();
		b0 = result0;
		b1 = result1;
		double nom = (LENGTH*calculate_sum(multiply_array(a,b))-calculate_sum(a)*calculate_sum(b));
		double denom = Math.pow(prepare_for_denom(a)*prepare_for_denom(b),0.5);
		double result = nom/denom;
//		System.out.println(nom);
//		System.out.println(denom);
		r = result;
//		System.out.println(result);
//		System.out.println(result*result);
		yk = result0+result1*xi ;
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
	 * calculate the numerical integration
	 * @param x variable
	 * @param dof degrees of freedom
	 * @return numerical integration
	 */
	public double go(double x, double dof){
		double e = 0.0000001;
		double num_seg = 100000;
		while(((calp(x,dof,num_seg)-calp(x,dof,2*num_seg))>e) || ((calp(x,dof,num_seg)-calp(x,dof,2*num_seg))<-e)){
			num_seg = 2 * num_seg;
		}
		return calp(x,dof,2*num_seg);
	}
	// end
	// method gam
	
	/**
	 * gamma function result
	 * @param dof degrees of freedom
	 * @return gamma function result
	 */
	public double gam(double dof){
		if(dof == 1)
			return 1;
		else if(dof == 0.5)
			return Math.sqrt(Math.PI);
		else
			return(dof-1)*gam(dof-1);
	}
	// end
	// method calp
	
	/**
	 * calculate the integral value
	 * @param x variable
	 * @param dof degrees of freedom
	 * @param num_seg initial number of segments, an even number
	 * @return the integral value
	 */
	public double calp(double x, double dof, double num_seg){
		
		double result = 0;
		double w = x/num_seg;
		double xi = -w;
		double first_gam = gam((dof+1)/2);
		double second_gam = gam(dof/2);
		double funcmul = first_gam/(Math.sqrt(dof*Math.PI)*second_gam);
		int multiplier;
		

		for(int i = 0 ; i <= num_seg ; i++){
			xi+=w;
			double temp = 1 + xi*xi/dof;
			temp = Math.pow(temp, -(0.5*(dof+1)));
			temp = funcmul*temp;
			
			if(i == 0 || i == num_seg)
				multiplier = 1;
			else if(i%2 != 0)
				multiplier = 4;
			else
				multiplier = 2;
			
			temp = temp * w/3 * multiplier;
			
			result += temp;
			
		}
		return result;
	}
	// end
	
	// method guess
	/**
	 * Find the value of x for which integrating the t function from 0 to x gives a result of p.
	 * @param p integration
	 * @param dof degrees of freedom
	 */
	public void guess(double p , double dof){
		double x = 1.0;
		double d = 0.5;
		if (check(p,x,dof) == 0)
			return;
		else {
			
			if(check(p,x,dof) == -1)
			x = x + d;
			else 
				x = x - d;
		}

		int former = check(p,x,dof);
		
		while(true){

		if (check(p,x,dof) == 0)
			return;
		else {
			if(former != check(p,x,dof)){
				former = check(p,x,dof);
			d = d/2;
			
			}
			if(check(p,x,dof) == -1)
			x = x + d;
			else 
				x = x - d;
			}

		}
			}
		
		
		
	
	// end
	// method check
	/**
	 * check if x is valid, too big or too small
	 * @param p integration
	 * @param x guessed variable to form the integration
	 * @param dof degrees of freedom
	 * @return 0 if x is valid, 1 if x is too big, -1 if x is too small
	 */
	public int check(double p, double x, double dof){
		if(go(x,dof) - p <= e && go(x,dof) - p >= -e){
			value = x;
			return 0;
		}
		else if(go(x,dof) - p > e)
			return 1;
		else 
			return -1;
		

	}
	// end
	// method cal_sig
	public double cal_sig(){
		double result = 0;
		double temp_r = r;
		if(r<0)
			temp_r = -r;
		double temp = temp_r*Math.pow(LENGTH - 2, 0.5)/Math.pow(1-r*r, 0.5);
		double p = go(temp,LENGTH-2);
		result = 1-2*p;
		return result;
	}
	// end
	// method cal_range
	public double cal_range(){
		
		double result = 0;
		guess(0.35,LENGTH-2);
		double[] temp = new double[LENGTH];
		for(int i = 0 ; i < LENGTH ; i ++)
			temp[i] = Math.pow(b[i] - b0 - b1*a[i],2);
		double sum = calculate_sum(temp);
		double dev = Math.pow(sum/(LENGTH-2),0.5);
		double x_avg = calculate_mean(a);
		for(int i = 0 ; i < LENGTH ; i++){
			temp[i] = Math.pow(a[i] - x_avg,2);
		}
		sum = calculate_sum(temp);
		double last = Math.pow(1+1.0/LENGTH+Math.pow(xi-x_avg,2)/sum, 0.5);
		result = value*dev*last;
		return result;
	}
	// end

	/**
	 * calculate the required data
	 * @param path1 第一个文件路径
	 * @param path2 第二个文件路径
	 * @param length 数组的长度
	 * @param x xi的值
	 */
	public void go(String path1,String path2,int length,int x){
		LENGTH = length;
		xi = x;
		 a = read_file(in,path1);
		 b = read_file(in,path2);
		calculate_correlation(a,b);
		System.out.println(r);
		System.out.println(r*r);
		System.out.println(cal_sig());
		System.out.println(b0);
		System.out.println(b1);
		System.out.println(yk);
		double temp = cal_range();
		System.out.println(temp);
		System.out.println(yk+temp);
		System.out.println(yk-temp);
		
		
	}
	// end

}
