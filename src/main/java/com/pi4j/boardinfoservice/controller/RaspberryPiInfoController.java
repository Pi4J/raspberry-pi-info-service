package com.pi4j.boardinfoservice.controller;

import com.pi4j.boardinfo.definition.BoardModel;
import com.pi4j.boardinfoservice.service.Pi4JInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/raspberrypi")
public class RaspberryPiInfoController {

    private final Pi4JInfoService pi4JInfoService;

    public RaspberryPiInfoController(Pi4JInfoService pi4JInfoService) {
        this.pi4JInfoService = pi4JInfoService;
    }

    @GetMapping("/board")
    public List<BoardModel> getBoards() {
        return pi4JInfoService.getRaspberryPiBoards();
    }

    @GetMapping("/board/{name}")
    public ResponseEntity<BoardModel> getBoardByName(@PathVariable String name) {
        var board = pi4JInfoService.getRaspberryPiBoardByName(name);
        if (board.isPresent()) {
            return ResponseEntity.ok().body(board.get());
        }
        return ResponseEntity.notFound().build();
    }
}
