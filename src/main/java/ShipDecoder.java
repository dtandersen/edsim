import java.util.*;

public class ShipDecoder
{
    private Blueprints blueprints;

    private Experimentals experimentals;

    public ShipDecoder(Blueprints blueprints, Experimentals experimentals)
    {
        this.blueprints = blueprints;
        this.experimentals = experimentals;
    }

    public Ship decode(ShipSpec spec, List<Integer> data)
    {
        Ship ship = new Ship();
        ship.hullMod = blueprints.get(data.get(0));
        return ship;
    }
}
