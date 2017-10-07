package me.zp4rker.core.logger;

import java.util.logging.Level;

/**
 * @author ZP4RKER
 */
class CustomLevel extends Level {

    CustomLevel(String name, int value) {
        super(name, Level.SEVERE.intValue() + value);
    }

}
