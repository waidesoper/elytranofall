package crimsonfluff.elytranofallfabric.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class ConfigElytraNoFall {
    @ConfigEntry.Category("General Settings")
    @ConfigEntry.Gui.CollapsibleObject
    @ConfigEntry.Gui.Tooltip
    public ConfigGeneral general = new ConfigGeneral();
}
