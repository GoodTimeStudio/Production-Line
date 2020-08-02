package com.mcgoodtime.productionline.tiles;


import com.mcgoodtime.productionline.core.ProductionLine;
import com.mcgoodtime.productionline.mixin.ISubTileGenerating;
import com.mcgoodtime.productionline.mixin.MixinSubTileGenerating;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;

public class SubTileFlowerOfOsiris extends SubTileFunctional{
    private int consume;
    private int growthLevel = 10;

    private int range = 3;

    @Override
    public void onUpdate() {
        if(!supertile.getWorld().isRemote){
            return;
        }
        for(BlockPos pos:BlockPos.getAllInBox(getPos().add(-range,0,-range),getPos().add(range,0,range))){
            SubTileGenerating tile = getManaGenerator(pos);
            if(tile!=null){
                if(((ISubTileGenerating)tile).canEnhance()){
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
        ProductionLine.LOGGER.info("我运行了");
        ((ISubTileGenerating)stg).setGrowth(growthData(growthLevel));
    }


    private int growthData(int growthLevel){
        return (int)Math.ceil((Math.log((5*(growthLevel-0.5)))*1.4));
    }

}
