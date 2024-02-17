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
@Table(name="container")
public class Container implements Saveable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="container_seq")
    @SequenceGenerator(name="container_seq",sequenceName="container_sequence", allocationSize=1)
    private Long id;

//    @NotNull
//    @Size(min = 1, max = 128)
    private String name;
    
//    @NotNull
    @ManyToOne
    private Room room;

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
    
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    private String getRoomName() {
        return room == null ? "Null room" : room.getName();
    }
    
    public String getFullName() {
        return name + ": " + getRoomName();
    }

    @Override
    public String toString() {
        return String.format("[Container %3d %s in %s]", id, name, getRoomName());
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
        Container other = (Container) obj;
        return Objects.equals(id, other.id);
    }

}
