import javax.swing.*;
import java.awt.*;

/**
 * Thread option pannel gui class
 */
public class ThreadOptionPannel extends JPanel
{
    private volatile boolean start;

    private JButton monitorBox;
    private volatile boolean monitor;
    private JButton singleThreadBox;
    private volatile boolean single;
    private JButton noMonitorBox;
    private volatile boolean nomonitor;
    private JButton cancelButon;
    private volatile boolean cancel;


    private JLabel infoCancel;
    private JLabel textCombobox;
    private JLabel elapsedTimetxt;
    private JComboBox size_cBox;

    private Thread UI_LISTENER_THREAD;
    private Thread ENGINE_THREAD;
    private volatile boolean engine_cancel;
    private volatile boolean engine_interrupt;

    private ConcurencyDFT DFT_Calculator;

    private final Object UI_Changed_Monitor = new Object();
    private final Object UI_ENGINE_monitor = new Object();

    /**
     * consturctor
     */
    public ThreadOptionPannel() {
        this.setLayout(null);
        this.requestFocus();
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.setBackground(Color.DARK_GRAY);

        this.start = false;
        this.cancel = false;
        this.single = false;
        this.monitor = false;
        this.nomonitor = false;
        this.engine_cancel = false;

        monitorBox = new JButton("4 Thread, Monitor");
        monitorBox.setBounds(0,50, 200,30);
        monitorBox.setBackground(Color.DARK_GRAY);
        monitorBox.setForeground(Color.WHITE);
        monitorBox.setFocusable(false);
        monitorBox.setVisible(true);
        monitorBox.addActionListener(t -> {
            this.start = true;
            this.monitor = true;
            this.single = false;
            this.nomonitor = false;

            synchronized (UI_Changed_Monitor) {
                UI_Changed_Monitor.notify();
            }
        });
        this.add(monitorBox);

        singleThreadBox = new JButton("Single Thread Option");
        singleThreadBox.setBounds(0,10, 200,30);
        singleThreadBox.setBackground(Color.DARK_GRAY);
        singleThreadBox.setForeground(Color.WHITE);
        singleThreadBox.setFocusable(false);
        singleThreadBox.setVisible(true);
        singleThreadBox.addActionListener(t -> {
            this.start = true;
            this.single = true;
            this.monitor = false;
            this.nomonitor = false;

            synchronized (UI_Changed_Monitor) {
                UI_Changed_Monitor.notify();
            }
        });
        this.add(singleThreadBox);

        noMonitorBox = new JButton("4 Thread, No Monitor");
        noMonitorBox.setBounds(0,90, 200,30);
        noMonitorBox.setBackground(Color.DARK_GRAY);
        noMonitorBox.setForeground(Color.WHITE);
        noMonitorBox.setFocusable(false);
        noMonitorBox.setVisible(true);
        noMonitorBox.addActionListener(t -> {
            this.start = true;
            this.nomonitor = true;
            this.monitor = false;
            this.single = false;

            synchronized (UI_Changed_Monitor) {
                UI_Changed_Monitor.notify();
            }
        });
        this.add(noMonitorBox);

        textCombobox = new JLabel("Matrix Size (n x n)");
        textCombobox.setBounds(0, 130, 200, 30);
        textCombobox.setFont(new Font("Arial", Font.BOLD, 15));
        textCombobox.setForeground(Color.WHITE);
        textCombobox.setFocusable(false);
        textCombobox.setVisible(true);
        this.add(textCombobox);

        elapsedTimetxt = new JLabel("Elapsed Time: ");
        elapsedTimetxt.setBounds(220, 10, 250, 30);
        elapsedTimetxt.setFont(new Font("Arial", Font.BOLD, 13));
        elapsedTimetxt.setForeground(Color.WHITE);
        elapsedTimetxt.setFocusable(false);
        elapsedTimetxt.setVisible(true);
        this.add(elapsedTimetxt);

        Integer choice[] = { 512, 1024, 2048, 4096, 8912 };
        size_cBox = new JComboBox(choice);
        size_cBox.setBounds(0, 160, 200, 30);
        size_cBox.setFocusable(false);
        size_cBox.setBackground(Color.DARK_GRAY);
        size_cBox.setForeground(Color.WHITE);
        size_cBox.setVisible(true);
        this.add(size_cBox);

        cancelButon = new JButton("Cancel");
        cancelButon.setBounds(0, 90, 200, 30);
        cancelButon.setFocusable(false);
        cancelButon.setBackground(Color.DARK_GRAY);
        cancelButon.setForeground(Color.WHITE);
        cancelButon.setVisible(false);
        cancelButon.addActionListener(t -> {
            this.cancel = true;
            this.engine_interrupt = true;

            synchronized (UI_Changed_Monitor) {
                UI_Changed_Monitor.notify();
            }
        });
        this.add(cancelButon);

        infoCancel = new JLabel("<html>Cancel request send the threads.<br/>Please wait for them to cancel.</html>");
        infoCancel.setBounds(0, 90, 300, 30);
        infoCancel.setFocusable(false);
        infoCancel.setBackground(Color.DARK_GRAY);
        infoCancel.setForeground(Color.WHITE);
        infoCancel.setVisible(false);
        this.add(infoCancel);

        UI_LISTENER_THREAD = new Thread(new Runnable() {
            @Override
            public void run() { UI_listener(); }
        });
        UI_LISTENER_THREAD.start();

        ENGINE_THREAD = new Thread(new Runnable() {
            @Override
            public void run() { DFT_ENGINE(); }
        });
    }

    /**
     * disable all buttons and show cancel
     */
    private void disableAllButton() {
        singleThreadBox.setVisible(false);
        monitorBox.setVisible(false);
        noMonitorBox.setVisible(false);
        cancelButon.setVisible(true);
    }

    /**
     * disable cancel button and show all buttons
     */
    private void disableCancelButton() {
        singleThreadBox.setVisible(true);
        monitorBox.setVisible(true);
        noMonitorBox.setVisible(true);
        cancelButon.setVisible(false);
    }

    /**
     * init dft engine thread
     */
    private void init_engineThread() {
        ENGINE_THREAD = new Thread(new Runnable() {
            @Override
            public void run() { DFT_ENGINE(); }
        });
    }

    /**
     * join all thread
     */
    private void joinAllThread() {
        try {
            cancelButon.setVisible(false);
            infoCancel.setVisible(true);

            synchronized (UI_ENGINE_monitor) {
                engine_interrupt = true;
                UI_ENGINE_monitor.notify();
            }
            ENGINE_THREAD.join();

            infoCancel.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * UI listener thread
     */
    private void UI_listener() {

        while (true) {

            synchronized (UI_Changed_Monitor) {
                try {
                    UI_Changed_Monitor.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (start) {
                init_engineThread();
                disableAllButton();
                cancel = false;
                start = false;
                ENGINE_THREAD.start();
            }

            if (cancel) {
                start = false;
                cancel = false;
                joinAllThread();
                disableCancelButton();
            }

            if (engine_cancel) {
                engine_cancel = false;
                disableCancelButton();
            }
        }
    }

    /**
     * DFT Calculator thread with My ConcurencyDFT Class
     */
    private void DFT_ENGINE() {

        int size = (Integer) size_cBox.getSelectedItem();

        long start_ = System.currentTimeMillis();
        try {

            if (monitor || nomonitor) {
                DFT_Calculator = new ConcurencyDFT(size, UI_ENGINE_monitor, false);
            } else if (single) {
                DFT_Calculator = new ConcurencyDFT(size, UI_ENGINE_monitor, true);
            }

            DFT_Calculator.startThreads();

            while (true) {

                synchronized (UI_ENGINE_monitor) {
                    if (!engine_interrupt)
                        UI_ENGINE_monitor.wait();
                }

                if (engine_interrupt) {
                    DFT_Calculator.stopThreads();
                    engine_interrupt = false;
                    engine_cancel = true;
                    break;
                }

                if (DFT_Calculator.isFinished()) {
                    engine_interrupt = false;
                    engine_cancel = true;

                    synchronized (UI_Changed_Monitor) {
                        UI_Changed_Monitor.notify();
                    }
                    break;
                }
            }

        }
        catch (OutOfMemoryError e) {
          JOptionPane.showMessageDialog(null, "Out Of  Memorry Error!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        DFT_Calculator = null;
        long end_ = System.currentTimeMillis();
        float sec = (end_ - start_) / 1000F;
        elapsedTimetxt.setText("Elapsed Time: " + String.format("%.03f", sec) + " sec");
    }
}
