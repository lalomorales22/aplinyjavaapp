package aplinyjavaapp;

import java.awt.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * PlinyMetalTheme
 *
 * A custom Metal Theme that sets some dark/red defaults.
 * Intended to evoke a sense of ancient manuscripts meets modern tech.
 */
public class PlinyMetalTheme extends DefaultMetalTheme {

    private final ColorUIResource primary1 = new ColorUIResource(64, 0, 0);
    private final ColorUIResource primary2 = new ColorUIResource(128, 0, 0);
    private final ColorUIResource primary3 = new ColorUIResource(192, 0, 0);

    private final ColorUIResource black = new ColorUIResource(Color.BLACK);
    private final ColorUIResource white = new ColorUIResource(Color.WHITE);

    @Override
    public ColorUIResource getWindowTitleBackground() {
        return primary2;
    }

    @Override
    public ColorUIResource getWindowTitleForeground() {
        return white;
    }

    @Override
    public ColorUIResource getControl() {
        return black;
    }

    @Override
    public ColorUIResource getPrimaryControlHighlight() {
        return primary3;
    }

    @Override
    public ColorUIResource getPrimaryControlDarkShadow() {
        return primary1;
    }

    @Override
    public ColorUIResource getControlTextColor() {
        return new ColorUIResource(Color.RED);
    }

    @Override
    public ColorUIResource getSystemTextColor() {
        return new ColorUIResource(Color.RED);
    }

    @Override
    public ColorUIResource getUserTextColor() {
        return new ColorUIResource(Color.RED);
    }

    @Override
    public ColorUIResource getWindowBackground() {
        return black;
    }
}