package com.fatec.easyhealthapi.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "objective")
public class Objective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name; // Ex: "Perder Peso"

    // Relação com os modelos de hábito.
    // O 'mappedBy' indica que a entidade HabitTemplate é a dona da relação.
    @ManyToMany(mappedBy = "objectives")
    private Set<HabitTemplate> habitTemplates;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<HabitTemplate> getHabitTemplates() { return habitTemplates; }
    public void setHabitTemplates(Set<HabitTemplate> habitTemplates) { this.habitTemplates = habitTemplates; }
}