package ru.fafurin.lesson7.dto;

import lombok.Data;
@Data
public class EpisodeDTO {
    private Integer id;
    private String name;
    private String air_date;
    private String episode;
    private String[] characters;
    private String url;
    private String created;
}
