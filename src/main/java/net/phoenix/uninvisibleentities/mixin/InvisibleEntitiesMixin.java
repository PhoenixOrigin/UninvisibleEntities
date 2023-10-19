package net.phoenix.uninvisibleentities.mixin;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.AbstractTeam;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public abstract class InvisibleEntitiesMixin {
    @Shadow public abstract void setGlowing(boolean glowing);
    @Shadow protected abstract boolean getFlag(int index);
    @Shadow @Nullable public abstract AbstractTeam getScoreboardTeam();

    @Inject(method = "isInvisible", at = @At("HEAD"), cancellable = true)
    private void disableInvisibleEntities(CallbackInfoReturnable<Boolean> cir) {
        if(this.getFlag(5)) {
            this.setGlowing(true);
        }
        cir.setReturnValue(false);
    }

    @Inject(method = "setInvisible", at = @At("HEAD"))
    private void disableInvisibleEntities(boolean invisible, CallbackInfo ci) {
        if(!invisible) {
            this.setGlowing(false);
        }
    }

    @Inject(method = "getTeamColorValue", at = @At("HEAD"), cancellable = true)
    private void getTeamColor(CallbackInfoReturnable<Integer> cir) {
        if(!this.getFlag(5)) {
            AbstractTeam abstractTeam = this.getScoreboardTeam();
            cir.setReturnValue(abstractTeam != null && abstractTeam.getColor().getColorValue() != null ? abstractTeam.getColor().getColorValue() : 16777215);
            return;
        }
        cir.setReturnValue(0xfa4369);
    }
}