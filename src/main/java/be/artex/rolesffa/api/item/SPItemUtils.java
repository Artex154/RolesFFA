package be.artex.rolesffa.api.item;

import java.util.ArrayList;

public class SPItemUtils {
    public static ArrayList<SPItem> registeredItems = new ArrayList<>();

    public static void registerItem(SPItem item) {
        registeredItems.add(item);
    }
}
