package com.mcmiddleearth.base.core.configuration;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
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

    public void save(File file) {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        try(FileWriter out = new FileWriter(file)) {
            yaml.dump(map, out);
        } catch (IOException e) {
            e.printStackTrace();
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

    public Map<String, Object> getMap() {
        return map;
    }

    public void set(String key, Object value) {
        set(map, key.split("\\."), value);
    }

    public void set(Map<String,Object> submap, String[] subkeys, Object value) {
        if(subkeys.length>1) {
            if(submap.containsKey(subkeys[0]) && (submap.get(subkeys[0]) instanceof Map)) {
                set((Map<String,Object>)submap.get(subkeys[0]),
                        Arrays.copyOfRange(subkeys, 1, subkeys.length),value);
            } else {
                Map<String, Object> insertion = new HashMap<>();
                submap.put(subkeys[0],insertion);
                set(insertion, Arrays.copyOfRange(subkeys, 1, subkeys.length),value);
            }
        } else {
            submap.put(subkeys[0],value);
        }
    }


}
