package com.joe.wordGraph.entity;

import jakarta.persistence.*;

@Entity
@Table(name="graphs")
public class Graph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="graph_address")
    private String graph_address;

    public Graph(String name, String graph_address) {
        this.name = name;
        this.graph_address = graph_address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGraph_address() {
        return graph_address;
    }

    public void setGraph_address(String graph_address) {
        this.graph_address = graph_address;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", graph_address='" + graph_address + '\'' +
                '}';
    }
}
