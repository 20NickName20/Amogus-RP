package sus.nickname.amogusrp;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static sus.nickname.amogusrp.AmogusRP.checkCollision;
import static sus.nickname.amogusrp.AmogusRP.world;

public abstract class Entity {
    public Vector2 pos;
    public Vector2 vel;

    public Entity() {
        pos = new Vector2();
        vel = new Vector2();
    }

    public void update(float dt) {
        move(dt);
    }

    public Vector2 getSpritePos() {
        return new Vector2();
    }

    public boolean isMoving() {
        return vel.x != 0 || vel.y != 0;
    }

    protected void move(float dt) {
        Vector2 vel1 = vel.cpy();
        Vector2 posAlign = new Vector2(MathUtils.round(pos.x), MathUtils.round(pos.y));
        HashMap<Vector2, Tile> loadedWorld0 = new HashMap<>();
        HashMap<Vector2, Tile> loadedWorld1 = new HashMap<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                Vector2 tilePos = new Vector2(posAlign.x + x, posAlign.y + y);
                Tile tile0 = world.layers[0].get(tilePos);
                if (tile0 != null) {
                    loadedWorld0.put(tilePos, tile0);
                }
                Tile tile1 = world.layers[1].get(tilePos);
                if (tile1 != null) {
                    loadedWorld1.put(tilePos, tile1);
                }
            }
        }

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                Vector2 tilePos = new Vector2(posAlign.x + x, posAlign.y + y);
                Tile tile0 = loadedWorld0.get(tilePos);

                if (tile0 != null && !tile0.getPassable() && !checkCollision(pos, tilePos)) {
                    if (checkCollision(pos.cpy().add(vel1.x * dt, 0), tilePos)) {
                        vel1.x = 0;
                    }
                }

                Tile tile1 = loadedWorld1.get(tilePos);

                if (tile1 != null && !tile1.getPassable() && !checkCollision(pos, tilePos)) {
                    if (checkCollision(pos.cpy().add(vel1.x * dt, 0), tilePos)) {
                        vel1.x = 0;
                    }
                }
            }
        }
        pos.x += vel1.x * dt;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                Vector2 tilePos = new Vector2(posAlign.x + x, posAlign.y + y);
                Tile tile0 = loadedWorld0.get(tilePos);

                if (tile0 != null && !tile0.getPassable() && !checkCollision(pos, tilePos)) {
                    if (checkCollision(pos.cpy().add(0, vel1.y * dt), tilePos)) {
                        vel1.y = 0;
                    }
                }

                Tile tile1 = loadedWorld1.get(tilePos);

                if (tile1 != null && !tile1.getPassable() && !checkCollision(pos, tilePos)) {
                    if (checkCollision(pos.cpy().add(0, vel1.y * dt), tilePos)) {
                        vel1.y = 0;
                    }
                }
            }
        }
        pos.y += vel1.y * dt;
    }
}
