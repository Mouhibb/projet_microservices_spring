package com.ms.player.service;


import com.ms.player.dto.PlayerDTO;
import com.ms.player.dto.DTOPlayer;
import com.ms.player.model.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerService {
    List<Player> findAll();
    Optional<Player> findById(Long id);
    Player save(Player medicine);
    void deleteById(Long id);
    PlayerDTO getPlayerWithTeamInfo(Long id);
    DTOPlayer getPlayerWithTeamDto(Long id);

}
