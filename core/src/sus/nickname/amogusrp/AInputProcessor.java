package sus.nickname.amogusrp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import sus.nickname.amogusrp.tiles.Rock;

import static sus.nickname.amogusrp.AmogusRP.*;

public class AInputProcessor extends InputAdapter {
    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.A) {
            player.facingRight = false;
        } else if (i == Input.Keys.D) {
            player.facingRight = true;
        }
        player.vel.x = 0;
        player.vel.y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.vel.y += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.vel.x -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.vel.y -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.vel.x += 1;
        }
        player.vel.setLength(player.getSpeed());
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        player.vel.x = 0;
        player.vel.y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.vel.y += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.vel.x -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.vel.y -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.vel.x += 1;
        }
        player.vel.setLength(player.getSpeed());
        return false;
    }

    public boolean scrolled(float x, float y) {
        return false;
    }

    public boolean touchDown(int x, int y, int pointer, int button) {
        /*
        Vector2 worldPos = viewport.unproject(new Vector2(x, y)).scl(1f / spriteSize).add(player.pos);
        Tile tile = world.layers[1].get(new Vector2(MathUtils.round(worldPos.x), MathUtils.round(worldPos.y)));
        if (tile instanceof Rock && player.pos.dst(worldPos) <= 5) {
            Rock rock = (Rock) tile;
            if (!rock.hasSmallRock) {
                rock.hasDrawnFace = true;
            }
        }
        */
        return false;
    }
}
