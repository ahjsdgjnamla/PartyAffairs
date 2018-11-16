package com.hrdl.partyaffairs.entity;

/**
 * 案件举报类型
 */
public class AccidentType {

    //类型id
    private String id;
    //类型名称
    private String name;

    public AccidentType() {
    }

    public AccidentType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
