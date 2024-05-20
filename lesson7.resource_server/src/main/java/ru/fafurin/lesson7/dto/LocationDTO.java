package ru.fafurin.lesson7.dto;

import lombok.Data;

@Data
public class LocationDTO {
    private Integer id;
    private String name;
    private String type;
    private String dimension;
    private String[] residents;
    private String url;
    private String created;
}
