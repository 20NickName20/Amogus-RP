package sus.nickname.amogusrp;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import static sus.nickname.amogusrp.AmogusRP.*;

public abstract class Tile {
    public boolean getPassable() {
        return false;
    }

    public void draw(SpriteBatch batch, Vector2 tilePos) {
        Vector2 spritePos = new Vector2();
        batch.draw(sprites[(int)spritePos.y][(int)spritePos.x], (tilePos.x - player.pos.x) * spriteSize - spriteSize / 2f, (tilePos.y - player.pos.y) * spriteSize - spriteSize / 2f);
    }
}
