package singh.akaalroop.winter_of_making;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import singh.akaalroop.winter_of_making.entities.ModEntities;

public class WinterOfMakingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        EntityRendererRegistry.register(
                ModEntities.KNOCKBACK_SNOWBALL,
                FlyingItemEntityRenderer::new
        );
    }
}