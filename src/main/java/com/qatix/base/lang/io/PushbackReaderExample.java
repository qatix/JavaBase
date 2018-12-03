package com.qatix.base.lang.io;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/3 9:31 AM
 * @see https://www.boraji.com/java-pushbackreader-example
 */
public class PushbackReaderExample {
    public static void main(String[] args) {
        String input = "This is an example of PushbackReader.";
        StringReader stringReader = null;
        PushbackReader pushbackReader = null;
        try {
            stringReader = new StringReader(input);
            pushbackReader = new PushbackReader(stringReader, 20);

            // Read first character
            int ch = pushbackReader.read();
            System.out.println((char) ch);

            // Push back first character
            pushbackReader.unread(ch);

            char[] c = new char[4];
            // Get first four characters
            pushbackReader.read(c);
            System.out.println(new String(c));

            // Push back first four characters
            pushbackReader.unread(c, 0, 4);

            // Read all characters
            c = new char[input.length()];
            pushbackReader.read(c);
            System.out.println(new String(c, 0, input.length()));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pushbackReader != null) {
                    pushbackReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
