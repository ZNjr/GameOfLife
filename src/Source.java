import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class Source {
    private MainFrame frame;
    private String[] rulesName = {"Convay", "Koral", "Ameba", "Miasto", "Replikator", "Labirynt", "Wolfram", "Fractal"};
    private HashMap<String, HashMap<String, int[]>> rules = new HashMap<>();

    private int gliderGun[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    private int pulsar[][] = {
            {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0}
    };

    private int glider[][] = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1},
    };
    private int oneCell[][] = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
    };

    class MainFrame extends JFrame {
        Board board;

        MainFrame() {
            super("Game for Life");
            createRules();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.board = new Board(new Point2D.Double(151, 151), new Point2D.Double(970, 800));
            add(board);
            pack();
            setVisible(true);
            setResizable(false);
        }

        private void createRules() {
            rules.put(rulesName[0], generateHashMap(new int[]{2, 3}, new int[]{3}));
            rules.put(rulesName[1], generateHashMap(new int[]{4, 5, 6, 7, 8}, new int[]{3}));
            rules.put(rulesName[2], generateHashMap(new int[]{1, 3, 4, 5}, new int[]{3, 5, 7}));
            rules.put(rulesName[3], generateHashMap(new int[]{2, 3, 4, 5}, new int[]{4, 5, 6, 7, 8}));
            rules.put(rulesName[4], generateHashMap(new int[]{1, 3, 5, 7}, new int[]{1, 3, 5, 7}));
            rules.put(rulesName[5], generateHashMap(new int[]{1, 2, 3, 4, 5}, new int[]{3}));
            rules.put(rulesName[6], generateHashMap(new int[]{0, 4, 5}, new int[]{0, 5, 7, 8}));
            rules.put(rulesName[7], generateHashMap(new int[]{1, 2}, new int[]{1}));
        }
    }

    private HashMap<String, int[]> generateHashMap(int[] survival, int[] born) {
        HashMap<String, int[]> tmp = new HashMap<>();
        tmp.put("S", survival);
        tmp.put("B", born);
        return tmp;
    }

    class Board extends JPanel {

        private class AnimationThread extends Thread {
            @Override
            public void run() {
                try {
                    sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }

        private CellsArray cells;
        private Point2D boardSize;
        private Point2D cellSize;
        private double ellipseOffset;
        int speed = 100;

        Board(Point2D size, Point2D resolution) {
            super();
            setPreferredSize(new Dimension((int) resolution.getX(), (int) resolution.getY()));

            this.boardSize = new Point2D.Double(resolution.getX() - 200, resolution.getY() - 200);
            this.cells = new CellsArray((int) size.getY() + 2, (int) size.getX() + 2, rules.get(rulesName[0]));

            setCellSize(new Point.Double(size.getX() + 2, size.getY() + 2));

            setBackground(new Color(22, 22, 22));
            setLayout(null);
            createMenu();
            handleScrollEvent();
        }

        private void setCellSize(Point2D newSize) {
            this.cellSize = new Point2D.Double(this.boardSize.getX() / (newSize.getX() - 2)*0.9, this.boardSize.getX() / (newSize.getX() - 2)*0.9);
            ellipseOffset = cellSize.getX()/9;

        }

        private void handleScrollEvent() {
            addMouseWheelListener(e -> {
                Point mousePosition = getMousePosition();
                int rotation = e.getWheelRotation();
                int indexOfCellMouseOnX = (mousePosition.x + 11) / (int) cellSize.getX();
                int indexOfCellMouseOnY = (mousePosition.y + 11) / (int) cellSize.getX();
                cells.resize(rotation, new Point(indexOfCellMouseOnX, indexOfCellMouseOnY));
                setCellSize(cells.nOCells());
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Rectangle subArray = cells.getSize();
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(new Color(44, 44, 44));
            Rectangle2D rectangle = new Rectangle2D.Double(10, 10, boardSize.getX(), boardSize.getX());
            g2d.fill(rectangle);

            for (int x = subArray.x + 1; x < subArray.getWidth() - 1; x++)
                for (int y = subArray.y + 1; y < subArray.getHeight() - 1; y++) {
                    if (cells.getDate()[y][x].is == Cell.State.Life) {
                        g2d.setPaint(new Color(40, 152, 252));
                        if (cells.getDate()[y][x].willBe == Cell.State.Dead) g2d.setPaint(new Color(162, 56, 86));
                        Ellipse2D ellipse = new Ellipse2D.Double(10 + (ellipseOffset + cellSize.getX()) * (x - subArray.x-1), 10 + (ellipseOffset + cellSize.getY()) * (y - subArray.y-1), cellSize.getX(), cellSize.getY());
                        g2d.fill(ellipse);
                    }
                }
            System.out.println(subArray);
            cells.updateCellsState();
            AnimationThread t = new AnimationThread();
            t.start();
        }

        private void createMenu() {
            JTextField posX = new JTextField("1");
            posX.setBounds((int) boardSize.getX() + 60, 120, 40, 20);
            posX.setBackground(new Color(44, 44, 44));
            posX.setForeground(new Color(40, 152, 252));

            JTextField posY = new JTextField("1");
            posY.setBounds((int) boardSize.getX() + 120, 120, 40, 20);
            posY.setBackground(new Color(44, 44, 44));
            posY.setForeground(new Color(40, 152, 252));

            JButton clear = new JButton("Clear");
            clear.setBounds((int) boardSize.getX() + 40, 390, 140, 50);
            clear.addActionListener(e -> cells.clearBoard());

            JComboBox<String> rulesList = new JComboBox<>(rulesName);
            rulesList.setBounds((int) boardSize.getX() + 40, 450, 140, 25);
            rulesList.setBackground(new Color(44, 44, 44));
            rulesList.setForeground(new Color(40, 152, 252));
            rulesList.addActionListener(e -> {
                JComboBox cb = (JComboBox) e.getSource();
                cells.setActualRules(rules.get(rulesName[cb.getSelectedIndex()]));
            });

            JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 100);
            slider.setBounds((int) boardSize.getX() + 40, 490, 140, 25);
            slider.setBackground(new Color(44, 44, 44));
            slider.addChangeListener(e -> {
                JSlider source = (JSlider) e.getSource();
                speed = source.getValue();
            });

            add(posY);
            add(posX);
            add(new MenuButton("Glider", new Rectangle.Double(boardSize.getX() + 40, 150, 140, 50), e -> {
                cells.addNewCells(glider, new Point(Integer.parseInt(posX.getText()), Integer.parseInt(posY.getText())));
                cells.setNextState();
            }));

            add(new MenuButton("Pulsar", new Rectangle.Double(boardSize.getX() + 40, 210, 140, 50), e -> {
                cells.addNewCells(pulsar, new Point(Integer.parseInt(posX.getText()), Integer.parseInt(posY.getText())));
                cells.setNextState();
            }));

            add(new MenuButton("Glider Gun", new Rectangle.Double(boardSize.getX() + 40, 270, 140, 50), e -> {
                cells.addNewCells(gliderGun, new Point(Integer.parseInt(posX.getText()), Integer.parseInt(posY.getText())));
                cells.setNextState();
            }));

            add(new MenuButton("One Cell", new Rectangle.Double(boardSize.getX() + 40, 330, 140, 50), e -> {
                cells.addNewCells(oneCell, new Point(Integer.parseInt(posX.getText()), Integer.parseInt(posY.getText())));
                cells.setNextState();
            }));

            add(clear);
            add(rulesList);
            add(slider);
        }
    }

    private void createFrame() {
        frame = new MainFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Source().createFrame());
    }
}
