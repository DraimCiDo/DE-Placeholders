package me.realized.de.placeholders.hooks;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.realized.de.placeholders.Placeholders;
import me.realized.de.placeholders.util.Updatable;
import me.realized.duels.api.Duels;
import me.realized.duels.api.kit.Kit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderHook implements Updatable<Kit> {

    private final Placeholders extension;
    private final PlaceholderExpansion previous;

    public PlaceholderHook(final Placeholders extension, final Duels api) {
        this.extension = extension;
        this.previous = PlaceholderAPIPlugin.getInstance().getLocalExpansionManager().getExpansion("duels");
        if (previous != null) {
            previous.unregister();
        }
        new PlaceholdersExpansion().register();
    }

    @Override
    public void update(final Kit value) {}

    public class PlaceholdersExpansion extends PlaceholderExpansion {

        @Override
        public @NotNull String getIdentifier() {
            return "duels";
        }

        @Override
        public @NotNull String getAuthor() {
            return "Realized";
        }

        @Override
        public @NotNull String getVersion() {
            return "1.0.0";
        }

        @Override
        public boolean canRegister() {
            return true;
        }

        @Override
        public boolean persist() {
            return true;
        }

        @Override
        public String onPlaceholderRequest(final Player player, final @NotNull String params) {
            final String result = extension.find(player, params);

            if (result != null) {
                return result;
            }

            return previous != null ? previous.onPlaceholderRequest(player, params) : null;
        }
    }
}
