package Entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{


    //These are making sure that GamePanel.Java and KeyHandler.Java are able to be linked in this file.
    GamePanel gp;
    KeyHandler keyH;

    //This is the player itself.
    public Player(GamePanel gp, KeyHandler keyH){

        this.gp=gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();

    }

    //This method is to set the default place where the character will spawn upon entering the game initially.
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    //This method is to get the player image.
    public void getPlayerImage() {

        //This links the png of the sprites to different variables.
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //This method is to move the character as well as change the sprite.
    public void update() {
        //This if statement makes it so that only when the keys are pressed does the sprite move as well as the animation changes.
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if(keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            }
            else if(keyH.downPressed == true){
                direction = "down";
                y += speed;
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if(spriteCounter > 10) {
                if (spriteNum ==1) {
                    spriteNum = 2;
                } else if (spriteNum ==2 ) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    //This method is for drawing the character, as well as drawing the actual game.
    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        //This switch is so that it links the "image" of the character based on the spriteNum, since up1, up2, etc is linked to a specific sprite.
        switch (direction) {
            case "up":
                if(spriteNum ==1) {
                    image = up1;
                }
                if (spriteNum ==2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum ==1) {
                    image = down1;
                }
                if (spriteNum ==2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum ==1) {
                    image = left1;
                }
                if (spriteNum ==2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum ==1) {
                    image = right1;
                }
                if (spriteNum ==2){
                    image = right2;
                }
                break;
        }
        //This draws the game.
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
