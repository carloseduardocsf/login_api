package br.carlos.api;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
    //http://localhost:8080/pessoa

    @Autowired
    private PessoaRepository repository;

    @PostMapping
    public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa){
        Pessoa p = repository.save(pessoa);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoa>> listar(){
        List<Pessoa> lista = repository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> consultarPorId(@PathVariable("id") Integer id){
        Pessoa pessoa = repository.findById(id).get();
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPorId(@PathVariable("id") Integer id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Pessoa> atualizar(@RequestBody Pessoa pessoa){

        if(Objects.isNull(pessoa.getId()) || !repository.existsById(pessoa.getId())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repository.save(pessoa));
    }


    @GetMapping
    public ResponseEntity<Pessoa> consultaPessoa(){
        Pessoa p = new Pessoa();
        p.setId(1);
        p.setCidade("Joao Pessoa");
        p.setSexo("Masculino");
        p.setNome("JOão da Silva");
        p.setIdade(19);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/idade")
    public String exibeIdadeProgramador(){
        return "Idade: 20";
    }

    @GetMapping("/nome")
    public String exibeNomeProgramador(){
        return "Carlos Eduardo";
    }

    @GetMapping("/idade/{idade}")
    public String verificaIdade(@PathVariable("idade") int idade){
        if(idade < 18){
            return "Essa pessoa n pode beber";
        }else {
            return "Beba a vontade";
        }
    }

    @GetMapping("/faculdade")
    public String faculdade(){
        return "Eng de comp";
    }
}
