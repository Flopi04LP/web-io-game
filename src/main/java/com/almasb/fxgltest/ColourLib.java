package com.almasb.fxgltest;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author Florian Büchi
 */
public class ColourLib {

    private Color[] colourArray = {
        Color.LAVENDERBLUSH,
        Color.LIGHTCYAN,
        Color.HONEYDEW,
        Color.LIGHTYELLOW,
        Color.ANTIQUEWHITE,
        Color.LAVENDER,
        Color.WHITESMOKE,
        Color.NAVAJOWHITE,
        Color.THISTLE,
        Color.PLUM,
        Color.LIGHTSTEELBLUE,
        Color.LIGHTSALMON
    };

    public Color getColour() {
        Random r = new Random();
        int index = r.nextInt(colourArray.length - 1);
        return colourArray[index];
    }
}
