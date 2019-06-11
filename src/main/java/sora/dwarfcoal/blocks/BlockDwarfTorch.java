package sora.dwarfcoal.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;

public class BlockDwarfTorch extends BlockBase{

  protected static final VoxelShape SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D);

  public BlockDwarfTorch() {
    super("dwarf_torch", Block.Properties.from(Blocks.TORCH).lightValue(10));
  }

  @Override
  public List<ItemStack> getDrops(BlockState p_220076_1_, LootContext.Builder p_220076_2_) {
    return Lists.newArrayList(new ItemStack(Item.getItemFromBlock(this)));
  }

  public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
    return SHAPE;
  }

  public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
    return p_196271_2_ == Direction.DOWN && !this.isValidPosition(p_196271_1_, p_196271_4_, p_196271_5_) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
  }

  public boolean isValidPosition(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
    return func_220055_a(p_196260_2_, p_196260_3_.down(), Direction.UP);
  }

  @OnlyIn(Dist.CLIENT)
  public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
    double lvt_5_1_ = (double)p_180655_3_.getX() + 0.5D;
    double lvt_7_1_ = (double)p_180655_3_.getY() + 0.4D;
    double lvt_9_1_ = (double)p_180655_3_.getZ() + 0.5D;
    p_180655_2_.addParticle(ParticleTypes.SMOKE, lvt_5_1_, lvt_7_1_, lvt_9_1_, 0.0D, 0.0D, 0.0D);
    p_180655_2_.addParticle(ParticleTypes.FLAME, lvt_5_1_, lvt_7_1_, lvt_9_1_, 0.0D, 0.0D, 0.0D);
  }

  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.CUTOUT;
  }
}


