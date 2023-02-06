package cn.com.pism.batslog.ui;

import com.intellij.ui.ColorChooser;
import com.intellij.util.ui.JBUI;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;

import static cn.com.pism.batslog.constants.BatsLogConstant.KEY_WORD_DEF_COL;

/**
 * @author wangyihuai
 * @since 2021/05/27 17:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ColorButton extends JButton {

    private Color selectColor;

    private int cbWidth;
    private int cbHeight;

    public ColorButton(Color color, int cbWidth, int cbHeight, Callback<Color> callback) {
        this.cbWidth = cbWidth;
        this.cbHeight = cbHeight;
        if (color != null) {
            this.selectColor = color;
        } else {
            this.selectColor = KEY_WORD_DEF_COL;
        }
        buttonInit(color, callback);
    }

    @SuppressWarnings("unused")
    public ColorButton(Color color) {
        new ColorButton(color, 12, 12);
    }

    public ColorButton(Color color, int width, int cbHeight) {
        new ColorButton(color, width, cbHeight, color1 -> this.selectColor = color);
    }

    @SuppressWarnings("unused")
    public ColorButton() {
        buttonInit(null, color -> this.selectColor = color);
    }

    private void buttonInit(Color color, Callback<Color> callback) {
        setMargin(JBUI.emptyInsets());
        setFocusable(false);
        setDefaultCapable(false);
        setFocusable(false);
        if (color != null) {
            selectColor = color;
        }
        addActionListener(e -> {
            Color color1 = ColorChooser.chooseColor(new JPanel(), "选择颜色", selectColor);
            if (color1 != null) {
                selectColor = color1;
                callback.call(selectColor);
            }
        });
    }


    @Override
    public void paint(Graphics g) {
        final Color color = g.getColor();
        if (selectColor == null) {
            g.setColor(KEY_WORD_DEF_COL);
        } else {
            g.setColor(selectColor);
        }
        g.fillRect(0, 0, this.cbWidth, this.cbHeight);
        g.setColor(color);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.cbWidth, this.cbHeight);
    }
}