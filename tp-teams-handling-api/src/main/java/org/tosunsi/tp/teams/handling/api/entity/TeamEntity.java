package org.tosunsi.tp.teams.handling.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class TeamEntity {

    public TeamEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slogan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    // -------------------------------------------------------------------------------
    // Fluent setters
    // -------------------------------------------------------------------------------

    public TeamEntity id(Long id) {
        this.id = id;
        return this;
    }

    public TeamEntity name(String name) {
        this.name = name;
        return this;
    }

    public TeamEntity slogan(String slogan) {
        this.slogan = slogan;
        return this;
    }
}
