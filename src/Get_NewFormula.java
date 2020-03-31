import java.io.File;
import java.io.IOException;

class Get_NewFormula {
    Get_NewFormula(int range,int number_of_questions) throws IOException {
        FileOperate file;
        get_Formula formula;
        int i;
        String[] Exercises = new String[10000];//用于存放题目的名字
        String[] Answers = new String[10000];//用于存放答案的名字
        for(i = 0 ;i<number_of_questions;i++){
        formula = new get_Formula(range);
        Exercises[i] = formula.formula;
        Answers[i] = formula.answer;
        for (int j = 0;j < i;j++){//比对式子是否一样，先从答案是否一样判断，若是，则进一步判断式子的长度是否一样，若是，则模糊地认为这两个式子一样，则重新生成式子并重新判断
            if (Answers[i].equals(Answers[j])){
                if (Exercises[i].length() == Exercises[j].length())
                    do {
                        formula = new get_Formula(range);
                        Exercises[i] = formula.formula;
                        Answers[i] = formula.answer;
                    }while(Exercises[i].length() != Exercises[j].length());
            }
        }
      //  System.out.println(formula.formula+ "=" + formula.answer);//answer是有分数时的值跟value是没有分数时的值
       }
        FileName name = new FileName();
        File writename1 = new File("./Exercises/Exercises" + (name.i+1) + ".txt");
        writename1.createNewFile();
        name.Exercises[name.i] = "Exercises" + (name.i+1) + ".txt";
        File writename2 = new File("./Answers/Answers" + (name.j+1) + ".txt");
        writename2.createNewFile();
        name.Answers[name.j] = "Answers" + (name.j+1) + ".txt";
        /*file = */new FileOperate(Exercises,Answers,"Exercises" + (name.i+1) + ".txt","Answers" + (name.j+1) + ".txt",i);
//        file = new FileOperate("Exercises1.txt","Answers1.txt");

    }
}


//随机数公式(int) (Math.random()*(max-min)+min)
class get_Formula{  //用于组合两个算式

    int value;
 //   String answer;
    String formula,answer;
    Formula f1;
    Formula f2;
    char symbol;
    int swap_or_not = 0;                            //是否出现负数需要调换
    public char[] all_symbol = new char[]{'+', '-', '*', '/'};

     get_Formula(int range){
        this.f1 = new Formula(range);
        this.f2 = new Formula(range);
        symbol = all_symbol[(int) (Math.random()*4)];
        if (f1.fraction_or_not == 0 && f2.fraction_or_not == 0){    //都不是分式
            if(f2.value==0 && symbol=='/'){
                do {
                    this.f2 = new Formula(range);
                }while (f2.value != 0);
            }             //除数为0
            else{
                if (symbol=='/' && f1.value % f2.value != 0){       //两个式子不能整除时
                    answer = f1.fraction(f1.value,f2.value);//补充个标记
                    formula = this.f1.formula + symbol + this.f2.formula;
                } else{                                             //普通情况
                    value = f1.get_value(f1.value, f2.value, symbol); //得到答案，仅仅是整数   （之后分式功能写完后要分分式的运算和整数的运算）

                    if(value < 0){                 //结果为负数，答案取反，调换两个算式的位置
                        value = -value;
                        answer = "" + value;
                        formula = this.f2.formula + symbol + this.f1.formula;
                    } else{
                        answer = "" + value;
                        formula = this.f1.formula + symbol + this.f2.formula;
                    }
                }
            }
        }else if (f1.fraction_or_not == 1 && f2.fraction_or_not == 1){  //两个都是分式的情况
            if (symbol == '-' && f1.value1 * f2.value2 < f2.value1 * f1.value2){
                formula = this.f2.formula + symbol + this.f1.formula;
            }else {
                formula = this.f1.formula + symbol + this.f2.formula;
            }
            answer = f1.get_answer(f1.value1,f1.value2,f2.value1,f2.value2,symbol);
        }else if (f1.fraction_or_not == 1){
            if (symbol == '-' && f1.value1 * 1 < f2.value * f1.value2){
                formula = this.f2.formula + symbol + this.f1.formula;
            }else {
                formula = this.f1.formula + symbol + this.f2.formula;
            }
            answer = f1.get_answer(f1.value1,f1.value2,f2.value,1,symbol);
        }else{
            if (symbol == '-' && f1.value * f2.value2 < f2.value1 * 1){
                formula = this.f2.formula + symbol + this.f1.formula;
            }else {
                formula = this.f1.formula + symbol + this.f2.formula;
            }
            answer = f1.get_answer(f1.value,1,f2.value1,f2.value2,symbol);
        }

     }
}



class Formula{
    int value;  //数的和，单个数的时候是本身，两个数的时候是两数的运算结果
    int value1; //两个数的时候第一个数
    int value2; //两个数的时候第二个数
    int isFu = 0;//isFu是标志是否负数。
    String formula,fenShu;//fenShu用来存放分数值，formula用来存放算式
    char symbol;
    int swap_or_not = 0;                            //是否出现负数需要调换
    int fraction_or_not = 0;                        //是否是分式
    public char[] all_symbol = new char[]{'+', '-', '*', '/'};

    public  Formula(int range){
        if((Math.random()*2) <=1){            //只有一数的情况
            value = (int)(Math.random()*(range-1)+1);
            formula = String.valueOf(value);
            symbol = '\0';
        }
        else{
            value1 = (int)(Math.random()*range + 1);
            value2 = (int)(Math.random()*range + 1);//两个数的情况
            symbol = all_symbol[(int) (Math.random()*4)];
            if (symbol == '/' && value1 % value2 != 0){
                fraction_or_not = 1;
                fenShu = fraction(value1 ,value2);
            }else
                value = get_value(value1, value2, symbol);
            if (swap_or_not == 1){
                value = -value;
                formula = "(" + value2 + symbol + value1 + ")";
            }else
                formula = "(" + value1 + symbol + value2 + ")";
            /*if(symbol == '+'||symbol == '-'){        //加减，需要加括号
                if (swap_or_not == 1){
                    value = -value;
                    formula = "(" + value2 + symbol + value1 + ")";}
                else
                    formula = "(" + value1 + symbol + value2 + ")";
            }
            else                                      //乘除
                formula =   String.valueOf(value1) + symbol + value2 ;*/

        }

    }

    public int get_value(int a,int b, char symbol){
        switch (symbol) {
            case '+':
                return a + b;
            case '-':
                if(a<b)
                    swap_or_not = 1;
                return a - b;
            case '/':
                /*if (a % b > 0 || a / b == 0) {
                   // fraction_or_not = 1;//是分式，通过分式类存储
                    //this.fraction[0] = a;     示例
                    //this.fraction[1] = b;
                    return 0;
                }*/
                return a / b;
            case '*':
                return a * b;
        }
        return 0;
    }

    public String get_answer(int a,int b,int c, int d,char symbol){
        int fenZi,fenMu;
        switch (symbol) {
            case '+':
                fenZi = a * d + b * c;
                fenMu = b * d;
                return fraction(fenZi,fenMu);
            case '-':
                fenZi = a * d - b * c;
                fenMu = b * d;
                if(fenZi<0) {
                    isFu = 1;
                    return fraction(-fenZi,fenMu);
                }
                return fraction(fenZi,fenMu);

            case '/':
                fenZi = a * d;
                fenMu = b * c;
                return fraction(fenZi,fenMu);
            case '*':
                fenZi = a * c;
                fenMu = b * d;
                return fraction(fenZi,fenMu);
        }
        return null;
    }


    public String fraction(int a,int b){  //处理分数
        int zhengShu = a / b, fenZi = a - zhengShu * b,i,maxYinShu = 1;
        for (i = 1;i <= fenZi;i++){//找最大公因数,化简分数
            if (fenZi % i == 0 && b % i == 0)
                maxYinShu = i;
        }
        String fenShu;
        if (zhengShu == 0){
            if (fenZi == 0)
                fenShu = String.valueOf(0);
            else
                fenShu = String.valueOf(fenZi / maxYinShu) + '/' +String.valueOf(b / maxYinShu);
        } else{
            if (fenZi == 0)
                fenShu = String.valueOf(zhengShu);
            else
                fenShu = String.valueOf(zhengShu) + '’' + String.valueOf(fenZi / maxYinShu) + '/' +String.valueOf(b / maxYinShu);
        }
        return fenShu;
    }

}