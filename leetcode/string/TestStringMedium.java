package java.leetcode.string;

import java.leetcode.string.medium.lengthOfLongestSubstringMedium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestStringMedium {
    public static long concurrentTime1, concurrentTime2, concurrentMemory1, concurrentMemory2;
    @Before
    public void before() {
        System.out.println("方法开始执行, 记得步进断点哦..");
        //得到程序开始时的系统时间（纳秒级，最终转化毫秒，保留小数点后两位）
        concurrentTime1 = System.nanoTime();
        //得到虚拟机运行、程序开始执行时jvm所占用的内存。
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory1 = runtime.totalMemory() - runtime.freeMemory();
    }

    @After
    public void after() {
        //得到程序执行完毕时的系统时间（毫秒级）
        concurrentTime2 = System.nanoTime();
        //得到虚拟机运行、所要测试的执行代码执行完毕时jvm所占用的内存（byte）。
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory2 = runtime.totalMemory()-runtime.freeMemory();

        //计算start和end之间的代码执行期间所耗时间(ms)与内存(M)。
        // 1毫秒(ms) = 1000微秒(us) = 1000 000纳秒(ns)
        // 1M = 1*2^20 byte = 1024 * 1024 byte;
        String time = String.valueOf((double)(concurrentTime2 - concurrentTime1)/1000000);
        String memory = String.valueOf((double)(concurrentMemory2-concurrentMemory1)/1024/1024) ;
        System.out.println("---------------您的代码执行时间为：" + time.substring(0,time.equals("0.0") ? 1 : (time.indexOf(".")+3)) + " ms, 消耗内存：" + memory.substring(0,memory.equals("0.0") ? 1 : (memory.indexOf(".")+3)) + " M + !---------------");
    }

    @Test
    public void lengthOfLongestSubstringMediumTest() {
        int i = lengthOfLongestSubstringMedium.lengthOfLongestSubstring2("bbbbb");
        System.out.println(i);
    }
}


