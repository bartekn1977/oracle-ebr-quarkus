package org.acme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "PET")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "petSeq")
    @SequenceGenerator(name = "petSeq", sequenceName = "PET_SEQ", allocationSize = 1)
    private Long id;
    private String name;
    private String tags;
    private String status;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private CategoryEntity category;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

}
