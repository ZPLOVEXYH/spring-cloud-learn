import Aladdin.Hasp;
import Aladdin.HaspStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {

    public static final String vendorCode = new String(
            "uRYMJiy/vciegdNgfd9aKujyEKIE/u4+Pf11USBh3FYBJ/Q569eigmMs/KiZGKowft22T+2oJMiDqHjb" +
                    "3MlXlpJWUaZVKl1mzSF6obDmtWhC8dYMYsX60v3av1V16Zfqe7MWfj5mTxtpa+rn34kpSz6SWFeVFeIB" +
                    "gNWuYPofho6qxZ/VFsfiPCcViXj+GM6WvMfmh9t0tEwZMqvbWnaW9esp3KaCgjmJ4wxaxY3aqL9TbV58" +
                    "xi99sFvp1Opf1vyzPoElOb8mj0GoKPXtc4w5mIOVWhMmbj/sJb0BhXdY7W+rQta7RSN8iPA+zW+2L9dC" +
                    "E5pfQGR+MgvKp2pFTtr+OAr1IjPWM1tGzNqT3x51oYexO+mYkVK8W+vcNMV6Q9C8pLe5gffoyjJLeR98" +
                    "TfKLqwnFYXdz5Y6TjhgEKOdqQjCZG/bGZllcnvywnJivruyTlRnkjyIzpGWIw/ACfV/tHxD1Ctvmoe9J" +
                    "eO3VAOhJMY9pPTIVxOmvkCP9IHa0PIS1nkUuVZrLHb0tPZXpLGBWMi2i96MMn849TVR7urlFEDz+MrbX" +
                    "ZxasLeOhyfgD7IijEBLxSWmSzjv5qBPschV0bJSRM/VeB0V0pKVbBtIGHc/G0k7urbv+mWglF1Am+s4O" +
                    "OmG4hyUhAgXnm8Ab2zfvlMNu0P1nzMEWAs++sL/hSiiQtfHecszA1TQbjwQSjh28n4h7fa/qvBWZqLE7" +
                    "s+XRvpzQPzkaTC/zAYQa7phztm0ADK7cZDG75cOT21/OCxfj8ixlHd6i5Vdss+JeU4+t822+/die2gBz" +
                    "XOdW1nvAiupEKHub9MUjRstRuLGVcDsu8sIcCzMrOcoHcZOrnPhzOrMRJuTeFe+mTWBknd6hCV3+dXq/" +
                    "Kx+S/p1seBCD65paH1UBFbHTH0bOyw8UP3HQUl5Za+RMlmR1/E6s9YegxS1kk6Axip5Q3CubY3sbPI6C" +
                    "wh11qHV4AOCUYeb58E84zw==");

    public static void main(String[] args) {
        Hasp hasp = new Hasp(Hasp.HASP_DEFAULT_FID);

        hasp.login(vendorCode);
        int status = hasp.getLastError();
        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("OK");
                break;
            case HaspStatus.HASP_FEATURE_NOT_FOUND:
                System.out.println("no Sentinel DEMOMA key found");
                break;
            case HaspStatus.HASP_HASP_NOT_FOUND:
                System.out.println("Sentinel key not found");
                break;
            case HaspStatus.HASP_OLD_DRIVER:
                System.out.println("outdated driver version or no driver installed");
                break;
            case HaspStatus.HASP_NO_DRIVER:
                System.out.println("Sentinel key not found");
                break;
            case HaspStatus.HASP_INV_VCODE:
                System.out.println("invalid vendor code");
                break;
            default:
                System.out.println("login to default feature failed");
        }
    }
}
