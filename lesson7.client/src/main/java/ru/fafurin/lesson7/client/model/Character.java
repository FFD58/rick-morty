package ru.fafurin.lesson7.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Character {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String url;
    private Date created;
    private Location location;
    private List<Episode> episodes = new ArrayList<>();
    @JsonIgnore
    private String origin;
}
