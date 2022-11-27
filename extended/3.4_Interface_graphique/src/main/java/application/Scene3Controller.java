package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Scene3Controller {

    public Button setCode;
    public Button checkResult;
    public Button mancheSuivant;
    public GridPane codeHumain;
    public GridPane choixHumainCode;
    public GridPane gameGridOrdi;
    @FXML
    Pane paneContainer;
    @FXML
    Label essaiLabel;
    @FXML
    Label mancheLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private GridPane gameGrid;
    @FXML
    private GridPane defaultColors;

    private MancheOrdinateur mancheOrdinateur;
    private Couleur couleur;
    private Couleur checkCouleur;
    private Color changeColor;
    private final EventHandler<MouseEvent> eventHandler = (e) -> {
        Circle circle = (Circle) e.getSource();
        circle.setFill(changeColor);
    };
    private int gameRowCounter = 0;
    private int scoreRowCounter = 0;
    private int lgCode;
    private int nbManches;
    private int nbEssais;
    private int nbCouleurs;
    private String couleursJeu;
    private int[] codeOrdi;
    private int[] rep;
    private int nbManchesCourantes;
    private int[] score;
    private int essaisCounterLabel;

    public void display(int lgCode, int nbManches, int nbEssais, String couleursJeu, Couleur couleur, int nbManchesCourantes, int[] score) {
        this.lgCode = lgCode;
        this.nbManches = nbManches;
        this.nbEssais = nbEssais;
        this.couleursJeu = couleursJeu;
        this.nbCouleurs = couleursJeu.length();
        this.couleur = couleur;
        this.nbManchesCourantes = nbManchesCourantes;
        this.score = score;

        mancheLabel.setText("Manche : " + nbManchesCourantes);
        essaisCounterLabel = 1;
        essaiLabel.setText("Essai : " + essaisCounterLabel + "/" + nbEssais);

        checkCouleur = new Couleur();

        changeColor = Color.rgb(255, 255, 255);
        codeHumain.addRow(1);
        for (int i = 0; i < lgCode; i++) {
            Circle circle = new Circle();
            circle.setRadius(12);
            circle.setStroke(Color.rgb(0, 0, 0));
            circle.setFill(Color.rgb(255, 255, 255));
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            codeHumain.addColumn(i, circle);
        }
        for (int i = 0; i < couleur.getCodeColorMap().size(); i++) {
            Circle circle = new Circle();
            circle.setRadius(12);
            Color color = couleur.getCOULEURS().get(i);
            circle.setStroke(Color.rgb(0, 0, 0));
            circle.setFill(color);
            choixHumainCode.addColumn(i, circle);
            circle.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> changeColor = (Color) circle.getFill());
        }
        checkResult.setVisible(false);
        mancheSuivant.setVisible(false);

//        UtMM.alertInfo("Choisissez le code secret pour l'ordinateur");
    }

    private void addNewGamingRow() {
        deactivateRow();
        gameGrid.addRow(++gameRowCounter);
        for (int i = 0; i < lgCode; i++) {
            Circle circle = new Circle();
            circle.setRadius(12);
            circle.setStroke(Color.rgb(0, 0, 0));
            circle.setFill(Color.rgb(180, 180, 180));
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            gameGrid.addColumn(i, circle);
        }
    }

    private void addNewGamingOrdiRow() {
        gameGridOrdi.addRow(++scoreRowCounter);
        for (int i = 0; i < lgCode; i++) {
            Circle circle = new Circle();
            circle.setRadius(12);
            Color color = couleur.getCOULEURS().get(codeOrdi[i]);
            circle.setStroke(Color.rgb(0, 0, 0));
            circle.setFill(color);
            gameGridOrdi.addColumn(i, circle);
        }
    }

    private void deactivateRow() {
        gameGrid.getChildren().forEach(n -> n.setDisable(true));
    }

    @FXML
    protected void mancheSuivantAction(ActionEvent event) throws IOException {
        nbManchesCourantes++;
        gameRowCounter = 0;
        scoreRowCounter = 0;
        gameGrid.getChildren().clear();

//        addNewGamingRow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        root = loader.load();
        Scene2Controller scene2Controller = loader.getController();
        scene2Controller.display(lgCode, nbManches, nbEssais, couleursJeu, nbManchesCourantes, score);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void checkResultOnClick(ActionEvent event) {

        essaisCounterLabel++;
        essaiLabel.setText("Essai : " + essaisCounterLabel + "/" + nbEssais);

        List<Node> nodes = gameGrid.getChildren().stream().filter(c -> !c.isDisabled()).toList();

        rep = new int[]{0, 0};

        for (int i = 0; i < lgCode; i++) {
            Circle circle = (Circle) nodes.get(i);
            Color circleColor = (Color) circle.getFill();
            switch (checkCouleur.getCHECK_COULEURS().indexOf(circleColor)) {
                case 0 -> rep[0]++;
                case 1 -> rep[1]++;
                default -> {
                }
            }
        }
        mancheOrdinateur.setReponse(rep);
        mancheOrdinateur.newCode();
        for (int i = 0; i < lgCode; i++) codeOrdi[i] = mancheOrdinateur.getCodeSecret()[i];

        score[1] += 1;

        if (rep[0] == lgCode) {
            UtMM.alertInfo("L'ordinateur a gagné cette manche!");
            checkResult.setVisible(false);
            mancheSuivant.setVisible(true);
            score[1] -= 6;
        }

        if ((rep[0] == lgCode && nbManchesCourantes == nbManches) || (essaisCounterLabel > nbEssais)) {
            if (score[0] < score[1]) UtMM.alertInfo("Vous avez gagné");
            else if (score[0] > score[1]) UtMM.alertInfo("L'ordinateur a gagné");
            else UtMM.alertInfo("Egalité");
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            return;
        }


        if (rep[0] == lgCode) {
            return;
        }

        if (essaisCounterLabel <= nbEssais) {
            addNewGamingRow();
            addNewGamingOrdiRow();
        }
    }

    @FXML
    public void setCodeSecret(ActionEvent actionEvent) {

        codeOrdi = new int[lgCode];
        for (int i = 0; i < lgCode; i++) codeOrdi[i] = 0;

        StringBuilder userSequence = new StringBuilder();
        List<Node> nodes = codeHumain.getChildren().stream().filter(c -> !c.isDisabled()).toList();
        for (int i = 0; i < lgCode; i++) {
            Circle circle = (Circle) nodes.get(i);
            Color circleColor = (Color) circle.getFill();
            if (!couleur.getCOULEURS().contains(circleColor)) {
                UtMM.alertInfo("Veuillez choisir une couleur pour chaque case");
                return;
            } else userSequence.append(couleur.getCOULEURS().indexOf(circleColor));
        }
        checkResult.setVisible(true);
        setCode.setVisible(false);
        choixHumainCode.setVisible(false);
        addNewGamingRow();
        addNewGamingOrdiRow();
        for (int i = 0; i < checkCouleur.getCodeCheckColorMap().size(); i++) {
            Circle circle = new Circle();
            circle.setRadius(12);
            Color color = checkCouleur.getCHECK_COULEURS().get(i);
            circle.setStroke(Color.rgb(0, 0, 0));
            circle.setFill(color);
            defaultColors.addColumn(i, circle);
            circle.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> changeColor = (Color) circle.getFill());
        }
        mancheOrdinateur = new MancheOrdinateur(lgCode, nbCouleurs, nbEssais);

        essaiLabel.setText("Essai : " + essaisCounterLabel + "/" + nbEssais);
    }
}
