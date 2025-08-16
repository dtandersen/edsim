package edsim;

import org.junit.jupiter.api.*;
import edsim.entity.*;
import edsim.repo.*;

public class EdSimTest
{
    private Experimentals experimentals;

    private Blueprints blueprints;

    @BeforeEach
    public void loadRepos()
    {
        blueprints = new Blueprints();
        experimentals = new Experimentals();
    }

    protected Effect blueprint(ModuleType type, String name)
    {
        return blueprints.findByName(type, name);
    }

    protected Effect experimental(ModuleType type, String name)
    {
        return experimentals.findByName(type, name);
    }

    protected int idOfBlueprint(ModuleType type, String name)
    {
        return blueprint(type, name).getId();
    }

    protected int idOfExperimental(ModuleType type, String name)
    {
        return experimental(type, name).getId();
    }
}
