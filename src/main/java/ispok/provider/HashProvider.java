/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.provider;

/**
 *
 * @author
 */
public interface HashProvider {
    /**
     * Calculates hash of the given string
     * @param s string
     * @return hash(s)
     */
    public String computeHash(String s);
}
