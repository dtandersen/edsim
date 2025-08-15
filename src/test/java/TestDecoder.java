import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.*;
import org.junit.jupiter.api.*;

public class TestDecoder
{
    private Experimentals experimentals;

    private Blueprints blueprints;

    @BeforeEach
    public void setup()
    {
        blueprints = new Blueprints();
        experimentals = new Experimentals();
    }

    @Test
    public void testDecode()
    {
        ShipDecoder decoder = new ShipDecoder(blueprints, experimentals);
        ShipSpec spec = new ShipSpec();
        Ship ship = decoder.decode(spec, List.of(blueprint("Blast Resistant"), experimental("Angled Plating")));

        assertThat(ship.hullMod, equalTo(blueprints.getByName("Blast Resistant")));
    }

    private int blueprint(String string)
    {
        return blueprints.get(string);
    }

    private int experimental(String string)
    {
        return experimentals.get(string);
    }
}
