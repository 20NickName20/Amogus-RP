package sus.nickname.amogusrp.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import sus.nickname.amogusrp.Tile;

import static sus.nickname.amogusrp.AmogusRP.*;
import static sus.nickname.amogusrp.AmogusRP.spriteSize;

public class Rock extends Tile {
    public static int variants = 8;
    private final int variant;
    public boolean hasSmallRock;

    public Rock(int variant, boolean small) {
        this.variant = variant;
        this.hasSmallRock = small;
    }

    @Override
    public void draw(SpriteBatch batch, Vector2 tilePos) {
        Vector2 spritePos = new Vector2(variant, hasSmallRock ? 6 : 7);
        batch.draw(sprites[(int)spritePos.y][(int)spritePos.x], (tilePos.x - player.pos.x) * spriteSize - spriteSize / 2f, (tilePos.y - player.pos.y) * spriteSize - spriteSize / 2f);
    }
}
