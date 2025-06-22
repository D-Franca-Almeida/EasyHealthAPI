package com.fatec.easyhealthapi.config;

import com.fatec.easyhealthapi.model.HabitTemplate;
import com.fatec.easyhealthapi.model.Objective;
import com.fatec.easyhealthapi.repository.HabitTemplateRepository;
import com.fatec.easyhealthapi.repository.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private HabitTemplateRepository habitTemplateRepository;

    @Override
    public void run(String... args) throws Exception {
        // A condição de `count() == 0` está correta.
        if (objectiveRepository.count() == 0) {
            System.out.println("Populando o catálogo de hábitos e objetivos...");

            // --- 1. CRIAR OBJETOS (sem relação ainda) ---
            HabitTemplate beberAgua = new HabitTemplate();
            beberAgua.setDescription("Beber 2L de água por dia");
            // Inicializa a coleção para evitar NullPointerException
            beberAgua.setObjectives(new HashSet<>());

            HabitTemplate correr5km = new HabitTemplate();
            correr5km.setDescription("Correr 5km");
            correr5km.setObjectives(new HashSet<>());

            HabitTemplate meditar10min = new HabitTemplate();
            meditar10min.setDescription("Meditar por 10 minutos");
            meditar10min.setObjectives(new HashSet<>());

            Objective perderPeso = new Objective();
            perderPeso.setName("Perder Peso");
            perderPeso.setHabitTemplates(new HashSet<>());

            Objective reduzirEstresse = new Objective();
            reduzirEstresse.setName("Reduzir Estresse");
            reduzirEstresse.setHabitTemplates(new HashSet<>());

            // --- 2. SALVAR AS ENTIDADES PRIMEIRO ---
            // Isso garante que todas elas tenham IDs antes de criarmos as relações.
            habitTemplateRepository.saveAll(Arrays.asList(beberAgua, correr5km, meditar10min));
            objectiveRepository.saveAll(Arrays.asList(perderPeso, reduzirEstresse));

            // --- 3. CONFIGURAR A RELAÇÃO (O LADO DONO) ---
            // Para o objetivo "Perder Peso"
            beberAgua.getObjectives().add(perderPeso);
            correr5km.getObjectives().add(perderPeso);

            // Para o objetivo "Reduzir Estresse"
            meditar10min.getObjectives().add(reduzirEstresse);
            correr5km.getObjectives().add(reduzirEstresse); // Note que correr5km pertence a ambos

            // --- 4. SALVAR O LADO DONO DA RELAÇÃO ---
            // Ao salvar os HabitTemplates agora, o JPA irá popular a tabela de junção `objective_habit`.
            habitTemplateRepository.saveAll(Arrays.asList(beberAgua, correr5km, meditar10min));

            System.out.println("Catálogo populado com sucesso!");
        }
    }
}