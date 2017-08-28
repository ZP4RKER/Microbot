package me.zp4rker.microbot.util;

import java.awt.*;
import java.lang.reflect.Field;

/**
 * @author ZP4RKER
 */
public class ColorUtil {

    public static Color randomColour() {
        // Return a random colour
        return Color.decode((int)(Math.random() * 0x1000000) + "");
    }

    public static Color fromRGB(String str) {
        Color c;
        try {
            String[] rgb = str.split(",");
            int r = Integer.parseInt(rgb[0]);
            int g = Integer.parseInt(rgb[1]);
            int b = Integer.parseInt(rgb[2]);
            c = new Color(r, g, b);
        } catch (Exception e) {
            c = null;
        }
        return c;
    }

    public static Color fromString(String str) {
        Color c;
        try {
            Field field = Class.forName("java.awt.Color").getField(str);
            c = (Color) field.get(null);
        } catch (Exception e) {
            c = null;
        }
        return c;
    }

}
