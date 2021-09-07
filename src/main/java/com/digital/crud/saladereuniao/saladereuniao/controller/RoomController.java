package com.digital.crud.saladereuniao.saladereuniao.controller;

import com.digital.crud.saladereuniao.saladereuniao.exception.ResourceNotFoundException;
import com.digital.crud.saladereuniao.saladereuniao.model.Room;
import com.digital.crud.saladereuniao.saladereuniao.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class RoomController {

    private final RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return this.roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Room room = this.roomRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Room not found:: " + id));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@RequestBody Room room) {
        return this.roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") Long id, @RequestBody Room roomDetails) throws ResourceNotFoundException {
        Room room = this.roomRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Room not found:: " + id));
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartHour(roomDetails.getStartHour());
        room.setEndHour(roomDetails.getEndHour());

        final Room updateRoom = this.roomRepository.save(room);

        return ResponseEntity.ok().body(updateRoom);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Room room = this.roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found:: " + id));

        this.roomRepository.delete(room);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }
}
