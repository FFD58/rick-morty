package ru.fafurin.lesson7.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class CharacterDTO {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String url;
    private Date created;
    private CharacterLocationDTO location;
    private String[] episode;
    @JsonIgnore
    private String origin;
}
