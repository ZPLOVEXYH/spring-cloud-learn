/*****************************************************************************
*
* Demo program for Sentinel LDK licensing services
*
*
* Copyright (C) 2014, SafeNet, Inc. All rights reserved.
*
*
* Sentinel DEMOMA key required with features 1 and 42 enabled
*
*****************************************************************************/

import java.nio.*;
import java.io.*;

import Aladdin.Hasp;
import Aladdin.HaspTime;
import Aladdin.HaspStatus;
import Aladdin.HaspApiVersion;

class hasp_demo
{
    public static final int DEMO_MEMBUFFER_SIZE = 128;

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

    public static final String scope = new String(
      "<haspscope>\n" +
      " <license_manager hostname=\"localhost\" />\n" +
      "</haspscope>\n");

    public static final String scope1 = new String(
      "<haspscope />\n");

    public static final String view = new String(
      "<haspformat root=\"my_custom_scope\">\n" +
      "  <hasp>\n" +
      "    <attribute name=\"id\" />\n" +
      "    <attribute name=\"type\" />\n" +
      "    <feature>\n" +
      "      <attribute name=\"id\" />\n" +
      "      <element name=\"concurrency\" />\n" +
      "      <element name=\"license\" />\n" +
      "      <session>\n" +
      "        <element name=\"username\" />\n" +
      "        <element name=\"hostname\" />\n" +
      "        <element name=\"ip\" />\n" +
      "        <element name=\"apiversion\" />\n" +
      "      </session>\n" +
      "    </feature>\n" +
      "  </hasp>\n" +
      "</haspformat>\n");

    public static final byte[] data = { 0x74, 0x65, 0x73, 0x74, 0x20, 0x73, 0x74, 0x72,
                                        0x69, 0x6e, 0x67, 0x20, 0x31, 0x32, 0x33, 0x00 };
    private static HaspTime datetime;

    /************************************************************************
     *
     * helper function: dumps a given block of data, in hex and ascii
     */

    /*
     * Converts a byte to hex digit and writes to the supplied buffer
     */
    private static void byte2hex(byte b, StringBuffer buf)
    {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                            '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

    /*
     * Converts a byte array to hex string
     */
    private static String toHexString(byte[] block)
    {
        StringBuffer buf = new StringBuffer();

        int len = block.length;

        for (int i = 0; i < len; i++) {
            byte2hex(block[i], buf);
            if (i < len - 1) {
                buf.append(":");
            }
        }
        return buf.toString();
    }

    public static void dump(byte[] data, String margin)
    {
        int i, j;
        byte b;
        byte[] s = new byte[16];
        byte hex[] = {0};
        String shex;
        String PrtString;

        if (data.length == 0) return;

        s[0] = 0;
        j = 0;
        for (i = 0; i < data.length; i++) {
            if (j == 0) System.out.print(margin);
            b = data[i];
            if ((b < 32) || (b > 127)) s[j] = '.'; else s[j] = b;
            if (j < 15)
                s[j+1] = 0;
            hex[0] = b; shex = toHexString(hex);
            System.out.print(shex + " ");
            j++;
            if (((j & 3) == 0) && (j < 15)) System.out.print("| ");
            PrtString = new String(s);
            if (j > 15) { 
                System.out.println("[" + PrtString + "]"); 
                j = 0; 
                s[0] = 0;
            }
        }
        if (j != 0) {
            while (j < 16) {
                System.out.print("   ");
                j++;
                if (((j & 3) == 0) && (j < 15)) System.out.print("| ");
            }
            PrtString = new String(s);
            System.out.println(" [" + PrtString + "]");
        }
    }

    public static void main(String argv[]) throws java.io.IOException
    {

        int status;
        String infos;
        int i;
        int fsize;
        byte c;
        int input = 0;
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(reader);

        Hasp hasp = new Hasp(Hasp.HASP_DEFAULT_FID);

        System.out.println("\nThis is a simple demo program for the Sentinel LDK licensing functions\n");
        System.out.println("Copyright (C) 2013 SafeNet, Inc. All rights reserved.\n\n");

        HaspApiVersion version = hasp.getVersion(vendorCode);
        status = version.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                break;
            case HaspStatus.HASP_NO_API_DYLIB:
                System.out.println("Sentinel API dynamic library not found");
                return;
            case HaspStatus.HASP_INV_API_DYLIB:
                System.out.println("Sentinel API dynamic library is corrupt");
                return;
            default:
                System.out.println("unexpected error");
        }

        System.out.println("API Version: " + version.majorVersion() + "." 
                        + version.minorVersion()
                        + "." + version.buildNumber() + "\n");

        /**********************************************************************
         * hasp_login
         *   establish a context for Sentinel services
         */

        System.out.print("login to default feature         : ");

        /* login feature 0 */
        /* this default feature is available on any key */
        /* search for local and remote Sentinel key */
        hasp.login(vendorCode);
        status = hasp.getLastError();

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

        /********************************************************************
         * hasp_get_sessioninfo
         *   retrieve Sentinel key attributes
         */

        System.out.print("\nget session info                 : ");

        infos = hasp.getSessionInfo(Hasp.HASP_KEYINFO);
        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.print("OK, Sentinel key attributes retrieved\n\n"
                                 + "Key info:\n===============\n" + infos
                                 + "\n===============\n");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("handle not active");
                break;
            case HaspStatus.HASP_INV_FORMAT:
                System.out.println("unrecognized format");
                break;
            case HaspStatus.HASP_HASP_NOT_FOUND:
                System.out.println("Sentinel key not found");
                break;
            default:
                System.out.println("hasp_get_sessioninfo failed");
        }

        /*******************************************************************/

        System.out.println("\npress ENTER to continue");
        input = in.read();

        /********************************************************************
         * hasp_get_size
         *   retrieve the memory size of the Sentinel key
         */

        System.out.println("\nretrieving the key's memory size : ");

        fsize = hasp.getSize(Hasp.HASP_FILEID_RW);
        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("memory size is " + fsize + " bytes");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("handle not active");
                break;
            case HaspStatus.HASP_INV_FILEID:
                System.out.println("invalid file id");
                break;
            case HaspStatus.HASP_HASP_NOT_FOUND:
                System.out.println("Sentinel key not found");
                break;
            default:
                System.out.println("could not retrieve memory size");
        }

        if (fsize != 0) {      /* skip memory access if no memory available */

            /******************************************************************
             * hasp_read
             *   read from memory
             */

            /* limit memory size to be used in this demo program */

            if (fsize > DEMO_MEMBUFFER_SIZE) fsize = DEMO_MEMBUFFER_SIZE;

            System.out.println("\nreading " + fsize + " bytes from memory   : ");
        
            byte[] membuffer = new byte[DEMO_MEMBUFFER_SIZE];

            hasp.read(Hasp.HASP_FILEID_RW, 0, membuffer);
            status = hasp.getLastError();

            switch (status) {
                case HaspStatus.HASP_STATUS_OK:
                    System.out.println("OK");
                    dump(membuffer, "    ");
                    break;
                case HaspStatus.HASP_INV_HND:
                    System.out.println("handle not active");
                    break;
                case HaspStatus.HASP_INV_FILEID:
                    System.out.println("invalid file id");
                    break;
                case HaspStatus.HASP_MEM_RANGE:
                    System.out.println("beyond memory range of attached Sentinel key");
                    break;
                case HaspStatus.HASP_HASP_NOT_FOUND:
                    System.out.println("hasp not found");
                    break;
                default:
                    System.out.println("read memory failed\n");
            }

            /******************************************************************
             * hasp_write
             *   write to memory
             */

            System.out.println("\nincrementing every byte in memory buffer");
            for (i = 0; i < fsize; i++) membuffer[i]++;

            System.out.println("\nwriting " + fsize + " bytes to memory     : ");

            hasp.write(Hasp.HASP_FILEID_RW, 0, membuffer);
            status = hasp.getLastError();

            switch (status) {
                case HaspStatus.HASP_STATUS_OK:
                    System.out.println("OK");
                    break;
                case HaspStatus.HASP_INV_HND:
                    System.out.println("handle not active");
                    break;
                case HaspStatus.HASP_INV_FILEID:
                    System.out.println("invalid file id");
                    break;
                case HaspStatus.HASP_MEM_RANGE:
                    System.out.println("beyond memory range of attached Sentinel key");
                    break;
                case HaspStatus.HASP_HASP_NOT_FOUND:
                    System.out.println("Sentinel key not found");
                    break;
                default:
                    System.out.println("write memory failed");
            }

            /******************************************************************
             * hasp_read
             *   read from memory
             */

            System.out.println("\nreading "+fsize+" bytes from memory   : ");

            hasp.read(Hasp.HASP_FILEID_RW, 0, membuffer);

            switch (status) {
                case HaspStatus.HASP_STATUS_OK:
                    System.out.println("OK");
                    dump(membuffer, "    ");
                    break;
                case HaspStatus.HASP_INV_HND:
                    System.out.println("handle not active\n");
                    break;
                case HaspStatus.HASP_INV_FILEID:
                    System.out.println("invalid file id");
                    break;
                case HaspStatus.HASP_MEM_RANGE:
                    System.out.println("beyond memory range of attached Sentinel key");
                    break;
                case HaspStatus.HASP_HASP_NOT_FOUND:
                    System.out.println("Sentinel key not found");
                    break;
                default:
                    System.out.println("read memory failed");
            }
        } /* end of memory demo */

        /**********************************************************************
         * hasp_encrypt
         *   encrypts a block of data using the Sentinel key
         *   (minimum buffer size is 16 bytes)
         */

        System.out.println("\nencrypting a data buffer:");
        dump(data, "     ");

        hasp.encrypt(data);
        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("encryption ok:");
                dump(data, "     ");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("handle not active");
                break;
            case HaspStatus.HASP_TOO_SHORT:
                System.out.println("data length too short");
                break;
            case HaspStatus.HASP_ENC_NOT_SUPP:
                System.out.println("attached key does not support AES encryption");
                break;
            case HaspStatus.HASP_FEATURE_NOT_FOUND:
                System.out.println("Sentinel key not found");
                break;
            default:
                System.out.println("encryption failed");
        }

        /**********************************************************************
         * hasp_decrypt
         *   decrypts a block of data using the Sentinel key
         *   (minimum buffer size is 16 bytes)
         */

        hasp.decrypt(data);
        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("decryption ok:");
                dump(data, "     ");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("handle not active");
                break;
            case HaspStatus.HASP_TOO_SHORT:
                System.out.println("data length too short");
                break;
            case HaspStatus.HASP_ENC_NOT_SUPP:
                System.out.println("attached key does not support AES encryption");
                break;
            case HaspStatus.HASP_FEATURE_NOT_FOUND:
                System.out.println("key not found");
                break;
            default:
                System.out.println("decryption failed");
        }

        /**********************************************************************/

        System.out.print("login to feature 42              : ");

        Hasp hasp1 = new Hasp(2);

        /* search for local and remote HASP key */
        hasp1.login(vendorCode);
        status = hasp1.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("OK");
                break;
            case HaspStatus.HASP_FEATURE_NOT_FOUND:
                System.out.println("no Sentinel DEMOMA key found with feature 42 enabled");
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
                System.out.println("login feature 42 failed");
        }

        System.out.println("\nencrypt/decrypt again to see different encryption for different features:");

        /**********************************************************************
         * hasp_encrypt
         *   encrypts a block of data using the Sentinel key
         *   (minimum buffer size is 16 bytes)
         */

        System.out.println("\nencrypting a data buffer:");
        dump(data, "     ");

        hasp1.encrypt(data);
        status = hasp1.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("encryption ok:");
                dump(data, "     ");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("handle not active");
                break;
            case HaspStatus.HASP_TOO_SHORT:
                System.out.println("data length too short");
                break;
            case HaspStatus.HASP_ENC_NOT_SUPP:
                System.out.println("attached key does not support AES encryption");
                break;
            case HaspStatus.HASP_FEATURE_NOT_FOUND:
                System.out.println("key not found");
                break;
            default:
                System.out.println("encryption failed");
        }

        /**********************************************************************
         * hasp_decrypt
         *   decrypts a block of data using the Sentinel key
         *   (minimum buffer size is 16 bytes)
         */

        hasp1.decrypt(data);
        status = hasp1.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("decryption ok:");
                dump(data, "     ");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("handle not active");
                break;
            case HaspStatus.HASP_TOO_SHORT:
                System.out.println("data length too short");
                break;
            case HaspStatus.HASP_ENC_NOT_SUPP:
                System.out.println("attached key does not support AES encryption");
                break;
            case HaspStatus.HASP_FEATURE_NOT_FOUND:
                System.out.println("key not found");
                break;
            default:
                System.out.println("decryption failed");
        }

        hasp1.logout();
        status = hasp1.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("OK");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("failed: handle not active");
                break;
            default:
                System.out.println("failed with status " + status);
        }

        /**********************************************************************
         * hasp_get_rtc
         *   read current time from Sentinel Time key
         */

        System.out.print("\nreading current time and date    : ");

        datetime = hasp.getRealTimeClock();

        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("time: " + datetime.getHour() + ":"
                                   + datetime.getMinute() + ":" + datetime.getSecond() +" H:M:S");
                System.out.println("                                   date: "
                                   + datetime.getDay() + "/" + datetime.getMonth() + "/"
                                   + datetime.getYear() + " D/M/Y");
                break;
            case HaspStatus.HASP_INV_TIME:
                System.out.println("time value outside supported range\n");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("handle not active");
                break;
            case HaspStatus.HASP_NO_TIME:
                System.out.println("no Sentinel Time connected");
                break;
            default:
                System.out.println("could not read time from Sentinel key");
        }

        /**********************************************************************
         * hasp_logout
         *   closes established session and releases allocated memory
         */

        System.out.print("\nlogout from feature 1            : ");

        hasp.logout();

        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("OK");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("failed: handle not active");
                break;
            default:
                System.out.println("failed");
        }

        /********************************************************************
         * hasp_login_scope
         *   establishes a context for Sentinel services
         *   allows specification of several restrictions
         */

        hasp = new Hasp(Hasp.HASP_DEFAULT_FID);

        System.out.println("restricting the license to be used to 'local':");

        System.out.println(scope);

        System.out.print("hasp_login_scope                 : ");

        hasp.loginScope(scope,vendorCode);
        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("OK");
                break;
            case HaspStatus.HASP_FEATURE_NOT_FOUND:
                System.out.println("login to default feature failed");
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
            case HaspStatus.HASP_INV_SCOPE:
                System.out.println("invalid XML scope");
                break;
            default:
                System.out.println("login to default feature failed with status " + status);
        }

        /*******************************************************************/

        System.out.print("\ngetting information about connected keys and usage:  ");

        infos = hasp.getInfo(scope1, view, vendorCode);
        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                /* use the info you received ... */
                System.out.println("OK\n" + infos);
                break;
            case HaspStatus.HASP_INV_FORMAT:
                System.out.println("invalid XML info format\n");
                break;
            case HaspStatus.HASP_INV_SCOPE:
                System.out.println("invalid XML scope\n");
                break;
            default:
                System.out.println("hasp_get_info failed with status " + status);
        }

        /********************************************************************
         * hasp_logout
         *   closes established session and releases allocated memory
         */

        System.out.print("\nlogout                           : ");
        hasp.logout();
        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("OK");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("failed: handle not active");
                break;
            default:
                System.out.println("failed\n");
        }

        /**********************************************************************
         * hasp_login
         *   establish a context for Sentinel services
         */
        hasp = new Hasp(Hasp.HASP_PROGNUM_DEFAULT_FID);

        System.out.print("login to default classic feature         : ");

        /* login feature 0 */
        /* this default feature is available on any key */
        /* search for local and remote HASP key */
        hasp.login(vendorCode);
        status = hasp.getLastError();

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
                System.out.println("login to default classic feature failed");
        }

        /* 
         * No need to continue if classic functionality is not provided
         * by this key
         */
        if (status != HaspStatus.HASP_STATUS_OK)
            return;

        /**********************************************************************
         * hasp_legacy_encrypt
         *   encrypts a block of data using the HASP key
         *   (minimum buffer size is 16 bytes)
         */
        System.out.println("\nlegacy encrypting a data buffer:");
        dump(data, "     ");

        hasp.legacyencrypt(data);
        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("legacy encryption ok:");
                dump(data, "     ");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("handle not active");
                break;
            case HaspStatus.HASP_TOO_SHORT:
                System.out.println("data length too short");
                break;
            case HaspStatus.HASP_ENC_NOT_SUPP:
                System.out.println("attached key does not support AES encryption");
                break;
            case HaspStatus.HASP_FEATURE_NOT_FOUND:
                System.out.println("key not found");
                break;
            default:
                System.out.println("legacy encryption failed");
        }

        /**********************************************************************
         * hasp_legacy_decrypt
         *   decrypts a block of data using the HASP key
         *   (minimum buffer size is 16 bytes)
         */

        hasp.legacydecrypt(data);
        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("legacy decryption ok:");
                dump(data, "     ");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("handle not active");
                break;
            case HaspStatus.HASP_TOO_SHORT:
                System.out.println("data length too short");
                break;
            case HaspStatus.HASP_ENC_NOT_SUPP:
                System.out.println("attached key does not support AES encryption");
                break;
            case HaspStatus.HASP_FEATURE_NOT_FOUND:
                System.out.println("key not found");
                break;
            default:
                System.out.println("legacy decryption failed");
        }
        
        /********************************************************************
         * hasp_logout
         *   closes established session and releases allocated memory
         */

        System.out.print("\nlogout                           : ");
        hasp.logout();
        status = hasp.getLastError();

        switch (status) {
            case HaspStatus.HASP_STATUS_OK:
                System.out.println("OK");
                break;
            case HaspStatus.HASP_INV_HND:
                System.out.println("failed: handle not active");
                break;
            default:
                System.out.println("failed\n");
        }
       
    }
}
