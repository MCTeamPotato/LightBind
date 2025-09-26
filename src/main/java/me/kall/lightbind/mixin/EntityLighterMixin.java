package me.kall.lightbind.mixin;

import me.jellysquid.mods.sodium.client.model.light.EntityLighter;
import me.jellysquid.mods.sodium.client.render.entity.EntityLightSampler;
import me.kall.lightbind.LightBind;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLighter.class)
public class EntityLighterMixin {
    @Inject(method = "getBlendedLight", at = @At("HEAD"), cancellable = true)
    private static <T extends Entity> void bind$getLight(EntityLightSampler<T> lighter, @NotNull T entity, float tickDelta, CallbackInfoReturnable<Integer> cir) {
        if (LightBind.LIGHTEST) {
            cir.setReturnValue(240);
            return;
        }
        if (LightBind.BIND_PAIRS.isEmpty()) return;
        int bind = LightBind.BIND_PAIRS.getOrDefault(entity.getType().getRegistryName(), -1);
        if (bind == -1) return;
        cir.setReturnValue(bind);
    }
}
