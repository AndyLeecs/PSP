

/**     
* @author 李安迪
* @date 2017年7月15日
* @description calculate numerical integration
*/
// class Integration
public class Integration {
	
	// method go
	/**
	 * 
	 * @param x variable
	 * @param dof degrees of freedom
	 * @return numerical integration
	 */
	public void go(double x, double dof){
		double e = 0.00001;
		double num_seg = 10;
		while(((calp(x,dof,num_seg)-calp(x,dof,2*num_seg))>e) || ((calp(x,dof,num_seg)-calp(x,dof,2*num_seg))<-e)){
			num_seg = 2 * num_seg;
		}
		System.out.println( calp(x,dof,2*num_seg)+"");
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
}
