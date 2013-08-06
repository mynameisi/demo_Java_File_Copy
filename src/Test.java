import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class Test {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		copy_3("./1/1.txt", "./1/11.txt");
	}
	/**
	 * 这是一个灰常基础，同时也灰常满的文件拷贝方法</br>
	 * 这个方法只用了最基础的两个文件字节流IO类：</br>
	 * 1. 文件输入字节流 FileInputStream
	 * 2. 文件输出字节流 FileOutputStream
	 * 使用的思想是：
	 * 用输入流的read方法，一点(32位)一点的读一个文件，一直到把文件读完</br>
	 * 在读的过程中，把每一次读到的那一点儿内容写出到目标文件中
	 * 
	 * @param f1  拷贝源头文件
	 * @param f2 拷贝目标文件
	 *  
	 * @throws IOException
	 */
	public static void copy_0(String f1, String f2) {
		File A = new File(f1); //1.	准备好复制源：文件A
		File B = new File(f2); //2.	准备好复制目标：文件B
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(A); //3.	准备好我们的眼睛：输入流InputStream对象in
			out = new FileOutputStream(B); //4.	准备好我们的笔：输出流OutputStream对象out
			while (true) { //5.	循环
				int data = in.read(); //a.	用in读取A中的8位内容，读到计算机中
				if (data == -1) { //b.	如果读到的内容不是文章的结尾(-1)，
									//      读到的内容就有效，否则就退出循环
					break;
				}
				out.write(data); //c.	读到的内容有效，用out把读到的8位内容写到B中
			}
			//in.close(); 					
			//out.close(); 					
		} catch (IOException e) {
			System.out.println("文件拷贝操作出现异常");
		} finally {
			try {
				in.close(); //6.	关闭输入流
				out.close(); //7.    关闭输出流
			} catch (Exception e) {
				System.out.println("关闭流时产生异常" + e.getMessage());
			}
		}
	}
	public static void copy_1(String from, String to) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			InputStream inFile = new FileInputStream(from);
			in = new BufferedInputStream(inFile);
			OutputStream outFile = new FileOutputStream(to);
			out = new BufferedOutputStream(outFile);
			while (true) {
				int data = in.read();
				if (data == -1) {
					break;
				}
				out.write(data);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
	public static void copy_2(String from, String to) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(from); //1. 直接通过文件名构建文件输入流
			out = new FileOutputStream(to); //2. 直接通过文件名构建文件输出流	
											//3. 构建缓存
			int length = in.available(); //  a. 获得文件还剩余多少byte的信息	
			byte[] bytes = new byte[length]; //  b. 按照获得长度，一次性的构建一个byte数组
			in.read(bytes); //4. 把文件全部内容到byte数组中
			out.write(bytes); //5. 把这个数组中所有内容一下子写出去
		} catch (IOException e) {
			System.out.println("文件拷贝操作出现异常");
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e) {
				System.out.println("关闭流时产生异常" + e.getMessage());
			}
		}
	}
	
	static byte[] buffer = new byte[100000];	   //0   声明一个1M的缓存
	
	public static void copy_3(String from, String to) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(from);			//1. 直接通过文件名构建文件输入流
			out = new FileOutputStream(to);			//2. 直接通过文件名构建文件输出流
			while (true) {							//3.	循环
				//byte[] buffer = new byte[100000];	//  a   声明一个1M的缓存
				int amountRead = in.read(buffer);	//  b.	用in读取A中最多1M内容，读到计算机中
				if (amountRead == -1) {				//  c.	如果读到的内容不是文章的结尾(-1)
					break;							//      读到的内容就有效，否则就退出循环
				}
				out.write(buffer, 0, amountRead);   //  d.  读到的内容有效，用out把读到的最多1M内容写到B中
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
