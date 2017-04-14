package com.example.repositories;

/**
 * Created by fan-gk on 2017/4/14.
 */

public interface IWriteableSingleRepository<T> {
    void addOrReplace(T value);
    void clear();
}