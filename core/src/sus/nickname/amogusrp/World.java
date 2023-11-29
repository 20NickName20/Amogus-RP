package sus.nickname.amogusrp;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import sus.nickname.amogusrp.entities.Player;
import sus.nickname.amogusrp.tiles.Grass;
import sus.nickname.amogusrp.tiles.Rock;
import sus.nickname.amogusrp.tiles.Tree;
import sus.nickname.amogusrp.tiles.Water;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    public HashMap<Vector2, Tile>[] layers;
    public ArrayList<Entity> entities;

    public World() {
        layers = new HashMap[2];
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new HashMap<>();
        }
        entities = new ArrayList<>();
    }

    public void update(float dt) {
        for (Entity entity : entities) {
            entity.pos.add(entity.vel);
            if (entity instanceof Player) {

            }
        }
    }

    public void testMap() {
        for (int x = -100; x < 100; x++) {
            for (int y = -100; y < 100; y++) {
                Vector2 tilePos = new Vector2(x, y);
                //TODO: ИСПРАВИТЬ ВОДУ!!!!!!!!!!!
                double noiseWater = SimplexNoise.noise(x / 50d, y / 50d);
                double noiseRock = SimplexNoise.noise(x / 20d, y / 20d);
                double noiseTree = SimplexNoise.noise(x / 10d, y / 10d);
                if (noiseWater > 0.6) {
                    float waterLevel = (float) (noiseWater - 0.6) * 0.4f;
                    layers[0].put(tilePos, new Water(waterLevel, waterLevel));
                    continue;
                }
                layers[0].put(tilePos, new Grass());
                if (noiseRock > (0.6 + MathUtils.random(-0.1f, 0.1f))) {
                    layers[1].put(tilePos, new Rock(MathUtils.random(Rock.variants - 1), noiseRock > (0.75 + MathUtils.random(-0.2f, 0.2f))));
                    continue;
                }
                if (noiseTree > 0.5) {
                    layers[1].put(tilePos, new Tree(MathUtils.random(Tree.variants - 1), MathUtils.random(3)));
                }
            }
        }
    }
}