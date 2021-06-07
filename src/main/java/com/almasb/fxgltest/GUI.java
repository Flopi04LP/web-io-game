package com.almasb.fxgltest;

import com.almasb.fxgl.dsl.FXGL;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * author Florian Büchi
 */
public class GUI {

    public GUI() {
        Stats stats = Stats.getInstance();


        Text textScore = new Text();
        textScore.setText("Score: ");
        textScore.setTranslateX((getAppWidth() / 2 + 10));
        textScore.setTranslateY(getAppHeight() - 10);

        Text textHp = new Text("HP: ");
        textHp.setTranslateX(0);
        textHp.setTranslateY(getAppHeight() - 10);

        Text textUpgrade = new Text("Upgrade Tokens: ");
        textUpgrade.setTranslateX(0);
        textUpgrade.setTranslateY(20);

        FXGL.getGameScene().addUINodes(textScore, textHp, textUpgrade);

        textHp.setFont(Font.font(15.0));
        textScore.setFont(Font.font(15.0));

        textUpgrade.textProperty().bind(FXGL.getip("upgradeTokens").asString("Upgrade Tokens: %d"));
        textScore.textProperty().bind(FXGL.getip("score").asString("Score: %d"));
        textHp.textProperty().bind(FXGL.getip("hp").asString("Hp: %d"));
    }
}
