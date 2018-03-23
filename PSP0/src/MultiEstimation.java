import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**     
* @author 李安迪
* @date 2017年7月18日
* @description calculate the required data
*/
// class MultiEstimation
public class MultiEstimation {
	public static Scanner in; //文件扫描仪
	public static double[] array; //数组指针
	public static int LENGTH = 6;//两个传入数组的大小
//	public static int xi = 386;//需要依赖的xi值
	final double e = 0.0000001;//permitted error range
//	public static double r;//the required r
//	public static double yk;//the required yk
	
	public static double b0;
	public static double b1;
	public static double b2;
	public static double b3;
	
	public static double value;
	public static double[] a;
	public static double[] b;
//	
	public static double[] w_list;
	public static double[] x_list;
	public static double[] y_list;
	public static double[] z_list;
	
	public static double[][] matrix = new double[4][5];
	
	public static int added;
	public static int reused;
	public static int modified;
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
//	public void calculate_correlation(double[] a, double[] b){
//		double x_avg = calculate_mean(a);
////		System.out.println(x_avg);
//		double y_avg = calculate_mean(b);
////		System.out.println(y_avg);
//		double result1 = prepare_for_beit(a,b,x_avg,y_avg)/prepare_for_beit(a,a,x_avg,x_avg);
//		double result0 = y_avg - result1*x_avg;
//		System.out.println();
//		b0 = result0;
//		b1 = result1;
//		double nom = (LENGTH*calculate_sum(multiply_array(a,b))-calculate_sum(a)*calculate_sum(b));
//		double denom = Math.pow(prepare_for_denom(a)*prepare_for_denom(b),0.5);
//		double result = nom/denom;
////		System.out.println(nom);
////		System.out.println(denom);
//		r = result;
////		System.out.println(result);
////		System.out.println(result*result);
//		yk = result0+result1*xi ;
//	}
//	// end
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
	/**
	 * calculate significance
	 * @return result 
	 */
//	public double cal_sig(){
//		double result = 0;
//		double temp_r = r;
//		if(r<0)
//			temp_r = -r;
//		double temp = temp_r*Math.pow(LENGTH - 2, 0.5)/Math.pow(1-r*r, 0.5);
//		double p = go(temp,LENGTH-2);
//		result = 1-2*p;
//		return result;
//	}
	// end
	// method cal_range
	/**
	 * calculate range
	 * @return range
	 */
	public double cal_range(){
		
		double result = 0;
		guess(0.35,LENGTH-4);
		double[] temp = new double[LENGTH];
		for(int i = 0 ; i < LENGTH ; i ++)
			temp[i] = Math.pow(z_list[i] - b0 - b1*w_list[i] - b2*x_list[i] -b3*y_list[i],2);
		double sum = calculate_sum(temp);
		double dev = Math.pow(sum/(LENGTH-4),0.5);
		
		double x_avg = calculate_mean(x_list);
		for(int i = 0 ; i < LENGTH ; i++){
			temp[i] = Math.pow(x_list[i] - x_avg,2);
		}
		double x_sum = calculate_sum(temp);
		
		double y_avg = calculate_mean(y_list);
		for(int i = 0 ; i < LENGTH ; i++){
			temp[i] = Math.pow(y_list[i] - y_avg,2);
		}
		double y_sum = calculate_sum(temp);
		
		double w_avg = calculate_mean(w_list);
		for(int i = 0 ; i < LENGTH ; i++){
			temp[i] = Math.pow(w_list[i] - w_avg,2);
		}
		double w_sum = calculate_sum(temp);
//		System.out.println(Math.pow(added-w_avg,2) + " " + Math.pow(reused-x_avg,2) + " "+ Math.pow(modified-y_avg,2));
		double last = Math.pow(1+1.0/LENGTH+Math.pow(reused-x_avg,2)/x_sum+Math.pow(modified-y_avg,2)/y_sum+Math.pow(added-w_avg,2)/w_sum, 0.5);
		
		result = value*dev*last;
//		System.out.println("value"+value);
//		System.out.println("dev"+dev);
		return result;
	}
	// end

	// method gauss
	/**
	 * calculate the results of b0, b1, b2, b3
	 */
	public void gauss(){
		//cal_line
		
			double[] temp1=new double[5];
			for(int i=1;i<5;i++){
			matrix[1][i]=matrix[1][i]-(matrix[1][0]/matrix[0][0])*matrix[0][i];
			}
			
			for(int i=1;i<5;i++){
			temp1[i]=matrix[2][i]-(matrix[2][0]/matrix[0][0])*matrix[0][i];
			}
			
			for(int i=2;i<5;i++){
				matrix[2][i]=temp1[i]-(temp1[1]/matrix[1][1])*matrix[1][i];
//				System.out.println(matrix[2][i]);
			}

			double[] temp2=new double[5];
			double[] temp3=new double[5];
			for(int i=1;i<5;i++){
				temp2[i]=matrix[3][i]-(matrix[3][0]/matrix[0][0])*matrix[0][i];
			}

			for(int i=2;i<5;i++){
				temp3[i]=temp2[i]-(temp2[1]/matrix[1][1])*matrix[1][i];
			}
			
			for(int i=3;i<5;i++){
				matrix[3][i]=temp3[i]-(temp3[2]/matrix[2][2])*matrix[2][i];
			}
			
			matrix[1][0]=0;
			matrix[2][0]=0;
			matrix[2][1]=0;
			matrix[3][0]=0;
			matrix[3][1]=0;
			matrix[3][2]=0;
			
		
		//method_end
//		int count = 0;
//		while(count < 4){
//		searchMax(count);
////		reduce(matrix[count],count);
//		 for(int i = 0 ; i < 4 ; i ++){
//			 for(int j = 0 ; j < 5 ; j++){
//		 System.out.print(matrix[i][j]+" ");
//			 }
//		 System.out.println();
//		 }
//
//		for(int i = count+1 ; i < 4; i++){
////			System.out.println(matrix[count][count]);
//			double co = matrix[i][count]/matrix[count][count];
//			double[] temp = new double[5];
//			for(int j = 0 ; j < 5 ; j ++){
//				temp[j] = matrix[count][j]*(-1)*co;
//				matrix[i][j] = matrix[i][j] + temp[j];
//			}
//			
//		}
//		count++;
//		System.out.println(count);
//		 for(int i = 0 ; i < 4 ; i ++){
//			 for(int j = 0 ; j < 5 ; j++){
//		 System.out.print(matrix[i][j]+" ");
//			 }
//		 System.out.println();
//		 }
//System.out.println();
		

//		}
		
//		 for(int i = 0 ; i < 4 ; i ++){
//			 for(int j = 0 ; j < 5 ; j++){
//		 System.out.print(matrix[i][j]+" ");
//			 }
//		 System.out.println();
//		 }
//	 System.exit(0);
//		}
			b3 = matrix[3][4]/matrix[3][3];
			b2 = (matrix[2][4] - matrix[2][3]*b3)/matrix[2][2];
			b1 = (matrix[1][4] - matrix[1][3]*b3 - matrix[1][2]*b2)/matrix[1][1];
			b0 = (matrix[0][4] - matrix[0][3]*b3 - matrix[0][2]*b2 - matrix[0][1]*b1)/matrix[0][0];
		
	}
	// end
	
	// method searchMax
	/**
	 * Find the equation with the largest absolute coefficient in the pivot column and do the exchange  
	 */
	public void searchMax(int col){
		double max = 0;
		int index = 0;
		for(int i = col ; i < 4 ; i++){
			if(matrix[i][col] > max){
				index = i; 
				max = matrix[i][col];
			}
		}
		
//		System.out.println(index);
		if(index != 0){
			exchange(matrix[col],matrix[index]);
		}
//		 for(int i = 0 ; i < 4 ; i ++){
//			 for(int j = 0 ; j < 5 ; j++){
//		 System.out.print(matrix[i][j]+" ");
//			 }
//		 System.out.println();
//		 }
//		 System.exit(0);

	}
	// end
	
	// method exchange
	/**
	 * exchange the two matrixes
	 * @param a a certain row in the matrix
	 * @param b a certain row in the matrix
	 */
	public void exchange(double[] a,double[] b){
		for(int i = 0 ; i < a.length ; i++){
			a[i] = b[i] + a[i];
			b[i] = a[i] - b[i];
			a[i] = a[i] - b[i];
		}

		
	}
	// end
	
	// method 
	  /**
	   * Reduce the coefficient for the pivot term to 1 by multiplying through by the reciprocal of the coefficient.
	 * @param a a certain row in the matrix
	 * 
	 */
//	public void reduce(double[]a,int count){
//		  if(a[count] != 1){
//			  double temp = a[count];
//			  for(int i = count ; i < a.length ; i++)
//				  a[i] = a[i]/temp;
//			 
//		  }
		  
//			 for(int i = 0 ; i < 4 ; i ++){
//				 for(int j = 0 ; j < 5 ; j++){
//			 System.out.print(matrix[i][j]+" ");
//				 }
//			 System.out.println();
//			 }
//			 System.exit(0);

//	  }
	  // end
	// method check
	/**
	 * @return the validity to quit and get the result
	 */
//	public boolean check(){
////		 for(int i = 0 ; i < 4 ; i ++){
////			 for(int j = 0 ; j < 5 ; j++){
////		 System.out.print(matrix[i][j]+" ");
////			 }
////		 System.out.println();
////		 }
//
//		boolean result = false;
//		for(int i = 0 ; i < 4 ; i++){
//			for(int j = 0 ; j <i  ; j++){
//				if(matrix[i][j] != 0)
//					return false;
//			}
//			
//		}
//			result = true;
//		return result;
//	}
	// end
	/**
	 * calculate the required data
	 * @param path1 第一个文件路径
	 * @param path2 第二个文件路径
	 * @param path3 第三个文件路径
	 * @param path4 第四个文件路径
	 * @param added 增加的行数
	 * @param reused 复用的行数
	 * @param modified 更改的行数
	 */
	public void go(String path1,String path2,String path3, String path4,int added_t,int reused_t,int modified_t){


		added = added_t;
		reused = reused_t;
		modified = modified_t;
		
		 w_list = read_file(in,path1);
		 x_list = read_file(in,path2);
		 y_list = read_file(in,path3);
		 z_list = read_file(in,path4);
		 	
		 //initialize the matrix
		 matrix[0][0] = LENGTH;
		 matrix[1][0] = matrix[0][1] = calculate_sum(w_list);
		 matrix[2][0] = matrix[0][2] = calculate_sum(x_list);
		 matrix[3][0] = matrix[0][3] = calculate_sum(y_list);
		 
		 
		 matrix[1][1] = calculate_sum(multiply_array(w_list,w_list));
		 matrix[2][2] = calculate_sum(multiply_array(x_list,x_list));
		 matrix[3][3] = calculate_sum(multiply_array(y_list,y_list));
		 
		 matrix[1][2] = matrix[2][1] = calculate_sum(multiply_array(w_list,x_list));
		 matrix[1][3] = matrix[3][1] = calculate_sum(multiply_array(w_list,y_list));
		 matrix[2][3] = matrix[3][2] = calculate_sum(multiply_array(x_list,y_list));
		 
		 matrix[0][4] = calculate_sum(z_list);
		 matrix[1][4] = calculate_sum(multiply_array(w_list,z_list));
		 matrix[2][4] = calculate_sum(multiply_array(x_list,z_list));
		 matrix[3][4] = calculate_sum(multiply_array(y_list,z_list));
		 
		 //do gauss
		 gauss();
//		 for(int i = 0 ; i < 4 ; i ++){
//			 for(int j = 0 ; j < 5 ; j++){
//		 System.out.print(matrix[i][j]+" ");
//			 }
//		 System.out.println();
//		 }
//System.exit(0);
		 double z = b0 + added*b1 + reused*b2 + modified * b3;
		double temp = cal_range();

		System.out.println(b0);
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(z);
		
		System.out.println(z+temp);
		System.out.println(z-temp);
//		
		
	}
	// end

}
