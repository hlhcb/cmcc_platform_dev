import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author:terry
 * @create: 2023-12-23 14:53
 * @Description: 波兰表达式
 */
public class PolishNotation {

    public static int evalRPN(ArrayList<String> list) {
        int[] arr = new int[]{1,2,3};
//        int arr[] = new int[3];
        String exrepssStr = "";
        // 创建一个栈，用于辅助计算
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            // 正则表达式匹配多位数字
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {

                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num2 - num1;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("表达式符号有误！");
                }

                if ("" == exrepssStr) {
                    exrepssStr = String.format("(%d %s %d)",num2,item, num1);
                } else {
                    exrepssStr = String.format("(%d %s %s)",num2,item,exrepssStr );
                }
                // res为int类型需转为String后入栈
                stack.push("" + res);
            }
        }
        // 返回计算结果，即最终留在栈中的数
        String result = stack.pop();
        System.out.println("输出："+result);
        System.out.println("解释："+exrepssStr +" = " + result);
        return  Integer.parseInt(result);
    }


    public static ArrayList<String> getList(String polishNotation) {
        // 用d逗号分割表达式，并将数字与运算符存入字符串数组
        String[] split = polishNotation.split(",");
        ArrayList<String> list = new ArrayList<>();
        for (String item : split) {
            item = item.replaceAll("\"", "");
            list.add(item);
        }
        return list;
    }

    public static void main(String[] args) {

//        ArrayList<String> list = Arrays.asList("","","","","");

        String polishNotation = "\"2\",\"1\",\"+\",\"3\",\"*\"";
        ArrayList<String> list = getList(polishNotation);

        System.out.println("输入：["+polishNotation+"]");
        evalRPN(list);
//        System.out.println("输出："+evalRPN(list));


        String polishNotation1 = "\"4\",\"13\",\"5\",\"/\",\"+\"";
        ArrayList<String> list1 = getList(polishNotation1);

        System.out.println("输入：["+polishNotation1+"]");
        evalRPN(list1);
//        System.out.println("输出："+evalRPN(list1));

    }

}
