package com.ms.player.service;


import com.ms.player.dto.PlayerDTO;
import com.ms.player.dto.DTOPlayer;
import com.ms.player.dto.TeamDto;
import com.ms.player.feign.ITeamClient;
import com.ms.player.model.Player;
import com.ms.player.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements IPlayerService {

    private final PlayerRepository playerRepository;
    private final ITeamClient ITeamClient;
    private final TeamService teamService;

    public PlayerServiceImpl(PlayerRepository playerRepository, ITeamClient ITeamClient, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.ITeamClient = ITeamClient;
        this.teamService = teamService;
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public PlayerDTO getPlayerWithTeamInfo(Long id) {
        Optional<Player> playerOpt = playerRepository.findById(id);
        if (playerOpt.isPresent()) {
            Player player = playerOpt.get();
            TeamDto teamDTO = teamService.getTeamById(player.getTeamId());
            return new PlayerDTO(player.getName(), player.getNumber(), teamDTO.team());
        } else {
            return null; // or throw an exception
        }
    }
    @Override
    public DTOPlayer getPlayerWithTeamDto(Long id) {
        Optional<Player> playerOpt = playerRepository.findById(id);
        if (playerOpt.isPresent()) {
            Player player = playerOpt.get();
            TeamDto teamDTO = teamService.getTeamByIdRest(player.getTeamId());
            return new DTOPlayer(player.getName(), player.getNumber(), teamDTO);

        } else {
            return null;
        }
    }
}

