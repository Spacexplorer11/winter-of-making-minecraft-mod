package singh.akaalroop.winter_of_making;

import net.fabricmc.api.ClientModInitializer;
        //? if >= 1.21.9 {
/*import net.minecraft.client.render.entity.EntityRendererFactories;
 *///? } else {
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
//? }
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import singh.akaalroop.winter_of_making.entities.ModEntities;

public class WinterOfMakingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //? if >= 1.21.9 {
        /*EntityRendererFactories.register(
                ModEntities.KNOCKBACK_SNOWBALL,
                FlyingItemEntityRenderer::new
        );
        *///? } else {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        EntityRendererRegistry.register(
                ModEntities.KNOCKBACK_SNOWBALL,
                FlyingItemEntityRenderer::new
        );
        //? }
    }
}