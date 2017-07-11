import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 李安迪
 * @date 2017年7月11日
 * @description 计算一个文件中的代码行数，方法个数，每个方法的代码行数
 */
// class Count
class Count {
	int total_lines;// 总行数
	int method_count;// 方法数
	String[] method_names;// 方法的名字
	int[] method_lines;// 方法内的行数

	// method count_all

	/**
	 * 统计文件中的总行数和方法数
	 * 
	 * @param path
	 *            文件路径
	 */
	public void count_all(String path) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(path));
			String line;
			while ((line = bf.readLine()) != null) {
				line = line.trim();
				// 如果是方法行开始提示，方法行加一
				if (line.trim().startsWith("// method")) {
					method_count++;
				}
				// 如果是类行开始提示，打印类名称
				if (line.trim().startsWith("// class")) {
					System.out.println(line.trim().replaceAll(" ", "").replaceAll("//class", ""));
				}
				// 如果不是空行和注释行，总行数加一
				if (line.trim() != null && (!line.startsWith("/") && (!line.startsWith("*")))) {
					total_lines++;
				}

				// 初始化方法名和方法内行数的数组
				method_names = new String[method_count];
				method_lines = new int[method_count];
			}
			// 关闭reader
			bf.close();

			// 打印结果
			System.out.println("total lines" + total_lines);
			System.out.println("method_count" + method_count);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// method count_method

	/**
	 * 统计方法中的总行数和方法名
	 * 
	 * @param path
	 *            文件路径
	 */
	public void count_method(String path) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(path));
			String line;
			int index = -1;// 目前处理到的方法的索引
			boolean isMethod = false;// 判断目前处理的是否是方法内部的代码

			while ((line = bf.readLine()) != null) {
				line = line.trim();
				// 如果是方法行开始提示，方法行加一
				if (line.trim().startsWith("// method")) {
					index++;
					isMethod = true;
					method_names[index] = line.replaceAll(" ", "").replaceAll("//method", "");

				}

				// 如果方法结束，isMethod置为true
				if (line.trim().startsWith("// end")) {
					isMethod = false;
				}
				// 如果不是空行和注释行，总行数加一
				if (line.trim() != null && (line.trim().length() > 0)
						&& (!line.startsWith("/") && (!line.startsWith("*")) && (isMethod))) {
					// System.out.println(line);
					method_lines[index]++;
				}

			}
			// 关闭reader
			bf.close();
			// 打印结果
			for (int i = 0; i < method_names.length; i++) {
				System.out.println(method_names[i]);
				System.out.println(method_lines[i]);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
// class end