

package Entity.Properties;

import TileMap.TileMap;

/**
*
* @author nkostiai
*
* TileVariables säilyttää jokaisen MapObject -luokan perivän instanssin tarvitsemia
* tietoja nykyisestä TileMapista.
*
*/
public class TileVariables {
    private TileMap tileMap;
    private int tileSize;
    private double xMapPosition;
    private double yMapPosition;
    
    public TileVariables(){
        
    }
    
    public void setTileMap(TileMap tm){
        this.tileMap = tm;
    }
    
    public void setTileSize(int tilesize){
        this.tileSize = tilesize;
    }
    
    public void setXMapPosition(int xmapposition){
        this.xMapPosition = xmapposition;
    }
    
    public void setYMapPosition(int yMapPosition){
        this.yMapPosition = yMapPosition;
    }
    
    public TileMap getTileMap(){
        return this.tileMap;
    }
    
    public int getTileSize(){
        return tileSize;
    }
    
    public double getXMapPosition(){
        return xMapPosition;
    }
    
    public double getYMapPosition(){
        return yMapPosition;
    }
    
}