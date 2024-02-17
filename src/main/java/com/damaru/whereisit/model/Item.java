package com.damaru.whereisit.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="item")
public class Item implements Saveable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="item_seq")
    @SequenceGenerator(name="item_seq",sequenceName="item_sequence", allocationSize=1)
    private Long id;

//    @NotNull
//    @Size(min = 1, max = 128)
    private String name;
    
//    @NotNull
    @ManyToOne
    private Container container;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return String.format("[Item %3d %s in %s]", id, name, (container == null ? "Null container" : container.toString()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        return Objects.equals(id, other.id);
    }

}
