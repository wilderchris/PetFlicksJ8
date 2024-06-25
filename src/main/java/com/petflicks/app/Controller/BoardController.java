package com.petflicks.app.Controller;

import com.petflicks.app.Models.Board;
import com.petflicks.app.Service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/board/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class BoardController {

    private BoardService boardServ;

    public BoardController() {
        super();
    }

    @PostMapping
    public ResponseEntity<Map<String,Integer>> create(@RequestBody Board newBoard) {

        try
        {
            newBoard = boardServ.create(newBoard);
            Map<String, Integer> newIdMap = new HashMap<>();
            newIdMap.put("generatedId", newBoard.getBoardId());
            return ResponseEntity.status(HttpStatus.CREATED).body(newIdMap);

        }catch (Exception e)	{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path="/v1/{boardId}")//
    public ResponseEntity<Board> getBoardById(@PathVariable int boardId){
        System.out.println("get by board id");
        Board board = boardServ.findById(boardId);
        if (board != null) {
            return ResponseEntity.ok(board);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping(path="/{boardId}")//
    public ResponseEntity<Board> updateBoard(@PathVariable int boardId,
                                         @RequestBody Board boardToEdit) {

        if (boardToEdit != null && boardToEdit.getBoardId() == boardId) {
            boardToEdit = boardServ.update(boardToEdit);

            if (boardToEdit != null)
                return ResponseEntity.ok(boardToEdit);
            else
                return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(path="/{boardId}")//
    public ResponseEntity<Void> deleteBoard(@RequestBody Board deleteBoard) {
        if (deleteBoard != null) {
            boardServ.delete(deleteBoard);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
