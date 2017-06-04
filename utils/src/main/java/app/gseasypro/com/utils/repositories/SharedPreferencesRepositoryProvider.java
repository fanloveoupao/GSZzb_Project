package app.gseasypro.com.utils.repositories;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.repositories.ISharedPreferencesRepositoryProvider;
import com.example.repositories.ISingleRepository;
import com.example.repositories.IWriteableSingleRepository;

import app.gseasypro.com.utils.utils.GsonUtils;

/**
 * Created by fan-gk
 * on 2017/5/24.
 */

public class SharedPreferencesRepositoryProvider implements ISharedPreferencesRepositoryProvider {

    private class WriteableSingleRepository<T> implements IWriteableSingleRepository<T>{
        private final String sharedPreferencesName;
        private final Application application;
        protected final String key;

        private WriteableSingleRepository(String sharedPreferencesName, String key, Application application) {
            this.sharedPreferencesName = sharedPreferencesName;
            this.key = key;
            this.application = application;
        }

        protected SharedPreferences getSharedPreferences(){
            return application.getSharedPreferences(sharedPreferencesName, Application.MODE_PRIVATE);
        }

        @Override
        public void addOrReplace(T value) {
            getSharedPreferences().edit().putString(key, GsonUtils.stringify(value)).commit();
        }


        @Override
        public void clear() {
            getSharedPreferences().edit().remove(key).commit();
        }
    }

    private class SingleRepository<T> extends WriteableSingleRepository<T> implements ISingleRepository<T>{
        private Class<T> classOfT;

        @Override
        public T get() {
            String json = getSharedPreferences().getString(key, null);
            return GsonUtils.tryParse(classOfT, json);
        }

        public SingleRepository(String sharedPreferencesName, String key, Application application, Class<T> classOfT) {
            super(sharedPreferencesName, key, application);
            this.classOfT = classOfT;
        }

    }

    private Application context;
    public SharedPreferencesRepositoryProvider(Application context) {
        this.context = context;
    }

    @Override
    public <T> ISingleRepository<T> GetSingleRepository(String sharedPreferencesName, String key, Class<T> classOfT) {
        return new SingleRepository<>(sharedPreferencesName, key, context, classOfT);
    }

    @Override
    public IWriteableSingleRepository GetWriteableSingleRepository(String sharedPreferencesName, String key) {
        return new WriteableSingleRepository(sharedPreferencesName, key, context);
    }
}

