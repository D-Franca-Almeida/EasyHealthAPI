package com.fatec.easyhealthapi.service;

import com.fatec.easyhealthapi.enums.Genero;
import com.fatec.easyhealthapi.model.Paciente;
import com.fatec.easyhealthapi.model.Person;
import com.fatec.easyhealthapi.model.Profissional;
import com.fatec.easyhealthapi.repository.PacienteRepository;
import com.fatec.easyhealthapi.repository.PersonRepository;
import com.fatec.easyhealthapi.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PersonRepository personRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Cadastra um novo Paciente no sistema.
     * A lógica foi simplificada para chamar os métodos de ajuda.
     */
    public Paciente signupPaciente(Map<String, String> body) throws Exception {
        // 1. Valida se o email ou CPF já existem
        validateNewUser(body.get("email"), body.get("cpf"));

        // 2. Cria a instância e preenche os dados comuns da Pessoa
        Paciente paciente = new Paciente();
        populatePersonFromMap(paciente, body);

        // 3. Preenche os dados específicos do Paciente
        paciente.setAltura(Double.parseDouble(body.get("altura")));
        paciente.setPeso(Double.parseDouble(body.get("peso")));
        paciente.setEstadoCivil(body.get("estadocivil"));
        paciente.setNacionalidade(body.get("nacionalidade"));
        paciente.setNomeSocial(body.get("nomesocial"));
        paciente.setOcupacao(body.get("ocupacao"));

        // 4. Salva no banco
        return pacienteRepository.save(paciente);
    }

    /**
     * Cadastra um novo Profissional no sistema.
     * A lógica foi simplificada para chamar os métodos de ajuda.
     */
    public Profissional signupProfissional(Map<String, String> body) throws Exception {
        // 1. Valida se o email ou CPF já existem
        validateNewUser(body.get("email"), body.get("cpf"));

        // 2. Cria a instância e preenche os dados comuns da Pessoa
        Profissional profissional = new Profissional();
        populatePersonFromMap(profissional, body);

        // 3. Preenche os dados específicos do Profissional
        profissional.setEspecialidade(body.get("especialidade"));
        profissional.setIdentificacao(body.get("identificacao"));

        // 4. Salva no banco
        return profissionalRepository.save(profissional);
    }

    /**
     * Autentica um usuário e retorna um token JWT.
     * Este método permanece igual.
     */
    public String signin(String email, String senha) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, senha)
        );

        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuário autenticado não encontrado no banco de dados."));

        return jwtService.generateToken(person);
    }

    // =====================================================================
    // MÉTODOS PRIVADOS DE AJUDA (HELPER METHODS)
    // =====================================================================

    /**
     * Método privado para validar se o e-mail ou CPF já existem no banco.
     * Evita a duplicação dessa verificação nos dois métodos de signup.
     */
    private void validateNewUser(String email, String cpf) throws Exception {
        if (personRepository.findByEmail(email).isPresent()) {
            throw new Exception("E-mail já cadastrado");
        }
        if (personRepository.findByCpf(cpf).isPresent()) {
            throw new Exception("CPF já cadastrado");
        }
    }

    /**
     * Método privado que popula os campos comuns da entidade Person
     * a partir do Map de dados. Centraliza a lógica de mapeamento e
     * a criptografia da senha.
     */
    private void populatePersonFromMap(Person person, Map<String, String> body) {
        person.setNome(body.get("nome"));
        person.setEmail(body.get("email"));
        person.setSenha(passwordEncoder.encode(body.get("senha")));
        person.setCpf(body.get("cpf"));
        person.setTelefone(body.get("telefone"));
        person.setEndereco(body.get("endereco"));
        person.setDataNascimento(LocalDate.parse(body.get("dataNascimento"), DATE_FORMATTER));
        person.setGenero(Genero.valueOf(body.get("genero").toUpperCase()));
    }
}