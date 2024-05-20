package ru.fafurin.lesson7.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.fafurin.lesson7.dto.LocationDTO;
import ru.fafurin.lesson7.mapper.LocationMapper;
import ru.fafurin.lesson7.model.Location;
import ru.fafurin.lesson7.repository.LocationRepository;

@Service
@AllArgsConstructor
public class LocationDBService {

    private final ObjectMapper mapper;
    private final Environment environment;
    private final LocationRepository repository;

    public void saveAllLocationsToDB() throws JsonProcessingException {
        int locationsCount = getLocationsCount();
        for (int i = 1; i <= locationsCount; i++) {
            saveLocationsToDB(environment.getProperty("LOCATION_API") + i);
        }
    }

    private void saveLocationsToDB(String url) throws JsonProcessingException {
        LocationDTO locationDTO = mapper.readValue(WebLoaderService.downloadWebPage(url), LocationDTO.class);
        Location location = LocationMapper.getLocation(locationDTO);
        repository.save(location);
    }

    private int getLocationsCount() {
        String res = WebLoaderService.downloadWebPage(environment.getProperty("LOCATION_API"));
        res = res.substring(res.indexOf("count"), res.indexOf("pages"));
        res = res.replaceAll("[^0-9]+", "");
        return Integer.parseInt(res);
    }

}
