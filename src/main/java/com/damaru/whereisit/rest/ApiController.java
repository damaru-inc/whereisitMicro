package com.damaru.whereisit.rest;

import com.damaru.whereisit.db.RoomRepository;
import com.damaru.whereisit.model.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private final RoomRepository roomRepository;

    public ApiController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/rooms")
    public @ResponseBody Iterable<Room> rooms() {
        return roomRepository.findAll();
    }
}
