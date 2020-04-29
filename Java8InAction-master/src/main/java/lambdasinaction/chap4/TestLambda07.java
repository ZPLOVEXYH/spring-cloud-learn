package lambdasinaction.chap4;

import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

public class TestLambda07 {

    /**
     * 分区
     * @param args
     */
    public static void main(String[] args) {
        List<Dish> dishList = Dish.menu;
        Map<Boolean, List<Dish>> dishMap = dishList.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(dishMap.get(true));
        System.out.println(dishMap.get(false));

        List<Dish> dishs = dishList.stream().filter(Dish::isVegetarian).collect(toList());
        System.out.println(dishs);


    }
}
