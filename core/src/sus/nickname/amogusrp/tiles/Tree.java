package sus.nickname.amogusrp.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import sus.nickname.amogusrp.Tile;

import static sus.nickname.amogusrp.AmogusRP.*;

public class Tree extends Tile {
    public static int variants = 4;
    private final int variant;
    public int apples;

    public Tree(int variant, int apples) {
        this.variant = variant;
        this.apples = apples;
    }

    @Override
    public void draw(SpriteBatch batch, Vector2 tilePos) {
        Vector2 spritePos = new Vector2(variant, 2 + apples);
        batch.draw(sprites[(int)spritePos.y][(int)spritePos.x], (tilePos.x - player.pos.x) * spriteSize - spriteSize / 2f, (tilePos.y - player.pos.y) * spriteSize - spriteSize / 2f);
    }
}
