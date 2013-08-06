

public class FinallyPractice {
	public static void main(String[] args) {
		int[] i=new int[2];
		try{
			System.out.println("1");
			i[2]=2;
		}catch(Exception e){
			System.out.println("3");
		}finally{
			System.out.println("2");
		}
	}
}


