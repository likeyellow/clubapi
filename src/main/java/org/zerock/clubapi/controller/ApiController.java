package org.zerock.clubapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.clubapi.dto.NoteDTO;
import org.zerock.clubapi.service.NoteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ApiController {
    
    private final NoteService noteService; 

    @GetMapping("/list")
    public void apiList() {

        log.info("----------------api--------------------");
        //log.info(noteDTO);

        //Long num = noteService.register(noteDTO); 

  
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoteDTO>> getList(String email) {

        log.info("---------------------getList--------------------");
        log.info(email);

        return new ResponseEntity<>(noteService.getAllWithWriter(email), HttpStatus.OK);
    }
}
