package com.example.repositories;

/**
 * Created by fan-gk on 2017/4/14.
 */

public interface ISingleRepository<T> extends IWriteableSingleRepository<T> {
    T get();
}
