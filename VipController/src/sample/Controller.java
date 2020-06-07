package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.*;

public class Controller {


    @FXML
    private Button add;

    @FXML
    private Button check;

    @FXML
    private Button allButton;
    @FXML

    private Button upDate;

    @FXML
    private Label allArea;

    @FXML
    private TextField steamid;

    @FXML
    private TextField date;

    @FXML
    private Label LabelDate;

    @FXML
    private Label LabelMounth;

    @FXML
    private TextField mounth;

    @FXML
    private TextField year;

    @FXML
    private Label LabelYear;

    @FXML
    private Label vipTake;

    @FXML
    private Label vipDate;

    @FXML
    private Button save;

    @FXML
    private Map<String, Date> SteamDate = new HashMap<>();

    @FXML
    void upDate() {
        try {
            FileReader fr = new FileReader("base.txt");
            Scanner s = new Scanner(fr);
            while (s.hasNextLine()) {
                String str2 = s.nextLine();
                str2 = str2.substring(1);
                System.out.println(str2);
                int a = s.nextInt();
                System.out.println(a);
                int b = s.nextInt();
                System.out.println(b);
                int c = s.nextInt();
                System.out.println(c);
                Date date = new Date(c, b, a);
                SteamDate.put(str2, date);
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        steamid.setDisable(false);
        add.setDisable(false);
        check.setDisable(false);
        allButton.setDisable(false);
        upDate.setDisable(true);
    }


    @FXML
    void bntAdd() {
        add.setDisable(true);
        date.setDisable(false);
        mounth.setDisable(false);
        year.setDisable(false);
        save.setDisable(false);
    }

    @FXML
    void saveData() {
        try (FileWriter FW = new FileWriter("base.txt", true)) {
            String sID = steamid.getText();
            steamid.setText("");
            int d = Integer.parseInt(date.getText());
            int m = Integer.parseInt(mounth.getText());
            int y = Integer.parseInt(year.getText());
            Date dateSave = new Date(y, m, d);
            SteamDate.put(sID, dateSave);
            save.setDisable(true);
            add.setDisable(false);
            mounth.setDisable(true);
            mounth.setText("");
            year.setDisable(true);
            year.setText("");
            date.setDisable(true);
            date.setText("");
            FW.write(" " + sID + "\n" + dateSave.getDate() + "\n" + dateSave.getMonth() + "\n" + dateSave.getYear());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void Enter() {

        String str = "";
        str = str + ("     Steam                          Дата выдачи vip\n");
        for (Map.Entry<String, Date> vip2 : SteamDate.entrySet()) {
            str = str + vip2.getKey() + "      " + vip2.getValue().getDate() + ".";
            if (vip2.getValue().getMonth() == 0) {
                str = str + "12";
                str = str + "." + (vip2.getValue().getYear() - 1) + "\n";
            } else {
                str = str + vip2.getValue().getMonth() + "." + vip2.getValue().getYear() + "\n";
            }
            System.out.println("Enter2 check");
            System.out.println(SteamDate.size());
        }
        allArea.setText(str);
        int one = 0;
        for (Map.Entry<String, Date> vip2 : SteamDate.entrySet()) {
            one = one + 1;
            System.out.println(one);
            System.out.println(vip2.getKey() + "  "
                    + vip2.getValue().getDate() + "."
                    + vip2.getValue().getMonth() + "."
                    + vip2.getValue().getYear() + "\n");
        }
    }


    @FXML
    void checkSteamId() {
        String steamID = steamid.getText();
        String str = "";
        boolean light = false;
        for (Map.Entry<String, Date> vip2 : SteamDate.entrySet()) {
            if (steamID.equals(vip2.getKey())) {
                str = str + vip2.getValue().getDate() + ".";
                if (vip2.getValue().getMonth() == 0) {
                    str = str + "12";
                    str = str + "." + (vip2.getValue().getYear() - 1);
                } else {
                    str = str + vip2.getValue().getMonth() + "." + vip2.getValue().getYear();
                }
                light = true;
                vipDate.setText(str);
            }

        }
        if (!light) {
            vipDate.setText("Steam ID не найден");
        }
    }

}

