package me.kall.lightbind;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import me.kall.jsonate.api.JsonConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod(LightBind.MOD_ID)
public final class LightBind {
    public static final String MOD_ID = "lightbind";

    public static final JsonConfig CONFIG = JsonConfig.create(MOD_ID, "1.0.1")
            .put("BindPair", Lists.newArrayList("cataclysm:flame_strike;240"))
            .put("AllEntitiesLightest", false)
            .initialize();

    public static final Object2IntMap<ResourceLocation> BIND_PAIRS = new Object2IntOpenHashMap<>();
    public static final boolean LIGHTEST = CONFIG.getBoolean("AllEntitiesLightest");

    static {
        if (!CONFIG.getBoolean("AllEntitiesLightest")) {
            CONFIG.getStream("BindPair", String.class).forEach(entry -> {
                String[] parts = entry.split(";");
                String name = parts[0];
                int light = Integer.parseInt(parts[1]);
                BIND_PAIRS.put(ResourceLocation.tryParse(name), light);
            });
        }
    }
}
