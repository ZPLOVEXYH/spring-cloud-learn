4.1 构建server-zipkin

在spring Cloud为F版本的时候，已经不需要自己构建Zipkin Server了，只需要下载jar即可，下载地址：

https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/

也可以在这里https://github.com/forezp/SpringCloudLearning/tree/master/sc-f-chapter9 下载。下载完成jar 包之后，需要运行jar，如下：

    java -jar zipkin-server-2.10.1-exec.jar

访问浏览器localhost:9411

博客地址：https://www.fangzhipeng.com/springcloud/2018/08/09/sc-f9-sleuth.html