package TilePackage;

import java.awt.*;

/**
 * Tile spec class
 */
public class TileSpec
{
    /**
     * type
     */
    private TileType type;

    /**
     * color
     */
    private Color color;

    /**
     * tile spec constructor
     * @param type tile type
     */
    public TileSpec(TileType type) {
        setType(type);
    }

    /**
     * setter
     * @param type, type of tile spec
     */
    public void setType(TileType type) {
        if (type == TileType.RED_FIRE) {
            this.type = TileType.RED_FIRE;
            this.color = Color.RED;
        } else if (type == TileType.BLUE_ICE) {
            this.type = TileType.BLUE_ICE;
            this.color = Color.BLUE;
        } else if (type == TileType.GREEN_NATURE) {
            this.type = TileType.GREEN_NATURE;
            this.color = Color.GREEN;
        }
    }

    /**
     * getter
     * @return get tile type
     */
    public TileType getType() {
        return this.type;
    }

    /**
     * getter
     * @return get tile color
     */
    public Color getColor() {
        return this.color;
    }
}