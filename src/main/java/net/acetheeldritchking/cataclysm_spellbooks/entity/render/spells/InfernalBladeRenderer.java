package net.acetheeldritchking.cataclysm_spellbooks.entity.render.spells;

import net.acetheeldritchking.cataclysm_spellbooks.entity.spells.infernal_blade.InfernalBladeModel;
import net.acetheeldritchking.cataclysm_spellbooks.entity.spells.infernal_blade.InfernalBladeProjectile;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class InfernalBladeRenderer extends GeoEntityRenderer<InfernalBladeProjectile> {
    public InfernalBladeRenderer(EntityRendererProvider.Context context) {
        super(context, new InfernalBladeModel());
        this.shadowRadius = 0.5f;
    }
}
