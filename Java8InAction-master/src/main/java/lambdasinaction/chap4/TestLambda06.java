package lambdasinaction.chap4;

import static java.util.stream.Collectors.*;
import java.util.List;
import java.util.Map;

public class TestLambda06 {

    public static void main(String[] args) {
        // 1、分组
        List<Dish> menuList = Dish.menu;
        Map<Dish.Type, List<Dish>> menuMap = menuList.stream().collect(groupingBy(Dish::getType));
        System.out.println(menuMap);
    }
}
