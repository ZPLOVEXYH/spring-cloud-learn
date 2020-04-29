package lambda_20200407;

import java.util.Comparator;
import java.util.stream.Stream;

public class TestLambda04 {

    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("a", "b", "c");
        long count = stringStream.count();
        System.out.println("当前流有多少元素：" + count);
    }
}



