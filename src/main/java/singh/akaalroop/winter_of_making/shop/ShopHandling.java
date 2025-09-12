package singh.akaalroop.winter_of_making.shop;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.BiomeKeys;
import org.slf4j.event.Level;
import singh.akaalroop.winter_of_making.entities.KnockbackSnowball;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static singh.akaalroop.winter_of_making.WinterOfMaking.LOGGER;
import static singh.akaalroop.winter_of_making.items.ModItems.SNOWFLAKE;

public class ShopHandling {
    public static int buyShopItem(CommandContext<ServerCommandSource> context) {
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
        PlayerInventory inventory = player.getInventory();

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);

            if (!stack.isEmpty() && stack.isOf(SNOWFLAKE)) {
                int countInStack = stack.getCount();

                if (amountToRemove >= countInStack) {
                    inventory.setStack(i, ItemStack.EMPTY);
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
            StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.SATURATION, 7200 * 20, 0, false, true, true);
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
            ItemStack snow_block = new ItemStack(Items.SNOW_BLOCK, 1);
            ItemStack iron_block = new ItemStack(Items.IRON_BLOCK, 1);
            Objects.requireNonNull(context.getSource().getPlayer()).getInventory().insertStack(snow_block);
            Objects.requireNonNull(context.getSource().getPlayer()).getInventory().insertStack(iron_block);
            Objects.requireNonNull(context.getSource().getPlayer()).dropItem(snow_block, false);
            Objects.requireNonNull(context.getSource().getPlayer()).dropItem(iron_block, false);
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
        if (snowflakes >= 300) {
            ServerWorld world = Objects.requireNonNull(context.getSource().getPlayer()).getWorld();
            for (int i = 0; i < 25; i++) {
                SnowGolemEntity snowGolem = new SnowGolemEntity(EntityType.SNOW_GOLEM, world);
                snowGolem.refreshPositionAndAngles(
                        context.getSource().getPlayer().getX(),
                        context.getSource().getPlayer().getY(),
                        context.getSource().getPlayer().getZ(),
                        0.0F,
                        0.0F
                );
                Objects.requireNonNull(context.getSource().getServer().getWorld(world.getRegistryKey())).spawnEntity(snowGolem);
            }
            context.getSource().sendFeedback(() -> Text.literal("You successfully bought Snow Golem Overlord for 300 snowflakes!").formatted(Formatting.DARK_GREEN), false);
            removeSnowflakes(Objects.requireNonNull(context.getSource().getPlayer()), 300);
        } else {
            context.getSource().sendFeedback(() -> Text.literal("You do not have enough snowflakes to buy Snow Golem Overlord for 300 snowflakes! (Snowflakes must be in your inventory to purchase)").formatted(Formatting.RED), false);
        }
        return 1;
    }

    static int snowy(CommandContext<ServerCommandSource> context, int snowflakes) {
        if (snowflakes >= 500) {
            try {
                List<ServerPlayerEntity> players = context.getSource().getServer().getPlayerManager().getPlayerList();
                int limit = Math.min(players.size(), 5);
                List<ServerPlayerEntity> playersToTeleport = new ArrayList<>(players.subList(0, limit));
                if (players.size() > 1) {
                    playersToTeleport.add(context.getSource().getPlayer());
                }
                ServerWorld world = context.getSource().getWorld();
                BlockPos playerPos = Objects.requireNonNull(context.getSource().getPlayer()).getBlockPos();
                LOGGER.info("Player who bought snowy position: {}", playerPos);

                var result = world.locateBiome(biome -> biome.matchesKey(BiomeKeys.ICE_SPIKES) || biome.matchesKey(BiomeKeys.SNOWY_PLAINS) || biome.matchesKey(BiomeKeys.SNOWY_BEACH) || biome.matchesKey(BiomeKeys.SNOWY_SLOPES) || biome.matchesKey(BiomeKeys.SNOWY_TAIGA), playerPos, 12801, 20, 20);
                if (result == null) {
                    context.getSource().sendError(Text.literal("No snowy biome found nearby!"));
                    return 0;
                }
                var biomePos = result.getFirst();
                LOGGER.info("Nearest biome position: {}", biomePos);

                while (world.getBlockState(biomePos).isSolidBlock(world, biomePos)) {
                    biomePos = biomePos.up();
                    LOGGER.info("The biome pos was in a solid block, it's now: {}", biomePos);
                }

                world.setWeather(0, 3000, true, false);

                for (ServerPlayerEntity player : playersToTeleport) {
                    player.teleport(world,
                            biomePos.getX() + 0.5,
                            biomePos.getY() + 1.0,
                            biomePos.getZ() + 0.5,
                            Set.of(),
                            player.getYaw(),
                            player.getPitch(),
                            false
                    );
                }

                context.getSource().sendFeedback(() -> Text.literal("You successfully bought Snowy for 500 snowflakes!").formatted(Formatting.DARK_GREEN), false);
                removeSnowflakes(Objects.requireNonNull(context.getSource().getPlayer()), 500);
            } catch (Exception e) {
                LOGGER.atLevel(Level.ERROR).log("Shop crashed {}", e);
                context.getSource().sendError(Text.literal(
                        "Shop error when trying to purchase item 7: " + e
                ));
            }
        } else {
            context.getSource().sendFeedback(() -> Text.literal("You do not have enough Snowy to buy Snowballs for 500 snowflakes! (Snowflakes must be in your inventory to purchase)").formatted(Formatting.RED), false);
        }
        return 1;
    }

    static int snowBomb(CommandContext<ServerCommandSource> context, int snowflakes) {
        if (snowflakes >= 1000) {
            List<ServerPlayerEntity> players = context.getSource().getServer().getPlayerManager().getPlayerList();
            ServerWorld world = context.getSource().getWorld();
            for (ServerPlayerEntity player : players) {
                for (int i = 0; i < 10; i++) {
                    spawnKnockbackSnowball(player, world);
                }
            }
            context.getSource().sendFeedback(() -> Text.literal("You successfully bought SNOW BOMB for 1000 snowflakes!").formatted(Formatting.DARK_GREEN), false);
            removeSnowflakes(Objects.requireNonNull(context.getSource().getPlayer()), 1000);
        } else {
            context.getSource().sendFeedback(() -> Text.literal("You do not have enough snowflakes to buy SNOW BOMB for 1000 snowflakes! (Snowflakes must be in your inventory to purchase)").formatted(Formatting.RED), false);
        }
        return 1;
    }

    static void spawnKnockbackSnowball(ServerPlayerEntity player, ServerWorld world) {
        Vec3d lookVec = player.getRotationVec(1.0F).normalize();

        Vec3d spawnPos = player.getPos().subtract(lookVec.multiply(2)).add(0, 1, 0);

        KnockbackSnowball knockbackSnowball = new KnockbackSnowball(EntityType.SNOWBALL, world);
        knockbackSnowball.setPosition(spawnPos.x, spawnPos.y, spawnPos.z);

        Vec3d target = player.getPos().add(0, 1, 0);
        Vec3d velocity = target.subtract(spawnPos).normalize().multiply(1.5);
        knockbackSnowball.setVelocity(velocity.x, velocity.y, velocity.z);

        world.spawnEntity(knockbackSnowball);
    }
}
