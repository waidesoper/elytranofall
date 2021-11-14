package crimsonfluff.elytranofallfabric;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;

public interface onLivingDamagedCallback {
    Event<onLivingDamagedCallback> EVENT = EventFactory.createArrayBacked(onLivingDamagedCallback.class,
            (listeners) -> (entity, source) -> {
                for (onLivingDamagedCallback listener : listeners) {
                    ActionResult result = listener.checkSource(entity, source);

                    if(result != ActionResult.PASS){
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    ActionResult checkSource(Entity entity, DamageSource source);

}
