package org.acme.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "PET")
public class PetEntity {

    private Long id;
    private String name;
    private String tags;
    private String status;
    private CategoryEntity category;

    @Id
    @SequenceGenerator(name = "PET_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=SEQUENCE, generator="PET_SEQ")
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
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    public CategoryEntity getCategory() {
        return category;
    }

}
