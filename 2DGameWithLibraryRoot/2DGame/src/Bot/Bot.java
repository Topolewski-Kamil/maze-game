package Bot;

import creaturesSight.Creature;
import main.Handler;
import world.Assets;
import world.Tile;

import java.awt.*;
import java.util.Random;

public class Bot extends CreatureBot {

    public Bot(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 10;
        bounds.y = 5;
        bounds.width = 50;
        bounds.height = 50;
       //  speed = 10.0f;
    }

   // boolean state = true;


    public void update() {
        getInput();
        move();
        //     handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        Random r = new Random();
        int k, ok = 1;
        // while (state) {
        k = r.nextInt(4);


            if (k == 0) {
                int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
               // if (ok == 1) {
                    if (!collision((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                            !collision((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                        yMove = -speed;
                    } else if (collision((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                        collision((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                        k = r.nextInt(4) ;
                        //ok = 0;
                    }
              //  }
            }  if (k == 1) {

                int ty = (int) ((y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT);
              //  if (ok == 1) {
                    if (!collision((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                            !collision((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) yMove = speed;
                    else if (collision((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                            collision((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                        k = r.nextInt(4) ;
                        //ok = 0;
                    }
               // }
            }
        if (k == 2) {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            //if (ok == 1) {
            if (!collision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
                xMove = speed;
            else if (collision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    collision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                k = r.nextInt(4);
                //  ok = 0;
            }
            //}
        }
        if (k == 3) {
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

                if (!collision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
                        && !collision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) xMove = -speed;
                else if (collision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
                        && collision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
                    k = r.nextInt(4);


        }

    }

        public void render (Graphics g){
              //   if (this.isEaten() == true) {
            //    g.drawImage(Assets.grass, (int) (260 - handler.getGameCamera().getxOffset()), (int) (68 - handler.getGameCamera().getyOffset()), 60, 60, null);
            //     }
                g.drawImage(Assets.policeFront, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            }

//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
        }




