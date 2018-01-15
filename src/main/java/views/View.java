package views;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
/**
 * Created by dylan on 14.01.18.
 */
public interface View {

    @NotNull
    public JComponent getGui();
}
