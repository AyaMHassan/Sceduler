package com.example.demo2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriod;

public class HelloApplication extends Application {

    String[] Array = new String[4];
    String type = new String();
    Button FCFS = new Button("First come first serve");
    Button SJF_Pre = new Button("Shortest job first preemptive");
    Button SJF_non = new Button("Shortest job first non preemptive");

    Button priority_pre = new Button("Priority preemptive");
    Button priority_non = new Button("Priority non preemptive");
    Button round_robin = new Button("Round Robin");

    public void start(Stage primaryStage) throws IOException{
        StackPane layout = new StackPane();
        VBox vBox = new VBox();
        vBox.setSpacing(6);
        vBox.getChildren().add(FCFS);
        vBox.getChildren().add(SJF_Pre);
        vBox.getChildren().add(SJF_non);
        vBox.getChildren().add(priority_pre);
        vBox.getChildren().add(priority_non);
        vBox.getChildren().add(round_robin);

        layout.getChildren().add(vBox);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);


        FCFS.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    type = "FCFS";
                    Array = display();
                } catch (Exception er1) {
                    er1.printStackTrace();
                }
            }
        }
        ));

        SJF_Pre.setOnMouseClicked(e ->{
            try{
                type = "SJF_Pre";
                Array = display();
            }
            catch(Exception er1){
                er1.printStackTrace();

            }
        } );
        SJF_non.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    type = "SJF_non";
                    Array = display();
                } catch (Exception er1) {
                    er1.printStackTrace();

                }
            }
        }));
        priority_pre.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    type="priority_pre";
                    Array = display();
                } catch (Exception er1) {
                    er1.printStackTrace();

                }
            }
        }));
        priority_non.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                try {
                    type="priority_non";
                    Array = display();

                } catch (Exception er1) {
                    er1.printStackTrace();

                }
            }

        }
        ));
        round_robin.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    type="RR";
                    Array = display();
                } catch (Exception er1) {
                    er1.printStackTrace();
                }
            }

        }
        ));
        primaryStage.show();
    }


    public  String[] display() {
        String[]arr = new String[4];
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        StackPane layout = new StackPane();
        VBox vBox = new VBox();
        vBox.setSpacing(6);
        TextField  name= new TextField();
        name.setPromptText("Processes names");
        name.setFocusTraversable(false);
        TextField  burstTimes= new TextField();
        burstTimes.setPromptText("Burst times");
        burstTimes.setFocusTraversable(false);
        TextField  arrivalTimes= new TextField();
        arrivalTimes.setPromptText("Arrival times");
        arrivalTimes.setFocusTraversable(false);
        TextField  temp= new TextField();
        if(type == "priority_pre" || type == "priority_non"){
            temp.setPromptText("Priority");
        }
        else if(type == "RR"){
            temp.setPromptText("Quantum");
        }

        temp.setFocusTraversable(false);
        Button gantt = new Button("Gantt chart");
        Button avg_wait = new Button("Average Waiting time");
        if(type == "priority_pre" || type == "priority_non" || type=="RR"){
            vBox.getChildren().addAll(name,burstTimes,arrivalTimes,temp,avg_wait,gantt);
        }
        else{
            vBox.getChildren().addAll(name,burstTimes,arrivalTimes,avg_wait,gantt);
        }
        layout.getChildren().add(vBox);
        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.show();

        avg_wait.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    time_display(100);
                } catch (Exception er1) {
                    er1.printStackTrace();
                }
            }
        }
        ));
        gantt.setOnMouseClicked((new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                try {
                    gantt_display();
                }catch (Exception er1) {
                    er1.printStackTrace();
                }
            }
        }
        ));
        arr[0]=name.getText();
        arr[1]=burstTimes.getText();
        arr[2]=arrivalTimes.getText();
        arr[3]=temp.getText();
        return arr;
    }

    public  void time_display(float x) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        BorderPane layout = new BorderPane();
        window.setTitle("arrival time");
        Text t;
        t = new Text(String.valueOf(x));
        layout.setCenter(t);
        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

    }
    public  void gantt_display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

    }
    public int arrIndex(String[]arr,String s){
        int res = -1;
        for(int i = 0; i < arr.length;i++){
            if(arr[i] == s){
                res = i;
                break;
            }
        }
        return res;
    }
    private IntervalCategoryDataset createDataset(String[]arr,String[]processes, String[]duration) {

        TaskSeriesCollection dataset = new TaskSeriesCollection();
        TaskSeries[] series = new TaskSeries[arr.length];
        for(int i = 0; i < processes.length; i++){
            series[arrIndex(arr,processes[i])].add (new Task(processes[i],
                    new SimpleTimePeriod(Integer.parseInt(duration[i].substring(0,duration[i].indexOf(":"))),
                            Integer.parseInt(duration[i].substring(duration[i].indexOf(":")+1)))));
        }
        for(int i = 0; i < arr.length; i++){
            dataset.add(series[i]);
        }
        return dataset;
    }

    public static void main(String[] args) {
        launch();
    }

}
