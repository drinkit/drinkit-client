package ua.kiev.naiv.drinkit.cocktail.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 25.07.13
 * Time: 22:31
 */
@Entity
@Table(name = "recipe_options", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "option")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Option {

    private int id;
    private String option;
    private String name;

    @Id
    @GeneratedValue
    @Column
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
