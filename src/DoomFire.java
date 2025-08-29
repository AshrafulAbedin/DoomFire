import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

class ColorPalette {
    private static final Color[] intensityColors = {
            new Color(7, 7, 7),
            new Color(31, 7, 7),
            new Color(47, 15, 7),
            new Color(71, 15, 7),
            new Color(87, 23, 7),
            new Color(103, 31, 7),
            new Color(119, 31, 7),
            new Color(143, 39, 7),
            new Color(159, 47, 7),
            new Color(175, 63, 7),
            new Color(191, 71, 7),
            new Color(199, 71, 7),
            new Color(223, 79, 7),
            new Color(223, 87, 7),
            new Color(223, 87, 7),
            new Color(215, 95, 7),
            new Color(215, 95, 7),
            new Color(215, 103, 15),
            new Color(207, 111, 15),
            new Color(207, 119, 15),
            new Color(207, 127, 15),
            new Color(207, 135, 23),
            new Color(199, 135, 23),
            new Color(199, 143, 23),
            new Color(199, 151, 31),
            new Color(191, 159, 31),
            new Color(191, 159, 31),
            new Color(191, 167, 39),
            new Color(191, 167, 39),
            new Color(191, 175, 47),
            new Color(183, 175, 47),
            new Color(183, 183, 47),
            new Color(183, 183, 55),
            new Color(207, 207, 111),
            new Color(223, 223, 159),
            new Color(239, 239, 199)
    };

    public static Color getColorByIntensity(int intensity) {
        if (intensity >= 0 && intensity < intensityColors.length) {
            return intensityColors[intensity];
        }
        return Color.BLACK;
    }
}

public class DoomFire extends JPanel implements Runnable {
    final int SIZE = 512;
    boolean isRunning;
    Thread thread;
    BufferedImage view;
    Graphics2D g2;

    int pixelSize = 8;
    int[][] firePixels;
    int fireWidth = 64, fireHeight = 64;
    Random random = new Random();

    public DoomFire() {
        setPreferredSize(new Dimension(SIZE, SIZE));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Doom Fire");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(new DoomFire());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            isRunning = true;
            thread.start();
        }
    }

    public void start() {
        view = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        g2 = view.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

        firePixels = new int[fireWidth][fireHeight];

        for (int x = 0; x < fireWidth; x++) {
            for (int y = 0; y < fireHeight; y++) {
                if (y == fireHeight - 1) {
                    firePixels[x][y] = 35;
                } else {
                    firePixels[x][y] = 0;
                }
            }
        }
    }

    public void update() {

        for (int y = fireHeight - 2; y >= 0; y--) {
            for (int x = 0; x < fireWidth; x++) {

                int belowIntensity = firePixels[x][y + 1];

                int decay = random.nextInt(3);
                int newIntensity = Math.max(belowIntensity - decay, 0);


                int drift = random.nextInt(3) - 1;
                int newX = Math.max(0, Math.min(fireWidth - 1, x + drift));

                firePixels[newX][y] = newIntensity;
            }
        }
    }

    public void draw() {

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, SIZE, SIZE);

        for (int x = 0; x < fireWidth; x++) {
            for (int y = 0; y < fireHeight; y++) {
                g2.setColor(ColorPalette.getColorByIntensity(firePixels[x][y]));
                g2.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
            }
        }

        Graphics g = getGraphics();
        if (g != null) {
            g.drawImage(view, 0, 0, null);
            g.dispose();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (view != null) {
            g.drawImage(view, 0, 0, null);
        }
    }

    @Override
    public void run() {
        try {
            start();
            while (isRunning) {
                update();
                draw();
                repaint();
                Thread.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (g2 != null) {
                g2.dispose();
            }
        }
    }
}
