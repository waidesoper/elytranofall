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
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ElytraItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;

public class ElytranofallFabric implements ModInitializer {
    public static final String MOD_ID = "elytranofall";
    public static ElytraNoFallConfig CONFIG;

    @Override
    public void onInitialize() {
        AutoConfig.register(ElytraNoFallConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ElytraNoFallConfig.class).getConfig();

        ConfigBuilder builder = ConfigBuilder.create().setTitle(new TranslatableText("title.elytranofall.config"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        onLivingDamagedCallback.EVENT.register((entity, source) ->{
            if(! (entity instanceof ServerPlayerEntity)) return ActionResult.PASS;

            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            if(player.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof ElytraItem){
                if(source == DamageSource.FLY_INTO_WALL || source == DamageSource.FALL){
                    if(EnchantmentHelper.getLevel(Enchantments.FEATHER_FALLING, player.getEquippedStack(EquipmentSlot.FEET)) >= CONFIG.elytraNoFall.general.requiresFeatherFalling){
                        return ActionResult.FAIL;
                    }
                }
            }
            return ActionResult.PASS;
        });
    }


}
