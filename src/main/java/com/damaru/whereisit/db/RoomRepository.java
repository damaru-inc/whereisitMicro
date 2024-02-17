package com.damaru.whereisit.db;

import com.damaru.whereisit.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
