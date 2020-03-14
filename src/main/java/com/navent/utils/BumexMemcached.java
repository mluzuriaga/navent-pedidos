package com.navent.utils;

import java.util.HashMap;

public class BumexMemcached {

    private static BumexMemcached single_instance;

    private HashMap<String, Object> mapaObjetos;

    private BumexMemcached() {

        this.mapaObjetos = new HashMap<>();

    }

    public static BumexMemcached getInstance() {

        if (single_instance == null)
            single_instance = new BumexMemcached();

        return single_instance;

    }

    public void set(String key, Object value) {

        this.mapaObjetos.put(key, value);

    }

    public Object get(String key) {

        return this.mapaObjetos.get(key);

    }

    public void delete(String key) {

        this.mapaObjetos.remove(key);

    }

}
