/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class NumberedLevelDto extends AbstractDto {

    private Long levelId;

    private int number;

    /**
     * Get the value of number
     *
     * @return the value of number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set the value of number
     *
     * @param number new value of number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get the value of levelId
     *
     * @return the value of levelId
     */
    public Long getLevelId() {
        return levelId;
    }

    /**
     * Set the value of levelId
     *
     * @param levelId new value of levelId
     */
    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

}
