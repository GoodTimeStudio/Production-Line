package com.mcgoodtime.productionline.tiles;


import com.mcgoodtime.productionline.core.ProductionLine;
import ic2.core.energy.grid.Tile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;

public class SubTileFlowerOfOsiris extends SubTileFunctional{
    private int consume;
    private int growthLevel = 10;

    private int growthData;

    private int range = 3;

    @Override
    public void onUpdate() {
        if(!supertile.getWorld().isRemote){
            return;
        }
        for(BlockPos pos:BlockPos.getAllInBox(getPos().add(-range,0,-range),getPos().add(range,0,range))){
            SubTileGenerating tile = getManaGenerator(pos);
            if(tile!=null) {
                if(!hasMultiOsiris(tile)){
                    enhance(tile);
                }
            }
        }
    }

    private SubTileGenerating getManaGenerator(BlockPos pos) {
       if(supertile.getWorld().getBlockState(pos).getBlock() instanceof BlockSpecialFlower){
            TileEntity tile = supertile.getWorld().getTileEntity(pos);
            SubTileEntity subTile= ((TileSpecialFlower) tile).getSubTile();
                if(subTile instanceof SubTileGenerating){
                   return (SubTileGenerating)subTile;
                }
           }
        return null;
    }

    private void enhance(SubTileGenerating stg){
        calculateGrowthData(growthLevel);
        for(int i=0;i<growthData;i++){
            stg.onUpdate();
        }
    }

    private boolean hasMultiOsiris(SubTileGenerating GeneratingTile){
        int count=1;
        for(BlockPos pos:BlockPos.getAllInBox(getPos().add(-range*2,0,-range*2),getPos().add(range*2,0,range*2))){
            if(!pos.equals(this.getPos())){
                TileEntity tile=supertile.getWorld().getTileEntity(pos);
                if(tile != null){
                    SubTileEntity subTile=((TileSpecialFlower) tile).getSubTile();
                    if(subTile instanceof SubTileFlowerOfOsiris){
                        count++;
                    }
                }
            }
        }
        if(count >=2){
            GeneratingTile.getWorld().setBlockState(GeneratingTile.getPos(),Blocks.DEADBUSH.getDefaultState(),3);
            return true;
        }else
            return false;
    }

    private void calculateGrowthData(int growthLevel){
        this.growthData=(int)Math.ceil((Math.log((5*(growthLevel-0.5)))*1.4));
    }

    @Override
    public int getMaxMana() {
        return 0;
    }
}
