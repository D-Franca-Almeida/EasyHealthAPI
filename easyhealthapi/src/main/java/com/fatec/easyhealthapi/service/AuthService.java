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
@RequiredArgsConstructor // Anotação do Lombok que cria um construtor com todos os campos `final`. É uma forma moderna de fazer injeção de dependência.
public class AuthService {

<<<<<<< HEAD
    private PersonRepository personRepository;
    private PacienteRepository pacienteRepository;
    private ProfissionalRepository profissionalRepository;
=======
    // Injeção de dependências via construtor (melhor prática)
    private final PersonRepository personRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
>>>>>>> parent of f2f66c5 (refatorando código e adicionando jwt)
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Cadastra um novo Paciente no sistema.
     * A senha é criptografada antes de ser salva.
     */
    public Paciente signupPaciente(Map<String, String> body) throws Exception {
        if (personRepository.findByEmail(body.get("email")).isPresent()) {
            throw new Exception("E-mail já cadastrado");
        }
        if (personRepository.findByCpf(body.get("cpf")).isPresent()) {
            throw new Exception("CPF já cadastrado");
        }

        Paciente paciente = new Paciente();
        paciente.setNome(body.get("nome"));
        paciente.setEmail(body.get("email"));
        // CRIPTOGRAFANDO A SENHA
        paciente.setSenha(passwordEncoder.encode(body.get("senha")));
        paciente.setCpf(body.get("cpf"));
        paciente.setTelefone(body.get("telefone"));
        paciente.setEndereco(body.get("endereco"));
        paciente.setDataNascimento(LocalDate.parse(body.get("dataNascimento"), DATE_FORMATTER));
        paciente.setGenero(Genero.valueOf(body.get("genero").toUpperCase()));
        paciente.setAltura(Double.parseDouble(body.get("altura")));
        paciente.setPeso(Double.parseDouble(body.get("peso")));
        paciente.setEstadoCivil(body.get("estadocivil"));
        paciente.setNacionalidade(body.get("nacionalidade"));
        paciente.setNomeSocial(body.get("nomesocial"));
        paciente.setOcupacao(body.get("ocupacao"));

        return pacienteRepository.save(paciente);
    }

    /**
     * Cadastra um novo Profissional no sistema.
     * A senha é criptografada antes de ser salva.
     */
    public Profissional signupProfissional(Map<String, String> body) throws Exception {
        if (personRepository.findByEmail(body.get("email")).isPresent()) {
            throw new Exception("E-mail já cadastrado");
        }
        if (personRepository.findByCpf(body.get("cpf")).isPresent()) {
            throw new Exception("CPF já cadastrado");
        }

        Profissional profissional = new Profissional();
        profissional.setNome(body.get("nome"));
        profissional.setEmail(body.get("email"));
        // CRIPTOGRAFANDO A SENHA
        profissional.setSenha(passwordEncoder.encode(body.get("senha")));
        profissional.setCpf(body.get("cpf"));
        profissional.setTelefone(body.get("telefone"));
        profissional.setEndereco(body.get("endereco"));
        profissional.setDataNascimento(LocalDate.parse(body.get("dataNascimento"), DATE_FORMATTER));
        profissional.setGenero(Genero.valueOf(body.get("genero").toUpperCase()));
        profissional.setEspecialidade(body.get("especialidade"));
        profissional.setIdentificacao(body.get("identificacao"));

        return profissionalRepository.save(profissional);
    }

    /**
     * Autentica um usuário e retorna um token JWT.
     */
    public String signin(String email, String senha) {
        // 1. O AuthenticationManager usa o UserDetailsService e o PasswordEncoder que configuramos
        // para verificar se o usuário existe e se a senha (criptografada) confere.
        // Se as credenciais estiverem erradas, ele lançará uma exceção (que o controller irá capturar).
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, senha)
        );

        // 2. Se a linha acima não lançou uma exceção, o usuário está autenticado.
        // Agora, buscamos os dados completos da pessoa para gerar o token.
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuário autenticado não encontrado no banco de dados."));

        // 3. Geramos e retornamos o token JWT.
        return jwtService.generateToken(person);
    }
}