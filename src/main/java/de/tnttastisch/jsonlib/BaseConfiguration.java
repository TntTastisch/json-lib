package de.tnttastisch.jsonlib;

import de.tnttastisch.jsonlib.json.JsonConfig;

import java.io.File;
import java.io.IOException;

public class BaseConfiguration {

    protected final JsonConfig config = new JsonConfig();
    private final File file;

    public BaseConfiguration(File dataFolder, String path) {
        this.file = new File(dataFolder, path);
        if (!this.file.exists()) {
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public final void load() {
        try {
            config.load(file);
        } catch (Throwable throwable) {
            System.err.printf("Failed to load file {}:\n{}", file.getName(), throwable);
        }
        try {
            onLoad();
        } catch (Throwable throwable) {
            System.err.printf("Failed to run onLoad of config file {}:\n{}", file.getName(), throwable);
        }
    }

    public final void save() {
        try {
            onSave();
        } catch (Throwable throwable) {
            System.err.printf("Failed to run onSave of config file {}:\n{}", file.getName(), throwable);
        }
        try {
            config.save(file);
        } catch (Throwable throwable) {
            System.err.printf("Failed to save file {}:\n{}", file.getName(), throwable);
        }
    }

    public final void reload() {
        load();
        save();
    }

    protected void onLoad() throws Throwable {
    }

    protected void onSave() throws Throwable {
    }
}