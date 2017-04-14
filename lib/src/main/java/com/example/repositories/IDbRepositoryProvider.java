package com.example.repositories;

/**
 * Created by fan-gk on 2017/4/14.
 */

public interface IDbRepositoryProvider {
    <T extends IDbRepository<E, ?>, E> T getDbRepository(Class<T> classOfT);
}
