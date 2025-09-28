package me.kall.lightbind.mixin;

import me.kall.lightbind.api.ToLight;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityType.class)
public class EntityTypeMixin implements ToLight {
    @Unique private int bind$light = -1;

    @Override
    public int bind$getLight() {
        return this.bind$light;
    }

    @Override
    public void bind$setLight(int light) {
        this.bind$light = light;
    }
}
