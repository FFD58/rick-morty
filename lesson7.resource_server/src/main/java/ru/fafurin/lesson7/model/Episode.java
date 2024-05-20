package ru.fafurin.lesson7.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String airDate;

    private String episode;

    @JsonIgnore
    @ManyToMany(mappedBy = "episodes")
    private List<Character> characters = new ArrayList<>();

    private String url;

    private String created;

    public void addCharacter(Character character) {
        if (character != null) {
            characters.add(character);
        }
    }
}
