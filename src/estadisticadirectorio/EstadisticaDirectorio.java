/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadirectorio;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
/**
 *
 * @author Augusto
 */

// un nuevo comentario de prueba
// otro comentsrio de prueba
public class EstadisticaDirectorio {
    /**
     * @param args the command line arguments
     */
    Scanner input = new Scanner(System.in);
    File archivo = new File("C:\\Users\\Augusto\\Documents\\DesAplMult\\PRO\\2daEval\\MiDir\\MiDir");        
    
    private String[] obtenerExtensiones(File myFile){
        String[] nombreArchivos = myFile.list();
        String[] extensiones = new String[nombreArchivos.length+1];
        int puntoExtension = 0;
        char caracter;
        String nombre;
        for(int i=0; i<nombreArchivos.length; i++){
            nombre = nombreArchivos[i];
            for(int c=0; c<nombre.length(); c++){
                caracter = nombre.charAt(c);
                if(caracter == '.'){
                    puntoExtension = c;
                }
            }
            extensiones[i] = nombre.substring(puntoExtension);
        }
        return extensiones;
    }
    
    private void contarArchivos(){
        int[] cantidadArchivos =  new int[4];
        ArrayList<Integer> archsPorDirect;      
        archsPorDirect = new ArrayList<>();
        //String contenidoDirectorios = "";
        String nombreDirectorios = "";
        String[] arrayDirectorios;
        String[] extensiones = obtenerExtensiones(archivo);
        String nombre;
        boolean presenciaSub = false;
        //constante que representa el número de tipos de archivos que se van a contar por directorio
        final int NUM_TIPOS_ARCHS = 4;
        if(archivo.isDirectory()){
            for(int i=0; i<extensiones.length; i++/*String nombre : obtenerExtensiones(archivo)*/){
                nombre = extensiones[i];
                switch (nombre){
                    case ".txt":
                        cantidadArchivos[0]++;
                        break;
                    case ".java":
                        cantidadArchivos[1]++;
                        break;
                    case ".class":
                        cantidadArchivos[2]++;
                        break;
                    default:
                        File archivo2 = new File("C:\\Users\\Augusto\\Documents\\DesAplMult\\PRO\\2daEval\\MiDir\\MiDir\\"+nombre);
                        if(archivo2.isDirectory()){
                            String[] nombres = archivo2.list();
                            if(nombres.length == 0){
                                cantidadArchivos[3]++;
                            }else{
                                presenciaSub = true;
                                nombreDirectorios += " "+nombre;
                                /*
                                for(String coleccionNombre : nombres){
                                contenidoDirectorios += "\n- "+coleccionNombre;
                                }
                                */
                                aumentarALSegunTipos(archsPorDirect, NUM_TIPOS_ARCHS);
                                for(String nombreArchsSub : obtenerExtensiones(archivo2)){
                                    switch(nombreArchsSub){
                                        case ".txt":
                                            //ArchsPorDirect.set(0, ArchsPorDirect.get(0+(i*NUM_TIPOS_ARCHS)));
                                            sumarALArchsPorDirect(archsPorDirect, 0, NUM_TIPOS_ARCHS, i);
                                        case ".java":
                                            sumarALArchsPorDirect(archsPorDirect, 1, NUM_TIPOS_ARCHS, i);
                                        case ".class":
                                            sumarALArchsPorDirect(archsPorDirect, 2, NUM_TIPOS_ARCHS, i);
                                        default:
                                            sumarALArchsPorDirect(archsPorDirect, 3, NUM_TIPOS_ARCHS, i);
                                    }
                                }
                            }
                        }
                        break;
                }
            }
            //lectura del contenido del directorio principal
            System.out.println("En el directorio especificado hay:\n- "+cantidadArchivos[0]+" Archivos de texto (.txt)\n- "
                    +cantidadArchivos[1]+" Archivos tipo java (.java)\n- "+cantidadArchivos[2]+" Archivos tipo clase (.class)\n- "+cantidadArchivos[3]
                    +" directorios vacíos.");
            //lectura del contenido de los subdirectorios           
            if(presenciaSub){
                arrayDirectorios = nombreDirectorios.split(" ");
                for(int i = 0; i<arrayDirectorios.length; i++){
                    String nombreDir = arrayDirectorios[i];
                    System.out.println("- Un directorio llamado \""+nombreDir+"\" que, a su vez, contiene:\n- "+
                            archsPorDirect.get(0+(i*NUM_TIPOS_ARCHS))+" Archivos de texto (.txt)\n- "+
                            archsPorDirect.get(1+(i*NUM_TIPOS_ARCHS))+" Archivos tipo java (.java)\n- "+archsPorDirect.get(2+(i*NUM_TIPOS_ARCHS))+
                            " Archivos tipo clase (.class)\n- "+archsPorDirect.get(3+(i*NUM_TIPOS_ARCHS))+" directorios.");                                    
                }
            }
        }else{
            System.out.println("La ruta especificada no es un directorio.");
        }
    }

    /*
    private void contarArchivosSub(String nombre, int[] cantidadArchivos, String nombreDirectorios, ArrayList<Integer> ArchsPorDirect) {
        File archivo2 = new File("C:\\Users\\Augusto\\Documents\\DesAplMult\\PRO\\2daEval\\MiDir\\MiDir\\"+nombre);
        if(archivo2.isDirectory()){
            String[] nombres = archivo2.list();
            if(nombres.length == 0){
                cantidadArchivos[3]++;
            }else{
                nombreDirectorios += " "+nombre;
                
                //for(String coleccionNombre : nombres){
                //contenidoDirectorios += "\n- "+coleccionNombre;
                //}
                
                generarALPorTipos(ArchsPorDirect, 4);
                for(String nombreSub : obtenerExtensiones(archivo2)){
                    
                }
            }
        }
    }
    */
    
    private void sumarALArchsPorDirect(ArrayList<Integer> ArchsPorDirect, int PosSegunTipos, int NUM_TIPOS_ARCHS ,int i) {
        ArchsPorDirect.set(PosSegunTipos, ArchsPorDirect.get(PosSegunTipos+(i*NUM_TIPOS_ARCHS)));
    }
    
    // Método que añade 'casillas' en un Array List según el número de tipos de archivo que se van a contar
    private void aumentarALSegunTipos(ArrayList<Integer> numArchivos, int numCasillas){
        for(int i = 0; i<numCasillas; i++){
            numArchivos.add(0);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        EstadisticaDirectorio ed1 = new EstadisticaDirectorio();
        ed1.contarArchivos();
    }
    
}
