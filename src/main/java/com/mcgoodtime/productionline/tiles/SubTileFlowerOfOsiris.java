package com.mcgoodtime.productionline.tiles;



import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;

    /*
        Consume the energy in the network to accelerate the surrounding flowers
        Energy consume unfinished
     */

public class SubTileFlowerOfOsiris extends SubTileFunctional{

    private int consume;

    private int accelerateLevel = 10;

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
                    accelerate(tile);
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

    private void accelerate(SubTileGenerating stg){
        calculateConsume(accelerateLevel);
        for(int i=0;i<=Math.min(accelerateLevel,getMaxAccelerateLevel());i++){
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

    private void calculateConsume(int growthLevel){
        this.consume = (int)Math.ceil(64*(((Math.log(growthLevel)/Math.log(5))+((1/3*Math.pow(growthLevel,2))+2))*(1+(0.1*range))));
    }

    private int getMaxAccelerateLevel(){
        return 10;
    }


    @Override
    public int getMaxMana() {
        return 0;
    }
}
