package com.webward.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dustinosthzn on 2014/11/09.
 */
@XmlRootElement
public class ItemDto {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
