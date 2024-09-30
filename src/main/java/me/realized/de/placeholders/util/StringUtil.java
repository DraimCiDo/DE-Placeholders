package me.realized.de.placeholders.util;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class StringUtil {
    private static final DecimalFormat formatter = new DecimalFormat("#,###.##", new DecimalFormatSymbols( new Locale("pt", "BR")));

    @Contract("_ -> new")
    public static @NotNull String color(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @Contract(pure = true)
    public static @NotNull String format(int number) {
        return formatter.format(number);
    }

    private StringUtil() {}
}
