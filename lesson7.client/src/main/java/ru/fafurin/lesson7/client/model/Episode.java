package ru.fafurin.lesson7.client.model;

import lombok.Data;

@Data
public class Episode {
    private Integer id;
    private String name;
    private String airDate;
    private String episode;
    private String url;
    private String created;
}
