package be.artex.rolesffa.util.api;

import be.artex.rolesffa.api.SPItem;

import java.util.ArrayList;

public class SPItemUtils {
    public static ArrayList<SPItem> registeredItems = new ArrayList<>();

    public static void registerItem(SPItem item) {
        registeredItems.add(item);
    }
}
