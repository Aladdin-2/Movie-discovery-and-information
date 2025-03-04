package com.aladdin.managing_movie_rental_system.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @JsonProperty("Title")
    private String title;

    @Column(name = "actors")
    @JsonProperty("Actors")
    private String actors;

    @JsonProperty("Writer")
    @Column(name = "writer")
    private String writer;

    @JsonProperty("Language")
    @Column(name = "language")
    private String language;

    @JsonProperty("Genre")
    @Column(name = "genre")
    private String genre;

    @JsonProperty("Plot")
    @Column(name = "description")
    private String description;

    @JsonProperty("Runtime")
    @Column(name = "runtime")
    private String runtime;

    @Column(name = "imdbRating")
    private String imdbRating;

    @JsonProperty("Website")
    @Column(name = "movie_site")
    private String website;

    @JsonProperty("Poster")
    @Column(name = "movie_link")
    private String link;

    @ManyToMany(mappedBy = "movies")
    @JsonBackReference
    private Set<User> users = new HashSet<>();

}
