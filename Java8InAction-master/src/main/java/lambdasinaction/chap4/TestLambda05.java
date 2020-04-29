package lambdasinaction.chap4;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class TestLambda05 {

    public static void main(String[] args) {
        // 1、求出菜单列表中的总热量
        List<Dish> dishList = Dish.menu;
        int sumCalor = dishList.stream().collect(summingInt(Dish::getCalories));
        System.out.println("求出菜单列表中的总热量为：" + sumCalor);

        // 2、求出菜单列表中的平均热量
        double avgCalories = dishList.stream().collect(averagingInt(Dish::getCalories));
        System.out.println("求出菜单列表中的平均热量为：" + avgCalories);

        // 3、求出菜单列表中的菜单个数
        Long count = dishList.stream().count();
        System.out.println("求出菜单列表中的菜单个数：" + count);

        System.out.println("====================================================");

        // 4、获取得到汇总
        IntSummaryStatistics iss = dishList.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("获取得到菜单列表中的最大热量值为：" + iss.getMax());
        System.out.println("获取得到菜单列表中的最小热量值为：" + iss.getMin());
        System.out.println("获取得到菜单列表中的平均热量值为：" + iss.getAverage());
        System.out.println("获取得到菜单列表中的总数为：" + iss.getCount());
        System.out.println("获取得到菜单列表中的热量总和为：" + iss.getSum());

        int totalCalories = dishList.stream().collect(reducing(0, Dish::getCalories, (a, b) -> a + b));
        System.out.println("获取得到的菜单列表的总热量为：" + totalCalories);

        Optional<Dish> dishOptional = dishList.stream().collect(reducing((a, b) -> a.getCalories() > b.getCalories() ? a : b));
        System.out.println(dishOptional.get().getCalories());

        // 5、把菜单中所有菜肴的名称连接起来
        String longDishName = dishList.stream().map(Dish::getName).collect(joining(" "));
        System.out.println("5、把菜单中所有菜肴的名称连接起来的字符串为：" + longDishName);

        // 6、如何使用reduce方法来实现collect的collectors.toList();功能
        List<String> strList = dishList.stream().reduce(new ArrayList<>(), (List<String> x, Dish y) -> {
            x.add(y.getName());
            return x;
        }, (List<String> a, List<String> b) -> {
            a.addAll(b);
            return a;
        });
        System.out.println("菜单列表中的菜单名称为：" + strList.toString());

        // 7、toSet
        Set<String> setString = dishList.stream().reduce(new HashSet<>(), (Set<String> x, Dish y) -> {
            x.add(y.getName());
            return x;
        }, (Set<String> s1, Set<String> s2) -> {
            s1.addAll(s2);
            return s1;
        });

        System.out.println("2、菜单中的菜单名称为：" + setString);

        int s = dishList.stream().reduce(new Integer(0), (Integer a, Dish d) -> {
            System.out.println("a = " + a + ", d = " + d.getCalories());
            return d.getCalories() + a;
        }, Integer::sum);
        System.out.println("菜单列表中的热量总计为：" + s);


        String outString = dishList.stream().collect(reducing("", Dish::getName, (a, b) -> a + b));
        System.out.println("输出的菜单名称为：" + outString);


        int s2 = dishList.stream().reduce(new Integer(0), (Integer a, Dish d) -> {
            System.out.println("a = " + a + ", d = " + d.getCalories());
            return d.getCalories() + a;
        }, Integer::sum);
        System.out.println("菜单列表中的热量总计为：" + s);

    }
}
