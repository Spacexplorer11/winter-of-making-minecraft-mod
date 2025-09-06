package singh.akaalroop.winter_of_making.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import singh.akaalroop.winter_of_making.items.KnockbackSnowball;

public class ModEntities {
    public static final RegistryKey<EntityType<?>> KNOCKBACK_SNOWBALL_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("winter_of_making", "knockback_snowball"));
    public static final EntityType<KnockbackSnowball> KNOCKBACK_SNOWBALL =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    KNOCKBACK_SNOWBALL_KEY,
                    EntityType.Builder.create(KnockbackSnowball::new, SpawnGroup.MISC)
                            .dimensions(0.25F, 0.25F)
                            .build(KNOCKBACK_SNOWBALL_KEY)
            );

    public static void initialise() {
    }
}
