package singh.akaalroop.winter_of_making.shop;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Objects;

import static singh.akaalroop.winter_of_making.items.ModItems.SNOWFLAKE;

public class ShopHandling {
    public static int buyShopItem(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        int shopItem = IntegerArgumentType.getInteger(context, "shopItem");
        int snowflakes = Objects.requireNonNull(context.getSource().getPlayer()).getInventory().count(SNOWFLAKE);
        switch (shopItem) {
            case 1 -> {
                return hotCocoa(context, snowflakes);
            }
            case 2 -> {
                return snowBlock(context, snowflakes);
            }
            case 3 -> {
                return snowQueen(context, snowflakes);
            }
            case 4 -> {
                return snowKing(context, snowflakes);
            }
            case 5 -> {
                return snowballs(context, snowflakes);
            }
            case 6 -> {
                return snowGolemOverlord(context, snowflakes);
            }
            case 7 -> {
                return snowy(context, snowflakes);
            }
            case 8 -> {
                return snowBomb(context, snowflakes);
            }
            default -> {
                context.getSource().sendFeedback(() -> Text.literal("The item you requested is not in the shop (please type /shop) then type the number of the item you want"), false);
            }
        }
        return 1;
    }

    static void removeSnowflakes(ServerPlayerEntity player, int amountToRemove) {
        var inv = player.getInventory();

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);

            if (!stack.isEmpty() && stack.isOf(SNOWFLAKE)) {
                int countInStack = stack.getCount();

                if (amountToRemove >= countInStack) {
                    inv.setStack(i, ItemStack.EMPTY);
                    amountToRemove -= countInStack;
                } else {
                    stack.decrement(amountToRemove);
                    return;
                }

                if (amountToRemove <= 0) {
                    return;
                }
            }
        }
    }

    static int hotCocoa(CommandContext<ServerCommandSource> context, int snowflakes) {
        if (snowflakes >= 20) {
            var effect = new StatusEffectInstance(StatusEffects.SATURATION, 7200 * 20, 0, false, true, true);
            Objects.requireNonNull(context.getSource().getPlayer()).addStatusEffect(effect);
            context.getSource().sendFeedback(() -> Text.literal("You successfully bought Hot Cocoa for 20 snowflakes!").formatted(Formatting.DARK_GREEN), false);
            removeSnowflakes(context.getSource().getPlayer(), 20);
        } else {
            context.getSource().sendFeedback(() -> Text.literal("You do not have enough snowflakes to buy Hot Cocoa for 20 snowflakes! (Snowflakes must be in your inventory to purchase)").formatted(Formatting.RED), false);
        }
        return 1;
    }

    static int snowBlock(CommandContext<ServerCommandSource> context, int snowflakes) {
        if (snowflakes >= 25) {
            Objects.requireNonNull(context.getSource().getPlayer()).getInventory().insertStack(Registries.ITEM.get(Identifier.of("minecraft", "snow_block")).getDefaultStack());
            Objects.requireNonNull(context.getSource().getPlayer()).getInventory().insertStack(Registries.ITEM.get(Identifier.of("minecraft", "iron_block")).getDefaultStack());
            context.getSource().sendFeedback(() -> Text.literal("You successfully bought Snow Block for 25 snowflakes!").formatted(Formatting.DARK_GREEN), false);
            removeSnowflakes(context.getSource().getPlayer(), 25);
        } else {
            context.getSource().sendFeedback(() -> Text.literal("You do not have enough snowflakes to buy Snow Block for 25 snowflakes! (Snowflakes must be in your inventory to purchase)").formatted(Formatting.RED), false);
        }
        return 1;
    }

    static int snowQueen(CommandContext<ServerCommandSource> context, int snowflakes) {
        if (snowflakes >= 30) {
            context.getSource().getServer().getPlayerManager()
                    .broadcast(Text.literal("[Official Server Announcement] ")
                            .formatted(Formatting.GOLD).append(Objects.requireNonNull(Objects.requireNonNull(context.getSource().getPlayer()).getDisplayName())).append(Text.literal(" is a Snow Queen, slayyyy ~ :3").formatted(Formatting.LIGHT_PURPLE)), false);
            context.getSource().sendFeedback(() -> Text.literal("You successfully bought Snow Queen for 30 snowflakes!").formatted(Formatting.DARK_GREEN), false);
            removeSnowflakes(Objects.requireNonNull(context.getSource().getPlayer()), 30);
        } else {
            context.getSource().sendFeedback(() -> Text.literal("You do not have enough snowflakes to buy Snow Queen for 30 snowflakes! (Snowflakes must be in your inventory to purchase)").formatted(Formatting.RED), false);
        }
        return 1;
    }

    static int snowKing(CommandContext<ServerCommandSource> context, int snowflakes) {
        if (snowflakes >= 30) {
            context.getSource().getServer().getPlayerManager()
                    .broadcast(Text.literal("[Official Server Announcement] ")
                            .formatted(Formatting.GOLD).append(Objects.requireNonNull(Objects.requireNonNull(context.getSource().getPlayer()).getDisplayName())).append(Text.literal(" is a Snow King, bow down to him :o").formatted(Formatting.GREEN)), false);
            context.getSource().sendFeedback(() -> Text.literal("You successfully bought Snow King for 30 snowflakes!").formatted(Formatting.DARK_GREEN), false);
            removeSnowflakes(Objects.requireNonNull(context.getSource().getPlayer()), 30);
        } else {
            context.getSource().sendFeedback(() -> Text.literal("You do not have enough snowflakes to buy Snow King for 30 snowflakes! (Snowflakes must be in your inventory to purchase)").formatted(Formatting.RED), false);
        }
        return 1;
    }

    static int snowballs(CommandContext<ServerCommandSource> context, int snowflakes) {
        if (snowflakes >= 200) {
            ItemStack snowballs = new ItemStack(Items.SNOWBALL, 2048);
            Objects.requireNonNull(context.getSource().getPlayer()).giveItemStack(snowballs);
            Objects.requireNonNull(context.getSource().getPlayer()).dropItem(snowballs, false);
            context.getSource().sendFeedback(() -> Text.literal("You successfully bought Snowballs for 200 snowflakes!").formatted(Formatting.DARK_GREEN), false);
            removeSnowflakes(Objects.requireNonNull(context.getSource().getPlayer()), 200);
        } else {
            context.getSource().sendFeedback(() -> Text.literal("You do not have enough snowflakes to buy Snowballs for 200 snowflakes! (Snowflakes must be in your inventory to purchase)").formatted(Formatting.RED), false);
        }
        return 1;
    }

    static int snowGolemOverlord(CommandContext<ServerCommandSource> context, int snowflakes) {
        return 1;
    }

    static int snowy(CommandContext<ServerCommandSource> context, int snowflakes) {
        return 1;
    }

    static int snowBomb(CommandContext<ServerCommandSource> context, int snowflakes) {
        return 1;
    }
}
