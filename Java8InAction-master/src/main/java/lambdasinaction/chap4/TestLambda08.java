package lambdasinaction.chap4;

import java.lang.reflect.InvocationTargetException;

public class TestLambda08 {

    /**
     * 分区
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ClassLoader loader = ParentDish.class.getClassLoader();
        System.out.println(loader);

        Class clazz = Class.forName("lambdasinaction.chap4.ParentDish");
        ParentDish pd = (ParentDish) clazz.newInstance();
        pd.test("test");
        System.out.println(clazz.getMethod("test", String.class).invoke(new ParentDish(), "hello world"));


    }


}
