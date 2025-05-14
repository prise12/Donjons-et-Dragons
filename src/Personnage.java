import java.util.Map;
import java.util.LinkedHashMap;

public class Personnage {

    protected String m_nom;
    protected Map<String, Integer> m_carac = new LinkedHashMap<>();

    public Personnage(String nom, LinkedHashMap<String, Integer> carac){
        this.m_nom = nom;
        this.m_carac = carac;
    }

}
