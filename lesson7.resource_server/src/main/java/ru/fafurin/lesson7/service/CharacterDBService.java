package ru.fafurin.lesson7.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.fafurin.lesson7.dto.CharacterDTO;
import ru.fafurin.lesson7.mapper.CharacterMapper;
import ru.fafurin.lesson7.model.Character;
import ru.fafurin.lesson7.model.Episode;
import ru.fafurin.lesson7.model.Location;
import ru.fafurin.lesson7.repository.CharacterRepository;
import ru.fafurin.lesson7.repository.EpisodeRepository;
import ru.fafurin.lesson7.repository.LocationRepository;

@Service
@AllArgsConstructor
public class CharacterDBService {

    private final Environment environment;
    private final CharacterRepository characterRepository;
    private final LocationRepository locationRepository;
    private final EpisodeRepository episodeRepository;
    private final ObjectMapper mapper;

    public void saveAllCharactersToDB() throws JsonProcessingException {
        int charactersCount = getCharactersCount();
        for (int i = 1; i <= charactersCount; i++) {
            saveCharacterToDB(environment.getProperty("CHARACTER_API") + i);
        }
    }

    private void saveCharacterToDB(String url) throws JsonProcessingException {
        CharacterDTO characterDTO = mapper.readValue(WebLoaderService.downloadWebPage(url), CharacterDTO.class);
        Character character = CharacterMapper.getCharacter(characterDTO);

        for (String episodeUrl : characterDTO.getEpisode()) {
            String episodeId = episodeUrl.replaceAll("[^0-9]+", "");
            Episode episode = episodeRepository.findById(Integer.valueOf(episodeId)).get();
            character.addEpisode(episode);
        }

        String locationUrl = characterDTO.getLocation().getUrl();
        if (!locationUrl.equals("")) {
            String locationId = locationUrl.replaceAll("[^0-9]+", "");
            Location location = locationRepository.findById(Integer.valueOf(locationId)).get();
            location.addResident(character);
        }
        characterRepository.save(character);
    }

    private int getCharactersCount() {
        String res = WebLoaderService.downloadWebPage(environment.getProperty("CHARACTER_API"));
        res = res.substring(res.indexOf("count"), res.indexOf("pages"));
        res = res.replaceAll("[^0-9]+", "");
        return Integer.parseInt(res);
    }
}
