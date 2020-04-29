package lambda_20200407;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestLambda01 {

    public static void main(String[] args) {
        // 勾股定律1
        Stream<int[]> s = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        s.forEach(w -> System.out.println("a = " + w[0] + ",b = " + w[1] + ",c = " + w[2]));

        System.out.println("-----------------------------------------------");
        // 勾股定律2
        Stream<int[]> streams = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new int[]{a, b, a * a + b * b})
                ).filter(t -> Math.sqrt(t[2]) % 1 == 0);

        streams.forEach(stream -> System.out.println("a = " + stream[0] + ",b = " + stream[1] + ",c = " + stream[2]));



    }
}
