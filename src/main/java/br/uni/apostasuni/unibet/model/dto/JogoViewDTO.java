package br.uni.apostasuni.unibet.model.dto;


import br.uni.apostasuni.unibet.model.ETipoResultado;
import br.uni.apostasuni.unibet.model.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class JogoViewDTO {

    private LocalDateTime dataJogo;
    private Time timeA;
    private Time timeB;
    private ETipoResultado resultado;
}
