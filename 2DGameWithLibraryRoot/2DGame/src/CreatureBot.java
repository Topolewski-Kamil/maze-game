import java.awt.Graphics;

public abstract class CreatureBot extends Entity { // using methods from Entity

 //   public PowerUpEntity p1;

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 4.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 50, DEFAULT_CREATURE_HEIGHT = 60;

    protected int health;
    public float speed;
    protected float xMove, yMove;

    private static long millis = System.currentTimeMillis() % 1000;
    private static long currentTime = 10000000;

    public int getHealth() {
        return health;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void move() {
        moveX();
        moveY();
    }

    public void moveX() {
        if (xMove > 0) {// Moving right

            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if (!collision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
                    && !collision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
             //   while()
                x += xMove;

            }

        } else if (xMove < 0) {// Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if (!collision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
                    && !collision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;

            }
        }
    }

    public void moveY() {
        if (yMove < 0) {// Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if (!collision((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
                    && !collision((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;

            }
        } else if (yMove > 0) {// Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if (!collision((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
                    && !collision((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
               y += yMove;

            }
        }
    }

    protected boolean collision(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

 //   protected boolean collectedPowerUp(int x, int y) {
 //       return handler.getWorld().getTile(x, y).isPowerUp();
 //   }

    public CreatureBot(Handler handler, float x, float y, int width, int height) { // has to has the same variables as
        // Entity constructor
        super(handler, x, y, width, height); // passes those variables to Entity constructor variables
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
}
