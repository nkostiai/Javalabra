package TileMap;

import Global.GlobalConstants;
import java.awt.image.*;

import java.io.*;
import javax.imageio.ImageIO;

/**
 *
 * @author nkostiai TileMap kuvaa aina tiettyä pelitason kenttää. TileMapin
 * säilyttää kartan kaksiulotteisessa Tile[][] -taulukossa, johon arvot luetaan
 * resurssikansiossa olevasta .map -tiedostosta.
 * 
*/
public class TileMap {

    /**
     * X-koordinaatti.
     */
    private double x;
    
    /**
     * Y-koordinaatti.
     */
    private double y;

    /**
     * Minimi X-koordinaatti.
     */
    private int xmin;
    
    /**
     * Minimi Y-koordinaatti.
     */
    private int ymin;
    
    /**
     * Maksimi X-koordinaatti.
     */
    private int xmax;
    
    /**
     * Maksimi Y-koordinaatti.
     */
    private int ymax;

    /**
     * Kartta kaksiulotteisena kokonaislukutaulukkona.
     */
    private int[][] map;
    
    /**
     * Tiilien sivun pituus. 
     */
    private final int tileSize;
    
    /**
     * Kuinka monta tiiliä kartalle mahtuu pystysuuntaan.
     */
    private int numberOfRows;
    
    
    /**
     * Kuinka monta tiiliä kartalle mahtuu vaakasuuntaan.
     */
    private int numberofColumns;
    
    /**
     * Kartan leveys pikseleissä.
     */
    private int width;
    
    /**
     * Kartan korkeus pikseleissä.
     */
    private int height;

    /**
     * Kartan tileset -kuva.
     */
    private BufferedImage tileset;
    
    /**
     * Montako tiiliä tileset -kuvassa on rivissä.
     */
    private int numberOfTilesPerRow;
    
    /**
     * Kartta kaksiulotteisena tiilitaulukkona.
     */
    private Tile[][] tiles;

    /**
     * Kuinka paljon ollaan pystysuunnassa.
     */
    private int rowOffset;
    
    /**
     * Kuinka paljon ollaan vaakasuunnassa.
     */
    private int columnOffset;
    
    /**
     * Montako riviä piirretään.
     */
    private final int numberOfRowsToDraw;
    
    /**
     * Montako saraketta piirretään.
     */
    private final int numberOfColumnsToDraw;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        numberOfRowsToDraw = GlobalConstants.WINDOWHEIGHT / tileSize + 4;
        numberOfColumnsToDraw = GlobalConstants.WINDOWWIDTH / tileSize + 4;
    }
    
    /**
     * Lataa tiilit kartalta muistiin.
     */
    public void loadTiles(String s) {
        try {
            tileset = ImageIO.read(getClass().getResourceAsStream(s));
            numberOfTilesPerRow = tileset.getWidth() / tileSize;
            tiles = new Tile[2][numberOfTilesPerRow];
            setTiles();

        } catch (IOException e) {
            GlobalConstants.error("An error occured while trying to load tiles. Teminating");
        }
    }
    
    
    /**
     * Asettaa tiilit tiilitaulukkoon.
     */
    private void setTiles() {
        BufferedImage subimage;
        for (int col = 0; col < numberOfTilesPerRow; col++) {
            subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
            tiles[0][col] = new Tile(subimage, Tile.NONSOLID);
            subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
            tiles[1][col] = new Tile(subimage, Tile.SOLID);
        }
    }
    
    /**
     * Lataa karttatiedoston muistiin.
     */
    public void loadMap(String s) {
        try {
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            numberofColumns = Integer.parseInt(br.readLine());
            numberOfRows = Integer.parseInt(br.readLine());
            setMapAttributes();
            setUpMap(br);
            
        } catch (IOException | NumberFormatException e) {
            GlobalConstants.error("An error occured while loading mapfile. Terminating.");
        }
    }
    
    /**
     * Parseaa karttatiedoston ja asettaa numerot taulukkoon.
     */
    private void setUpMap(BufferedReader br) throws IOException{
        String regex = "\\s+";
            for (int row = 0; row < numberOfRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(regex);
                for (int col = 0; col < numberofColumns; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
    }
    
    /**
     * Asettaa kartan attribuutit.
     */
    private void setMapAttributes() {
        map = new int[numberOfRows][numberofColumns];
        width = numberofColumns * tileSize;
        height = numberOfRows * tileSize;

        xmin = GlobalConstants.WINDOWWIDTH - width;
        xmax = 0;
        ymin = GlobalConstants.WINDOWHEIGHT - height;
        ymax = 0;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getColumnsToDraw() {
        return numberOfColumnsToDraw;
    }

    public int getRowsToDraw() {
        return numberOfRowsToDraw;
    }

    public int getNumberOfColumnsInTileSet() {
        return numberOfTilesPerRow;
    }

    public BufferedImage getTileset() {
        return tileset;
    }

    public int getrowOffset() {
        return rowOffset;
    }

    public int getcolumnOffset() {
        return columnOffset;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberofColumns;
    }

    public int[][] getMap() {
        return map;
    }

    public int getNumberOfTilesPerRow() {
        return numberOfTilesPerRow;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
    
    /**
     * Palauttaa kysytyn tiilin tyypin.
     * 
     * @param row Halutun tiilin rivi.
     * @param col Halutun tiilin sarake.
     * @return  Tiilin tyyppinumero.
     */
    public int getType(int row, int col) {
        if (row < 0 || col < 0 || row >= map.length || col >= map[0].length) {
            return Tile.NONSOLID;
        }
        int rc = map[row][col];
        int r = rc / numberOfTilesPerRow;
        int c = rc % numberOfTilesPerRow;
        if (r < 0 || c < 0 || r > tiles.length || c > tiles[0].length) {
            return Tile.NONSOLID;
        } else {
            return tiles[r][c].getType();
        }
    }
    
    /**
     * Asettaa kartan sijainnin.
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;

        fixBounds();

        columnOffset = (int) -this.x / tileSize;
        rowOffset = (int) -this.y / tileSize;
    }
    
    /**
     * Korjaa sijainnin jos mennyt rajojen ulkopuolelle.
     */
    private void fixBounds() {
        if (x < xmin) {
            x = xmin;
        }
        if (y < ymin) {
            y = ymin;
        }
        if (x > xmax) {
            x = xmax;
        }
        if (y > ymax) {
            y = ymax;
        }

    }
}
