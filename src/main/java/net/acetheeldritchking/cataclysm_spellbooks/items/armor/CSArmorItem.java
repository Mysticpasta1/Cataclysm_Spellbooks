package net.acetheeldritchking.cataclysm_spellbooks.items.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.client.RenderProvider;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CSArmorItem extends ArmorItem {
    private static final UUID[] ARMOR_ATTRIBUTE_UUID_PER_SLOT = new UUID[]
            {UUID.fromString("F7BFFA65-547A-49D2-8804-3D533070E432"),
                    UUID.fromString("B05AF2C0-5862-4CE6-860A-522C11E1571A"),
                    UUID.fromString("15C1FE6B-3596-412A-B6CF-4077CB37140F"),
                    UUID.fromString("82A575D1-366A-4BBD-91F8-25DB6B804F06")};
    private final Multimap<Attribute, AttributeModifier> ARMOR_ATTRIBUTES;

    public CSArmorItem(CSArmorMaterials materialIn, Type slot, Properties settings) {
        super(materialIn, slot, settings);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        float defense = materialIn.getDefenseForType(slot);
        float toughness = materialIn.getToughness();
        float knockbackResistance = materialIn.getKnockbackResistance();
        UUID uuid = ARMOR_ATTRIBUTE_UUID_PER_SLOT[slot.getSlot().getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier",
                defense, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness",
                toughness, AttributeModifier.Operation.ADDITION));
        if (knockbackResistance > 0)
        {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance",
                    knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        for (Map.Entry<Attribute, AttributeModifier> modifierEntry : materialIn.getAdditionalAttributes().entrySet())
        {
            AttributeModifier atr = modifierEntry.getValue();
            atr = new AttributeModifier(uuid, atr.getName(), atr.getAmount(), atr.getOperation());
            builder.put(modifierEntry.getKey(), atr);
        }
        ARMOR_ATTRIBUTES = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        if (pEquipmentSlot == this.getEquipmentSlot())
        {
            return ARMOR_ATTRIBUTES;
        }
        else
        {
            return ImmutableMultimap.of();
        }
    }
}
