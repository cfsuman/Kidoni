package com.cfsuman.me.kidoni;


import android.graphics.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapManager {
    protected HashMap getHashMap(){
        HashMap<String,Integer> colors = new HashMap<>();
        colors.put("Alice Blue", Color.parseColor("#F0F8FF"));
        colors.put("Antique White", Color.parseColor("#FAEBD7"));
        colors.put("Aqua", Color.parseColor("#00FFFF"));
        colors.put("Aquamarine", Color.parseColor("#7FFFD4"));
        colors.put("Azure", Color.parseColor("#F0FFFF"));

        colors.put("Beige", Color.parseColor("#F5F5DC"));
        colors.put("Bisque", Color.parseColor("#FFE4C4"));
        colors.put("Black", Color.parseColor("#000000"));
        colors.put("Blanched Almond", Color.parseColor("#FFEBCD"));
        colors.put("Blue", Color.parseColor("#0000FF"));
        colors.put("Blue Violet", Color.parseColor("#8A2BE2"));
        colors.put("Brown", Color.parseColor("#A52A2A"));
        colors.put("Burly Wood", Color.parseColor("#DEB887"));

        colors.put("Cadet Blue", Color.parseColor("#5F9EA0"));
        colors.put("Chartreuse", Color.parseColor("#7FFF00"));
        colors.put("Chocolate", Color.parseColor("#D2691E"));
        colors.put("Coral", Color.parseColor("#FF7F50"));
        colors.put("Cornflower Blue", Color.parseColor("#6495ED"));
        colors.put("Cornsilk", Color.parseColor("#FFF8DC"));
        colors.put("Crimson", Color.parseColor("#DC143C"));
        colors.put("Cyan", Color.parseColor("#00FFFF"));

        colors.put("Dark Blue", Color.parseColor("#00008B"));
        colors.put("Dark Cyan", Color.parseColor("#008B8B"));
        colors.put("Dark Golden Rod", Color.parseColor("#B8860B"));
        colors.put("Dark Gray", Color.parseColor("#A9A9A9"));
        colors.put("Dark Green", Color.parseColor("#006400"));
        colors.put("Dark Khaki", Color.parseColor("#BDB76B"));
        colors.put("Dark Magenta", Color.parseColor("#8B008B"));
        colors.put("Dark Olive Green", Color.parseColor("#556B2F"));
        colors.put("Dark Orange", Color.parseColor("#FF8C00"));
        colors.put("Dark Orchid", Color.parseColor("#9932CC"));
        colors.put("Dark Red", Color.parseColor("#8B0000"));
        colors.put("Dark Salmon", Color.parseColor("#E9967A"));
        colors.put("Dark Sea Green", Color.parseColor("#8FBC8F"));
        colors.put("Dark Slate Blue", Color.parseColor("#483D8B"));
        colors.put("Dark Slate Gray", Color.parseColor("#2F4F4F"));
        colors.put("Dark Turquoise", Color.parseColor("#00CED1"));
        colors.put("Dark Violet", Color.parseColor("#9400D3"));
        colors.put("Deep Pink", Color.parseColor("#FF1493"));
        colors.put("Deep Sky Blue", Color.parseColor("#00BFFF"));
        colors.put("Dim Gray", Color.parseColor("#696969"));
        colors.put("Dodger Blue", Color.parseColor("#1E90FF"));

        colors.put("Fire Brick", Color.parseColor("#B22222"));
        colors.put("Floral White", Color.parseColor("#FFFAF0"));
        colors.put("Forest Green", Color.parseColor("#228B22"));
        colors.put("Fuchsia", Color.parseColor("#FF00FF"));

        colors.put("Gainsboro", Color.parseColor("#DCDCDC"));
        colors.put("Ghost White", Color.parseColor("#F8F8FF"));
        colors.put("Gold", Color.parseColor("#FFD700"));
        colors.put("Golden Rod", Color.parseColor("#DAA520"));
        colors.put("Gray", Color.parseColor("#808080"));
        colors.put("Green", Color.parseColor("#008000"));
        colors.put("Green Yellow", Color.parseColor("#ADFF2F"));

        colors.put("Honey Dew", Color.parseColor("#F0FFF0"));
        colors.put("Hot Pink", Color.parseColor("#FF69B4"));

        colors.put("Indian Red ", Color.parseColor("#CD5C5C"));
        colors.put("Indigo", Color.parseColor("#4B0082"));
        colors.put("Ivory", Color.parseColor("#FFFFF0"));

        colors.put("Khaki", Color.parseColor("#F0E68C"));

        colors.put("Lavender", Color.parseColor("#E6E6FA"));
        colors.put("Lavender Blush", Color.parseColor("#FFF0F5"));
        colors.put("Lawn Green", Color.parseColor("#7CFC00"));
        colors.put("Lemon Chiffon", Color.parseColor("#FFFACD"));
        colors.put("Light Blue", Color.parseColor("#ADD8E6"));
        colors.put("Light Coral", Color.parseColor("#F08080"));
        colors.put("Light Cyan", Color.parseColor("#E0FFFF"));
        colors.put("Light Golden Rod Yellow", Color.parseColor("#FAFAD2"));
        colors.put("Light Gray", Color.parseColor("#D3D3D3"));
        colors.put("Light Green", Color.parseColor("#90EE90"));
        colors.put("Light Pink", Color.parseColor("#FFB6C1"));
        colors.put("Light Salmon", Color.parseColor("#FFA07A"));
        colors.put("Light Sea Green", Color.parseColor("#20B2AA"));
        colors.put("Light Sky Blue", Color.parseColor("#87CEFA"));
        colors.put("Light Slate Gray", Color.parseColor("#778899"));
        colors.put("Light Steel Blue", Color.parseColor("#B0C4DE"));
        colors.put("Light Yellow", Color.parseColor("#FFFFE0"));
        colors.put("Lime", Color.parseColor("#00FF00"));
        colors.put("Lime Green", Color.parseColor("#32CD32"));
        colors.put("Linen", Color.parseColor("#FAF0E6"));

        colors.put("Magenta", Color.parseColor("#FF00FF"));
        colors.put("Maroon", Color.parseColor("#800000"));
        colors.put("Medium Aqua Marine", Color.parseColor("#66CDAA"));
        colors.put("Medium Blue", Color.parseColor("#0000CD"));
        colors.put("Medium Orchid", Color.parseColor("#BA55D3"));
        colors.put("Medium Purple", Color.parseColor("#9370DB"));
        colors.put("Medium Sea Green", Color.parseColor("#3CB371"));
        colors.put("Medium Slate Blue", Color.parseColor("#7B68EE"));
        colors.put("Medium Spring Green", Color.parseColor("#00FA9A"));
        colors.put("Medium Turquoise", Color.parseColor("#48D1CC"));
        colors.put("Medium Violet Red", Color.parseColor("#C71585"));
        colors.put("Midnight Blue", Color.parseColor("#191970"));
        colors.put("Mint Cream", Color.parseColor("#F5FFFA"));
        colors.put("Misty Rose", Color.parseColor("#FFE4E1"));
        colors.put("Moccasin", Color.parseColor("#FFE4B5"));

        colors.put("Navajo White", Color.parseColor("#FFDEAD"));
        colors.put("Navy", Color.parseColor("#000080"));

        colors.put("Old Lace", Color.parseColor("#FDF5E6"));
        colors.put("Olive", Color.parseColor("#808000"));
        colors.put("Olive Drab", Color.parseColor("#6B8E23"));
        colors.put("Orange", Color.parseColor("#FFA500"));
        colors.put("Orange Red", Color.parseColor("#FF4500"));
        colors.put("Orchid", Color.parseColor("#DA70D6"));

        colors.put("Pale Golden Rod", Color.parseColor("#EEE8AA"));
        colors.put("Pale Green", Color.parseColor("#98FB98"));
        colors.put("Pale Turquoise", Color.parseColor("#AFEEEE"));
        colors.put("Pale Violet Red", Color.parseColor("#DB7093"));
        colors.put("Papaya Whip", Color.parseColor("#FFEFD5"));
        colors.put("Peach Puff", Color.parseColor("#FFDAB9"));
        colors.put("Peru", Color.parseColor("#CD853F"));
        colors.put("Pink", Color.parseColor("#FFC0CB"));
        colors.put("Plum", Color.parseColor("#DDA0DD"));
        colors.put("Powder Blue", Color.parseColor("#B0E0E6"));
        colors.put("Purple", Color.parseColor("#800080"));

        colors.put("Rebecca Purple", Color.parseColor("#663399"));
        colors.put("Red", Color.parseColor("#FF0000"));
        colors.put("Rosy Brown", Color.parseColor("#BC8F8F"));
        colors.put("Royal Blue", Color.parseColor("#4169E1"));

        colors.put("Saddle Brown", Color.parseColor("#8B4513"));
        colors.put("Salmon", Color.parseColor("#FA8072"));
        colors.put("Sandy Brown", Color.parseColor("#F4A460"));
        colors.put("Sea Green", Color.parseColor("#2E8B57"));
        colors.put("Sea Shell", Color.parseColor("#FFF5EE"));
        colors.put("Sienna", Color.parseColor("#A0522D"));
        colors.put("Silver", Color.parseColor("#C0C0C0"));
        colors.put("Sky Blue", Color.parseColor("#87CEEB"));
        colors.put("Slate Blue", Color.parseColor("#6A5ACD"));
        colors.put("Slate Gray", Color.parseColor("#708090"));
        colors.put("Snow", Color.parseColor("#FFFAFA"));
        colors.put("Spring Green", Color.parseColor("#00FF7F"));
        colors.put("Steel Blue", Color.parseColor("#4682B4"));

        colors.put("Tan", Color.parseColor("#D2B48C"));
        colors.put("Teal", Color.parseColor("#008080"));
        colors.put("Thistle", Color.parseColor("#D8BFD8"));
        colors.put("Tomato", Color.parseColor("#FF6347"));
        colors.put("Turquoise", Color.parseColor("#40E0D0"));

        colors.put("Violet", Color.parseColor("#EE82EE"));

        colors.put("Wheat", Color.parseColor("#F5DEB3"));
        colors.put("White", Color.parseColor("#FFFFFF"));
        colors.put("White Smoke", Color.parseColor("#F5F5F5"));

        colors.put("Yellow", Color.parseColor("#FFFF00"));
        colors.put("Yellow Green", Color.parseColor("#9ACD32"));

        // Return the colors HashMap
        return colors;
    }

    protected String[] getKeysArray(){
        HashMap<String,Integer> map = getHashMap();
        String[] keys = new String[map.size()];
        int index = 0;
        for (Map.Entry<String, Integer> mapEntry : map.entrySet()) {
            keys[index] = mapEntry.getKey();
            index++;
        }
        Arrays.sort(keys);
        return keys;
    }

    protected Integer[] getValuesArray(){
        HashMap<String,Integer> map = getHashMap();
        Integer[] values = new Integer[map.size()];
        int index = 0;
        for (Map.Entry<String, Integer> mapEntry : map.entrySet()) {
            values[index] = mapEntry.getValue();
            index++;
        }
        return values;
    }

    protected int getValueByKey(String key){
        return (int)getHashMap().get(key);
    }
}
