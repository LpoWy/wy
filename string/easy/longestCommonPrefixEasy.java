package leetcode.string.easy;

//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
// 示例 1:
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
//
//
// 示例 2:
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
//
//
// 说明:
//
// 所有输入只包含小写字母 a-z 。
// Related Topics 字符串
public class longestCommonPrefixEasy {
    //不断的缩小字符基串, 循环判断, 直到所有的字符串都包含这个基串
    public static String longestCommonPrefix1(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        String str = strs[0];
        //这是直接操作第一个string, 加入第一个字符串长到你绝望, 那就尴尬
        for(String s:strs){
            while(s.indexOf(str) != 0){ //公共>0, 公共前缀 ==0
                str=str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    public static String longestCommonPrefix2(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        //这是直接操作第一个string, 加入第一个字符串长到你绝望, 那就尴尬
        //其实找到长度最小的string进行操作, 多一次外循环,保证最少的内循环, 稳定些!
        //顺便可以做一下空处理...
        String shortestStr = strs[0];
        for (String s : strs) {
            if(s == null || s.length() == 0){
                return "";
            }
            if (s.length() < shortestStr.length()) {
                shortestStr = s;
            }
        }
        for(String s : strs){
            while(s.contains(shortestStr)){ //注意, 优先使用Java 核心类库的方法 不建议这里写index of
                shortestStr=shortestStr.substring(0, shortestStr.length() - 1);
            }
        }
        return shortestStr;
    }

}
