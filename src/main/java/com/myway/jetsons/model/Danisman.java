package com.myway.jetsons.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @author Ebru Göksal
 */
@Entity
@Table(name = "danisman")
public class Danisman {

    @ApiModelProperty(value = "The ID for Danisman")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_danisman")
    @SequenceGenerator(name = "seq_danisman", sequenceName = "seq_danisman", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "ofis_id")
    private Ofis ofis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ofis getOfis() {
        return ofis;
    }

    public void setOfis(Ofis ofis) {
        this.ofis = ofis;
    }
}
