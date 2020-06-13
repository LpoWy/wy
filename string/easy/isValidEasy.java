package leetcode.string.easy;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
// 注意空字符串可被认为是有效字符串。
//
// 示例 1:
//
// 输入: "()"
//输出: true
//
//
// 示例 2:
//
// 输入: "()[]{}"
//输出: true
//
//
// 示例 3:
//
// 输入: "(]"
//输出: false
//
//
// 示例 4:
//
// 输入: "([)]"
//输出: false
//
//
// 示例 5:
//
// 输入: "{[]}"
//输出: true
// Related Topics 栈 字符串

import java.util.Stack;

public class isValidEasy {
    //思想: 结果只有两种, 要么对, 要么错. 仔细观察对的, 寻找出等价特点即可, 剩下的就是错的

    //1.消消乐法: 对称, (最中央的括号总是成对的), 消掉中央这对, 新的中央还是可以消掉, 从中间开始消消乐

    /**
     * @Author steven
     * @Description
     * @Date 10:36 2020/6/13
     * @Param [s]
     * @return boolean
     **/
    public static boolean isValidBracket1(String s) {
        if (null == s) {
            return false;
        } else if (!(s.contains("()") || s.contains("{}") || s.contains("[]"))) {  //必须含
            return false;
        } else if ((s.length() % 2) > 0) { //必须是偶数
            return false;
        }
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", ""); //注意String是不可变类, 必须重新赋值
            s = s.replace("{}", ""); //如果这里的replace行数比较多, 那考虑写elseif
            s = s.replace("[]", "");
        }
            return s.isEmpty();  //注意, 优先使用Java核心类库的方法, 不要自己 equals 甚至==
        }

    //2.for循环法 : 对称的,  很容易看到 s.i == left && s.(s.length-i) == right 必须成立 相当于从两端开始消消乐

    //3. 我们能不能从一端开始消消乐呢, 如果可以, 数据结构就可以使用先进先出的栈了
    // 假如 {(([]))}  这其实是个"假对称", 数据的对称是应该长这样子的 }))]]))}
    /**
     * @Author steven
     * @Description
     * @Date 10:37 2020/6/13
     * @Param [s]
     * @return boolean
     **/
    public static boolean isValidBracket2(String s) {
        if (null == s) {
            return false;
        } else if (!(s.contains("()") || s.contains("{}") || s.contains("[]"))) {  //必须含
            return false;
        } else if ((s.length() % 2) > 0) { //必须是偶数
            return false;
        }
        Stack<Character>stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(c=='(')stack.push(')');
            else if(c=='[')stack.push(']');
            else if(c=='{')stack.push('}');
            else if (stack.isEmpty() || c != stack.pop()) { //移除并返回栈顶元素
                return false;
            }
        }
        return stack.isEmpty();
    }
}
