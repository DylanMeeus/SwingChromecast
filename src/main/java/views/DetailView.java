package views;

import beans.DetailBean;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.value.ValueModel;
import org.jetbrains.annotations.NotNull;
import pm.DetailPM;
import pm.PM;
import su.litvak.chromecast.api.v2.ChromeCast;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by dylan on 18.01.18.
 */
public class DetailView implements View{

    private JPanel mainPanel;
    private DetailPM pm = new DetailPM();
    private JLabel titleLabel = new JLabel();

    public DetailView(){
        setupGui();
        setupListeners();
    }

    private void setupGui(){
        mainPanel = new JPanel();
        mainPanel.add(titleLabel);
    }

    public void setupListeners(){
        pm.getBean().addPropertyChangeListener(DetailBean.CHROMECAST, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                titleLabel.setText(((ChromeCast)propertyChangeEvent.getNewValue()).getTitle());
            }
        });
    }

    @NotNull
    @Override
    public JComponent getGui() {
        return mainPanel;
    }

    @Override
    public DetailPM getPM(){
        return pm;
    }
}
