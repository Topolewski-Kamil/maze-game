package Bot;

import creaturesSight.Creature;
import main.Handler;
import world.Assets;
import world.Tile;

import java.awt.*;
import java.util.Random;

public class Bot extends CreatureBot {
    //bot njksd.acklkaXLKAMLXjwhkajdnja
    public Bot(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 10;
        bounds.y = 5;
        bounds.width = 50;
        bounds.height = 50;

    }

    // boolean state = true;


    public void update() {
        getInput();
        move();
        //     handler.getGameCamera().centerOnEntity(this);
    }

    public static int getRandom(int[] array) {
        int r = new Random().nextInt(array.length);
        return array[r];
    }


    private void getInput() {
        xMove = 0;
        yMove = 0;

        //  Random r = new Random();
        int[] numbers = new int[100];
        int k;
// populate the first 20 with the value '1'
        for (int i = 0; i < 100; ++i) {
            if (i < 35)
                numbers[i] = 1;
            else if (i < 50) numbers[i] = 0;
            else if (i < 85) numbers[i] = 2;
            else numbers[i] = 3;
        }
        // for (int i = 0; i < 100; ++i) {
        k=getRandom(numbers);

        //  int ix = (int)(r.nextInt(4) * 100);
        //  int k = numbers[ix];

        // while (System.currentTimeMillis()%1000!=1) {



        if (k == 0) {
//                int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            // if (ok == 1) {
//                    if (!collision((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
            //                          !collision((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
            //  yMove = -speed;


            yMove = -speed;


            //                 int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            //          if (collision((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
            //                            collision((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))k = r.nextInt(4);
            //ok = 0;

        }
        if (k == 1) {
            //   yMove = speed;

            yMove = speed;


//                int ty = (int) ((y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT);
            //  if (ok == 1) {
            //                   if (!collision((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
            //                         !collision((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {yMove = speed; }
            //                int ty = (int) ((y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT);

            //              if (collision((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
            //                    collision((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) k = r.nextInt(4) ;


        }

        if (k == 2) {
//            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            //          //if (ok == 1) {
            //        if (!collision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
            //              !collision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
            //  xMove = speed;
            xMove = speed;
            //          int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            //                if (collision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
            //                    collision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))k = r.nextInt(4);


        }
        // }
        if (k == 3) {
            // xMove = -speed;
            xMove = -speed;
            //        int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            //            if (!collision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
            //                 && !collision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {xMove = -speed; }

            //     int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            //   if (collision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
            //         && collision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) k = r.nextInt(4);

        }
        //}


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




