package com.example.poker.controller;

import com.example.poker.service.PokerService;
import com.example.poker.domain.Card;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/poker")
@OpenAPIDefinition(info = @Info(title = "Poker Hand Analyzer API", version = "1.0",
        description = "There are 52 cards total. Each card has a suit [(H) Hearts, (C) Clubs, (D) Diamonds or (S) Spades] " +
                "and a value [Ace, 2-9, (T) 10 , (J) Jack, (Q) Queen or (K) King]." +
                "A sample 7 hand card request body: [\"9D\", \"8C\",\"2S\", \"3C\", \"4H\", \"5D\", \"6C\"]"))
public class MainController{

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private PokerService service;

    //@Autowired
    public MainController(PokerService service){
        this.service = service;
    }
    
    @PostMapping(value = "/isstraight", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Determines if the provided hand is a straight. \n\n Sample request body: [\"9D\", \"8C\",\"2S\", \"3C\", \"4H\", \"5D\", \"6C\"]\n" +
                    "\n",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Boolean> isStraight( @RequestBody List<Card> hand) {
        try {
            boolean result = service.isStraight(hand);
            return ResponseEntity.ok(result);
        } 
        catch (IllegalArgumentException e){
            logger.error("Bad request: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        catch (Exception e) {
            logger.error("Unexpected error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

}
