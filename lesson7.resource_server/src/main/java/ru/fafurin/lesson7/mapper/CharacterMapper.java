package ru.fafurin.lesson7.mapper;

import ru.fafurin.lesson7.dto.CharacterDTO;
import ru.fafurin.lesson7.model.Character;

public class CharacterMapper {

    public static Character getCharacter(CharacterDTO characterDTO) {
        Character character = new Character();
        character.setName(characterDTO.getName());
        character.setStatus(characterDTO.getStatus());
        character.setSpecies(characterDTO.getSpecies());
        character.setType(characterDTO.getType());
        character.setGender(characterDTO.getGender());
        character.setImage(characterDTO.getImage());
        character.setUrl(characterDTO.getUrl());
        character.setCreated(characterDTO.getCreated());
        character.setOrigin(characterDTO.getOrigin());
        return character;
    }
}
