/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mizanreport;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author Minami
 */
public class Staticvar {

    public static String url = "http://192.168.1.5:556/";
    //public static String url = "http://172619802169.ip-dynamic.com:556/";
    //public static String url = "http://198.50.174.114:556/";
    public static String MAIN_URL = "http://192.168.1.5:556/";
    public static JPanel MAIN_PANE = null;
    public static JPanel POPUP_PANE = null;
    public static String getresult = "";
    public static ArrayList<Integer> stylecolum = new ArrayList<>();
    public static HashMap map_var = new HashMap();
    public static String ids = "";
    public static String idsextend_1 = "";
    public static String idsextend_2 = "";
    public static String idsextend_3 = "";
    public static String preid = "";
    public static String prelabel = "";
    public static String prevalue = "";
    public static String prevalueextended = "";
    public static String resid = "";
    public static String reslabel = "";
    public static String resvalue = "";
    public static String resvalueextended = "";
    public static boolean isupdate = false;
    public static String sfilter = "";
    public static Color globaltablecolor = Color.decode("#F2F2F2");
    public static Color globaltablecolorselect = Color.decode("#3399FF");
    public static Color globaltablecolorheader = Color.decode("#F2F2F2");
    public static Color globaltablecolortextheader = Color.decode("#F2F2F2");

}
