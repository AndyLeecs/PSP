import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Scanner;


public class mean_and_standard_deviation {
	public static Scanner in;
	public static double[] array;
	public static final int LENGTH = 10;
	public static int count;
	public static String utl;
	public static DecimalFormat df = new DecimalFormat(".##");

public static void main(String args[]){
		array = read_file(in,"src/column1.txt");
		double mean = calculate_mean(array);
		
		System.out.println(df.format(mean)+" "+df.format(calculate_deviation(mean,array)));
		array = read_file(in,"src\\column2.txt");
		mean = calculate_mean(array);
		System.out.println(df.format(mean)+" "+df.format(calculate_deviation(mean,array)));
}


public static double[] read_file(Scanner in,String utl){
	try {
		in = new Scanner(new BufferedReader(new FileReader(new File(utl))));
		array = new double[LENGTH];
		for(int i = 0 ; i < LENGTH ; i ++){
			array[i] = in.nextDouble();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return array;
}

public static double calculate_mean(double[] array){
	double sum = 0;
	double result;
	
	for(int i = 0 ; i < LENGTH ; i++){
		sum+= array[i];
		
	}
	
	result = sum/LENGTH;
	return result;
}

public static double calculate_deviation(double mean , double[] array){
	double sum = 0;
	double result;
	
	for(int i = 0 ; i < LENGTH ; i++){
//		System.out.println(array[i]);
		sum+= Math.pow(array[i]-mean,2);
//		System.out.println(sum);
	}
	
//	System.out.println(sum);
	result = Math.pow(sum/(LENGTH-1), 0.5);
	return result;
}
}

