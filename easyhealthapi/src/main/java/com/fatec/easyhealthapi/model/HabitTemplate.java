package com.fatec.easyhealthapi.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "habit_template")
public class HabitTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description; // Ex: "Correr 5 km por dia"

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "objective_habit",
            joinColumns = @JoinColumn(name = "habit_template_id"),
            inverseJoinColumns = @JoinColumn(name = "objective_id")
    )
    private Set<Objective> objectives;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Set<Objective> getObjectives() { return objectives; }
    public void setObjectives(Set<Objective> objectives) { this.objectives = objectives; }
}