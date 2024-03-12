package com.damaru.whereisit.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="room")
public class Room implements Saveable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="room_seq")
    @SequenceGenerator(name="room_seq",sequenceName="room_sequence", allocationSize=1)
    private Long id;

//    @NotNull
//    @Size(min = 1, max = 128)
    private String name;

    public Room() {
    }

    public Room(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[Room %3d %s]", id, name);
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
        Room other = (Room) obj;
        return Objects.equals(id, other.id);
    }

}
