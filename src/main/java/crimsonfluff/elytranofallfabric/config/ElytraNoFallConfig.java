package crimsonfluff.elytranofallfabric.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "elyranofall")
public class ElytraNoFallConfig implements ConfigData{
    @ConfigEntry.Category("Elytra No Fall")
    @ConfigEntry.Gui.Tooltip()

    public ConfigElytraNoFall elytraNoFall = new ConfigElytraNoFall();
}
