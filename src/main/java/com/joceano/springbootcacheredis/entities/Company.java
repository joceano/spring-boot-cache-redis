package com.joceano.springbootcacheredis.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
