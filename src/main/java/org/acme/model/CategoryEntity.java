package org.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    private Long id;
    private String name;
    private List<PetEntity> pets;

    @Id
    @JsonIgnore
    @SequenceGenerator(name = "CATEGORY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "CATEGORY_SEQ")
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    public List<PetEntity> getPets() {
        return pets;
    }


}
