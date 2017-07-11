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
	path = "src/Count.java";
	count = new Count();
	count.count_all(path);
	count.count_method(path);

}
}
