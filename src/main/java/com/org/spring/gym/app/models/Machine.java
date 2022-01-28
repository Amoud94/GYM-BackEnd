package com.org.spring.gym.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "machines")
public class Machine {
    @Id
    private String id;
    private String ref;
    private String name;
    private String category;
    private String desc;
    private boolean doesFunction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() { return ref; }

    public void setRef(String ref) { this.ref = ref; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getDesc() { return desc; }

    public void setDesc(String desc) { this.desc = desc; }

    public boolean isDoesFunction() { return doesFunction; }

    public void setDoesFunction(boolean doesFunction) { this.doesFunction = doesFunction; }

    public Machine(String ref, String name, String category, String desc, boolean doesFunction) {
        this.ref = ref;
        this.name = name;
        this.category = category;
        this.desc = desc;
        this.doesFunction = doesFunction;
    }
}
