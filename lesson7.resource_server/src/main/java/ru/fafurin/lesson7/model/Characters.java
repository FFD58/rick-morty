package ru.fafurin.lesson7.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Characters {
    private List<Character> characters = new ArrayList<>();

    public void addCharacter(Character character) {
        characters.add(character);
    }

}
