package com.entity;

public class Target {
    private String id;
    private String target;
    private TargetType targetType;
    private String department;
    private String owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
