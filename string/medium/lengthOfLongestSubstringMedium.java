package leetcode.string.medium;

//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
public class lengthOfLongestSubstringMedium {
    //[3]无重复字符的最长子串的长度 Longest Substring Without Reapting Characters
    //窗口思想——双指针: https://www.jianshu.com/p/800b73a0184d
//    循环不变式：一直求得是以start开头的最长无重复字符串
//    说明：以abcdcbaefga为例
//    step1. start = 0
//    end一直累加，直到end指向4
//            这是以0开头的最长的无重复字符串
//    step2. 当end = 4时，因为此时start = 0，因为start - end之间含有两个c
//    因此，移动start = 1，此时还是有两个c，因此 此时的end - start无疑较小，继续累加，这是以1开头的最长无重复
//            start =2 ，此时还有两个c，继续累加，已经以2开头的最长无重复字符串
//            start = 3，无重复，end终于可用不是4了(也就是窗口终于可能变大了), 此时需要累加end，直到出现重复, 以求得以3开头的最长无重复字符串
    //这种方法的缺点: 一.剔除那个重复字符的while循环, 窗口其实是一直减小的
    /**
     * @Author steven
     * @Description
     * @Date 10:38 2020/6/13
     * @Param [s]
     * @return int
     **/
    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0 ) return 0;
        char[] window = new char[128];   //用于记录每个字符(ascii值对应的下标)在窗口中的个数(其实就是map, 为了针对字符串优化调成了char[])
        int left = 0 , right = 0 ;        //双指针控制窗口大小
        int maxlength = 0 ;               //记录窗口长度 (窗口变小时没必要记录)

        while(right< s.length()){
            //每轮固定一个left, 求出此left开头的最长无重复字符串
            char chR = s.charAt(right);
            window[chR]++;
            while ((window[chR] > 1)) {//结束条件: 窗口右边界到了
                //如果此时right - left已经过半, 就不用接着数了, 已经拿到最大的了
                if (right - left > s.length() / 2) {
                    return right - left;
                }
                char chL = s.charAt(left); //裁掉旧left指向的值
                window[chL]--;
                left++; //旧left右移
            }
            maxlength = Math.max(right - left+1 , maxlength); //窗口可能变大, 所以需要比较
            right++;
        }
        return maxlength;
    }

/*    以这个字符串为例：abcabcbb，当i等于3时，也就是指向了第二个a, 此时我就需要查之前有没有出现过a, 如果出现了是在哪一个位置出现的。
    然后通过last[index] 查到等于1, 也就是说，如果start 依然等于0的话，那么当前窗口就有两个a了，也就是字符串重复了，
    所以我们需要移动当前窗口的start指针，移动到什么地方呢？移动到什么地方，窗口内就没有重复元素了呢？
    对了，就是a上一次出现的位置的下一个位置，就是1 + 1 = 2。当start == 2, 当前窗口就没有了重复元素，
    那么以当前字符为结尾的最长无重复子串就是bca,然后再和之前的res取最大值。然后i指向后面的位置，按照同样思路计算。*/
//todo last[index]只记录最后一个的位置, 不记录个数   2. 如何满足一半就退出
    /**
     * @Author steven
     * @Description
     * @Date 10:38 2020/6/13
     * @Param [s]
     * @return int
     **/
    public static int lengthOfLongestSubstring2(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }
}


