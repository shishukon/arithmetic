import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Instruction {
    public static void main(String[] args) throws IOException {
        int range = 0, number_of_questions = 0;
        String Exercises_path = "";
        String Answers_path = "";
        FileName question = new FileName();

        System.out.println("欢迎使用");
        System.out.println("当前已有的题目文件：");
        question.get_ExercisesName();
        //       question.get_AnswersName();
        System.out.println("_______________________________");

        System.out.println("指令示例：");
        System.out.println("指定生成题目的个数，示例：-n 10 ");
        System.out.println("指定题目中数值的范围，示例：-r 10");
        System.out.println("对给定的题目文件和答案文件，判定答案中的对错并进行数量统计，示例：-e <exercisefile>.txt -a <answerfile>.txt");

        while(true){
            System.out.println("_______________________________");
            System.out.println(" ");
            if(range == 0 || number_of_questions == 0){
                if (number_of_questions == 0 )
                    System.out.println("未输入数值范围");
                else
                    System.out.println("题目数量已记录，将生成题目数量为：" + number_of_questions);
                if (range == 0 )
                    System.out.println("未输入题目数量");
                else
                    System.out.println("数值范围已记录，数值的范围为：" + range);}

            else{
                System.out.println("生成题目ing...");
                //生成过程
                System.out.println("题目生成完毕，题目文件路径为：");

            }


            Scanner scanner = new Scanner(System.in); // 创建Scanner对象
            System.out.print("输入你的指令："); // 打印提示
            String arr = scanner.nextLine(); // 读取一行输入并获取字符串
            String [] instruction = arr.split("\\s+");//空格分割字符串

            switch (instruction[0]){
                case "-n":
                    number_of_questions = Integer.parseInt(instruction[1]);
                    break;
                case "-r":
                    range = Integer.parseInt(instruction[1]);
                    break;
                case "-e":
                    //-e Exercise1.txt -a Answers1.txt
                    Exercises_path = "Exercises\\" + instruction[1];
                    Answers_path = "Answers\\" + instruction[3];
                    File Exercises_file = new File(Exercises_path);//题目文件的路径
                    File Answers_file = new File(Answers_path);//答案文件的路径

                    Desktop.getDesktop().open(Answers_file);//打开文件，测试路径是否正确
                    break;

            }
        }
    }
}
