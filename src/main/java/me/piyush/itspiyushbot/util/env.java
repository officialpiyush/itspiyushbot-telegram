package me.piyush.itspiyushbot.util;

import io.github.cdimascio.dotenv.Dotenv;

public class env {
    private Dotenv _env;

    public env() {
        this._init();
    }

    private void _init() {
        this._env = Dotenv.load();
    }

    public String get(String key) {
        return _env.get(key);
    }
}
