/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testiranje;

import funkcionalnosti.Funkcionalnosti;
import java.sql.Date;
import java.util.List;
import student.mm140457;
import testovi.JavniTest;
import testovi.tajniTest;

/**
 *
 * @author stefan
 */
public class Testiranje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Funkcionalnosti funkcionalnosti = new mm140457();

        double procenata = JavniTest.test(funkcionalnosti);
        double koeficijentDomaci = 0.2;
        double koeficijentJavniTest = 0.5;
        double koeficijentTajniTestovi = 0.5;
        
        System.out.println();
        System.out.println("==============================================");
        System.out.println("Na javnom testu osvojili ste " + procenata * koeficijentDomaci * koeficijentJavniTest + " poena");
        System.out.println("==============================================");

      /*double res = tajniTest.test(funkcionalnosti);
        System.out.println("Osvojeno "+res);*/
}
    
}
