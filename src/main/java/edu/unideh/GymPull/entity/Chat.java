package edu.unideh.GymPull.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idchat;

    private LocalDateTime fecha;

    private Integer idcliente;

    /**
     * Tipo de mensaje:
     * 1 = Enviado por el cliente
     * 2 = Respuesta
     */
    private Integer tipo;

    @Column(length = 200)
    private String mensaje;
}
