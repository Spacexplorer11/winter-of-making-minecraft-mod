package singh.akaalroop.winter_of_making.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KnockbackSnowball extends SnowballEntity {

    public KnockbackSnowball(EntityType<? extends SnowballEntity> KnockbackSnowball, World world) {
        super(KnockbackSnowball, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        Entity target = entityHitResult.getEntity();
        if (target instanceof LivingEntity living) {
            living.takeKnockback(
                    10.0,
                    this.getX() - living.getX(),
                    this.getZ() - living.getZ()
            );
        }
    }
}