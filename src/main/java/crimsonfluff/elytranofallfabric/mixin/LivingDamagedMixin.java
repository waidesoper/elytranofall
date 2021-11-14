package crimsonfluff.elytranofallfabric.mixin;

import crimsonfluff.elytranofallfabric.onLivingDamagedCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingDamagedMixin{
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    protected void onLivingDamaged(final DamageSource source, final float amount, final CallbackInfoReturnable<Boolean> cir){
        ActionResult result = onLivingDamagedCallback.EVENT.invoker().checkSource((Entity) (Object) this, source);

        if(result == ActionResult.FAIL){
            cir.cancel();
        }
    }
}
