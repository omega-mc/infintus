package draylar.infintus.mixin;

import draylar.infintus.registry.Items;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract ItemStack getMainHandStack();

    @Shadow public abstract ItemStack getOffHandStack();

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance statusEffectInstance);

    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo ci) {
        if(this.getMainHandStack().getItem().equals(Items.INFINITY_DAGGER) || this.getOffHandStack().getItem().equals(Items.INFINITY_DAGGER)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 2, 0, true, false));
        }
    }
}
