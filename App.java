package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

public class Main extends Application {

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
                type = "SJF_pre";
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
                    if(type == "FCFS"){
                        time_display(100);
                    }
                    else if(type == "SJF_pre"){
                        time_display(100);
                    }
                    else if(type == "SJF_non"){
                        time_display(100);
                    }
                    else if(type == "priority_pre"){
                        time_display(100);
                    }
                    else if(type == "priority_non"){
                        time_display(100);
                    }
                    else if(type == "RR"){
                        time_display(100);
                    }

                } catch (Exception er1) {
                    er1.printStackTrace();
                }
            }
        }
        ));
        gantt.setOnMouseClicked((new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                try {
                    if(type == "FCFS"){
                        gantt_display(
                                new String[]{"p1", "p2", "p3", "p4"},
                                new String[]{"p1", "p4", "p1", "p3", "p2", "p2"},
                                new String[]{"0:3", "3:6", "6:8", "8:12", "12:15", "15:18"}
                        );
                    }
                    else if(type == "SJF_pre"){
                        gantt_display(
                                new String[]{"p1", "p2", "p3", "p4"},
                                new String[]{"p1", "p4", "p1", "p3", "p2", "p2"},
                                new String[]{"0:3", "3:6", "6:8", "8:12", "12:15", "15:18"}
                        );
                    }
                    else if(type == "SJF_non"){
                        gantt_display(
                                new String[]{"p1", "p2", "p3", "p4"},
                                new String[]{"p1", "p4", "p1", "p3", "p2", "p2"},
                                new String[]{"0:3", "3:6", "6:8", "8:12", "12:15", "15:18"}
                        );
                    }
                    else if(type == "priority_pre"){
                        gantt_display(
                                new String[]{"p1", "p2", "p3", "p4"},
                                new String[]{"p1", "p4", "p1", "p3", "p2", "p2"},
                                new String[]{"0:3", "3:6", "6:8", "8:12", "12:15", "15:18"}
                        );
                    }
                    else if(type == "priority_non"){
                        gantt_display(
                                new String[]{"p1", "p2", "p3", "p4"},
                                new String[]{"p1", "p4", "p1", "p3", "p2", "p2"},
                                new String[]{"0:3", "3:6", "6:8", "8:12", "12:15", "15:18"}
                        );
                    }
                    else if(type == "RR"){
                        gantt_display(
                                new String[]{"p1", "p2", "p3", "p4"},
                                new String[]{"p1", "p4", "p1", "p3", "p2", "p2"},
                                new String[]{"0:3", "3:6", "6:8", "8:12", "12:15", "15:18"}
                        );
                    }

                }
                catch (Exception er1) {
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
    public  void gantt_display(String[]arr,String[]processes,String[]duration) throws FileNotFoundException {
        System.out.println("before");

        JFreeChart chart = ChartFactory.createGanttChart("Scheduler", "Processes", "Time", createDataset(arr,processes,duration),
                true, true, false);
        System.out.println("after");

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        try{
            ChartUtilities.saveChartAsJPEG(new File("/Users/Holmez/Desktop/os/chart.jpeg"),chart,500,300);

        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Image image = new Image(new FileInputStream("/Users/Holmez/Desktop/os/chart.jpeg"));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(25);

        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);
        Scene scene = new Scene(root, 600, 500);

        window.setScene(scene);
        window.show();

    }
    public int arrIndex(String[]arr,String s){
        int res = 0;
        for(int i = 0; i < arr.length;i++){
            if(arr[i].equals(s)){
                res = i;
                break;
            }
        }
        return res;
    }
    private IntervalCategoryDataset createDataset(String[]arr,String[]processes, String[]duration) {

        TaskSeriesCollection dataset = new TaskSeriesCollection();
        TaskSeries[] series = new TaskSeries[arr.length];
        for(int i = 0; i < arr.length; i++){
            series[i]=new TaskSeries(arr[i]);
        }
        for(int i = 0; i < processes.length; i++){
            series[arrIndex(arr,processes[i])].add (new Task(Integer.toString(i),
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
