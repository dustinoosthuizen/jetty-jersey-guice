package com.webward.entities;

import javax.persistence.*;

/**
 * Created by dustinosthzn on 2014/11/09.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Item.findById",query = "select t from Item t where t.id = :id"),
        @NamedQuery(name = "Item.findByName",query = "select t from Item t where t.name = :name"),
        @NamedQuery(name = "Item.deleteById",query = "delete from Item t where t.id = :id")
})
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;


    public  Item()
    {


    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


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

        Item testTable = (Item) o;

        if (id != testTable.id) return false;
        if (name != null ? !name.equals(testTable.name) : testTable.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
