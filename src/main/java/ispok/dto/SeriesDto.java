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
public class SeriesDto extends AbstractDto {

    private String name;
    private String info;

    /**
     *
     */
    public SeriesDto() {

    }

    /**
     *
     * @param name
     */
    public SeriesDto(String name) {
        this.name = name;
    }

    public SeriesDto(SeriesDto seriesDto) {
        this.id = seriesDto.id;
        this.name = seriesDto.name;
        this.info = seriesDto.info;
    }

    /**
     * Get the value of info
     *
     * @return the value of info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Set the value of info
     *
     * @param info new value of info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}
