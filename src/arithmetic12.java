public class arithmetic12 {
    public static void main(String[] args){
        Arithmetic test = new Arithmetic();
        int range = 10;

        for(int j = 0; j<=10;j++){
            test.getArithmetic(range);
            //下面全部是用来组合字符串的
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<test.arithmetic.length;i++){
                if(test.arithmetic[i]!=null)
                    sb.append(test.arithmetic[i]);
            }
            String sb1 = sb.toString();
            System.out.println(sb1);
        }

    }
}

class Arithmetic{
    public String[] all_symbol = new String[]{"+", "-", "*", "/"};
    public String[] arithmetic = new String[7];
    public int number_parameter;

    public void getArithmetic(int range){
        number_parameter = (int) (Math.random()*4);//这个算式有几个符号
        String[] new_arithmeti = new String[7];//1、3、5为符号位
        //存入数字和符号
        new_arithmeti[0] = String.valueOf((int)(Math.random()*(range + 1)));
        new_arithmeti[2] = String.valueOf((int)(Math.random()*(range + 1)));
        new_arithmeti[1] = all_symbol[(int)(Math.random()*(3 + 1))];

        //如果是两个算数符号
        if(number_parameter == 2){
            new_arithmeti[3] = all_symbol[(int)(Math.random()*(3 + 1))];//第二个符号
            new_arithmeti[4] = String.valueOf((int)(Math.random()*(range + 1)));//第三个计算数
        }

        //如果是三个算数符号
        else if (number_parameter == 3){

            if(new_arithmeti[1].equals("+") || new_arithmeti[1].equals("-")){
                new_arithmeti[0] = "(" + new_arithmeti[0];
                new_arithmeti[2] = new_arithmeti[2] + ')';
                //加括号，但还没做格式化
            }

            new_arithmeti[3] =  all_symbol[(int)(Math.random()*(3 + 1))];//第二个符号
            new_arithmeti[5] =  all_symbol[(int)(Math.random()*(3 + 1))];//都三个符号
            new_arithmeti[4] = String.valueOf((int)(Math.random()*(range + 1)));//第三个计算数
            new_arithmeti[6] = String.valueOf((int)(Math.random()*(range + 1)));//第四个计算数

            if(new_arithmeti[5].equals("+") || new_arithmeti[5].equals("-")){
                new_arithmeti[4] = "(" + new_arithmeti[4];
                new_arithmeti[6] = new_arithmeti[6] + ')';
                //加括号，但还没做格式化
            }
        }


        this.arithmetic = new_arithmeti;
    }

}