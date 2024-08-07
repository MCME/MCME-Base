package com.mcmiddleearth.base.core.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YamlConfiguration {
    private Map<String,Object> map = new HashMap<>();

    public YamlConfiguration(Map<String,Object> map) {
        this.map = map;
    }

    public YamlConfiguration() {}

    public YamlConfiguration(File file) {
        Yaml yaml = new Yaml();
        try {
            map = yaml.load(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(YamlConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Map<String,Object> getSection(String key) {
        Object value = getValue(key);
        return (value instanceof Map?(Map<String,Object>) value:null);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Object value = getValue(key);
        return (value!=null?(Boolean)value:defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        Object value = getValue(key);
        if(value instanceof Integer) {
            return (Integer)value;
        } else if(value instanceof  Double) {
            return ((Double) value).intValue();
        } else {
            return defaultValue;
        }
    }

    public double getDouble(String key, double defaultValue) {
        Object value = getValue(key);
        if(value instanceof Integer) {
            return ((Integer)value).doubleValue();
        } else if(value instanceof  Double) {
            return (Double) value;
        } else {
            return defaultValue;
        }
    }

    public float getFloat(String key, float defaultValue) {
        Object value = getValue(key);
        if(value instanceof Integer) {
            return ((Integer)value).floatValue();
        } else if(value instanceof  Double) {
            return ((Double) value).floatValue();
        } else {
            return defaultValue;
        }
    }

    public String getString(String key, String defaultValue) {
        Object value = getValue(key);
        return (value!=null?(String)value:defaultValue);
    }

    public List<String> getStringList(String key) {
        return (List<String>)getValue(key);
    }

    public List<Object> getList(String key) {return (List<Object>)getValue(key); }

    public Object getValue(String key) {
        return getValue(map, key.split("\\."));
    }

    private Object getValue(Map<String,Object> submap, String[] subkeys) {
        if(subkeys.length>1) {
            if(submap.containsKey(subkeys[0])) {
                return getValue((Map<String,Object>)submap.get(subkeys[0]),
                        Arrays.copyOfRange(subkeys, 1, subkeys.length));
            } else {
                return null;
            }
        } else {
            return submap.get(subkeys[0]);
        }
    }

    public Set<String> getKeys() {
        return map.keySet();
    }
}
