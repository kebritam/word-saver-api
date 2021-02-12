package com.teimour.wordsapi.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Word {

    @Id
    @GeneratedValue
    @Column(name = "word_id")
    private UUID uuid;

    @NotBlank
    @Column(length = 30, unique = true, nullable = false)
    private String wordValue;

    @NotEmpty
    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = WordClass.class)
    @JoinTable(name = "word_class")
    private Set<@Valid WordClass> wordClasses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id")
    private Set<@Valid Definition> definitions;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id")
    private Note notes;

    @NotBlank
    @Column(length = 60, nullable = false)
    private String phonetic;

    @ManyToMany
    @JoinTable(name = "synonyms",
        joinColumns = @JoinColumn(name = "word_id"),
        inverseJoinColumns = @JoinColumn(name = "synonym_id"))
    private Set<@Valid Word> synonyms;

    @ManyToMany
    @JoinTable(name = "antonyms",
        joinColumns = @JoinColumn(name = "word_id"),
        inverseJoinColumns = @JoinColumn(name = "antonym_id"))
    private Set<@Valid Word> antonyms;

    @ManyToMany(mappedBy = "words")
    private Set<@Valid Category> categories;

}
