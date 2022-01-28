package com.org.spring.gym.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String ref;
    private String name;
    private String desc;
    private String category;
    private String quantity;
    private String price;

    public Product(String ref, String name, String desc, String category, String quantity, String price) {
        this.ref = ref;
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getRef() { return ref; }

    public void setRef(String ref) { this.ref = ref; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDesc() { return desc; }

    public void setDesc(String desc) { this.desc = desc; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getQuantity() { return quantity; }

    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }
}
