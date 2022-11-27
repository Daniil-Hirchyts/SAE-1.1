package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Scene1Controller {
    @FXML
    public CheckBox rCheckbox;
    @FXML
    public CheckBox bCheckbox;
    @FXML
    public CheckBox vCheckbox;
    @FXML
    public CheckBox jCheckbox;
    @FXML
    public CheckBox oCheckbox;
    @FXML
    public CheckBox nCheckbox;
    @FXML
    public Pane paneContainer;
    public Label title;
    @FXML
    TextField longueurCodeField;
    @FXML
    TextField nbrManchesField;
    @FXML
    TextField nbrEssaisField;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int checkFields;
    private Couleur couleur;
    private String couleursJeu = "";
    public void jouer(ActionEvent event) throws IOException {

        int lgCode;
        int nbManches;
        int nbEssais;

        if (Objects.equals(longueurCodeField.getText(), "")) lgCode = 0;
        else lgCode = Integer.parseInt(longueurCodeField.getText());
        if (Objects.equals(nbrManchesField.getText(), "")) nbManches = 0;
        else nbManches = Integer.parseInt(nbrManchesField.getText());
        if (Objects.equals(nbrEssaisField.getText(), "")) nbEssais = 0;
        else nbEssais = Integer.parseInt(nbrEssaisField.getText());

        checkFields = 0;

        if (lgCode >= 4 && lgCode <= 6) checkFields++;
        else UtMM.alertError("La longueur du code doit être comprise entre 4 et 6");

        if (nbManches >= 2 && nbManches % 2 == 0) checkFields++;
        else UtMM.alertError("Le nombre de manches doit être un nombre pair supérieur ou égal à 2");

        if (nbEssais >= 4 && nbEssais <= 12) checkFields++;
        else UtMM.alertError("Le nombre d'essais doit être compris entre 4 et 12");

        if (couleursJeu.length() >= 4 && couleursJeu.length() <= 6) checkFields++;
        else UtMM.alertError("Le nombre de couleurs doit être compris entre 4 et 6");

        if (checkFields == 4) startGame(lgCode, nbManches, nbEssais, couleursJeu, event);
    }

    public void startGame(int lgCode, int nbManches, int nbEssais, String couleursJeu, ActionEvent event) throws IOException {

        couleur = new Couleur(couleursJeu.length(), couleursJeu);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        root = loader.load();
        Scene2Controller scene2Controller = loader.getController();
        scene2Controller.display(lgCode, nbManches, nbEssais, couleursJeu, 1, new int[]{0, 0});

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void addColors(ActionEvent event) {
        if (rCheckbox.isSelected() && !couleursJeu.contains("r")) couleursJeu += "r";
        else if (!rCheckbox.isSelected()) couleursJeu = couleursJeu.replace("r", "");

        if (bCheckbox.isSelected() && !couleursJeu.contains("b")) couleursJeu += "b";
        else if (!bCheckbox.isSelected()) couleursJeu = couleursJeu.replace("b", "");

        if (vCheckbox.isSelected() && !couleursJeu.contains("v")) couleursJeu += "v";
        else if (!vCheckbox.isSelected()) couleursJeu = couleursJeu.replace("v", "");

        if (jCheckbox.isSelected() && !couleursJeu.contains("j")) couleursJeu += "j";
        else if (!jCheckbox.isSelected()) couleursJeu = couleursJeu.replace("j", "");

        if (oCheckbox.isSelected() && !couleursJeu.contains("o")) couleursJeu += "o";
        else if (!oCheckbox.isSelected()) couleursJeu = couleursJeu.replace("o", "");

        if (nCheckbox.isSelected() && !couleursJeu.contains("n")) couleursJeu += "n";
        else if (!nCheckbox.isSelected()) couleursJeu = couleursJeu.replace("n", "");

    }

}
