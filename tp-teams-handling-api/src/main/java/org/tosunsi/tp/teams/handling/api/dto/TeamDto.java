package org.tosunsi.tp.teams.handling.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TeamDto(@JsonProperty("id") Long id,
                      @JsonProperty("name") String name,
                      @JsonProperty("slogan") String slogan) {

    public TeamDto(@JsonProperty("name") String name,
                   @JsonProperty("slogan") String slogan) {
        this(null, name, slogan);
    }
}
