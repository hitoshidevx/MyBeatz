package main.java.br.com.mybeatz.beat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity

public class Beat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "o campo título do beat é OBRIGATÓRIO")
    private String beatTitle;

    @Size(min = 10, message = "a descrição deve ter PELO MENOS 10 caractéres")
    private String beatDescription;

    @Size(min = 3, message = "o genero deve ter PELO MENOS 3 caractéres")
    private String beatGenre;

}