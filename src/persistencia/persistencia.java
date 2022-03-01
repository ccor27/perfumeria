package persistencia;

import model.Perfumeria;

public class persistencia {

	 public static final String RUTA_ARCHIVO_MODELO_PERFUMERIA_XML = "src/resource/model.xml";
	
	    public static void guardarRecursoPerfumeriaXML(Perfumeria perfumeria) {

	        try {
	            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PERFUMERIA_XML, perfumeria);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
    
    public static Perfumeria cargarRecursoPerfumeriaXML() {

    	Perfumeria perfumeria = null;

        try {
        	perfumeria = (Perfumeria) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PERFUMERIA_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return perfumeria;

    }
}
