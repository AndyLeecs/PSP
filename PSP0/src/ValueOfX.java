

/**     
* @author 李安迪
* @date 2017年7月16日
* @description Find the value of x for which integrating the t function from 0 to x gives a result of p.
*/
public class ValueOfX {
	final double e = 0.0000001;//permitted error range
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
			System.out.println(x);
			return 0;
		}
		else if(go(x,dof) - p > e)
			return 1;
		else 
			return -1;
		

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
		if(x == 0)
			return 0;
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
}
