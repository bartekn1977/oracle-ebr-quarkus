package org.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "categorySeq")
    @SequenceGenerator(name = "categorySeq", sequenceName = "CATEGORY_SEQ", allocationSize = 1)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PetEntity> pets;

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public List<PetEntity> getPets() {
        return pets;
    }


}
