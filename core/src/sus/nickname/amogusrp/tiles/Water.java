package sus.nickname.amogusrp.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import sus.nickname.amogusrp.Tile;

import static sus.nickname.amogusrp.AmogusRP.*;

public class Water extends Tile {
    float waterLevel, maxLevel;

    public Water(float waterLevel, float maxLevel) {
        this.waterLevel = waterLevel;
        this.maxLevel = maxLevel;
    }

    @Override
    public boolean getPassable() {
        return true;
    }

    @Override
    public void draw(SpriteBatch batch, Vector2 tilePos) {
        Vector2 spritePosWater = new Vector2(1, 1);
        Vector2 spritePosGravel = new Vector2(2, 1);
        batch.draw(sprites[(int)spritePosGravel.y][(int)spritePosGravel.x], (tilePos.x - player.pos.x) * spriteSize - spriteSize / 2f, (tilePos.y - player.pos.y) * spriteSize - spriteSize / 2f);
        batch.setColor(1, 1, 1, waterLevel);
        batch.draw(sprites[(int)spritePosWater.y][(int)spritePosWater.x], (tilePos.x - player.pos.x) * spriteSize - spriteSize / 2f, (tilePos.y - player.pos.y) * spriteSize - spriteSize / 2f);
        batch.setColor(1, 1, 1, 1);
    }
}
