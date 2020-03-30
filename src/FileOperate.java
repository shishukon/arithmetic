import java.io.*;

public class FileOperate {//传式子跟答案进文件
    FileOperate(String[] formula,String[] answer,String exercisesName,String answersName,int j){
        try{
            File writeName1 = new File("./Exercises/" + exercisesName);
            BufferedWriter out1 = new BufferedWriter(new FileWriter(writeName1));
            File writeName2 = new File("./Answers/" + answersName);
            BufferedWriter out2 = new BufferedWriter(new FileWriter(writeName2));
            for (int i = 1;i <= j;i++){
                out1.write(i + "、" + formula[i-1] + "=" + "\n");
                out1.flush();
                out2.write(i + "、" + formula[i-1] + "=" + answer[i-1] + "\n");
                out2.flush();
            }
            out1.close();
            out2.close();
            /*BufferedReader out2 = new BufferedReader(new FileReader(writeName));
            String str = out2.readLine();
            out2.close();
            System.out.println(str);*/



        } catch (IOException e)
        {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    FileOperate(String exercisesName,String answersName) throws IOException {//取式子跟答案比对
        int i = 0;//用来记录第几道题
        int j = 0,t = 0;//j是正确的题数，t是错误的题数
        String w,q;
        String[] correct = new String[10000];
        String[] wrong = new String[10000];
        File writeName1 = new File("./Exercises/" + exercisesName);
        BufferedReader out1 = new BufferedReader(new FileReader(writeName1));
        File writeName2 = new File("./Answers/" + answersName);
        BufferedReader out2 = new BufferedReader(new FileReader(writeName2));
        while ((w = out1.readLine()) != null && (q = out2.readLine()) != null){
            i++;
            if (w.equals(q) && !w.equals("\n")){
                j++;
                correct[j-1] = String.valueOf(i);
            }else {
                t++;
                wrong[t-1] = String.valueOf(i);
            }
        }
        File writeName3= new File("./Grade/Grade.txt");
        BufferedWriter out3 = new BufferedWriter(new FileWriter(writeName3));
        out3.write("Correct:" + j + "(");
        out3.flush();
        for (int x = 0;x < j;x++){
            if (x != j-1)
                out3.write(correct[x] + ",");
            else
                out3.write(correct[x]);
            out3.flush();
        }
        out3.write(")" + "\n" + "Wrong:" + t + "(");
        out3.flush();
        for (int x = 0;x < t;x++){
            if (x != t-1)
                out3.write(wrong[x] + ",");
            else
                out3.write(wrong[x]);
            out3.flush();
        }
        out3.write(")" + "\n");
        out3.flush();
        out3.close();

    }

}
