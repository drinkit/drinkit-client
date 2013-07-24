package ua.kiev.naiv.drinkit.cocktail.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 17:32
 */
@Entity
@Table(name = "cocktales_types")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CocktailType implements Serializable {

    private Integer id;
    private String name;
//    private Set<Cocktail> cocktails;

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

//    @OneToMany(mappedBy = "cocktailType")
//    public Set<Cocktail> getCocktails() {
//        return cocktails;
//    }
//
//    public void setCocktails(Set<Cocktail> cocktails) {
//        this.cocktails = cocktails;
//    }
}
