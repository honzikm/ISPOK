/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.helper;

import java.util.Random;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class PasswordUtil {

    private static final String charString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRTUVWXYZ0123456789";
    private static final int charStringLength = charString.length();
    private static final Random r = new Random();

    /**
     *
     * @return
     */
    public static String GetRandomPassword() {
        String password = "";
        for (int i = 0; i < 6; i++) {
            password += charString.charAt(r.nextInt(charStringLength));
        }
        return password;
    }
}
