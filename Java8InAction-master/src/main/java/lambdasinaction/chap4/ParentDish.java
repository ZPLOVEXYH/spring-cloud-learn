package lambdasinaction.chap4;

public class ParentDish {
    static {
        System.out.println("我是静态代码块");
    }

    public void test(String msg){
        System.out.println("我是方法块！！！" + msg);
    }
}
