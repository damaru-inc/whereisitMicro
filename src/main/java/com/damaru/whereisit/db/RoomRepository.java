package com.damaru.whereisit.db;

import com.damaru.whereisit.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findAll();
}
