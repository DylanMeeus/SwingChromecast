package views;

import beans.DetailBean;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;
import pm.DetailPM;
import su.litvak.chromecast.api.v2.ChromeCast;
import su.litvak.chromecast.api.v2.MediaStatus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by dylan on 18.01.18.
 */
public class DetailView implements View {

    private JButton playPauseButton;
    private JButton stopAppButton;

    private JPanel mainPanel;
    private JPanel controlPanel;

    private DetailPM pm = new DetailPM();

    private JLabel addressLabel = new JLabel();
    private JLabel titleLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel appsURLLabel = new JLabel();
    private JLabel applicationLabel = new JLabel();
    private JLabel appTitleLabel = new JLabel();
    private JLabel modelLabel = new JLabel();


    private static final String PLAY = "Play";
    private static final String PAUSE = "Pause";
    private static final String QUIT = "Quit";

    public DetailView() {
        setupGui();
        setupListeners();
    }

    private void setupGui() {
        mainPanel = new JPanel(new MigLayout("wrap 2"));

        mainPanel.add(new JLabel("Title: "));
        mainPanel.add(titleLabel);

        mainPanel.add(new JLabel("Address: "));
        mainPanel.add(addressLabel);

        mainPanel.add(new JLabel("Name: "));
        mainPanel.add(nameLabel);

        mainPanel.add(new JLabel("Application: "));
        mainPanel.add(appTitleLabel);

        controlPanel = createControlPanel();
        mainPanel.add(controlPanel, "wrap");
    }

    private JPanel createControlPanel() {
        JPanel controls = new JPanel();
        controls.setLayout(new MigLayout(""));
        playPauseButton = new JButton();
        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChromeCast currentChromeCast = getPM().getCurrentChromeCast();
                if (currentChromeCast == null) {
                    return;
                }
                try {
                    MediaStatus mediaStatus = currentChromeCast.getMediaStatus();
                    MediaStatus.PlayerState playerState = mediaStatus.playerState;
                    System.out.println(playerState);
                    currentChromeCast.pause();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        stopAppButton = new JButton();
        stopAppButton.setToolTipText("Terminates the currently running application");
        stopAppButton.setAction(new TerminateAppAction());
        controls.add(playPauseButton);
        controls.add(stopAppButton);
        return controls;
    }

    public void setupListeners() {
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

    private void updateUI(ChromeCast chromeCast) {
        try {
            Objects.requireNonNull(chromeCast);
            titleLabel.setText(chromeCast.getTitle());
            addressLabel.setText(chromeCast.getAddress());
            nameLabel.setText(chromeCast.getName());
            appTitleLabel.setText(chromeCast.getStatus().getRunningApp().name);

            // Set state from the play / stop button
            boolean idling = chromeCast.getRunningApp().isIdleScreen;
            if (!idling) {
                playPauseButton.setEnabled(true);
                MediaStatus.PlayerState playerState = chromeCast.getMediaStatus().playerState;
                if (playerState == MediaStatus.PlayerState.PAUSED) {
                    playPauseButton.setText(PLAY);
                } else if (playerState == MediaStatus.PlayerState.PLAYING) {
                    playPauseButton.setText(PAUSE);
                }
            } else {
                // todo: different way to indicate that nothing is running?
                playPauseButton.setEnabled(false);
            }
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
    public DetailPM getPM() {
        return pm;
    }

    /**
     * Terminates the currently running application
     */
    private class TerminateAppAction extends AbstractAction {

        public TerminateAppAction(){
            putValue(NAME, QUIT);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ChromeCast currentChromeCast = getPM().getCurrentChromeCast();
            if (currentChromeCast == null) {
                return;
            }
            try {
                currentChromeCast.stopApp();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
