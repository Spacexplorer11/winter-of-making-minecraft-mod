package singh.akaalroop.winter_of_making.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowballEntity.class)
public abstract class SnowballEntityMixin {
    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void addKnockback(EntityHitResult hitResult, CallbackInfo ci) {
        Entity target = hitResult.getEntity();
        if (target instanceof LivingEntity living) {
            living.takeKnockback(
                    2.0,
                    ((SnowballEntity) (Object) this).getX() - hitResult.getEntity().getX(),
                    ((SnowballEntity) (Object) this).getZ() - hitResult.getEntity().getZ()
            );
        }
    }
}
