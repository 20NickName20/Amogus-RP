package sus.nickname.amogusrp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import sus.nickname.amogusrp.entities.Player;

import java.util.Map;


public class AmogusRP extends ApplicationAdapter {
	public static OrthographicCamera camera;
	public static Viewport viewport;
	public static SpriteBatch batch;
	Texture spritesheet;
	public static TextureRegion[][] sprites;
	long startTime;
	public final static int spriteSize = 64;
	public static World world;
	public static Player player;
	float dt;

	public static boolean checkCollision(Vector2 first, Vector2 second) {
		return (first.x + 0.1f) < second.x + 1 &&
				(first.x + 0.1f) + 0.8f > second.x &&
				(first.y + 0.1f) < second.y + 1 &&
				(first.y + 0.1f) + 0.8f > second.y;
	}

	public static void fixBleeding(TextureRegion[][] regions) {
		for (TextureRegion[] region : regions) {
			for (TextureRegion textureRegion : region) {
				fixBleeding(textureRegion);
			}
		}
	}

	public static void fixBleeding(TextureRegion region) {
		float fix = 0.01f;

		float x = region.getRegionX();
		float y = region.getRegionY();
		float width = region.getRegionWidth();
		float height = region.getRegionHeight();
		float invTexWidth = 1f / region.getTexture().getWidth();
		float invTexHeight = 1f / region.getTexture().getHeight();
		region.setRegion((x + fix) * invTexWidth, (y + fix) * invTexHeight, (x + width - fix) * invTexWidth, (y + height - fix) * invTexHeight);
	}

	@Override
	public void create() {
		startTime = TimeUtils.millis();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new FitViewport(spriteSize * 16, spriteSize * 10, camera);
		viewport.apply();
		camera.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		spritesheet = new Texture("spritesheet.png");
		spritesheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

		sprites = TextureRegion.split(spritesheet, spriteSize, spriteSize);
		fixBleeding(sprites);

		startTime = TimeUtils.millis();

		world = new World();
		world.testMap();

		player = new Player(0, 0);

		Gdx.input.setInputProcessor(new AInputProcessor());
	}

	@Override
	public void render() {
		dt = Gdx.graphics.getDeltaTime();

		player.update(dt);
		world.update(dt);

		//ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		for (int layer = 0; layer < world.layers.length; layer++) {
			for (Map.Entry<Vector2, Tile> entry : world.layers[layer].entrySet()) {
				Vector2 tilePos = entry.getKey();
				Tile tile = entry.getValue();
				tile.draw(batch, tilePos);
			}
		}
		Vector2 playerSpritePos = player.getSpritePos();
		batch.draw(sprites[(int)playerSpritePos.y][(int)playerSpritePos.x], -spriteSize / 2f, -spriteSize / 2f);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void dispose() {
		batch.dispose();
		spritesheet.dispose();
	}
}
