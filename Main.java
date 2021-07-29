package com.company;

import javax.naming.directory.*;
import java.util.Hashtable;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class Main {

    public static void main(String args[]) {
        String[] testData = {
                "이메일주소@naver.com",
        };
        for (String str : testData) {
            try {
                Hashtable<String, String> env = new Hashtable<>();
                env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
                DirContext ictx = new InitialDirContext(env);

                String[] result = str.split("@");

                System.out.println("메일 서버====>>>" + result[result.length - 1]);

                Attributes attrs = ictx.getAttributes(result[result.length - 1], new String[]{"MX"});

                Attribute attr = attrs.get("MX");
                if (attr == null) {
                    System.out.println("이메일 서버 없음!");
                } else {
                    System.out.println(attr.size() + "의 이메일 서버 있음!");
                }
            } catch (Exception e) {
                System.out.println("이메일 서버 없음! : " + e.getMessage());
            }
        }
    }

}
