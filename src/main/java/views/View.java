package views;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pm.PM;

import javax.swing.*;
/**
 * Created by dylan on 14.01.18.
 */
public interface View {

    @NotNull
    public JComponent getGui();

    @Nullable
    public PM getPM();
}
