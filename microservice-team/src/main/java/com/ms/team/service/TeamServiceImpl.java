package com.ms.team.service;

import com.ms.team.model.Team;
import com.ms.team.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository){this.teamRepository = teamRepository;}
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
        return teamRepository.save(team);
    }

    @Override
    public void deleteById(String id) {
        teamRepository.deleteById(id);
    }
}
