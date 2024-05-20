package ru.fafurin.lesson7.client.model;

import lombok.Data;

@Data
public class Location {
    private Integer id;
    private String name;
    private String type;
    private String dimension;
    private String url;
    private String created;
}
