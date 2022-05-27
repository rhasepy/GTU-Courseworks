package Game;

import CharacterPackage.*;
import TilePackage.TileType;
import TilePackage.TileSpec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Game Engine class
 */
public class GameEngine extends JPanel
{
    /**
     * Private static inner Tile class
     * This class extend JButton
     * Cause of static, to access in game engine static variable
     */
    private static class Tile extends JButton
    {
        /**
         * Tile type (red, green or blue)
         */
        private TileSpec tile_type;

        /**
         * coord_i button
         */
        private int coord_i;

        /**
         * coord_j button
         */
        private int coord_j;

        /**
         * Constructor
         * @param tile_type, type of button
         * @param i, coord i
         * @param j, coord j
         */
        public Tile(TileType tile_type, int i, int j) {
            this.tile_type = new TileSpec(tile_type);
            this.coord_i = i;
            this.coord_j = j;
            this.addActionListener(lambda -> { buttonAction(); });
        }

        /**
         * Constructor, random type choice
         * @param i, coord i
         * @param j, coord j
         */
        public Tile(int i, int j) {

            int typeRand = (new Random()).nextInt();

            if (typeRand % 3 == 0) {
                this.tile_type = new TileSpec(TileType.RED_FIRE);
                this.setBackground(this.tile_type.getColor());
            } else if (typeRand % 3 == 1) {
                this.tile_type = new TileSpec(TileType.BLUE_ICE);
                this.setBackground(this.tile_type.getColor());
            } else {
                this.tile_type = new TileSpec(TileType.GREEN_NATURE);
                this.setBackground(this.tile_type.getColor());
            }

            this.coord_i = i;
            this.coord_j = j;

            this.setFocusable(false);
            this.setVisible(true);
            this.addActionListener(lambda -> { buttonAction(); });
        }

        /**
         * setter i
         * @param i, coord i
         */
        public void setCoord_i(int i) {
            if (i >= 0)
                this.coord_i = i;
        }

        /**
         * setter j
         * @param j coord j
         */
        public void setCoord_j(int j) {
            if (j >= 0)
                this.coord_j = j;
        }

        /**
         * change spec of button (type and background color)
         * @param spec, spec of tile
         */
        public void changeSpec(TileSpec spec) {
            this.tile_type = spec;
            this.setBackground(this.tile_type.getColor());
            this.setText("");
        }

        /**
         * getter spec
         * @return spec of button
         */
        public TileSpec getSpec() { return this.tile_type; }

        /**
         * getter
         * @return get coord i
         */
        public int getCoord_i() { return this.coord_i; }

        /**
         * getter
         * @return get coord j
         */
        public int getCoord_j() { return this.coord_j; }

        /**
         * button action listener thread
         */
        private void buttonAction() {
            if (GameEngine.startState && (!GameEngine.pauseState))
            {
                if ((!click_1)) {
                    GameEngine.click_1 = true;
                    this.setText("X");
                    clicked1.setLocation(getCoord_i(), getCoord_j());
                } else if ((!click_2)) {
                    GameEngine.click_2 = true;
                    this.setText("X");
                    clicked2.setLocation(getCoord_i(), getCoord_j());
                }
            }
        }

        /**
         * change itself specs
         */
        public void changeMySelfRandom() {
            Random rand = new Random();
            int rand_ = rand.nextInt();
            if (rand_ % 3 == 0)
                this.changeSpec(new TileSpec(TileType.RED_FIRE));
            else if (rand_ % 3 == 1)
                this.changeSpec(new TileSpec(TileType.BLUE_ICE));
            else
                this.changeSpec(new TileSpec(TileType.GREEN_NATURE));
        }
    }

    /**
     * tile board as a main board
     */
    private Tile[][] tileBoard;

    /**
     * log message area
     */
    private JTextArea logArea;

    /**
     * log area wrapper panel
     */
    private JScrollPane logArea_pane;

    /**
     * DELTA TIME FOR SMOOTH cause of thread very fast
     */
    private static final int DELTA_TIME_FOR_SMOOTH = 7;

    /**
     * BOARD COLUMN SIZES
     */
    private static final int TILES_COLUMN = 9;

    /**
     * BOARD ROW SIZES
     */
    private static final int TILES_ROW = 6;

    /**
     * click 1 or not
     */
    private static boolean click_1;

    /**
     * click 2 or not
     */
    private static boolean click_2;

    /**
     * clicked button1 i,j
     */
    private static Point clicked1;

    /**
     * cliclked button2 i,j
     */
    private static Point clicked2;

    /**
     * User character list
     */
    private ArrayList<GameCharacter> characterList;

    /**
     * AI character list
     */
    private ArrayList<GameCharacter> enemyList;

    /**
     * UI component list of user characters
     */
    private ArrayList<JButton> UI_CLIST;

    /**
     * UI component list of AI characters
     */
    private ArrayList<JButton> UI_ELIST;

    /**
     * start button
     */
    private JButton start_button;

    /**
     * pause button
     */
    private JButton pause_button;

    /**
     * exit button
     */
    private JButton exit_button;

    /**
     * pause state
     */
    private static boolean pauseState;

    /**
     * start state
     */
    private static boolean startState;

    /**
     * AI attack choice (char 1, char 2 or char 3 etc.)
     */
    private int AI_MOVE_CHOICE;

    /**
     * if the AI mode active the action attack enemy to character
     */
    private boolean AI_ACTIVE;

    /**
     * Constructor
     * Game engine
     */
    public GameEngine() {
        INIT_COMPONENT();
        INIT_CHARACTERS();
        INIT_AI_ENGINE();
        INIT_UI();
        INIT_THREAD();
    }

    /**
     * AI engine initiliaze
     */
    private void INIT_AI_ENGINE() {
        this.AI_MOVE_CHOICE = 0;
        this.AI_ACTIVE = false;
    }

    /**
     * characters initiliaze (with factor desgin pattern)
     */
    private void INIT_CHARACTERS() {
        RedFire fire = new RedFire();
        BlueIce ice = new BlueIce();
        GreenNature nature = new GreenNature();

        characterList = new ArrayList<>();
        enemyList = new ArrayList<>();

        for (int i = 0; i < 3; ++i) {

            int rand = ((int) (Math.random() * 10)) % 3;

            if (rand == 0) {

                int rand_ = ((int) (Math.random() * 10)) % 3;
                if (rand_ == 0)
                    characterList.add(fire.buildGameCharacter(CharacterStyle.ATLANTIS));
                else if (rand_ == 1)
                    characterList.add(fire.buildGameCharacter(CharacterStyle.VALHALLA));
                else
                    characterList.add(fire.buildGameCharacter(CharacterStyle.UNDERWILD));

            } else if (rand == 1) {

                int rand_ = ((int) (Math.random() * 10)) % 3;
                if (rand_ == 0)
                    characterList.add(ice.buildGameCharacter(CharacterStyle.ATLANTIS));
                else if (rand_ == 1)
                    characterList.add(ice.buildGameCharacter(CharacterStyle.VALHALLA));
                else
                    characterList.add(ice.buildGameCharacter(CharacterStyle.UNDERWILD));

            } else {

                int rand_ = ((int) (Math.random() * 10)) % 3;
                if (rand_ == 0)
                    characterList.add(nature.buildGameCharacter(CharacterStyle.ATLANTIS));
                else if (rand_ == 1)
                    characterList.add(nature.buildGameCharacter(CharacterStyle.VALHALLA));
                else
                    characterList.add(nature.buildGameCharacter(CharacterStyle.UNDERWILD));
            }
        }

        for (int i = 0; i < 3; ++i) {

            int rand = ((int) (Math.random() * 10)) % 3;

            if (rand == 0) {

                int rand_ = ((int) (Math.random() * 10)) % 3;
                if (rand_ == 0)
                    enemyList.add(fire.buildGameCharacter(CharacterStyle.ATLANTIS));
                else if (rand_ == 1)
                    enemyList.add(fire.buildGameCharacter(CharacterStyle.VALHALLA));
                else
                    enemyList.add(fire.buildGameCharacter(CharacterStyle.UNDERWILD));

            } else if (rand == 1) {

                int rand_ = ((int) (Math.random() * 10)) % 3;
                if (rand_ == 0)
                    enemyList.add(ice.buildGameCharacter(CharacterStyle.ATLANTIS));
                else if (rand_ == 1)
                    enemyList.add(ice.buildGameCharacter(CharacterStyle.VALHALLA));
                else
                    enemyList.add(ice.buildGameCharacter(CharacterStyle.UNDERWILD));

            } else {

                int rand_ = ((int) (Math.random() * 10)) % 3;
                if (rand_ == 0)
                    enemyList.add(nature.buildGameCharacter(CharacterStyle.ATLANTIS));
                else if (rand_ == 1)
                    enemyList.add(nature.buildGameCharacter(CharacterStyle.VALHALLA));
                else
                    enemyList.add(nature.buildGameCharacter(CharacterStyle.UNDERWILD));
            }
        }
    }

    /**
     * main game thread initiliaze
     */
    private void INIT_THREAD() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                gameLoop();
            }
        }).start();
    }

    /**
     * component and variable field initiliaze
     */
    private void INIT_COMPONENT() {
        this.tileBoard = new Tile[6][9];
        this.pauseState = false;
        this.startState = false;
        GameEngine.click_1 = false;
        GameEngine.click_2 = false;
        GameEngine.clicked1 = new Point(0, 0);
        GameEngine.clicked2 = new Point(0, 0);
    }

    /**
     * UI initiliaze
     */
    private void INIT_UI() {
        this.setLayout(null);
        this.requestFocus();
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.setBackground(Color.DARK_GRAY);
        this.initGameBoard();

        logArea = new JTextArea();
        logArea.setBounds(10, 585, 492, 140);
        logArea.setFocusable(false);
        logArea.setBackground(Color.DARK_GRAY);
        logArea.setForeground(Color.WHITE);
        logArea.setFont(new Font("Arial", Font.BOLD, 12));
        logArea.setVisible(true);
        String helloMSG = "Welcome to Campuses and Puzzles Game!" +
                "\nOnly kings can leave this campus!";
        logArea.setText(helloMSG);
        this.add(logArea);

        logArea_pane = new JScrollPane(logArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        logArea_pane.setBounds(10, 585, 492, 140);
        logArea_pane.setBackground(Color.DARK_GRAY);
        logArea_pane.setFocusable(false);
        logArea_pane.setVisible(true);
        this.add(logArea_pane);

        start_button = new JButton("Start");
        start_button.setBounds(0,0,65,20);
        start_button.setFont(new Font("Arial", Font.BOLD, 9));
        start_button.setFocusable(false);
        start_button.setVisible(true);
        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!startState) {
                    logArea.setText(logArea.getText() + "\nGame Started.\n");
                }
                startState = true;
            }});
        this.add(start_button);

        pause_button = new JButton("Pause");
        pause_button.setBounds(60,0,65,20);
        pause_button.setFont(new Font("Arial", Font.BOLD, 9));
        pause_button.setFocusable(false);
        pause_button.setVisible(true);
        pause_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startState)
                {
                    if (pauseState) {
                        pause_button.setText("Pause");
                        logArea.setText(logArea.getText() + "\nGo.");
                        pauseState = false;
                    }
                    else {
                        pauseState = true;
                        logArea.setText(logArea.getText() + "\nGame Paused.");
                        pause_button.setText("Go");
                    }
                }
            }
        });
        this.add(pause_button);

        exit_button = new JButton("Exit");
        exit_button.setBounds(120,0,65,20);
        exit_button.setFont(new Font("Arial", Font.BOLD, 9));
        exit_button.setFocusable(false);
        exit_button.setVisible(true);
        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { System.exit(1); } });
        this.add(exit_button);

        UI_CLIST = new ArrayList<>();
        UI_ELIST = new ArrayList<>();

        JTextArea area = new JTextArea();
        area.setBounds(225, 5, 60, 20);
        area.setBackground(Color.BLACK);
        area.setForeground(Color.WHITE);
        area.setFont(new Font("Arial", Font.BOLD, 12));
        area.setFocusable(false);
        area.setVisible(true);
        area.setText("Enemies");
        this.add(area);

        JTextArea area_ = new JTextArea();
        area_.setBounds(225, 135, 80, 20);
        area_.setBackground(Color.BLACK);
        area_.setForeground(Color.WHITE);
        area_.setFont(new Font("Arial", Font.BOLD, 12));
        area_.setFocusable(false);
        area_.setVisible(true);
        area_.setText("Characters");
        this.add(area_);

        int x = 31;
        int y = 30;
        for (int i = 0; i < enemyList.size(); ++i) {
            JButton label = new JButton();
            label.setBackground(enemyList.get(i).getColor());
            String text = "<html>" + enemyList.get(i).getStyle().toString() + "<br />" +
                    "Agility: " + enemyList.get(i).getAgility() + "<br />" +
                    "Strength: " + enemyList.get(i).getStrength() + "<br />" +
                    "Health: " + enemyList.get(i).getHealth() + "<br />" +
                    "Dmg: " + Math.floor(enemyList.get(i).getDamage()) +"</html>";
            label.setText(text);
            label.setBounds(x, y, 150, 100);
            label.setFocusable(false);
            label.setVisible(true);
            x += 150;
            this.add(label);
            UI_ELIST.add(label);
        }
        x = 31;
        y = 160;
        for (int i = 0; i < characterList.size(); ++i) {
            JButton label = new JButton();
            label.setBackground(characterList.get(i).getColor());
            String text = "<html>" + characterList.get(i).getStyle().toString() + "<br />" +
                    "Agility: " + characterList.get(i).getAgility() + "<br />" +
                    "Strength: " + characterList.get(i).getStrength() + "<br />" +
                    "Health: " + characterList.get(i).getHealth() + "<br />" +
                    "Dmg: " + Math.floor(characterList.get(i).getDamage()) +"</html>";
            label.setText(text);
            label.setBounds(x, y, 150, 100);
            label.setFocusable(false);
            label.setVisible(true);
            x += 150;
            this.add(label);
            UI_CLIST.add(label);
        }
    }

    /**
     * update characters health, dmg value etc.
     */
    private void printCharacters() {
        for (int i = 0; i < enemyList.size(); ++i) {
            String text = "<html>" + enemyList.get(i).getStyle().toString() + "<br />" +
                    "Agility: " + enemyList.get(i).getAgility() + "<br />" +
                    "Strength: " + enemyList.get(i).getStrength() + "<br />" +
                    "Health: " + enemyList.get(i).getHealth() + "<br />" +
                    "Dmg: " + Math.floor(enemyList.get(i).getDamage()) +"</html>";
            UI_ELIST.get(i).setText(text);

            text = "<html>" + characterList.get(i).getStyle().toString() + "<br />" +
                    "Agility: " + characterList.get(i).getAgility() + "<br />" +
                    "Strength: " + characterList.get(i).getStrength() + "<br />" +
                    "Health: " + characterList.get(i).getHealth() + "<br />" +
                    "Dmg: " + Math.floor(characterList.get(i).getDamage()) +"</html>";
            UI_CLIST.get(i).setText(text);
        }
    }

    /**
     * init feasible game board
     */
    private void initGameBoard() {

        int id_x = (512 - (TILES_COLUMN * 50)) / 2;
        int id_y = 275;

        for (int i = 0; i < TILES_ROW; ++i) {
            for (int j = 0; j < TILES_COLUMN; ++j) {

                Color temp;
                int ctr, ctr_;
                do {
                    this.tileBoard[i][j] = new Tile(i, j);

                    ctr = 0;
                    ctr_ = 0;
                    temp = tileBoard[i][j].getSpec().getColor();

                    for (int jdx = j; jdx >= 0; --jdx) {
                        if (tileBoard[i][jdx].getSpec().getColor() == temp)
                            ++ctr;
                        else
                            break;
                    }
                    for (int idx = i; idx >= 0; --idx) {
                        if (tileBoard[idx][j].getSpec().getColor() == temp)
                            ++ctr_;
                        else
                            break;
                    }
                } while ((ctr >= 3) || (ctr_ >= 3));

                this.tileBoard[i][j].setBounds(id_x, id_y, 50, 50);
                id_x += 50;
                this.add(tileBoard[i][j]);
            }
            id_x = (512 - (TILES_COLUMN * 50)) / 2;
            id_y += 50;
        }
    }

    /**
     * tile up cost
     * @param i_ i
     * @param j_ j
     * @param color color tile
     * @return cost of tiles
     */
    private int tile_upCost(int i_, int j_, Color color) {
        int ctr_up = 0;
        for (int i = i_; i >= 0; --i) {
            if (tileBoard[i][j_].getSpec().getColor() == color) ++ctr_up;
            else break;
        }
        return ctr_up;
    }

    /**
     * tile down cost
     * @param i_, i
     * @param j_, j
     * @param color, color
     * @return cost of tiles
     */
    private int tile_downCost(int i_, int j_, Color color) {
        int ctr_down = 0;
        for (int i = i_; i < TILES_ROW; ++i) {
            if (tileBoard[i][j_].getSpec().getColor() == color) ++ctr_down;
            else break;
        }
        return ctr_down;
    }

    /**
     * tile right cost
     * @param i_, i
     * @param j_, j
     * @param color, color
     * @return cost of tiles
     */
    private int tile_rightCost(int i_, int j_, Color color) {
        int ctr_right = 0;
        for (int j = j_; j < TILES_COLUMN; ++j) {
            if (tileBoard[i_][j].getSpec().getColor() == color) ++ctr_right;
            else break;
        }
        return ctr_right;
    }

    /**
     * tile left cost
     * @param i_, i
     * @param j_, j
     * @param color, color
     * @return cost of tiles
     */
    private int tile_leftCost(int i_, int j_, Color color) {
        int ctr_left = 0;
        for (int j = j_; j >= 0; --j) {
            if (tileBoard[i_][j].getSpec().getColor() == color) ++ctr_left;
            else break;
        }
        return ctr_left;
    }

    /**
     * which tiles attack which enemy
     * @param column, column of tile
     * @return which enemy index
     */
    private int enemyIndex(int column) {
        if (column >= 0 && column <= 2)
            return 0;
        else if (column >= 3 && column <= 5)
            return 1;
        else if (column >= 6 && column <= 8)
            return 2;
        else
            return 0;
    }

    /**
     * refresh board after any moving
     */
    private void refreshBoard() {
        for (int i = 0; i < TILES_ROW; ++i) {
            for (int j = 0; j < TILES_COLUMN; ++j) {

                Color temp;
                int ctr, ctr_;
                do {
                    this.tileBoard[i][j].changeMySelfRandom();

                    ctr = 0;
                    ctr_ = 0;
                    temp = tileBoard[i][j].getSpec().getColor();

                    for (int jdx = j; jdx >= 0; --jdx) {
                        if (tileBoard[i][jdx].getSpec().getColor() == temp)
                            ++ctr;
                        else
                            break;
                    }
                    for (int idx = i; idx >= 0; --idx) {
                        if (tileBoard[idx][j].getSpec().getColor() == temp)
                            ++ctr_;
                        else
                            break;
                    }
                } while ((ctr >= 3) || (ctr_ >= 3));
            }
        }
    }

    /**
     * check board intersects and do it operation
     * @param i1, i1 button 1
     * @param j1, j1 button 1
     * @param i2, i2 button 2
     * @param j2, j2 button 2
     * @return true intersect button1 between button2
     */
    private boolean checkBoard_IS(int i1, int j1, int i2, int j2) {

        Color colorCheck = tileBoard[i1][j1].getSpec().getColor();
        Color colorCheck_ = tileBoard[i2][j2].getSpec().getColor();

        int ctr_up = tile_upCost(i1, j1, colorCheck);
        int ctr_down = tile_downCost(i1, j1, colorCheck);
        int ctr_left = tile_leftCost(i1, j1, colorCheck);
        int ctr_right = tile_rightCost(i1, j1, colorCheck);

        int ctr_up_ = tile_upCost(i2, j2, colorCheck_);
        int ctr_down_ = tile_downCost(i2, j2, colorCheck_);
        int ctr_left_ = tile_leftCost(i2, j2, colorCheck_);
        int ctr_right_ = tile_rightCost(i2, j2, colorCheck_);

        if (!(((ctr_up + ctr_down) >= 4) || ((ctr_right + ctr_left) >= 4) || ((ctr_down_ + ctr_up_) >= 4) || ((ctr_right_ + ctr_left_) >= 4))) {
            return false;
        }

        // Button (i1, j1) reference
        int total_X_axis_cost = (ctr_right + ctr_left) - 1;
        int total_Y_axis_cost = (ctr_up + ctr_down) - 1;

        // Button (i1, j1) reference
        int total_X_axis_cost_ = (ctr_right_ + ctr_left_) - 1;
        int total_Y_axis_cost_ = (ctr_down_ + ctr_up_) - 1;

        if (!((total_X_axis_cost >= 3) || (total_Y_axis_cost > 3) || (total_X_axis_cost_ >= 3) || (total_Y_axis_cost_ >= 3))) {
            this.moveTile(i1, j1, i2, j2);
        }

        // Attack i1 j1 on y Axis
        if ((ctr_up + ctr_down) >= 4) {
            int total_iter = (ctr_up + ctr_down) - 1;
            int whichEnemy = enemyIndex(j1);
            Color soldierColor = tileBoard[i1][j1].getSpec().getColor();
            Color enemyColor = enemyList.get(whichEnemy).getColor();

            if (AI_ACTIVE) {
                enemyColor = characterList.get(whichEnemy).getColor();
            }

            if (soldierColor == Color.RED) {
                if (enemyColor == Color.RED){
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack(total_iter * characterList.get(whichEnemy).getDamage());
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + total_iter * characterList.get(whichEnemy).getDamage() + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack(total_iter * enemyList.get(whichEnemy).getDamage());
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + total_iter * enemyList.get(whichEnemy).getDamage() + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }
                else if (enemyColor == Color.BLUE) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }
                else if (enemyColor == Color.GREEN) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }
            }
            else if (soldierColor == Color.BLUE) {
                if (enemyColor == Color.RED) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }

                else if (enemyColor == Color.BLUE) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }

                else if (enemyColor == Color.GREEN) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }
            }
            else if (soldierColor == Color.GREEN) {
                if (enemyColor == Color.RED) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }

                else if (enemyColor == Color.BLUE) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + 2 * (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }

                else if (enemyColor == Color.GREEN) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }
            }
        }
        // Attack i2 j2 on y Axis
        if ((ctr_up_ + ctr_down_) >= 4) {
            int total_iter = (ctr_up_ + ctr_down_) - 1;
            int whichEnemy = enemyIndex(j2);
            Color soldierColor = tileBoard[i2][j2].getSpec().getColor();
            Color enemyColor = enemyList.get(whichEnemy).getColor();
            if (AI_ACTIVE) {
                enemyColor = characterList.get(whichEnemy).getColor();
            }

            if (soldierColor == Color.RED) {
                if (enemyColor == Color.RED){
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack(total_iter * characterList.get(whichEnemy).getDamage());
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + total_iter * characterList.get(whichEnemy).getDamage() + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack(total_iter * enemyList.get(whichEnemy).getDamage());
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + total_iter * enemyList.get(whichEnemy).getDamage() + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }
                else if (enemyColor == Color.BLUE) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }
                else if (enemyColor == Color.GREEN) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }
            }
            else if (soldierColor == Color.BLUE) {
                if (enemyColor == Color.RED) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }

                else if (enemyColor == Color.BLUE) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }

                else if (enemyColor == Color.GREEN) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }
            }
            else if (soldierColor == Color.GREEN) {
                if (enemyColor == Color.RED) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }

                else if (enemyColor == Color.BLUE) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + 2 * (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }

                else if (enemyColor == Color.GREEN) {
                    if (!AI_ACTIVE) {
                        enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                        String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                        if (enemyList.get(whichEnemy).getHealth() == 0.0)
                            msg += "Enemy " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                    else {
                        characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                        String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                        if (characterList.get(whichEnemy).getHealth() == 0.0)
                            msg += "User Character " + whichEnemy + " dead\n";
                        logArea.setText(logArea.getText() + msg);
                    }
                }
            }
        }
        // Attack i1 j1 on x Axis
        if (ctr_left + ctr_right >= 4) {
            for (int i = j1 + 1; i < (j1 + ctr_right); ++i) {
                int whichEnemy = enemyIndex(i);
                int total_iter = 1;
                Color soldierColor = tileBoard[i1][whichEnemy].getSpec().getColor();
                Color enemyColor = enemyList.get(whichEnemy).getColor();
                if (AI_ACTIVE) {
                    enemyColor = characterList.get(whichEnemy).getColor();
                }

                if (soldierColor == Color.RED) {
                    if (enemyColor == Color.RED){
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(total_iter * characterList.get(whichEnemy).getDamage());
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + total_iter * characterList.get(whichEnemy).getDamage() + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(total_iter * enemyList.get(whichEnemy).getDamage());
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + total_iter * enemyList.get(whichEnemy).getDamage() + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
                else if (soldierColor == Color.BLUE) {
                    if (enemyColor == Color.RED) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
                else if (soldierColor == Color.GREEN) {
                    if (enemyColor == Color.RED) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
            }

            for (int i = j1; i > (j1 - ctr_left); --i) {
                int whichEnemy = enemyIndex(i);
                int total_iter = 1;
                Color soldierColor = tileBoard[i1][whichEnemy].getSpec().getColor();
                Color enemyColor = enemyList.get(whichEnemy).getColor();
                if (AI_ACTIVE) {
                    enemyColor = characterList.get(whichEnemy).getColor();
                }

                if (soldierColor == Color.RED) {
                    if (enemyColor == Color.RED){
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(total_iter * characterList.get(whichEnemy).getDamage());
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + total_iter * characterList.get(whichEnemy).getDamage() + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(total_iter * enemyList.get(whichEnemy).getDamage());
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + total_iter * enemyList.get(whichEnemy).getDamage() + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
                else if (soldierColor == Color.BLUE) {
                    if (enemyColor == Color.RED) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
                else if (soldierColor == Color.GREEN) {
                    if (enemyColor == Color.RED) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
            }
        }
        // Attack i2 j2 on x Axis
        if (ctr_left_ + ctr_right_ >= 4) {
            for (int i = j2 + 1; i < (j2 + ctr_right_); ++i) {
                int whichEnemy = enemyIndex(i);
                int total_iter = 1;
                Color soldierColor = tileBoard[i2][whichEnemy].getSpec().getColor();
                Color enemyColor = enemyList.get(whichEnemy).getColor();
                if (AI_ACTIVE) {
                    enemyColor = characterList.get(whichEnemy).getColor();
                }

                if (soldierColor == Color.RED) {
                    if (enemyColor == Color.RED){
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(total_iter * characterList.get(whichEnemy).getDamage());
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + total_iter * characterList.get(whichEnemy).getDamage() + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(total_iter * enemyList.get(whichEnemy).getDamage());
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + total_iter * enemyList.get(whichEnemy).getDamage() + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
                else if (soldierColor == Color.BLUE) {
                    if (enemyColor == Color.RED) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
                else if (soldierColor == Color.GREEN) {
                    if (enemyColor == Color.RED) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
            }
            for (int i = j2; i > (j2 - ctr_left_); --i) {
                int whichEnemy = enemyIndex(i);
                int total_iter = 1;
                Color soldierColor = tileBoard[i2][whichEnemy].getSpec().getColor();
                Color enemyColor = enemyList.get(whichEnemy).getColor();
                if (AI_ACTIVE) {
                    enemyColor = characterList.get(whichEnemy).getColor();
                }

                if (soldierColor == Color.RED) {
                    if (enemyColor == Color.RED){
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(total_iter * characterList.get(whichEnemy).getDamage());
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + total_iter * characterList.get(whichEnemy).getDamage() + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(total_iter * enemyList.get(whichEnemy).getDamage());
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + total_iter * enemyList.get(whichEnemy).getDamage() + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
                else if (soldierColor == Color.BLUE) {
                    if (enemyColor == Color.RED) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
                else if (soldierColor == Color.GREEN) {
                    if (enemyColor == Color.RED) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()) / 2);
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()) / 2);
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) / 2 + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.BLUE) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack(2 * (total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + 2 * (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack(2 * (total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + 2 * (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }

                    else if (enemyColor == Color.GREEN) {
                        if (!AI_ACTIVE) {
                            enemyList.get(whichEnemy).takeAttack((total_iter * characterList.get(whichEnemy).getDamage()));
                            String msg = "User Character " + whichEnemy + " attack to enemy " + whichEnemy + "\n"
                                    + "Damage is " + (total_iter * characterList.get(whichEnemy).getDamage()) + "\n";
                            if (enemyList.get(whichEnemy).getHealth() == 0.0)
                                msg += "Enemy " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                        else {
                            characterList.get(whichEnemy).takeAttack((total_iter * enemyList.get(whichEnemy).getDamage()));
                            String msg = "Enemy " + whichEnemy + " attack to user character " + whichEnemy + "\n" +
                                    "Damage is " + (total_iter * enemyList.get(whichEnemy).getDamage()) + "\n";
                            if (characterList.get(whichEnemy).getHealth() == 0.0)
                                msg += "User Character " + whichEnemy + " dead\n";
                            logArea.setText(logArea.getText() + msg);
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * swap tile color
     * @param i1, i1 button 1
     * @param j1, j1 button 1
     * @param i2, i2 button 2
     * @param j2, j2 button 2
     */
    private void moveTile(int i1, int j1, int i2, int j2) {
        TileSpec temp = this.tileBoard[i1][j1].getSpec();
        this.tileBoard[i1][j1].changeSpec(this.tileBoard[i2][j2].getSpec());
        this.tileBoard[i2][j2].changeSpec(temp);
    }

    /**
     * action after tiles changed
     * @return true if tiles changed feasible, false it tiles changed invalig
     */
    private boolean actionUser() {

        boolean returnVal = true;
        int i_1 = (int) clicked1.getX();
        int j_1 = (int) clicked1.getY();
        int i_2 = (int) clicked2.getX();
        int j_2 = (int) clicked2.getY();

        boolean crossMove = ((i_1 > i_2) || (i_1 < i_2)) && ((j_1 > j_2) || (j_1 < j_2));
        boolean linearMove = (i_1 + 1 == i_2) || (i_1 - 1 == i_2) || (j_1 + 1 == j_2) || (j_1 - 1 == j_2);
        if ((!crossMove) && (linearMove)) {
            this.moveTile(i_1, j_1, i_2, j_2);
            returnVal = this.checkBoard_IS(i_1, j_1, i_2, j_2);
            if (!returnVal)
                this.moveTile(i_2, j_2, i_1, j_1);
        } else {
            returnVal = false;
        }

        this.tileBoard[i_1][j_1].setText("");
        this.tileBoard[i_2][j_2].setText("");
        GameEngine.click_1 = false;
        GameEngine.click_2 = false;
        return returnVal;
    }

    /**
     * action of AI
     * my AI algortihm runs this method
     * AI first attakc C1 and C2 and C3 and C1 like circular array
     * @return true if AI find any movement, false if AI cannot find any movement
     */
    private boolean actionAI() {

        ArrayList<Integer> neighboor = new ArrayList<>();

        for (int i = 0; i < TILES_ROW; ++i) {
            for (int j = 0; j < TILES_COLUMN; ++j) {

                if ((AI_MOVE_CHOICE % 3) == enemyIndex(j))
                {
                    // Check up
                    if (i > 0) {
                        clicked1.setLocation(i, j);
                        clicked2.setLocation(i - 1, j);
                        if (actionUser()) {
                            ++AI_MOVE_CHOICE;
                            return true;
                        }
                    }

                    // Check down
                    if (i < (TILES_ROW - 1)) {
                        clicked1.setLocation(i, j);
                        clicked2.setLocation(i + 1, j);
                        if (actionUser()) {
                            ++AI_MOVE_CHOICE;
                            return true;
                        }
                    }

                    // Check right
                    if (j < (TILES_COLUMN - 1)) {
                        clicked1.setLocation(i, j);
                        clicked2.setLocation(i, j + 1);
                        if (actionUser()){
                            ++AI_MOVE_CHOICE;
                            return true;
                        }
                    }

                    // Check left
                    if (j > 0) {
                        clicked1.setLocation(i, j);
                        clicked2.setLocation(i, j - 1);
                        if (actionUser()){
                            ++AI_MOVE_CHOICE;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * check user loses
     * @return true ai win, false continue game
     */
    private boolean checkAIWin() {
        int ctr = 0;
        for (GameCharacter chr : characterList)
            ctr += chr.getHealth();
        if (ctr == 0) return true; // AI Win
        return false;
    }

    /**
     * check ai loses
     * @return true user win, false continue game
     */
    private boolean checkUserWin() {
        int ctr = 0;
        for (GameCharacter chr : enemyList)
            ctr += chr.getHealth();
        if (ctr == 0) return true; // User win

        return false;
    }

    /**
     * Main game loop. A Thread that implemented call this method
     */
    public void gameLoop() {
        while (true) {
            if(GameEngine.click_1 && GameEngine.click_2) {

                // USER MOVEMENT //
                logArea.setText(logArea.getText() + "\n----------\n");
                this.AI_ACTIVE = false;
                boolean refresh = this.actionUser();
                this.printCharacters();
                if (this.checkUserWin()) {
                    JOptionPane.showMessageDialog(null, "You Win!", "Congrats!", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(1);
                }
                if (refresh)
                    this.refreshBoard();
                else {
                    logArea.setText(logArea.getText() + "\nInvalid Move. Try Again!\n");
                    continue;
                }

                logArea.setText(logArea.getText() + "----------\n");
                // AI MOVEMENT //
                this.AI_ACTIVE = true;
                refresh = this.actionAI();
                this.printCharacters();
                if (this.checkAIWin()) {
                    JOptionPane.showMessageDialog(null, "AI Win!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(1);
                }
                if (refresh)
                    this.refreshBoard();
                else
                    logArea.setText(logArea.getText() + "\nAI Can not find move.");
            }
            try { Thread.sleep(DELTA_TIME_FOR_SMOOTH); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}