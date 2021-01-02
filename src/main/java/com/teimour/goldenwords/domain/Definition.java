package com.teimour.goldenwords.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Definition {

    @Id
    @GeneratedValue
    @Column(name = "definition_id")
    private UUID uuid;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private WordClass wordClass;

    @NotBlank
    private String definitionValue;

    @NotEmpty
    @ElementCollection
    @JoinColumn(name = "definition_id")
    private Set<String> examples;
}
