package practica1.johanna.rodriguez;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        //String url= JOptionPane.showInputDialog("Escribe una url completa, iniciando http...");//Entrada de url
        String url = "https://www.pucmm.edu.do";

        try {
            Document doc = Jsoup.connect(url).get(); //Guarda el html de la pagina

            Connection.Response conexion = Jsoup.connect(url).execute();
            String cuerpo = conexion.body();

           //Apartado A
            //String[] lineas = doc.html().split("\n");
            //System.out.println("La cantidad de lineas del recurso es : " + lineas.length);
            int lineas = cuerpo.split("\n").length;
            System.out.println("La cantidad de lineas del recurso es : " + lineas);

            //Apartado B
            Elements parrafos= doc.getElementsByTag("p");
            System.out.println("La cantidad de parrafos del recurso es : " + parrafos.size());

            //Apartado C
            Elements imagenes = doc.select("p img");
            System.out.println("La cantidad de imagenes dentro de parrafos del HTML es : " + imagenes.size());

            //Apartado D
            Elements formGet = doc.select("form[method=get]");
            System.out.println("La cantidad de formularios metodo GET del HTML es : " + formGet.size());

            Elements formPost = doc.select("form[method=post]");
            System.out.println("La cantidad de formularios metodo POST del HTML es : " + formPost.size());

            //Apartado E
            for (Element input : doc.getElementsByTag("input")) {
                System.out.println(input);
            }

            //Apartado F
            for (Element form:doc.select("form[method=post]")) {
                String  URL= form.absUrl("action");
                Document doc1 = Jsoup.connect(URL)
                        .data("asignatura","practica1")
                        .header("matricula","20141811").post();
                System.out.println(doc1.body());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }

