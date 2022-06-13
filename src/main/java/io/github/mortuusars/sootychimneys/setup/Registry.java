package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.blocks.BrickChimneyBlock;
import io.github.mortuusars.sootychimneys.blocks.ChimneyBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SootyChimneys.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SootyChimneys.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SootyChimneys.MOD_ID);

    public static final TagKey<Block> CHIMNEY_BLOCK_TAG = BlockTags.create(new ResourceLocation("forge:chimney"));
    public static final TagKey<Item> CHIMNEY_ITEM_TAG = ItemTags.create(new ResourceLocation("forge:chimney"));

    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }

    public static final RegistryObject<BrickChimneyBlock> BRICK_CHIMNEY = BLOCKS.register("brick_chimney",
            () -> new BrickChimneyBlock(BlockBehaviour.Properties.of(Material.STONE)));


    public static final RegistryObject<BlockItem> BRICK_CHIMNEY_ITEM = ITEMS.register("brick_chimney",
            () -> new BlockItem(BRICK_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));


    public static final RegistryObject<BlockEntityType<ChimneyBlockEntity>> CHIMNEY_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("brick_chimney", () -> BlockEntityType.Builder.of(ChimneyBlockEntity::new, BRICK_CHIMNEY.get()).build(null));

}
