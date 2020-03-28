//没有考虑分数！！！！！！！！！！！！！！！！！！！！！！！！！！！！
//public class Get_NewFormula {
//    public static void main(String[] args){
//        for(int i =0 ;i<=10;i++){
//        get_Formula formula = new get_Formula(10);
//        System.out.println(formula.formula+ "=" + formula.value);
//       }
//    }
//}

//随机数公式(int) (Math.random()*(max-min)+min)
class get_Formula{  //用于组合两个算式

    int value;
 //   String answer;
    String formula;
    Formula f1;
    Formula f2;
    char symbol;
    int swap_or_not = 0;                            //是否出现负数需要调换
    public char[] all_symbol = new char[]{'+', '-', '*', '/'};

     get_Formula(int range){
        this.f1 = new Formula(range);
        this.f2 = new Formula(range);
        symbol = all_symbol[(int) (Math.random()*4)];
        if(f2.value==0&&symbol=='/')              //被除数为0
            formula = null;

        ///////////////////////////////////////////////////////////////////////////////
        //
        //f1或f2是分式的时运算
        //示例：
        //else if(f1.fraction_or_not == 1){ ....; }
        //
        ///////////////////////////////////////////////////////////////////////////////


        else{
            value = f1.get_value(f1.value, f2.value, symbol); //得到答案，仅仅是整数   （之后分式功能写完后要分分式的运算和整数的运算）

             if(value < 0){                 //结果为负数，答案取反，调换两个算式的位置
                 value = -value;
                 formula = this.f2.formula + symbol + this.f1.formula;
             }
            else
                formula = this.f1.formula + symbol + this.f2.formula;

    //        if (value > (int)value)
    //            answer = f1.value + "/" + f2.value;
    //        else
    //       answer = "" + value ;


        }
     }
}



class Formula{
    int value;  //数的和，单个数的时候是本身，两个数的时候是两数的运算结果
    int value1; //两个数的时候第一个数
    int value2; //两个数的时候第二个数
    String formula;
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
            value = get_value(value1, value2, symbol);
            if(symbol == '+'||symbol == '-'){        //加减，需要加括号
                if (swap_or_not == 1){
                    value = -value;
                    formula = "(" + value2 + symbol + value1 + ")";}
                else
                    formula = "(" + value1 + symbol + value2 + ")";
            }
            else                                      //乘除
                formula =   String.valueOf(value1) + symbol + value2 ;

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
                if (a % b > 0 || a / b == 0) {
                    fraction_or_not = 1;//是分式，通过分式类存储
                    //this.fraction[0] = a;     示例
                    //this.fraction[1] = b;
                    return 0;
                }
                return a / b;
            case '*':
                return a * b;
        }
        return 0;
    }

}