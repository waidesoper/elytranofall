package crimsonfluff.elytranofallfabric;

import crimsonfluff.elytranofallfabric.config.ElytraNoFallConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ElytraItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElytranofallFabric implements ModInitializer {
    public static final String MOD_ID = "elytranofall";
    public static ElytraNoFallConfig CONFIG;
    public static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void onInitialize() {
        AutoConfig.register(ElytraNoFallConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ElytraNoFallConfig.class).getConfig();

        ConfigBuilder builder = ConfigBuilder.create().setTitle(Text.of("Elytra No Fall Config"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        onLivingDamagedCallback.EVENT.register((entity, source) ->{
            if(! (entity instanceof ServerPlayerEntity)) return ActionResult.PASS;

            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            if(player.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof ElytraItem){
                if(source.getTypeRegistryEntry().matchesKey(DamageTypes.FLY_INTO_WALL)|| source.getTypeRegistryEntry().matchesKey(DamageTypes.FALL)){
                    if(EnchantmentHelper.getLevel(Enchantments.FEATHER_FALLING, player.getEquippedStack(EquipmentSlot.FEET)) >= CONFIG.elytraNoFall.general.requiresFeatherFalling){
                        LOGGER.info("Damage avoided");
                        return ActionResult.FAIL;
                    }
                }
            }
            return ActionResult.PASS;
        });
    }


}
