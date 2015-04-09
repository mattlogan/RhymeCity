package me.mattlogan.rhymecity.module;

import me.mattlogan.rhymecity.AppModule;

public final class Modules {
    public static Object[] list(String apiKey) {
        return new Object[] {
                new AppModule(apiKey)
        };
    }

    private Modules() {
        // No instances.
    }
}
