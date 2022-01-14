/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

/**
 *
 * @author USUARIO
 */
public class UtilGestion {
    
    public static String getStringFecha(String dia, String mes, String anio) {
        int d, m, a;
        try {
            d=Integer.parseInt(dia.trim());
            m=Integer.parseInt(mes.trim());
            a=Integer.parseInt(anio.trim());
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        
        return getStringFecha(d,m,a);
    }
    
    public static String getStringFecha(int dia, int mes, int anio) {
        Fecha fecha;
        
        fecha = getFecha(dia, mes, anio);
        
        if(fecha==null)
            return null;
        
        return fecha.toString();
    }
    
    public static Fecha getFecha(String dia, String mes, String anio) {
        int d, m, a;
        try {
            d=Integer.parseInt(dia.trim());
            m=Integer.parseInt(mes.trim());
            a=Integer.parseInt(anio.trim());
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        
        return getFecha(d,m,a);
    }
    
    public static Fecha getFecha(int dia, int mes, int anio) {
//        System.out.println(""+dia+"/"+mes+"/"+anio);
        if(anio<1900 || anio>3000) {
//            System.out.println("1");
            return null;
        }
        
        if(mes<1 || mes>12) {
//            System.out.println("2");
            return null;
        }
            
        
        if(dia<1 || dia>31) {
//            System.out.println("3");
            return null;
        }
        
        if(mes==2 && dia>29) {
//            System.out.println("4");
            return null;
        }
        
        if(mes==4 || mes==6 || mes==9 || mes==11)
            if(dia>30){
//                System.out.println("5");
                return null;
            }
            
        return new Fecha(dia,mes,anio);
    }
    public static boolean isInFecha(Fecha fecha, Fecha fechaDesde, Fecha fechaHasta) {
        return isInFecha(fecha.getDia(),fecha.getMes(),fecha.getAnio(),
                         fechaDesde.getDia(),fechaDesde.getMes(),fechaDesde.getAnio(),
                         fechaHasta.getDia(),fechaHasta.getMes(),fechaHasta.getAnio());
        
    }
    
    
    public static boolean isInFecha(int dia, int mes, int anio, int diaDesde, int mesDesde, int anioDesde, int diaHasta, int mesHasta, int anioHasta) {
        if(anio<anioDesde) {
            return false;
        } else if(anio==anioDesde) {
            if(mes<mesDesde) {
                return false;
            } else if(mes==mesDesde) {
                if(dia<diaDesde) {
                    return false;
                }
            }
        }
        
        if(anio>anioHasta) {
            return false;
        } else if(anio==anioHasta) {
            if(mes>mesHasta) {
                return false;
            } else if(mes==mesHasta) {
                if(dia>diaHasta) {
                    return false;
                }
            } 
        }
        return true;
    }
    
    public static double round(double value) {
        double intValue;
        double decValue;
        
        intValue = Math.rint(value);
        decValue = Math.rint(100*(value - intValue));
        return intValue + decValue/100.0;
    }
    
//    public static String stringRound(double value) {
//        double intValue;
//        double decValue;
//        
//        intValue = Math.rint(value);
//        decValue = Math.rint(100*(value - intValue));
////        return intValue + "." + decValue;
//        
//        return "123";
//    }

}
