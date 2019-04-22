package com.myway.jetsons.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @author Ebru Göksal
 */
@Entity
@Table(name = "ofis")
public class Ofis {
    @ApiModelProperty(value = "The ID for Ofis")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ofis")
    @SequenceGenerator(name = "seq_ofis", sequenceName = "seq_ofis", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "emlakci_id")
    private Emlakci emlakci;

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

    public Emlakci getEmlakci() {
        return emlakci;
    }

    public void setEmlakci(Emlakci emlakci) {
        this.emlakci = emlakci;
    }
}
