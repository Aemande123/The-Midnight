package com.mushroom.midnight.common.biome.cavern;

import com.mushroom.midnight.Midnight;
import com.mushroom.midnight.common.biome.MidnightBiomeConfigurator;
import com.mushroom.midnight.common.biome.MidnightSurfaceBuilders;
import com.mushroom.midnight.common.registry.MidnightEntities;
import net.minecraft.world.biome.Biome;

public class CrystalCavernBiome extends CavernousBiome {
    public CrystalCavernBiome() {
        super(new Properties()
                .surfaceBuilder(MidnightSurfaceBuilders.CAVERN, MidnightSurfaceBuilders.NIGHTSTONE_CONFIG)
                .heightScale(0.4F)
        );

        MidnightBiomeConfigurator.addRouxeClusters(this);
        MidnightBiomeConfigurator.addStandardUndergroundSpawns(this);

        this.add(Midnight.MIDNIGHT_MOB, new Biome.SpawnListEntry(MidnightEntities.NOVA, 100, 1, 2));
    }
}
