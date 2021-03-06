package models;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class Conector {
    static private String ruta_a = Path.of("").toAbsolutePath().toString();
    static private String RUTA = ruta_a + "/src/data/gameDb.db";
    /**
     * Descripción: Metodo para guardar el resultado de un juego en la base de datos.
     * @param name Nombre del usuario de la partida a guardar.
     * @param score Puntaje de la partida a guardar.
     * @return res retorna un 1 si guardo de manera exitosa o 0 si tiene algun problema.
     * @throws Exception
     */
    static Integer guardar(String name, int score) throws Exception{
        LocalDate date = LocalDate.now();
         //me conecto a la base de datos
        Class.forName("org.sqlite.JDBC");
        Connection conec = DriverManager.getConnection("jdbc:sqlite:"+RUTA);
        try(Statement sta = conec.createStatement()){
            int res = sta.executeUpdate("INSERT INTO usuarios(USER,PUNTAJE,FECHA) VALUES('"+name+"','"+score+"','"+date+"')");
            if(res>0){
                conec.close();
                return 1;
            }else{
                conec.close();
                return 0;
            }
        }
    }
    /**
     * Descripción: Metodo para traer una pregunta aleatoria de la base de datos. 
     * @param level Nivel de la pregunta a consultar
     * @return newQuestion retorna un objeto de la clase Question.  
     * @throws Exception
     */
    static Question consulta(int level) throws Exception{
        int index = (int) Math.floor(Math.random()*5 + 1);
        Class.forName("org.sqlite.JDBC");
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
    /**
     * Descripción: Metodo para traer el top 5 de jugadores ordenados por el resultado.
     * @return players retorna un arreglo de objetos de las clase Player
     * @throws Exception
     */
    static public ArrayList<Player> top() throws Exception{
        ArrayList<Player> players = new ArrayList<Player>();
        int counter = 1;
        Class.forName("org.sqlite.JDBC");
        Connection conec = DriverManager.getConnection("jdbc:sqlite:"+RUTA);
        try(Statement sta = conec.createStatement()){
            ResultSet res = sta.executeQuery("SELECT * FROM usuarios ORDER BY puntaje DESC, fecha DESC");
            while(res.next() && counter<=5){
                Player player = new Player(res.getString("USER"),res.getInt("puntaje"));
                players.add(player);
                counter+=1;
            }
            conec.close();
            return players;
        }
    }
}
