package guru.drinkit.cocktail.persistence.entity;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "recipe_options", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "option")
})
public class Option {

    private int id;
    private String option;
    private String name;

    public Option() {
    }

    public Option(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (id != option.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", option='" + option + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
