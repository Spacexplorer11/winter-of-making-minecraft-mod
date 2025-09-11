package singh.akaalroop.winter_of_making;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import singh.akaalroop.winter_of_making.entities.ModEntities;
import singh.akaalroop.winter_of_making.items.ModItems;
import singh.akaalroop.winter_of_making.shop.ShopHandling;

public class WinterOfMaking implements ModInitializer {
    public static final String MOD_ID = "winter-of-making";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("The winter is yours for the making");
        ModItems.initialise();
        ModEntities.initialise();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("wom-shop")
                .executes(context -> {
                    context.getSource().sendFeedback(() -> Text.literal("The available Winter of Making shop items are: \n").formatted(Formatting.AQUA)
                            .append(Text.literal("------------------------------------------------\n").formatted(Formatting.GRAY))
                            .append(Text.literal("1. Hot Cocoa (Saturation for 2 hours) - 20 snowflakes\n").formatted(Formatting.RED))
                            .append(Text.literal("2. Snow Block (snow block + iron block) - 25 snowflakes\n").formatted(Formatting.WHITE))
                            .append(Text.literal("3. Become Snow Queen (sends message that you are snow queen) - 30 snowflakes\n").formatted(Formatting.LIGHT_PURPLE))
                            .append(Text.literal("4. Become Snow King (sends message that you are snow king) - 30 snowflakes\n").formatted(Formatting.BLUE))
                            .append(Text.literal("5. Snowballs (Get given 2048 snowballs) - 200 snowflakes\n").formatted(Formatting.WHITE))
                            .append(Text.literal("6. Snow golem overlord (Spawn 25 snow golems at your position) - 300 snowflakes\n").formatted(Formatting.YELLOW))
                            .append(Text.literal("7. Snowy! (Change the weather to rainy + teleport you & up to 5 random players to the nearest snowy biome to you) - 500 snowflakes\n").formatted(Formatting.GREEN))
                            .append(Text.literal("8. SNOW BOMB! (Spawn 10 snowballs 2 blocks behind everyone's back flying towards them!) - 1000 snowflakes!\n").formatted(Formatting.BOLD).formatted(Formatting.GOLD)), false);
                    return 1;
                })
                .then(CommandManager.argument("shopItem", IntegerArgumentType.integer())
                        .executes(ShopHandling::buyShopItem))));
    }
}