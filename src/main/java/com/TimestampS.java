package com;

import java.sql.Timestamp;

public class TimestampS{
    private Timestamp timestamp;

    public TimestampS(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}