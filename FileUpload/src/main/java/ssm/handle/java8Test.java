package ssm.handle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class java8Test {
    public static void main(String[] args) {
        /*List<String> strs = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> list = strs.stream().filter(str -> str.indexOf("a") > -1).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list));
        String str2 = strs.stream().filter(str -> str.indexOf("a") > -1).collect(Collectors.joining(","));
        System.out.print(str2);*/
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});

        //求集合元素只和
        Integer result = stream.reduce(8, Integer::sum);
        System.out.println(result);
        List<String> list = new ArrayList<>();
        list.stream().filter( p ->
            p.equals("a")
        ).collect(Collectors.toList());
        List<String> collect = list.stream().map(String::toUpperCase).collect(Collectors.toList());
    }
}
