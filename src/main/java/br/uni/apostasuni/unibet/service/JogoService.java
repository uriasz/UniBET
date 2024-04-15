package br.uni.apostasuni.unibet.service;

import br.uni.apostasuni.unibet.model.ETipoResultado;
import br.uni.apostasuni.unibet.model.Jogo;
import br.uni.apostasuni.unibet.model.Time;
import br.uni.apostasuni.unibet.model.Usuario;
import br.uni.apostasuni.unibet.model.dao.JogoDAO;
import br.uni.apostasuni.unibet.model.dao.TimeDAO;
import br.uni.apostasuni.unibet.model.dto.JogoInputDTO;
import br.uni.apostasuni.unibet.model.dto.JogoViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    @Autowired
    JogoDAO jDAO;

    public List<Jogo> findAll() {
        return jDAO.findAll();
    }

    public Object findById(int id) {
        return jDAO.findById();
    }

    public List<JogoViewDTO> findByDate(LocalDateTime dataJogo) {
        List<Jogo> listaJogosPorData = jDAO.findByDataJogo(dataJogo);

        List<JogoViewDTO> listaJogosPorDataDTO = new ArrayList<>();

        for (Jogo j: listaJogosPorData){
            JogoViewDTO partida = new JogoViewDTO();
            partida.setDataJogo(j.getDataJogo());
            partida.setTimeA(j.getTimeA());
            partida.setTimeB(j.getTimeB());
            partida.setResultado(j.getResultado());
            listaJogosPorDataDTO.add(partida);
        }

        return listaJogosPorDataDTO;
    }

    public Jogo updateJogo(int id,Jogo jogo) throws Exception{
        Optional<Jogo> match = jDAO.findById(id);
        if (match.isEmpty()){
            throw new Exception("Jogo não encontrado.");
        }
        if (match.get().getResultado() != ETipoResultado.AGUARDANDO){
            throw new Exception("Jogo já possiu um resultado");
        }
        return jogo;
    }


    public Jogo salvarJogo(Jogo jogo) throws Exception{
        Optional<Jogo> j = jDAO.findById(jogo.getIdJogo());
        if ( !j.isEmpty()){
            throw new Exception("jogo ja existe");
        }
        return jDAO.save(jogo);
    }


    public void deletarJogo(@PathVariable int id) throws Exception {
        Optional<Jogo> j = jDAO.findById(id);
        if (j.isPresent()){
            jDAO.delete(j.get());
        }
        else {
            throw new Exception("Jogo não encontrado");
        }
    }
}