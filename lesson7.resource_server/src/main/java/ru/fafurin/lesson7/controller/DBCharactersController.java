package ru.fafurin.lesson7.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fafurin.lesson7.model.Characters;
import ru.fafurin.lesson7.repository.CharacterRepository;
import ru.fafurin.lesson7.service.CharacterDBService;
import ru.fafurin.lesson7.service.EpisodeDBService;
import ru.fafurin.lesson7.service.LocationDBService;

import java.util.List;

@RestController
@AllArgsConstructor
public class DBCharactersController {

    private CharacterDBService characterDBService;
    private LocationDBService locationDBService;
    private EpisodeDBService episodeDBService;

    private CharacterRepository characterRepository;

    @GetMapping("/load")
    public ResponseEntity<String> load() {
        try {
            locationDBService.saveAllLocationsToDB();
            episodeDBService.saveAllEpisodesToDB();
            characterDBService.saveAllCharactersToDB();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/send")
    public ResponseEntity<Characters> sendAllCharacters() {
        load();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        Characters characters = new Characters();
        for (int i = 1; i <= 12; i++) {
            characters.addCharacter(characterRepository.findById(i).get());
        }
        return new ResponseEntity<>(characters, headers, HttpStatus.OK);
    }
}
