package com.ms.team.controller;

import com.ms.team.model.Team;
import com.ms.team.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {


    private final TeamService teamService;
    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable String id) {
        return teamService.findById(id)
                .map(team -> ResponseEntity.ok().body(team))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.save(team);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable String id, @RequestBody Team teamDetails) {
        return teamService.findById(id)
                .map(team -> {
                    team.setTeam(teamDetails.getTeam());
                    Team updatedTeam = teamService.save(team);
                    return ResponseEntity.ok().body(updatedTeam);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTeam(@PathVariable String id) {
        return teamService.findById(id)
                .map(team -> {
                    teamService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
