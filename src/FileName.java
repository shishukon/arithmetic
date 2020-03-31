
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileName {
	/*public static void main(String[] args) {
		try
		{
			File writeName = new File("src/四则运算/Exercises.txt");
			BufferedWriter out1 = new BufferedWriter(new FileWriter(writeName));
			out1.write("Hello Word!\n");
			out1.flush();
			out1.close();
			BufferedReader out2 = new BufferedReader(new FileReader(writeName));
			String str = out2.readLine();
			out2.close();
			System.out.println(str);



		} catch (IOException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}*/

    String[] Exercises = new String[100];//用于存放题目的名字
    String[] Answers = new String[100];//用于存放题目的名字
    int i = 0;
    int j = 0;
    FileName(){//将题目文件存入数组
        File file = new File("Exercises");
        String[] list = file.list();
        assert list != null;
        for (String string : list) {
                Exercises[i] = string;//将题目文件名字放入字符串数组
                i ++;
        }

        //将答案文件存入数组

        File file2 = new File("Answers");
        String[] list2 = file2.list();
        assert list2 != null;
        for (String string : list2) {
            if(string.substring(string.length() - 3).equals("txt")){
                Answers[j] = string;//将答案文件名字放入字符串数组
                j ++;}
        }
    }

    void get_ExercisesName(){
        i = 0;
        File file = new File("Exercises");
        String[] list = file.list();
        assert list != null;
        for (String string : list) {
            Exercises[i] = string;//将题目文件名字放入字符串数组
            i ++;
        }
        for(int i = 0;Exercises[i]!=null; i++ )
            System.out.println(Exercises[i]);
    }


    void get_AnswersName(){
        for(int i = 0;Answers[i]!=null; i++ )
            System.out.println(Answers[i]);
    }

}



//    public static void main(String[] args) {
//
//        System.out.println("\n列出D:/1路径下的所有文件和文件夹:");
//        File file = new File("F:\\project_class\\arithmetic\\src");
//        String[] list = file.list();
//        assert list != null;
//        for (String string : list) {//迭代出来的都是文件名
//            System.out.println(string);
//        }
//
//        System.out.println("\n列出D:/1路径下的所有文件和文件夹的绝对路径:");
//        File[] listFiles = file.listFiles();
//        for (File file2 : listFiles) {//迭代出来的都是文件的绝对路径
//            System.out.println(file2);
//        }
//    }