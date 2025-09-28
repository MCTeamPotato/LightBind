package me.kall.lightbind;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import me.kall.jsonate.api.JsonConfig;
import me.kall.lightbind.api.ToLight;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@Mod(LightBind.MOD_ID)
public final class LightBind {
    public static final String MOD_ID = "lightbind";

    public static final @Nullable JsonConfig CONFIG;

    public static final @Nullable Object2IntMap<ResourceLocation> BIND_PAIRS;
    public static final boolean LIGHTEST;

    static {
        if (!FMLLoader.getDist().isClient()) {
            CONFIG = null;
            BIND_PAIRS = null;
            LIGHTEST = false;
        } else {
            CONFIG = JsonConfig.create(MOD_ID, "1.0.1")
                    .put("BindPair", Lists.newArrayList("cataclysm:flame_strike;240"))
                    .put("AllEntitiesLightest", false)
                    .initialize();
            BIND_PAIRS = new Object2IntOpenHashMap<>();
            LIGHTEST = CONFIG.getBoolean("AllEntitiesLightest");
            try {
                CONFIG.getStream("BindPair", String.class).forEach(entry -> {
                    String[] parts = entry.split(";");
                    String name = parts[0];
                    int light = Integer.parseInt(parts[1]);
                    BIND_PAIRS.put(ResourceLocation.tryParse(name), light);
                });
            } catch (Exception e) {
                LogManager.getLogger().error("Error parsing LightBind config entries", e);
            }
        }
    }

    public LightBind() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    public void setup(@NotNull FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            if (LIGHTEST) return;
            Optional.ofNullable(BIND_PAIRS).ifPresent(pairs -> pairs.forEach((id, light) -> Optional.ofNullable(((ToLight)ForgeRegistries.ENTITIES.getValue(id))).ifPresent(toLight -> toLight.bind$setLight(light))));
        });
    }
}
