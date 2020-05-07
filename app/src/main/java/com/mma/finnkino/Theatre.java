package com.mma.finnkino;

import androidx.annotation.NonNull;

public class Theatre {
    private long id;
    private String name;

    public Theatre(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getID() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
