package ua.kiev.naiv.drinkit.cocktail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 17:32
 */
@Entity
@Table(name = "cocktales_types")
public class CocktailType {

    private Integer id;
    private String name;

    @Id
    @Column(name = "ncocktaletypekey")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "cname")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
