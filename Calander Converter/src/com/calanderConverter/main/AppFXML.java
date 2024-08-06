package com.calanderConverter.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.InputMethodEvent;

public class AppFXML {
    private boolean affect = true;

    @FXML
    private TextField DDEC, MMEC, YYEC, DDGC, MMGC, YYGC;


    @FXML
    public void initialize() {
        Converter converter = new Converter();
        TextFormatter<String> dayFormatterDDEC = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            boolean valid = false;
            int[] date = {1,1,1};
            if (newText.isEmpty()) {
                valid = true;
            } else {
                date[0] = Integer.parseInt(newText);
                date[1] = Integer.parseInt(MMEC.getText());
                date[2] = Integer.parseInt(YYEC.getText());
                valid = converter.checkValidity(date, true);
            }
            if (newText.isEmpty() || (newText.matches("\\d{0,2}") && valid)) {
                return change;
            } else {
                return null;
            }
        });
        TextFormatter<String> dayFormatterDDGC = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            boolean valid = false;
            int[] date = {1,1,1};
            if (newText.isEmpty()) {
                valid = true;
            } else {
                date[0] = Integer.parseInt(newText);
                date[1] = Integer.parseInt(MMGC.getText());
                date[2] = Integer.parseInt(YYGC.getText());
                valid = converter.checkValidity(date, false);
            }
            if (newText.isEmpty() || (newText.matches("\\d{0,2}") && valid)) {
                return change;
            } else {
                return null;
            }
        });

        // TextFormatter for month fields (MMEC, MMGC)
        TextFormatter<String> monthFormatterMMEC = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            boolean valid = false;
            int[] date = {1,1,1};
            if (newText.isEmpty()) {
                valid = true;
            } else {
                date[0] = Integer.parseInt(DDEC.getText());
                date[1] = Integer.parseInt(newText);
                date[2] = Integer.parseInt(YYEC.getText());
                valid = converter.checkValidity(date, true);
            }
            if (newText.isEmpty() || (newText.matches("\\d{0,2}") && valid)) {
                return change;
            } else {
                return null;
            }
        });

        TextFormatter<String> monthFormatterMMGC = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            boolean valid = false;
            int[] date = {1,1,1};
            if (newText.isEmpty()) {
                valid = true;
            } else {
                date[0] = Integer.parseInt(DDGC.getText());
                date[1] = Integer.parseInt(newText);
                date[2] = Integer.parseInt(YYGC.getText());
                valid = converter.checkValidity(date, false);
            }
            if (newText.isEmpty() || (newText.matches("\\d{0,2}") && valid)) {
                return change;
            } else {
                return null;
            }
        });

        // TextFormatter for year fields (YYEC, YYGC)
        TextFormatter<String> yearFormatterYYEC = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            boolean valid = false;
            int[] date = {1,1,1};
            if (newText.isEmpty()) {
                valid = true;
            } else {
                date[0] = Integer.parseInt(DDEC.getText());
                date[1] = Integer.parseInt(MMEC.getText());
                date[2] = Integer.parseInt(newText);
                valid = converter.checkValidity(date, true);
            }
            if (newText.isEmpty() || (newText.matches("\\d{0,4}") && valid)) {
                return change;
            } else {
                return null;
            }
        });

        TextFormatter<String> yearFormatterYYGC = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            boolean valid = false;
            int[] date = {1,1,1};
            if (newText.isEmpty()) {
                valid = true;
            } else {
                date[0] = Integer.parseInt(DDGC.getText());
                date[1] = Integer.parseInt(MMGC.getText());
                date[2] = Integer.parseInt(newText);
                valid = converter.checkValidity(date, false);
            }
            if (newText.isEmpty() || (newText.matches("\\d{0,4}") && valid)) {
                return change;
            } else {
                return null;
            }
        });

        // Apply TextFormatters
        DDEC.setTextFormatter(dayFormatterDDEC);
        MMEC.setTextFormatter(monthFormatterMMEC);
        YYEC.setTextFormatter(yearFormatterYYEC);
        DDGC.setTextFormatter(dayFormatterDDGC);
        MMGC.setTextFormatter(monthFormatterMMGC);
        YYGC.setTextFormatter(yearFormatterYYGC);

        int[] date = {1, 1, 2000};

        DDGC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String check = DDGC.getText();
                if(!(check.isEmpty())){
                    if (affect) {
                    date[0] = Integer.parseInt(DDGC.getText());
                    date[1] = Integer.parseInt(MMGC.getText());
                    date[2] = Integer.parseInt(YYGC.getText());
                        Converter converter = new Converter();
                        int[] newDate = converter.convert(date, true);
                        setText(date, true);
                    }


                }


            }
        });
        MMGC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String check = MMGC.getText();
                if(!(check.isEmpty())){
                    if (affect) {
                        date[0] = Integer.parseInt(DDGC.getText());
                        date[1] = Integer.parseInt(MMGC.getText());
                        date[2] = Integer.parseInt(YYGC.getText());
                        Converter converter = new Converter();
                        int[] newDate = converter.convert(date, true);
                        setText(date, true);
                    }


                }


            }
        });
        YYGC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String check = YYGC.getText();
                if(!(check.isEmpty())){
                    if (affect) {
                        date[0] = Integer.parseInt(DDGC.getText());
                        date[1] = Integer.parseInt(MMGC.getText());
                        date[2] = Integer.parseInt(YYGC.getText());
                        Converter converter = new Converter();
                        int[] newDate = converter.convert(date, true);
                        setText(date, true);
                    }


                }


            }
        });

        DDEC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String check = DDEC.getText();
                if(!(check.isEmpty())){
                    if (affect) {
                        date[0] = Integer.parseInt(DDEC.getText());
                        date[1] = Integer.parseInt(MMEC.getText());
                        date[2] = Integer.parseInt(YYEC.getText());
                        Converter converter = new Converter();
                        int[] newDate = converter.convert(date, false);
                        setText(newDate, false);
                    }


                }


            }
        });

        MMEC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String check = MMEC.getText();
                if(!(check.isEmpty())){
                    if (affect) {
                        date[0] = Integer.parseInt(DDEC.getText());
                        date[1] = Integer.parseInt(MMEC.getText());
                        date[2] = Integer.parseInt(YYEC.getText());
                        Converter converter = new Converter();
                        int[] newDate = converter.convert(date, false);
                        setText(newDate, false);
                    }


                }


            }
        });

        YYEC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String check = YYEC.getText();
                if(!(check.isEmpty())){
                    if (affect) {
                        date[0] = Integer.parseInt(DDEC.getText());
                        date[1] = Integer.parseInt(MMEC.getText());
                        date[2] = Integer.parseInt(YYEC.getText());
                        Converter converter = new Converter();
                        int[] newDate = converter.convert(date, false);
                        setText(newDate, false);
                    }


                }


            }
        });

        //set method to avoid infinite loop

        // Repeat for other TextFields if needed
    }

    public void converter(InputMethodEvent inputMethodEvent) {
    }
    public void setText(int[] newDate, boolean ECDate) {
        affect = false;
        if (ECDate) {
            DDEC.setText(String.valueOf(newDate[0]));
            MMEC.setText(String.valueOf(newDate[1]));
            YYEC.setText(String.valueOf(newDate[2]));
            affect = true;
        } else {
            DDGC.setText(String.valueOf(newDate[0]));
            MMGC.setText(String.valueOf(newDate[1]));
            YYGC.setText(String.valueOf(newDate[2]));
            affect = true;
        }
    }
}
