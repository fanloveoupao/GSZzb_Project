package com.example.log;

/**
 * Created by fan-gk on 2017/2/18.
 */


public enum LogLevels {

    INFO(0), DEBUG(1), WARN(2), FAIL(3), ERROR(4);

    private int level;
    LogLevels(int level){
        this.level = level;
    }

    public boolean gte(LogLevels other){
        return !isNull(other) && level >= other.level;
    }

    private boolean isNull(LogLevels level){
        return level == null;
    }
}

