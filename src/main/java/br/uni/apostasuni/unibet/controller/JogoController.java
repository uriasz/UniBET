package br.uni.apostasuni.unibet.controller;

import br.uni.apostasuni.unibet.model.Jogo;
import br.uni.apostasuni.unibet.model.Time;
import br.uni.apostasuni.unibet.model.dao.JogoDAO;
import br.uni.apostasuni.unibet.model.dto.JogoInputDTO;
import br.uni.apostasuni.unibet.model.dto.JogoViewDTO;
import br.uni.apostasuni.unibet.service.JogoService;
import br.uni.apostasuni.unibet.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    @Autowired
    JogoDAO jDAO;

    @Autowired
    JogoService jogoService;

    @GetMapping("")
    public ResponseEntity<?> getJogos() {
        return ResponseEntity.ok(jogoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJogoById(@PathVariable int id){
         return ResponseEntity.ok(jogoService.findById(id));
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getJogoByDate(@PathVariable LocalDateTime dataJogo){
        try {
            List<JogoViewDTO> j = jogoService.findByDate(dataJogo);
            return ResponseEntity.ok(j);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJogo(@PathVariable int id,
                                        @RequestBody Jogo jogo){
        try{
            Jogo j = jogoService.updateJogo(id, jogo);
            return ResponseEntity.ok(jogo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> criarJogo(@RequestBody Jogo jogo) {
        try {jogoService.salvarJogo(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarJogo(@PathVariable int id) {
        try {
            jogoService.deletarJogo(id);
            return ResponseEntity.ok("Jogo removido com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
    
