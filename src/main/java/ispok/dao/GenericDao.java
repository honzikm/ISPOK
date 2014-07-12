package ispok.dao;

import ispok.bo.AbstractBusinessObject;
import java.util.List;
import java.util.Map;

/**
 * Definuje metody, ktere by melo kazde dao implementovat.
 *
 * @author
 */
public interface GenericDao {

    /**
     * Vrati vsechny entity serazene sestupne dle id
     *
     * @param <ENTITY>
     * @param clazz
     * @return vsechny entity pozadovaneho typu (dle implementace potomka tohoto
     * interfacu)
     */
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz);

    /**
     * Vrati stranku entit zacinajici from o velikosti maxResults
     *
     * @param <ENTITY> typovy parametr
     * @param from index uvodni entity
     * @param maxResults pocet entit na strance
     * @param clazz trida korespondujici s typem
     * @return maxResults entit
     */
    public <ENTITY> List<ENTITY> getPage(int from, int maxResults, Class<ENTITY> clazz);

    /**
     * Odstrani entitu dle jejiho id
     *
     * @param <ENTITY>
     * @param id entity k odstraneni
     * @param clazz
     */
    public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> clazz);

    /**
     * Vrati pocet entit dane tridy
     *
     * @param <ENTITY>
     * @param clazz trida
     * @return pocet
     */
    public <ENTITY> Long getCount(Class<ENTITY> clazz);

    /**
     *
     * @param <ENTITY>
     * @param filters
     * @param clazz
     * @return
     */
    public <ENTITY> Long getCount(Map<String, Object> filters, Class<ENTITY> clazz);

    /**
     * Odstrani dany objekt
     *
     * @param <ENTITY>
     * @param o objekt k odstraneni
     */
    public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o);

    /**
     * Ulozi nebo zaktualizuje danou entitu (at jiz je attached nebo detached),
     * entita bude po navratu teto funkce vzdy attached
     *
     * @param <ENTITY>
     * @param o
     * @return attached ulozeny (zaktualizovany objekt)
     */
    public <ENTITY extends AbstractBusinessObject> ENTITY saveOrUpdate(ENTITY o);

    /**
     * Vrati objekt (pomoci get) dane tridy dle ID
     *
     * @param <ENTITY>
     * @param id id objektu k vraceni
     * @param clazz
     * @return objekt identifikovany id, @null pokud neexistuje
     */
    public <ENTITY> ENTITY getById(Long id, Class<ENTITY> clazz);

    /**
     * Load (proxy objektu) dle identifikatoru
     *
     * @param <ENTITY>
     * @param id
     * @param clazz
     * @return
     */
    public <ENTITY> ENTITY loadById(long id, Class<ENTITY> clazz);

    /**
     * Vrati vsechny instance razene sestupne dle property
     *
     * @param <ENTITY>
     * @param property
     * @param clazz
     * @return
     */
    public <ENTITY> List<ENTITY> getAllOrderedDesc(String property, Class<ENTITY> clazz);

    /**
     * Vrati vsechny instance razene vzestupne dle property
     *
     * @param <ENTITY>
     * @param property
     * @param clazz
     * @return
     */
    public <ENTITY> List<ENTITY> getAllOrderedAsc(String property, Class<ENTITY> clazz);

    /**
     * Get all entities by property
     *
     * @param <ENTITY>
     * @param property the property to select
     * @param value value of the property
     * @param clazz
     * @return
     */
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz);

    /**
     * Get all entities by property
     *
     * @param <ENTITY>
     * @param property the property to select
     * @param value value of the property
     * @param clazz
     * @return
     */
    public <ENTITY> ENTITY getByPropertyUnique(String property, Object value, Class<ENTITY> clazz);

    /**
     * Get all entities by property
     *
     * @param <ENTITY>
     * @param first
     * @param rows
     * @param clazz
     * @param sortBy parametr, opdle ktereho dojde k razeni
     * @param ascending priznak toho, jestli razeni bude vzestupne nebo
     * sestupne, @true vzestupne, @false sestupne
     * @return
     */
    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Class<ENTITY> clazz);

    /**
     *
     * @param <ENTITY>
     * @param first
     * @param rows
     * @param sortBy
     * @param ascending
     * @param filters
     * @param clazz
     * @return
     */
    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Map<String, Object> filters, Class<ENTITY> clazz);

}
