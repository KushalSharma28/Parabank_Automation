package com.parabank.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Scenario Context for sharing data between step definitions
 */
public class ScenarioContext {
    private static final ThreadLocal<Map<String, Object>> scenarioContext = ThreadLocal.withInitial(HashMap::new);

    /**
     * Set value in context
     *
     * @param key Key
     * @param value Value
     */
    public static void setScenarioContext(String key, Object value) {
        scenarioContext.get().put(key, value);
    }

    /**
     * Get value from context
     *
     * @param key Key
     * @return Value
     */
    public static Object getScenarioContext(String key) {
        return scenarioContext.get().get(key);
    }

    /**
     * Check if key exists
     *
     * @param key Key
     * @return true if key exists
     */
    public static boolean isScenarioContextContains(String key) {
        return scenarioContext.get().containsKey(key);
    }

    /**
     * Clear all context
     */
    public static void clearScenarioContext() {
        scenarioContext.get().clear();
    }
}
