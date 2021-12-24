package models;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conector {
    static private String ruta_a = Path.of("").toAbsolutePath().toString();
    static private String RUTA = ruta_a + "/src/appdata/dbclub.db";
    
    static Integer guardar(String name, int score) throws Exception{
         //me conecto a la base de datos
        Class.forName("org.sqlite.JDBC");
        Connection conec = DriverManager.getConnection("jdbc:sqlite:"+RUTA);
        try(Statement sta = conec.createStatement()){
            int res = sta.executeUpdate("INSERT INTO usuarios(USER,PUNTAJE) VALUES('"+name+"','"+score+"')");
            if(res>0){
                conec.close();
                return 1;
            }else{
                conec.close();
                return 0;
            }
        }
    }

    static Question consulta(int level) throws Exception{

        int index = (int) Math.floor(Math.random()*5 + 1);
        Class.forName("org.sqlite.JDBC");
        System.out.println(RUTA);
        Connection conec = DriverManager.getConnection("jdbc:sqlite:"+RUTA);
        try(Statement sta = conec.createStatement()){
            ResultSet res = sta.executeQuery("SELECT * FROM preguntas where nivel = '"+level+"' AND PREGUNTA == '"+index+"'");
            if(res.next()){
                Question newQuestion = new Question(res.getString("DESCRIPCION"), res.getString("RESPUESTA1"), res.getString("RESPUESTA2"), res.getString("RESPUESTA3"), res.getString("RESPUESTA4"), res.getString("CORRECTA"));
                conec.close();
                return newQuestion;
            }
            else{
                conec.close();
                throw new Exception("Error al cargar");
            }
        }
        
    }
}
