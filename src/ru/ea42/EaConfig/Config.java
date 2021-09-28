package ru.ea42.EaConfig;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Config {

    public Config() {
        conf(ConfigType.OUT, "config.json");
        if (!jsEmpty()) {
            return;
        }
        conf(ConfigType.IN, "config.json");
        if (!jsEmpty()) {
            return;
        }
        conf(ConfigType.WEB, "http://localhost/config.json");
    }

    public Config(ConfigType type) {
        if (type == ConfigType.WEB) {
            conf(type, "http://localhost/config.json");
        } else {
            conf(type, "config.json");
        }
    }

    public Config(ConfigType type, String path) {
        conf(type, path);
    }

    public String jsStr;

    public boolean jsEmpty() {
        return jsStr == "";
    }

    private void conf(ConfigType type, String path) {
        jsStr = "";
        if (type == ConfigType.IN) {
            try {
                InputStream jsFS = this.getClass().getClassLoader().getResourceAsStream(path);
                if (jsFS != null) {
                    int ch;
                    ByteArrayOutputStream sb = new ByteArrayOutputStream();
                    while ((ch = jsFS.read()) != -1)
                        sb.write(ch);
                    jsStr = sb.toString("UTF8");
                    jsFS.close();
                }
            } catch (IOException e) {
            }
        }

        if (type == ConfigType.OUT) {
            try {
                InputStream jsFS = new FileInputStream(path);
                if (jsFS != null) {
                    int ch;
                    ByteArrayOutputStream sb = new ByteArrayOutputStream();
                    while ((ch = jsFS.read()) != -1)
                        sb.write(ch);
                    jsStr = sb.toString("UTF8");
                    jsFS.close();
                }
            } catch (IOException e) {
            }

        }

        if (type == ConfigType.WEB) {
            //TODO реализовать.
        }
    }

    public boolean getParamAsBoolean(String name) {
        boolean ret = false;
        try {
            JsonObject jsRoot = new JsonParser().parse(jsStr).getAsJsonObject();
            ret = jsRoot.get(name).getAsBoolean();
        } catch (NullPointerException e) {
        }
        return ret;
    }

    public boolean getParamAsBoolean(String section, String name) {
        boolean ret = false;
        try {
            JsonObject jsRoot = new JsonParser().parse(jsStr).getAsJsonObject();
            JsonObject jsSec = jsRoot.getAsJsonObject(section);
            ret = jsSec.get(name).getAsBoolean();
        } catch (NullPointerException e) {
        }
        return ret;
    }

    public int getParamAsInt(String name) {
        int ret = 0;
        try {
            JsonObject jsRoot = new JsonParser().parse(jsStr).getAsJsonObject();
            ret = jsRoot.get(name).getAsInt();
        } catch (NullPointerException e) {
        }
        return ret;
    }

    public int getParamAsInt(String section, String name) {
        int ret = 0;
        try {
            JsonObject jsRoot = new JsonParser().parse(jsStr).getAsJsonObject();
            JsonObject jsSec = jsRoot.getAsJsonObject(section);
            ret = jsSec.get(name).getAsInt();
        } catch (NullPointerException e) {
        }
        return ret;
    }

    public String getParamAsString(String name) {
        String ret = "";
        try {
            JsonObject jsRoot = new JsonParser().parse(jsStr).getAsJsonObject();
            ret = jsRoot.get(name).getAsString();
        } catch (NullPointerException e) {
        }
        return ret;
    }

    public String getParamAsString(String section, String name) {
        String ret = "";
        try {
            JsonObject jsRoot = new JsonParser().parse(jsStr).getAsJsonObject();
            JsonObject jsSec = jsRoot.getAsJsonObject(section);
            ret = jsSec.get(name).getAsString();
        } catch (NullPointerException e) {
        }
        return ret;
    }
}