package com.ms.team.service;

import com.ms.team.dto.PlayerDTO;
import com.ms.team.model.Team;
import com.ms.team.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private final KafkaProducerService kafkaProducerService;
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository, KafkaProducerService kafkaProducerService){
        this.teamRepository = teamRepository;
        this.kafkaProducerService = kafkaProducerService;
         }
    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(String id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team save(Team team) {

        Team savedTeam = teamRepository.save(team);




        // Appel asynchrone pour créer un joueur
        String message = "Create player for team ID: " + savedTeam.getId();
        kafkaProducerService.sendMessage(message);

        return savedTeam;
    }


    @Override
    public void deleteById(String id) {
        teamRepository.deleteById(id);
    }
}
