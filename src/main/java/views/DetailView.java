package views;

import beans.DetailBean;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.value.ValueModel;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;
import pm.DetailPM;
import pm.PM;
import su.litvak.chromecast.api.v2.ChromeCast;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * Created by dylan on 18.01.18.
 */
public class DetailView implements View{

    private JPanel mainPanel;
    private DetailPM pm = new DetailPM();

    private JLabel addressLabel = new JLabel();
    private JLabel titleLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel appsURLLabel = new JLabel();
    private JLabel applicationLabel = new JLabel();
    private JLabel appTitleLabel = new JLabel();
    private JLabel modelLabel = new JLabel();

    public DetailView(){
        setupGui();
        setupListeners();
    }

    private void setupGui(){
        mainPanel = new JPanel(new MigLayout("wrap 2"));

        mainPanel.add(new JLabel("Title: "));
        mainPanel.add(titleLabel);

        mainPanel.add(new JLabel("Address: "));
        mainPanel.add(addressLabel);

        mainPanel.add(new JLabel("Name: "));
        mainPanel.add(nameLabel);

        mainPanel.add(new JLabel("Application: "));
        mainPanel.add(appTitleLabel);
    }

    public void setupListeners(){
        pm.getBean().addPropertyChangeListener(DetailBean.CHROMECAST, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                ChromeCast chromeCast = (ChromeCast) propertyChangeEvent.getNewValue();
                updateUI(chromeCast);
            }
        });

        pm.getBean().addPropertyChangeListener(DetailBean.CAST_STATUS, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                updateUI(pm.getBean().getChromeCast());
            }
        });
    }

    private void updateUI(ChromeCast chromeCast){
        try {
            titleLabel.setText(chromeCast.getTitle());
            addressLabel.setText(chromeCast.getAddress());
            nameLabel.setText(chromeCast.getName());
            appTitleLabel.setText(chromeCast.getStatus().getRunningApp().name);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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
