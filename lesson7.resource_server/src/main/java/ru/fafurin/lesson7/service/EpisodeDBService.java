package ru.fafurin.lesson7.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.fafurin.lesson7.dto.EpisodeDTO;
import ru.fafurin.lesson7.mapper.EpisodeMapper;
import ru.fafurin.lesson7.model.Episode;
import ru.fafurin.lesson7.repository.EpisodeRepository;

@Service
@AllArgsConstructor
public class EpisodeDBService {

    private final ObjectMapper mapper;
    private final Environment environment;
    private final EpisodeRepository repository;

    public String saveAllEpisodesToDB() throws JsonProcessingException {
        int episodesCount = getEpisodesCount();
        for (int i = 1; i <= episodesCount; i++) {
            saveEpisodesToDB(environment.getProperty("EPISODE_API") + i);
        }
        return "OK!";
    }

    private void saveEpisodesToDB(String url) throws JsonProcessingException {
        EpisodeDTO episodeDTO = mapper.readValue(WebLoaderService.downloadWebPage(url), EpisodeDTO.class);
        Episode episode = EpisodeMapper.getEpisode(episodeDTO);
        repository.save(episode);
    }

    private int getEpisodesCount() {
        String res = WebLoaderService.downloadWebPage(environment.getProperty("EPISODE_API"));
        res = res.substring(res.indexOf("count"), res.indexOf("pages"));
        res = res.replaceAll("[^0-9]+", "");
        return Integer.parseInt(res);
    }

}
