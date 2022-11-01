package com.task.asynchronous.enums;

public enum TaskStatus {
    RUNNING("running"),
    FINISHED("finished");

    private final String name;

    TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
