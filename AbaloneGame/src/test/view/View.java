package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import model.player.Move;
import model.player.Player;

import java.util.ArrayList;


public class View extends Application {
    int Players;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    Stage window;
    String[] Colors2P = {"black","white"};
    String[] Colors3P = {"black","white","red","pink"};
    String[]  Colors4P = {"black","white","red"};

    Button twoplayer;
    //    Label turn2w = new Label("white");
//    Label turn2b = new Label("black");
    Button threeplayer;
    Button fourplayer;
    Button instructions;

    Button exit;
    Scene scene1, scene2, scene3, sceneRules, scene4;

    Button goBack, goBack2, goBack3, goBack4, selected2, selected3, selected4;
    Label rules;
    ArrayList<Circle> selection = new ArrayList<Circle>();
    SimpleObjectProperty<EventHandler<MouseEvent>> drag = new SimpleObjectProperty<>(this, "drag", new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            ((Circle) (event.getSource())).setTranslateX(newTranslateX);
            ((Circle) (event.getSource())).setTranslateY(newTranslateY);

            //sensitivity and when a move is made X horizontal trigger
            if (offsetX > 40) {
//                System.out.println((offsetX + orgSceneX) + "xxxx NEW TRANSLATE xxxxx " + (offsetY + orgSceneY));
                orgSceneX = orgSceneX + 65;
            }
            if (offsetX < -40) {
                orgSceneX = orgSceneX - 65;
//                System.out.println((offsetX + orgSceneX) + "xxxx NEW TRANSLATE xxxxx " + (offsetY + orgSceneY));
            }
            //sensitivity and when a move is made   Y Vertical trigger
            if (offsetY > 40 || offsetY < -40) {
                orgSceneY = orgSceneY + offsetY;
                orgSceneX = orgSceneX + offsetX;
//                System.out.println((offsetX + orgSceneX) + "xxxx NEW TRANSLATE xxxxx " + (offsetY + orgSceneY));
            }
        }
    });

    int selected = 0;

    EventHandler<MouseEvent> click =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (selection.size() > 2) {
                        for(int i = 0; i < selection.size();i++){
                            selection.get(i).setOpacity(10);

                        }
                        selected =0;
                        selection.clear();
                        //set move to invalid
                    }
                    orgSceneX = e.getSceneX();
                    orgSceneY = e.getSceneY();
//                    System.out.println("original X  is " + orgSceneX );
//                    System.out.println("original Y  is " + orgSceneY);
                    orgTranslateX = ((Circle) (e.getSource())).getTranslateX();
                    orgTranslateX = ((Circle) (e.getSource())).getTranslateX();
                    String fill = ((Circle) (e.getSource())).getFill().toString();//gets color of object marble/circle
//                    System.out.println("xxxxxxxx"); //breaker
//                    System.out.println(orgTranslateX); //offset
                    if (selection.size() > 3) {
                        if (fill.equals("0x808000ff")) {
//                            System.out.println("Gold bay");
                            if (selection.size() == 1) {
                                double movecenX = ((Circle) (e.getSource())).getCenterX();
                                double movecenY = ((Circle) (e.getSource())).getCenterY();
//                                System.out.println("Yessss");
                                selection.get(0).setCenterX(movecenX);
                                selection.get(0).setCenterY(movecenY);
                                selection.get(0).setOpacity(10);
                                selected--;
                            }
                        }

                    } else if (fill.equals("0x000000ff") && color.equals("black")) {
//                        System.out.println("Black");
                        if (((Circle) (e.getSource())).getOpacity() == .5) {
                            ((Circle) (e.getSource())).setOpacity(10);
                            selected--;
                        } else {
                            ((Circle) (e.getSource())).setOpacity(.5);
                            selected++;
                            selection.add(((Circle) (e.getSource())));
                            //System.out.println(selection.get(0).toString());
                        }
                    } else if (fill.equals("0xffffffff")&& color.equals("white")) {
//                        System.out.println("white");
                        if (((Circle) (e.getSource())).getOpacity() == .5) {
                            ((Circle) (e.getSource())).setOpacity(10);
                            selected--;
                        } else {
                            ((Circle) (e.getSource())).setOpacity(.5);
                            selected++;
                            selection.add(((Circle) (e.getSource())));
                        }
                    } else {
                        System.err.println(fill + "\nError object not accounted for");//error
                    }
                }
            };
    //    private void show(Stage primaryStage, Parent parent) {
//        primaryStage.setScene(new Scene(parent, 500, 500));
//        primaryStage.show();
//        primaryStage.setScene(new Scene(parent, 500, 500));
//        PopupControl main;
//        primaryStage.show();
//
//    }
    ArrayList<Circle> CurrentPs = new ArrayList<Circle>();
    ArrayList<Circle> WantingPosition = new ArrayList<Circle>();
    String color = "white";

    public static void main(String[] args) {
        launch(args);
    }

    private static void kevin(Polygon hex, Circle[] circle, Group blend, Label score_3_player,
                              Button goBack2, Button selected2, Circle[] blackpieces3, Circle[] whitepieces3, Circle[] redpieces3) {
        kevin_sub_first(hex, circle, blend, score_3_player, goBack2, selected2, blackpieces3, whitepieces3, redpieces3);
    }

    private static void kevin2(Polygon hexagon, Circle[] spots, Circle[] redPieces, Circle[] whitePieces,
                               Group root3, Button goBack3,Label colorlabel2 ,Label colorLabelw ,Button selected3, Label score) {
        kevin_sub_new(hexagon, spots, redPieces, whitePieces, root3, goBack3,colorlabel2,colorLabelw,selected3, score);
    }

    private static void kevin3(Polygon hexa, Circle[] spotty, Group blendy, Button goBack4, Label score_4_player,
                               Circle[] whitey, Circle[] bluey, Circle[] blacky, Circle[] redy) {
        kevin_suby(hexa, spotty, blendy, goBack4, score_4_player, whitey, bluey, blacky, redy);
    }

    private static void kevin_suby(Polygon hexa, Circle[] spotty, Group blendy, Button goBack4, Label score_4_player,
                                   Circle[] whitey, Circle[] bluey, Circle[] blacky, Circle[] redy) {
        blendy.getChildren().addAll(hexa, score_4_player,

                spotty[0], spotty[1], spotty[2], spotty[3], spotty[4], spotty[5], spotty[6], spotty[7], spotty[8], spotty[9], spotty[10],
                spotty[11], spotty[12], spotty[13], spotty[14], spotty[15], spotty[16], spotty[17], spotty[18], spotty[19], spotty[20],
                spotty[21], spotty[22], spotty[23], spotty[24], spotty[25], spotty[26], spotty[27], spotty[28], spotty[29], spotty[30],
                spotty[31], spotty[32], spotty[33], spotty[34], spotty[35], spotty[36], spotty[37], spotty[38], spotty[39], spotty[40],
                spotty[41], spotty[42], spotty[43], spotty[44], spotty[45], spotty[46], spotty[47], spotty[48], spotty[49], spotty[50],
                spotty[51], spotty[52], spotty[53], spotty[54], spotty[55], spotty[56], spotty[57], spotty[58], spotty[59], spotty[60],

                goBack4,

                whitey[0], whitey[1], whitey[2], whitey[3], whitey[4], whitey[5], whitey[6], whitey[7], whitey[8],
                bluey[0], bluey[1], bluey[2], bluey[3], bluey[4], bluey[5], bluey[6], bluey[7], bluey[8],

                blacky[0], blacky[1], blacky[2], blacky[3], blacky[4], blacky[5], blacky[6], blacky[7], blacky[8],
                redy[0], redy[1], redy[2], redy[3], redy[4], redy[5], redy[6], redy[7], redy[8]
        );
    }

    private static void kevin_sub_first(Polygon hex, Circle[] circle, Group blend, Label score_3_Player,
                                        Button goBack2, Button selected2, Circle[] blackpieces3, Circle[] whitepieces3, Circle[] redpieces3) {
        blend.getChildren().addAll(hex, score_3_Player, goBack2, selected2,

                circle[0], circle[1], circle[2], circle[3], circle[4], circle[5],
                circle[6], circle[7], circle[8], circle[9], circle[10]
                , circle[11], circle[12], circle[13], circle[14], circle[15],
                circle[16], circle[17], circle[18], circle[19], circle[20],
                circle[21], circle[22], circle[23], circle[24], circle[25],
                circle[26], circle[27], circle[28], circle[29], circle[30],
                circle[31], circle[32], circle[33], circle[34], circle[35], circle[36], circle[37], circle[38],
                circle[39], circle[40], circle[41], circle[42], circle[43], circle[44], circle[45],
                circle[46], circle[47], circle[48], circle[49], circle[50],
                circle[51], circle[52], circle[53], circle[54], circle[55],
                circle[56], circle[57], circle[58], circle[59], circle[60],

                blackpieces3[0], blackpieces3[1], blackpieces3[2], blackpieces3[3], blackpieces3[4], blackpieces3[5],
                blackpieces3[6], blackpieces3[7], blackpieces3[8], blackpieces3[9], blackpieces3[10],

                whitepieces3[0], whitepieces3[1], whitepieces3[2], whitepieces3[3], whitepieces3[4], whitepieces3[5],
                whitepieces3[6], whitepieces3[7], whitepieces3[8], whitepieces3[9], whitepieces3[10],

                redpieces3[0], redpieces3[1], redpieces3[2], redpieces3[3], redpieces3[4], redpieces3[5],
                redpieces3[6], redpieces3[7], redpieces3[8], redpieces3[9], redpieces3[10]
        );
    }

    private static void kevin_sub_new(Polygon hexagon, Circle[] spots, Circle[] redPieces, Circle[] whitePieces,
                                      Group root3, Button goBack3,Label colorlabel2,Label colorLabelw, Button selected3, Label score) {
        root3.getChildren().addAll(hexagon, score,
                spots[0], spots[1], spots[2], spots[3], spots[4], spots[5], spots[6], spots[7], spots[8], spots[9], spots[10],
                spots[11], spots[12], spots[13], spots[14], spots[15], spots[16], spots[17], spots[18], spots[19], spots[20],
                spots[21], spots[22], spots[23], spots[24], spots[25], spots[26], spots[27], spots[28], spots[29], spots[30],
                spots[31], spots[32], spots[33], spots[34], spots[35], spots[36], spots[37], spots[38], spots[39], spots[40],
                spots[41], spots[42], spots[43], spots[44], spots[45], spots[46], spots[47], spots[48], spots[49], spots[50],
                spots[51], spots[52], spots[53], spots[54], spots[55], spots[56], spots[57], spots[58], spots[59], spots[60],

                redPieces[0], redPieces[1], redPieces[2], redPieces[3], redPieces[4], redPieces[5]
                , redPieces[6], redPieces[7], redPieces[8], redPieces[9], redPieces[10],
                redPieces[11], redPieces[12], redPieces[13],

                whitePieces[0], whitePieces[1], whitePieces[2], whitePieces[3], whitePieces[4], whitePieces[5],
                whitePieces[6], whitePieces[7], whitePieces[8], whitePieces[9], whitePieces[10],
                whitePieces[11], whitePieces[12], whitePieces[13], goBack3,colorlabel2,colorLabelw,selected3);
    }
    /*
     spots[11],spots[12],spots[13],spots[14],spots[15],spots[16],spots[17],spots[18],spots[19],spots[20],
                spots[21],spots[22],spots[23],spots[24],spots[25],spots[26],spots[27],spots[28],spots[29],spots[30],
                spots[31],spots[32],spots[33],spots[34],spots[35],spots[36],spots[37],spots[38],spots[39],spots[40],
     */
    String keypressed;

    public void addKeyHandler(Scene scene) {

        scene.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.R)) {
                System.out.println("R");
                keypressed = "r";
                if(GuiIsLegal()){
                    r();
                }
                return;
            }
            if (keyCode.equals(KeyCode.T)) {
                System.out.println("T");
                keypressed = "t";
                if(GuiIsLegal()){
                    t();
                }
                return;
            }
            if (keyCode.equals(KeyCode.G)) {
                System.out.println("G");
                String r = "G";
                keypressed = "g";
                if(GuiIsLegal()){
                    g();
                }
                return;
            }
            if (keyCode.equals(KeyCode.V)) {
                System.out.println("V");
                keypressed = "v";
                if(GuiIsLegal()){
                    v();
                }
                return;
            }
            if (keyCode.equals(KeyCode.C)) {
                System.out.println("C");
                keypressed = "c";
                if(GuiIsLegal()){
                    c();
                }
                return;
            }
            if (keyCode.equals(KeyCode.D)) {
                System.out.println("D");
                String r = "D";
                keypressed = "d";
                if(GuiIsLegal()){
                    d();
                }
            }

        });
    }


    public void d() {
        for (int i = 0; i < selection.size(); i++) {
            double X = selection.get(i).getCenterX();
            selection.get(i).setCenterX(X - 65);
            selection.get(i).setOpacity(10);//NEW CHANGE
            selected--;
        }
//        selected = selected - selection.size();
        selection.clear();

    }

    public void g() {
//        if(spots) taken dont move there !

        for (int i = 0; i < selection.size(); i++) {
            double X = selection.get(i).getCenterX();
            selection.get(i).setCenterX(X + 65);
            selection.get(i).setOpacity(10);//NEW CHANGE
            selected--;
        }
        selected = 0;
        selection.clear();

    }

    public void r() {
        for (int i = 0; i < selection.size(); i++) {
            double X = selection.get(i).getCenterX();
            double Y = selection.get(i).getCenterY();
            X = X - 32;
            Y = Y - 56;
//            System.out.println("r");
            selection.get(i).setCenterX(X );
            selection.get(i).setCenterY(Y );
            selection.get(i).setOpacity(10);//NEW CHANGE
            selected--;
        }
        selection.clear();
    }
    public void c() {
        for (int i = 0; i < selection.size(); i++) {
            double X = selection.get(i).getCenterX();
            double Y = selection.get(i).getCenterY();
            X = X - 32;
            Y = Y + 56;
            selection.get(i).setCenterX(X );
            selection.get(i).setCenterY(Y );
            selection.get(i).setOpacity(10);//NEW CHANGE
            selected--;
        }
        selection.clear();
    }
    public void v() {
        for (int i = 0; i < selection.size(); i++) {
            double X = selection.get(i).getCenterX();
            double Y = selection.get(i).getCenterY();
            X = X + 32;
            Y = Y + 56;
            selection.get(i).setCenterX(X );
            selection.get(i).setCenterY(Y );
            selection.get(i).setOpacity(10);//NEW CHANGE
            selected--;
        }
        selection.clear();
    }
    public void t() {
        for (int i = 0; i < selection.size(); i++) {
            double X = selection.get(i).getCenterX();
            double Y = selection.get(i).getCenterY();
            X = X + 32;
            Y = Y - 56;
            selection.get(i).setCenterX(X );
            selection.get(i).setCenterY(Y );
            selection.get(i).setOpacity(10);//NEW CHANGE
            selected--;
        }
        selection.clear();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        //System.out.println(window.getWidth(),window.getWidth());
        primaryStage.show();
        window.setMaximized(true);//makes full screen
        window.setTitle("Abalone entry menu");
        twoplayer = new Button("two player game ");
        goBack = new Button("Return to main menu");
        goBack2 = new Button("Submit 2");
        goBack3 = new Button("Click before moving");
        goBack3.setLayoutY(200);
        Label colorLabelw = new Label("");
        Label colorlabel2 = new Label("white or black to move"); //  #equality
        colorlabel2.setFont(new Font("Arial", 30));
        colorlabel2.setFont(new Font("Arial", 30));
        colorlabel2.setLayoutX(300);
        colorLabelw.setLayoutX(300);
        goBack4 = new Button("submit 4");
        selected2 = new Button("selected 2");
        selected3 = new Button("Click before selection");
        selected4 = new Button("selected 4");
        selected2.setLayoutX(100);
        selected3.setLayoutX(0);
        selected4.setLayoutX(100);
        exit = new Button("Exit");
        Label label = new Label("Welcome");
        instructions = new Button("Game Rules");
        //------------------Rules-------------------------////////////////////
        rules = new Label("\n\n\n\nHOW TO PLAY \n" +
                "The board consists of 61 circular spaces arranged in a hexagon, \n" +
                " five on a side. Each player has a set number of marble:\n" +
                " 2-player: 28 marbles total. Each player ->(14 black and 14 white).\n" +
                " 3-player: 33 marbles total. Each player -> (11 black; 11 white; 11 blue).\n" +
                " 4-player: 36 marbles total. Each player ->(9 black; 9 white; 9 blue; 9 red)\n" +
                "The players take turns with the black marbles moving first. \n\nFor each move" +
                " a player moves a straight line of one, two or three marbles of one color \none space in one of six directions.\n" +
                " The move can be either broadside / arrow-like (parallel to the line of marbles)\n" +
                " or in-line / in a line (serial in respect to the line of marbles),\n" +
                "A player can push their opponent's marbles (a \"sumito\") that are in a line \n" +
                "to their own with an in-line move only. \n" +
                "They can only push if the pushing line has more marbles than the pushed line \n" +
                "(three can push one or two; two can push one). Marbles must be pushed to an empty space\n" +
                " (i.e. not blocked by a marble) or off the board. \n" +
                "\n" +
                " ABOVE WAS TAKEN FROM WIKIPEDIA \n \n \n" +
                "CONTROLS OF THE GUI \n" +
                "click the select button\n" +
                "Select the marbles you wish to move and they will be highlighted \n" +
                "now click the submit button\n" +
                "now look at the F key on your keyboard \n ABALONE INTENSIFIES \n" +
                "the keys R,T,G,V,C,D represent the direction in which you wish to move the circle\n" +
                "The submit button would be used to submit a move from the client to the server . \n" +
                "The selection button clears leftover buggy data in the selection array of \n" +
                "currently selected marbles to move" +
                "Note Currently only two player has functionality");



        threeplayer = new Button("three player game");
        fourplayer = new Button("four player game");
        twoplayer.setOnAction(e -> {
            boolean result = ConfirmBox.display("Two player", "Are you sure you want a two player game?");
            if (result) {
                window.setTitle("Abalone Game");
                window.setScene(scene3);
                Players = 2;
            }
        });


        goBack3.setOnKeyPressed(e -> {
            if(GuiIsLegal()){
                changeColor3(color);
                guiMove(color);

                addKeyHandler(scene3);
                if(color.equals("white")){
                    colorlabel2.setText("white");
                }
                if(color.equals("black")){
                    colorlabel2.setText("black");
                }
                System.out.println(color+"working");
            }
        });
        threeplayer.setOnAction(e -> {
            boolean result = ConfirmBox.display("Three player", "Are you sure you want a three player game?");
            if (result) {
                Players = 3;
                window.setTitle("Abalone Game");
                window.setScene(scene2);
            }
        });

        fourplayer.setOnAction(e -> {
            boolean result = ConfirmBox.display("four player", "Are you sure you want a four player game?");
            if (result) {
                Players = 4;
                window.setTitle("Abalone Game");
                window.setScene(scene4);
            }
        });

//        selected2.setOnAction(e ->{
//            getSelected();
//            scene1.ke;
//        });
        goBack.setOnAction(e -> {
            Players = 0;
            window.setScene(scene1);
            window.setTitle("menu");
        });

        goBack2.setOnAction(e -> {
            if(GuiIsLegal()){
                String selectable = Colors2P[1];
            }

            //window.setTitle("menu");
        });

        selected3.setOnAction(e -> {
            String s;
            s = getSelected();
            System.out.println(s);
            for(int i = 0 ; i < selection.size();i++){
                selection.get(i).setOpacity(10);
            }
            selection.clear();
            selected = 0;
        });
        selected2.setOnAction(e -> {
            String s;
            s = getSelected();

            System.out.println(s);
        });

        instructions.setOnAction(e -> {
            window.setTitle("Abalone Game");
            window.setScene(sceneRules);
        });
//-----------------------------EXITING-------
//Now when you press the x button in top right the close window command will be consumed
        /*
        thus not executing and we go to exit program method where you get the choice
        if you wanna close or not
         */
        window.setOnCloseRequest(e -> {
            e.consume();
            exitProgram();
        });

        exit.setOnAction(e -> {
            exitProgram();
        });
        //---//--------------------------------Changed scene-----------------------------------//

        //---------------BOARD SCENE-------------------------
        StackPane layout2 = new StackPane();
        Polygon hexagon = new Polygon();
        hexagon.setFill(Color.BROWN);
        Polygon hex = new Polygon();
        Polygon hexa = new Polygon();
        hexa.setFill(Color.BROWN);

        //-------------------------Creating board 3 player---------------------
        makeHex(hex);
        //-------------------------Creating board 4 player---------------------
        makeHex(hexa);
        //-------------------------Creating board 2 player ---------------------
        makeHex(hexagon);
        /*
        Can not use the same board due to group overlap else would
        require parent class and this is a lot easier to have separate groups
         */

        //-----------creating && initializing Pieces 2 player--------------------------//
        //BLACK IS RED
        Circle[] redPieces = red14();
        //----Creating and initializing White Pieces
        Circle[] whitePieces = white14();
        //extra_issue_fuck.setOnMousePressed(click);
        occupied();
        //--------------------Creating a circle object

        MyCircle[] circle = new MyCircle[61];// create spots two player
        MyCircle[] spots = new MyCircle[61];// create slots three player
        MyCircle[] spotty = new MyCircle[61];//creates spotties for four player game
        int x = 375;
        int y = 126;
        positioningHoles(  x, y, spots);
//Create rows
        positioningHoles( x, y, circle);

        positioningHoles(  x, y, spotty);
            /*
            Green Test
             circle[1].setFill(Color.GREEN);
        circle[1].setCursor(Cursor.HAND);
        circle[1].setOnMouseDragged(drag.get());
        circle[1].setOnMousePressed(click);
             */


        //add 53 to x and 56 to y NBNBNBNB

        //spaces are empty squares where a piece can be placed
        layout2.getChildren().add(exit);
        //Labels Lives
        //END OF 2 PLAYER INITIALIZATION
        //-----------creating && initializing Pieces 3 player--------------------------//
        Circle[] blackPieces3 = blackPieces3();
        Circle[] whitePieces3 = whitePieces3();
        Circle[] redPieces3 = redPieces3();



        //-----------creating && initializing Pieces 4 player--------------------------//
        Circle[] blacky = CircleInitBlack();
        Circle[] bluey = CircleInitBlue();
        Circle[] whitey = CircleInitWhite();
        Circle[] redy =  CircleInitRed();
        //End
        int player1live = 0; //black
        int player2live = 0; //white
        int player3live = 0; //red
        int team1score = 0; //team one 4 player
        int team2score = 0; //team 2 four player
        //Lives = score;
        Label score = new Label("Player 1 score :" + player1live + "\n" +
                "Player 2 score : " + player2live);
        score.setLayoutY(100); // space theyyo see go back button lol

        Label score_3_Player = new Label("Player 1 score :" + player1live + "\n" +
                "Player 2 score : " + player2live + "  \nPlayer 3 score :" + player3live);
        score_3_Player.setLayoutY(100); // space to see go back button lol

        Label score_4_Player = new Label(" \n Team 1 score : " + team1score +
                "\n Team 2 score " + team2score);
        score_4_Player.setLayoutY(100); // space to see go back button lol

        score.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        score_3_Player.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        score_4_Player.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));

        //ADDing all components to scene 2,3,4 Player
//--------------------------Scene 2,3,4 player Game AND Scene three player Game setting ------------------------------------------
        Group root3 = new Group(); //2 player
        Group blend = new Group(); // 3 player
        Group blendy = new Group(); // 4 player
        kevin(hex, circle, blend, score_3_Player, goBack2, selected2, blackPieces3, whitePieces3, redPieces3);
        kevin2(hexagon, spots, redPieces, whitePieces, root3, goBack3,colorlabel2,colorLabelw,selected3, score);
        kevin3(hexa, spotty, blendy, goBack4, score_4_Player, whitey, bluey, blacky, redy);
        //scene 1 = start menu
        //2 player is Scene 3
        scene3 = new Scene(root3, 1600, 1000, Color.LIGHTSKYBLUE);
        //3 player is scene 2
        scene2 = new Scene(blend, 1600, 1000, Color.LIGHTGRAY);
        //4 player is scene 4
        scene4 = new Scene(blendy, 1600, 1000, Color.GREY);


//--------------------------------RULES SCENE----------------------------
        HBox layout3 = new HBox();
        layout3.getChildren().addAll(goBack, rules);
        //scene3.setFill(Color.GREEN);

        sceneRules = new Scene(layout3, 1600, 1000);

        //------------------------starting scene--------------------------------//
        FlowPane flow = new FlowPane(label, twoplayer, threeplayer, fourplayer, instructions, exit);
        flow.setPadding(new Insets(5, 2, 5, 2));
        flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.setStyle("-fx-background-color: DAE6F3;");

        scene1 = new Scene(flow, 500, 500);
        window.setScene(scene1);
        window.show();

/*
        public double setLocation(double orgSceneX, int x_white){
            if(x_white +40 > orgSceneX){
                x_white = x_white + 65; //one to the right = 65
            }
        }*/
    }
    public Circle[] CircleInitWhite(){
        Circle[] whitey = new Circle[9];
        int allx = 375;   //56 y 65 x  plus / minus 30 diag
        int ally = 126;//(56*8)

        for (int j = 0; j < whitey.length; j++) {
            whitey[j] = new Circle(allx, ally, 20, Color.WHITE);
            whitey[j].setCursor(Cursor.HAND);
            //whitey[j].setOnMouseDragged(drag.get());
            whitey[j].setOnMousePressed(click);

            allx = allx + 65;
            if (j == 3) {
                allx = 375 + 30;
                ally = 126 + 56;
            }
            if (j == 6) {
                allx = 375 + 65;
                ally = 126 + 56 + 56;
            }
        }
        return whitey;
    }
    public Circle[] CircleInitBlack(){
        Circle[] blacky = new Circle[9];
        double allx = 375 - (30 * 4);
        double ally = 126 + (56 * 4);

        for (int j = 0; j < blacky.length; j++) {
            blacky[j] = new Circle(allx, ally, 20, Color.BLACK);
            blacky[j].setCursor(Cursor.HAND);
            blacky[j].setOnMouseDragged(drag.get());
            blacky[j].setOnMousePressed(click);
            allx = allx + 30;
            ally = ally + 56;
            if (j == 3) {
                allx = 375 - (30 * 2);
                ally = 126 + (56 * 4);
            }
            if (j == 6) {
                allx = 375;
                ally = 126 + (56 * 4);
            }
        }
        return blacky;
    }
    public Circle[] CircleInitBlue(){
        double allx = 375 + 65;
        double ally = 126 + (56 * 8);
        Circle[] bluey = new Circle[9];

        for (int j = 0; j < bluey.length; j++) {
            bluey[j] = new Circle(allx, ally, 20, Color.LIGHTCORAL);
            bluey[j].setCursor(Cursor.HAND);
            bluey[j].setOnMouseDragged(drag.get());
            bluey[j].setOnMousePressed(click);

            allx = allx + 65;
            if (j == 3) {
                allx = 375 + 30 + 65;
                ally = 126 + (56 * 7);
            }
            if (j == 6) {
                allx = 375 + 65 + 65;
                ally = 126 + (56 * 6);
            }
        }
        return  bluey;
    }
    public Circle[] CircleInitRed() {

        Circle[] redy = new Circle[9];
        int allx = 375;   //56 y 65 x  plus / minus 30 diag
        int ally = 126;//(56*8)

        allx = 375 + (30 * 4) + (65 * 4);
        ally = 126 + (56 * 4);
        for (int j = 0; j < redy.length; j++) {

            redy[j] = new Circle(allx, ally, 20, Color.ROYALBLUE);
            redy[j].setCursor(Cursor.HAND);
            //redy[j].setOnMouseDragged(drag.get());
            redy[j].setOnMousePressed(click);
            allx = allx - 30;
            ally = ally - 56;
            if (j == 3) {
                allx = 375 - (30 * 2) + (65 * 6);
                ally = 126 + (56 * 4);
            }
            if (j == 6) {
                allx = 375 + (65 * 4);
                ally = 126 + (56 * 4);
            }
        }
//        System.out.println(redy.length);
        return redy;
    }

    private boolean GuiIsLegal() {
        if(selection.size()>3){
            for (int i = 0 ; i < selection.size();i++){
                selection.get(i).setOpacity(10);
            }
            selection.clear();
            selected = 0;
            System.out.println("overselection");
            return false;
            //if somehow we select more than three objects
        }
        for(int i = 0; i < selection.size()-1;i++){
            if(selection.get(i).getFill() != selection.get(i+1).getFill()){
                System.out.println("not the same color how did you do this lol IMPLEMENTING apartheid");
                return false;
            }
            if(selection.size() == 1){
                System.out.println("1 selected only");
                return true;
            }

            if(selection.size() == 2){
                if(selection.get(i).getCenterY() == selection.get(i+1).getCenterY() &&
                        selection.get(i).getCenterX() < selection.get(i+1).getCenterX() &&
                        selection.get(i).getCenterX() > selection.get(i+1).getCenterX() - 80 || //neghbour to left
                        selection.get(i).getCenterX() > selection.get(i).getCenterX() &&
                                selection.get(i).getCenterX() < selection.get(i).getCenterX() - 80 )
                {
                    return true;
                }

                if(selection.get(i).getCenterX() < selection.get(i+1).getCenterX() &&
                        selection.get(i).getCenterX() > selection.get(i+1).getCenterX() - 40||
                        selection.get(i).getCenterX() > selection.get(i+1).getCenterX() &&
                                selection.get(i).getCenterX() < selection.get(i+1).getCenterX()+40)
                {
                    System.out.println("x is good now lets check y");
                    if(selection.get(i).getCenterY() < selection.get(i+1).getCenterY() &&
                            selection.get(i).getCenterY() > selection.get(i+1).getCenterY() - 62||
                            selection.get(i).getCenterY() > selection.get(i+1).getCenterY() &&
                                    selection.get(i).getCenterY() < selection.get(i+1).getCenterY()+62)
                    {
                        if(selection.size() == 2){
                            System.out.println("is neighbour ");
                            return true;
                        }
                        }
                    }
                }
            if (selection.size() == 3){
                double Xtotal = selection14ValX(0)+selection14ValX(1)+selection14ValX(2);
//                System.out.println(selection14ValX(0)+"ko"+selection14ValX(1)+selection14ValX(2));
                double avgX = Xtotal/3;
                double Ytotal = selection14ValY(1)+selection14ValY(2)+selection14ValY(0);

//                System.out.println(selection14ValY(0)+"koy"+selection14ValY(1)+selection14ValY(2));
                double avgY = Ytotal/3;
//                System.out.println(avgX+"avg"+avgY);
                for(int y=0;y<3;y++){
                    if(selection14ValX(y)-7<avgX && avgX<selection14ValX(y)+7 &&
                            selection14ValY(y) -7<avgY && selection14ValY(y) < avgY+6){
//                        System.out.println("three yall good");
                        return true;
                    }
                }
                return false;
            }
            //
            }
        System.out.println("we made it in legal so false");
        System.out.println(selection.size());
        if(selection.size()==1){
            return true;
        }
        return false;
    }

    private String changeColor3(String color) {
        if (color.equals("white")){
            color = "black";
            this.color = color;
        }
        else{
            color = "white";
            this.color = color;
        }
        return color;
    }



//    private Move move() {
//        return move;
//    }

    public Circle[] positioningHoles( int x, int y, Circle[] whatever) {
        for (int j = 0; j < whatever.length; j++) {
            whatever[j] = new MyCircle(x, y, 10, Color.OLIVE);// initialize circles with radius of 50 and color = grey
            //position
            x = x + 65;   //x coordinates
            whatever[j].setCursor(Cursor.HAND);
            whatever[j].setOnMousePressed(click);
            //Row 2  || row 8
            if (j == 4 || j == 49) {//5 circles made therefore shift to next row
                x = 345;
                y = y + 56; //126 + 56 + 56
            }
            //row 3 || row 7
            if (j == 10 || j == 42) {
                x = 315; //30 less than prior row
                y = y + 56; //126 +56 + 56
            }
            //row 4 || row 6
            if (j == 17 || j == 34) {
                x = 275;//30 less
                y = y + 56;
            }
            //row 5
            if (j == 25) {
                x = 245;//30 less
                y = y + 56;
            }
            //row 9 --last row
            if (j == 55) {
                x = 375;
                y = y + 56;
            }
        }
        return whatever;
    }

    private void exitProgram() {
        Boolean yes = ConfirmBox.display("You are exiting", "Are you sure you want to exit? " +
                "You may lose the Game");

        //ConfirmBox.display.setFont(Font.font("Verdana", FontPosture.ITALIC,10));
//        score.setFont(Fonat.font("Verdana", FontPosture.ITALIC,20));

        if (yes) {
            window.close();
        }
    }

    public Scene getScene2() {
        return scene2;
    }

    public String getSelected() {
        for (Circle circle : selection) {
//            System.out.println(circle.toString() + "ii");
            for(int i = 0; i < CurrentPs.size(); i++){
                if(circle != CurrentPs.get(i)){
                    CurrentPs.add(circle);
//                    System.out.println("why");
                }
            }
        }
        return String.valueOf(CurrentPs.size());
    }
    public Circle getSelectedz(int i) {
        for (Circle circle : selection) {
//            System.out.println(circle.toString() + "ii");
            for(int y = 0; y < CurrentPs.size(); y++){
                if(circle != CurrentPs.get(y)){
                    CurrentPs.add(circle);
                    System.out.println("why");
                }
            }
        }
        return CurrentPs.get(i);
    }
    public Move guiMove(String color){
        if(GuiIsLegal()){
            String move = null;
            String  selectedMarbleNumber = getSelected();
            String vector = keypressed;
            //get from (current marble positions)
            if(selection.size() == 1){
                double x = selection.get(0).getCenterX();
                double y = selection.get(0).getCenterY();
                move = selectedMarbleNumber+color+";"+ x+";"+y+";"+keypressed;
//                System.out.println(move);
            }
            if(selection.size()==2 && selection.get(0) != selection.get(1)){
                double x = selection.get(0).getCenterX();
                double y = selection.get(0).getCenterY();
                double x1 = selection.get(1).getCenterX();
                double y1 = selection.get(1).getCenterY();
                move = selectedMarbleNumber+color+";"+ x+";"+y+";" +x1+";"+y1+";"+keypressed;
//                System.out.println(move);

            }
            if(selection.size()==3){
                double x = selection.get(0).getCenterX();
                double y = selection.get(0).getCenterY();
                double x1 = selection.get(1).getCenterX();
                double y1 = selection.get(1).getCenterY();
                double x2 = selection.get(2).getCenterX();
                double y2 = selection.get(2).getCenterY();
                move = selectedMarbleNumber+";"+color+";"+ x+";"+y+";" +x1+";"+y1+";"+x2 +";"+y2+";"+keypressed;
//                System.out.println(move);
            }

            return null;
        }
        return null;
    }
    public Circle[] red14(){
        Circle[] redPieces = new Circle[14];
        int x_red = 375;
        int y_red = 126;
        for (int j = 0; j < redPieces.length; j++) {
            redPieces[j] = new Circle(x_red, y_red, 20, Color.BLACK);
            redPieces[j].setCursor(Cursor.HAND);
            //redPieces[j].setOnMouseDragged(drag.get());
            redPieces[j].setOnMousePressed(click);
            x_red = x_red + 65;//x coordinates
            //Row 2
            if (j == 4) {
                //5 circles made therefore shift to next row
                x_red = 345;
                y_red = y_red + 56;
            }
            //row 3
            if (j == 10) {
                x_red = 375 + 65;
                y_red = y_red + 56;
            }
        }
        return redPieces;
    }
    public Polygon makeHex(Polygon hex){
        hex.getPoints().addAll(
                340.0, 70.0,
                660.0, 70.0, //320 is side length
                820.0, 350.0,
                660.0, 630.0,
                340.0, 630.0,
                180.0, 350.0);
        hex.setFill(Color.BROWN);

        return hex;
    }
    //two player piecesinit
    public Circle[] white14(){
        Circle[] whitePieces = new Circle[14];
        int x_white = 375;   //56 y 65 x  plus / minus 30 diag
        int y_white = 126 + (56 * 8);
        for (int j = 0; j < whitePieces.length; j++) {
            whitePieces[j] = new Circle(x_white, y_white, 20, Color.WHITE);
            whitePieces[j].setCursor(Cursor.HAND);
            //whitePieces[j].setOnMouseDragged(drag.get());
            whitePieces[j].setOnMousePressed(click);
            x_white = x_white + 65;//x coordinates
            //Row 2
            if (j == 4) {
                //5 circles made therefore shift to next row
                x_white = 345;
                y_white = y_white - 56;
            }
            //row 3
            if (j == 10) {
                x_white = 375 + 65;
                y_white = y_white - 56;
            }
        }
        return whitePieces;
    }
    public Circle[] whitePieces3(){
        Circle[] whitePieces3 = new Circle[11];
        int white_x = 375 + (65 * 3);
        int white_y = 126;//65 56 diagonal is
        for (int j = 0; j < whitePieces3.length; j++) {
            whitePieces3[j] = new Circle(white_x, white_y, 20, Color.WHITE);
            whitePieces3[j].setCursor(Cursor.HAND);
            whitePieces3[j].setOnMouseDragged(drag.get());
            whitePieces3[j].setOnMousePressed(click);

            white_x = white_x + 30;
            white_y = white_y + 56;
            if (j == 5) {
                white_y = 126;
                white_x = 375 + (65 * 4);
            }
        }
        return whitePieces3;
    }
    public Circle[] blackPieces3(){
        Circle[] blackPieces3 = new Circle[11];
        int black_x = 375;//56 y ------------------   65 x
        int black_y = 126 + (56 * 8);
        for (int j = 0; j < blackPieces3.length; j++) {
            blackPieces3[j] = new Circle(black_x, black_y, 20, Color.BLACK);
            blackPieces3[j].setCursor(Cursor.HAND);
            blackPieces3[j].setOnMouseDragged(drag.get());
            blackPieces3[j].setOnMousePressed(click);

            black_x = black_x + 65;//x coordinates
            //Row 2
            if (j == 4) {
                //5 circles made therefore shift to next row
                black_x = 345;
                black_y = black_y - 56;
            }
            //row 3
            if (j == 10) {
                black_x = 375 + 65;
                black_y = black_y - 56;
            }
        }return blackPieces3;
    }
    public Circle[] redPieces3(){
        Circle[] redPieces3 = new Circle[11];
//        System.out.println("hi");
        double white_x = 375 + 65;
        double white_y = 126;
        for (int j = 0; j < redPieces3.length; j++) {
            redPieces3[j] = new Circle(white_x, white_y, 20, Color.RED);
            redPieces3[j].setCursor(Cursor.HAND);
            redPieces3[j].setOnMouseDragged(drag.get());
            redPieces3[j].setOnMousePressed(click);

            if (j == 5) {
                white_y = 126;
                white_x = 375;
            } else {
                white_x = white_x - 35;
                white_y = white_y + 56;
            }
        }
        return redPieces3;
    }
    /*
    finds value of circle in 2 player game with parameter as circle[i]
    its name at creation . retunr an integer of centre x+ centre y
    Can be used to get the position of any white circle in a 2 player game

    From this we can check if a move may be going to a position already  accupied
    from one of your own players which is illegal.

    We can also check if sumito is possible.

     */
    public Integer white14ValXY(int i,String color){
        this.color = color;
        if(color == "white"){
            String x = white14()[i].toString();
            String[] y = x.split(" ",4);
            String[] newX = y[0].split("=",2); //Centre x
            String[] newY =y[1].split("=",3);//Centre y
//        System.out.println(newX[1]);
            String[] newX2 = newX[1].split(".0,",2); //actual x val
            String[] newY2 = newY[1].split(".0,",2);//actual y val
//        System.out.println(newX2[0]+"hi"+newY2[0]);
            int res = Integer.parseInt(newX2[0] + newY2[0]);
//        System.out.println(res);
            return res;
        }
        if(color == "black"){
            String x = red14()[i].toString();
            String[] y = x.split(" ",4);
            String[] newX = y[0].split("=",2); //Centre x
            String[] newY =y[1].split("=",3);//Centre y
//        System.out.println(newX[1]);
            String[] newX2 = newX[1].split(".0,",2); //actual x val
            String[] newY2 = newY[1].split(".0,",2);//actual y val
//        System.out.println(newX2[0]+"hi"+newY2[0]);
            int res = Integer.parseInt(newX2[0] + newY2[0]);
//        System.out.println(res);
            return res;
        }
        else {
            System.out.println("ewwwwwwwwwwwwwwwwwwnooooooooo");
            return 1000;
        }
    }
    public Double selection14ValX(int i){
            String x = selection.get(i).toString();
            String[] y = x.split(" ",4);
            String[] newX = y[0].split("=",2); //Centre x
            String[] newY =y[1].split("=",3);//Centre y
//        System.out.println(newX[1]);
            String[] newX2 = newX[1].split(".0,",2); //actual x val
            String[] newY2 = newY[1].split(".0,",2);//actual y val
//        System.out.println(newX2[0]+"hi"+newY2[0]);
            double res = Double.parseDouble(newX2[0]);
//        System.out.println(res);
            return res;
    }
    public Double selection14ValY(int i){
        String x = selection.get(i).toString();
        String[] y = x.split(" ",4);
        String[] newX = y[0].split("=",2); //Centre x
        String[] newY =y[1].split("=",3);//Centre y
//        System.out.println(newX[1]);
        String[] newX2 = newX[1].split(".0,",2); //actual x val
        String[] newY2 = newY[1].split(".0,",2);//actual y val
//        System.out.println(newX2[0]+"hi"+newY2[0]);
        double res = Double.parseDouble(newY2[0]);
//        System.out.println(res);
        return res;
    }
//    public double selection14X(int i){
//             x = selection.get(i).getCenterX();
//            return x;
//    }

    /*
    Gives all values x&y coordinates which are currently occupied by a marble
    If a marble in the selected pile is going to a currenly occupied position this
    is an illegal move
     */
    public Integer[] occupied(){
        Integer[] occupied = new Integer[28];
        for (int i = 0 ; i < 14;i++){
            occupied[i] = white14ValXY(i,"white");
//            System.out.println(occupied[i]);//faggot
        }
        for (int i = 0 ,j=14 ; i < 14;i++,j++){
            occupied[j] = white14ValXY(i,"black");
//            System.out.println(occupied[i]);//faggot
        }
        occupied[2]=111;
        System.out.println(occupied[1]+occupied[2]);
        return occupied;
    }
    public void CurrentPositionsXY(){

    }
}

