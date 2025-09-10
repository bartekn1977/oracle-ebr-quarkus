package org.acme.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "CATEGORY")
public class Category {

    private Long id;
    private String name;

    @Id
    @SequenceGenerator(name = "CATEGORY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=SEQUENCE, generator="CATEGORY_SEQ")
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;

    public List<Pet> getPets() {
        return pets;
    }
}
