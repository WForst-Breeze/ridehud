package net.treset.ridehud.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.treset.ridehud.hud.VehicleHudRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void onRender(MatrixStack matrices, float tickDelta, CallbackInfo info) {

        VehicleHudRenderer.render(matrices);
    }

    @ModifyVariable(method = "renderHeldItemTooltip(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At(value = "STORE"), ordinal = 2)
    private int injectedInt(int k) {
        int value = k;
        if(VehicleHudRenderer.hud != null) {
            //move item tooltip over bars
            if(VehicleHudRenderer.displayTexts) {
                value -= 18 + VehicleHudRenderer.barOffset;
            } else {
                value -= 8 + VehicleHudRenderer.barOffset;
            }

            //fix creative mode item tooltip inconsistency
            if(MinecraftClient.getInstance().interactionManager == null) return value;
            if (!MinecraftClient.getInstance().interactionManager.hasStatusBars()) {
                value -= 14;
            }
        }
        return value;
    }
}