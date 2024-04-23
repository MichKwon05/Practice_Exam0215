package com.mich.crudPractice.controllers;

import com.mich.crudPractice.dto.HistoryDto;
import com.mich.crudPractice.models.history.History;
import com.mich.crudPractice.service.HistoryService;
import com.mich.crudPractice.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history/")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<History>>> getAll(){
        return new ResponseEntity<>(
                this.historyService.getAll(),
                HttpStatus.OK
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<History>> getOne(
            @PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.historyService.getOne(id),
                HttpStatus.OK
                );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<History>> insert(
            @RequestBody HistoryDto dto, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        CustomResponse<History> response = this.historyService.save(dto.getHistory());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<History>> update(
            @RequestBody HistoryDto dto, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.historyService.update(dto.getHistory()),
                HttpStatus.CREATED
        );
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> deleteById(Long id) {
        return new ResponseEntity<>(
                this.historyService.delete(id),
                HttpStatus.OK
        );
    }*/


}
