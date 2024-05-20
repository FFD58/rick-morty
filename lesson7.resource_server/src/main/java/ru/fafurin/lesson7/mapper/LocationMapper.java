package ru.fafurin.lesson7.mapper;

import ru.fafurin.lesson7.dto.LocationDTO;
import ru.fafurin.lesson7.model.Location;

public class LocationMapper {

    public static Location getLocation(LocationDTO locationDTO) {
        Location location = new Location();
        location.setName(locationDTO.getName());
        location.setType(locationDTO.getType());
        location.setDimension(locationDTO.getDimension());
        location.setUrl(locationDTO.getUrl());
        location.setCreated(locationDTO.getCreated());
        return location;
    }
}
