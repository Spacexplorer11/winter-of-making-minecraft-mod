package singh.akaalroop.winter_of_making;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ShopHandling {
    public static int buyShopItem(CommandContext<ServerCommandSource> context) {
        int shopItem = IntegerArgumentType.getInteger(context, "shopItem");
        switch (shopItem) {
            case 1 -> {
                return hotCocoa(context);
            }
            case 2 -> {
                return snowBlock(context);
            }
            case 3 -> {
                return snowQueen(context);
            }
            case 4 -> {
                return snowKing(context);
            }
            case 5 -> {
                return snowballs(context);
            }
            case 6 -> {
                return snowGolemOverlord(context);
            }
            case 7 -> {
                return snowy(context);
            }
            case 8 -> {
                return snowBomb(context);
            }
            default -> {
                context.getSource().sendFeedback(() -> Text.literal("The item you requested is not in the shop (please type /shop) then type the number of the item you want"), false);
            }
        }
        return 1;
    }

    static int hotCocoa(CommandContext<ServerCommandSource> context) {
        return 1;
    }

    static int snowBlock(CommandContext<ServerCommandSource> context) {
        return 1;
    }

    static int snowQueen(CommandContext<ServerCommandSource> context) {
        return 1;
    }

    static int snowKing(CommandContext<ServerCommandSource> context) {
        return 1;
    }

    static int snowballs(CommandContext<ServerCommandSource> context) {
        return 1;
    }

    static int snowGolemOverlord(CommandContext<ServerCommandSource> context) {
        return 1;
    }

    static int snowy(CommandContext<ServerCommandSource> context) {
        return 1;
    }

    static int snowBomb(CommandContext<ServerCommandSource> context) {
        return 1;
    }
}
