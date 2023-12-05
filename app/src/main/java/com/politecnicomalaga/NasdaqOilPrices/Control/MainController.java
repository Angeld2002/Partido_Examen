package com.politecnicomalaga.NasdaqOilPrices.Control;

import com.politecnicomalaga.NasdaqOilPrices.Model.Partido;
import com.politecnicomalaga.NasdaqOilPrices.View.MainActivity;

import java.util.LinkedList;
import java.util.List;

public class MainController {

    //SINGLETON Controller
    private static String DATA_URL;
    private static MainController mySingleController;

    private List<Partido> dataRequested;
    private static MainActivity activeActivity;
    //Comportamiento
    //Constructor
    private MainController() {
         dataRequested = new LinkedList<Partido>();
    }

    public static void setURL() {
        DATA_URL = "https://www.loteriasyapuestas.es/es/la-quiniela/escrutinios/la-quiniela-premios-y-ganadores-del-22-de-octubre-de-2023";
    }

    //Get instance
    public static MainController getSingleton() {
        if (MainController.mySingleController == null) {
            mySingleController = new MainController();
        }
        return mySingleController;
    }

    //To send data to view
    public List<Partido> getDataFromNasdaq() {
        return this.dataRequested;
    }

    public static String getDataUrl() {
        return DATA_URL;
    }
    //Called from the view
    public void requestDataFromNasdaq() {
        Peticion p = new Peticion();
        p.requestData(DATA_URL);
    }

    //Called when onResponse is OK
    public void setDataFromNasdaq(String json) {

        Respuesta answer = new Respuesta(json);
        dataRequested = answer.getData();
        //Load data on the list
        MainController.activeActivity.accessData();
    }

    public void setErrorFromNasdaq(String error) {

        //Load data on the list
        MainController.activeActivity.errorData(error);
    }


    public static void setActivity(MainActivity myAct) {
        activeActivity = myAct;
    }

}
