package lambda_20200407;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestLambda02 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        // 1、由值创建流
        Stream<String> stringStream = Stream.of("python", "java", "javascript", "mysql");
        System.out.println(stringStream.count());
//        stringStream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();
        System.out.println(emptyStream.count());

        // 2、由数组创建流
        int[] nums = {1, 4, 3, 5, 33};
        IntStream streams = Arrays.stream(nums);
        streams.forEach(z -> System.out.println(z));

        // 3、由文件生成流
        Path path = Paths.get("D:\\南通场站安装环境准备\\南通企业信息.txt");
        Stream<String> s1 = Files.lines(path, Charset.defaultCharset());
//        long l11 = s1.flatMap(t -> {
//            List<String> strList = Arrays.asList(t.split(""));
//            return strList.stream();
//        }).count();
//        System.out.println("test11 + " + l11);

        long l22 = s1.map(t -> {
            List<String> strList = Arrays.asList(t.split(""));
            return strList.stream();
        }).count();
        System.out.println("test2 + " + l22);


        System.out.println("-----------------------------");

        Stream<String> s2 = Files.lines(path, Charset.defaultCharset());
        long l2 = s2.flatMap(t -> {
            System.out.println("输出：" + t);
            List<String> strList = Arrays.asList(t.split(""));
            return strList.stream();
        }).distinct().count();
        System.out.println(l2);


    }
}



