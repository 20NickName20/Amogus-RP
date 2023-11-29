package sus.nickname.amogusrp.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import sus.nickname.amogusrp.Entity;
import sus.nickname.amogusrp.Item;

import java.util.ArrayList;

public class Player extends Entity {
    static float speed = 3.7f;
    public float health, hunger, thirst, temp;
    public boolean facingRight;
    public byte animation_frame;
    public float animation_timer;
    int selectedSlot;
    ArrayList<Item> inventory;

    public Player(float x, float y) {
        this.pos.x = x;
        this.pos.y = y;

        facingRight = false;
        animation_frame = 0;
        animation_timer = 0;

        health = 100;
        hunger = 100;
        thirst = 100;
        temp = 36.6f;

        selectedSlot = 0;
        inventory = new ArrayList<>();
    }

    public void update(float dt) {
        this.move(dt);
        updateAnimation(dt);
    }

    private void updateAnimation(float dt) {
        if (this.vel.isZero()) {
            animation_timer = 0.9f;
        } else {
            animation_timer += dt * getSpeed();
        }
        animation_frame = (byte)(animation_timer % 2);
    }

    public float getSpeed() {
        return speed * (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 1.35f : 1);
    }

    @Override
    public Vector2 getSpritePos() {
        return new Vector2((facingRight ? 2 : 0) + animation_frame, 0);
    }
}
