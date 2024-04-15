package com.joe.wordImage.entity;

import jakarta.persistence.*;

/**
 * 图片实体类
 */
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
    private String address;

    public Graph(String name, String graph_address) {
        this.name = name;
        this.address = graph_address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String graph_address) {
        this.address = graph_address;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", graph_address='" + address + '\'' +
                '}';
    }
}
