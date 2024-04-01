package com.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.Map;

public class LoxInstance {
    private LoxClass klass;
    private final Map<String, Object> fields = new HashMap<>();

    LoxInstance(LoxClass klass) {
        this.klass = klass;
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexme)) {
            return fields.get(name.lexme);
        }

        LoxFunction method = klass.findMethod(name.lexme);
        if (method != null) {
            return method.bind(this);
        }

        throw new RuntimeError(name, "Undefined property '" + name.lexme + "'.");
    }

    void set(Token name, Object value) {
        fields.put(name.lexme, value);
    }

    @Override
    public String toString() {
        return klass.name + " instance";
    }
}
