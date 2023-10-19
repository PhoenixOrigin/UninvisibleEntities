package net.phoenix.uninvisibleentities.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class InvisibleEntitiesMixin {
    @Inject(method = "isInvisible", at = @At("HEAD"), cancellable = true)
    private void disableInvisibleEntities(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}