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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Scene2Controller {

    public Button checkResult;
    public Button mancheSuivant;
    @FXML
    GridPane codeReponseOrdi;
    @FXML
    Label mancheLabel;
    @FXML
    Label essaiLabel;
    @FXML
    Label bPlace;
    @FXML
    Label mPlace;
    @FXML
    TextField bPlaceField;
    @FXML
    TextField mPlaceField;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private GridPane gameGrid;
    @FXML
    private GridPane scoreGrid;
    @FXML
    private GridPane defaultColors;
    @FXML
    private Button restartGame;

    private MancheHumain mancheHumain;
    private Couleur couleur;
    private int essaisCounter;
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
    private int nbManchesCourantes;
    private int[] score;
    private int essaisCounterLabel;

    public void display(int lgCode, int nbManches, int nbEssais, String couleursJeu, int nbManchesCourantes, int[] score) {
        this.lgCode = lgCode;
        this.nbManches = nbManches;
        this.nbEssais = nbEssais;
        this.couleursJeu = couleursJeu;
        this.nbCouleurs = couleursJeu.length();
        this.nbManchesCourantes = nbManchesCourantes;
        this.score = score;
        mancheLabel.setText("Manche : " + nbManchesCourantes);
        essaisCounterLabel = 1;
        essaiLabel.setText("Essai : " + essaisCounterLabel + "/" + nbEssais);


        checkResult.setVisible(true);
        mancheSuivant.setVisible(false);

        mancheHumain = new MancheHumain(lgCode, nbCouleurs);
        couleur = new Couleur(nbCouleurs, couleursJeu);

        changeColor = Color.rgb(255, 255, 255);
        addNewGamingRow();
        addNewScoreRow();
        for (int i = 0; i < couleur.getCodeColorMap().size(); i++) {
            Circle circle = new Circle();
            circle.setRadius(12);
            Color color = couleur.getCOULEURS().get(i);
            circle.setStroke(Color.rgb(0, 0, 0));
            circle.setFill(color);
            defaultColors.addColumn(i, circle);
            circle.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> changeColor = (Color) circle.getFill());
        }
    }

    private void addNewGamingRow() {
        deactivateRow();
        gameGrid.addRow(++gameRowCounter);
        for (int i = 0; i < lgCode; i++) {
            Circle circle = new Circle();
            circle.setRadius(12);
            circle.setStroke(Color.rgb(0, 0, 0));
            circle.setFill(Color.rgb(255, 255, 255));
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            gameGrid.addColumn(i, circle);
        }
    }

    private void addNewScoreRow() {
        scoreGrid.addRow(++scoreRowCounter);
        for (int i = 0; i < lgCode; i++) {
            Circle circle = new Circle();
            circle.setRadius(12);
            circle.setFill(Color.rgb(180, 180, 180));
            circle.setStroke(Color.rgb(0, 0, 0));
            scoreGrid.addColumn(i, circle);
        }
    }

    private void afficherCodeSecret() {
        codeReponseOrdi.addRow(0);
        for (int i = 0; i < lgCode; i++) {
            Circle circle = new Circle();
            circle.setRadius(12);
            Color color = couleur.getCOULEURS().get(mancheHumain.getCodeSecret()[i]);
            circle.setStroke(Color.rgb(0, 0, 0));
            circle.setFill(color);
            codeReponseOrdi.addColumn(i, circle);
        }
    }

    private void deactivateRow() {
        gameGrid.getChildren().forEach(n -> n.setDisable(true));
    }

    @FXML
    protected void mancheSuivantAction(ActionEvent event) throws IOException {
        nbManchesCourantes++;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
        root = loader.load();
        Scene3Controller scene3Controller = loader.getController();
        scene3Controller.display(lgCode, nbManches, nbEssais, couleursJeu, couleur, nbManchesCourantes, score);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void checkResultOnClick(ActionEvent event) {
        StringBuilder userSequence = new StringBuilder();
        List<Node> nodes = gameGrid.getChildren().stream().filter(c -> !c.isDisabled()).toList();

        for (int i = 0; i < lgCode; i++) {
            Circle circle = (Circle) nodes.get(i);
            Color circleColor = (Color) circle.getFill();
            if (couleur.getCOULEURS().contains(circleColor)) userSequence.append(couleur.getCOULEURS().indexOf(circleColor));
            else {
                UtMM.alertError("Veuillez choisir une couleur parmi celles proposées");
                return;
            }
        }
        essaisCounterLabel++;
        int[] result = mancheHumain.getScore(userSequence.toString());
        int bP = result[0];
        int mP = result[1];
        for (int i = 0; i < (result[0] + result[1]); i++) {
            Circle circle = (Circle) scoreGrid.getChildren().get(scoreGrid.getChildren().size() - lgCode + i);
            if (bP > 0) {
                circle.setFill(Color.BLACK);
                bP--;
            } else if (mP > 0) {
                circle.setFill(Color.WHITE);
                mP--;
            }
        }

        score[0] += 1;

        if (result[0] == lgCode) {
            UtMM.alertInfo("Vous avez gagné cette manche!");
            score[0] -= 6;
            checkResult.setVisible(false);
            mancheSuivant.setVisible(true);
            afficherCodeSecret();
            return;
        }
        if (essaisCounterLabel <= nbEssais) {
            addNewGamingRow();
            addNewScoreRow();
            essaiLabel.setText("Essai : " + essaisCounterLabel + "/" + nbEssais);
        } else {
            UtMM.alertInfo("Vous avez perdu cette manche!");
            checkResult.setVisible(false);
            mancheSuivant.setVisible(true);
            afficherCodeSecret();
        }

    }
}
