package com.ms.player.controller;

import com.ms.player.dto.PlayerDTO;
import com.ms.player.dto.DTOPlayer;
import com.ms.player.model.Player;
import com.ms.player.service.IPlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final IPlayerService playerService;

    public PlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return playerService.findById(id)
                .map(player -> ResponseEntity.ok().body(player))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.save(player);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {
        return playerService.findById(id)
                .map(player -> {
                    player.setName(playerDetails.getName());
                    player.setNumber(playerDetails.getNumber());
                    player.setTeamId(playerDetails.getTeamId());
                    Player updatedPlayer = playerService.save(player);
                    return ResponseEntity.ok().body(updatedPlayer);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlayer(@PathVariable Long id) {
        return playerService.findById(id)
                .map(player -> {
                    playerService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/with-team/{id}")
    public ResponseEntity<PlayerDTO> getPlayerWithTeamInfo(@PathVariable Long id) {
        PlayerDTO responseDTO = playerService.getPlayerWithTeamInfo(id);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/team/{id}")
    public ResponseEntity<DTOPlayer> getPlayerWithTeamDto(@PathVariable Long id) {
        DTOPlayer responseDTO = playerService.getPlayerWithTeamDto(id);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/team")
    public ResponseEntity<List<PlayerDTO>> getPlayerWithTeamDto() {

        return ResponseEntity.ok(playerService.findAll().stream().map(player -> {
            return playerService.getPlayerWithTeamInfo(player.getId());
        }).collect(Collectors.toList()));
    }
}
