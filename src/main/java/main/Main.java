package main;
import spark.Spark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.staticFiles;


public class Main {

    public static void main(String[] args) {

        Spark.staticFiles.location("/Plantillas");

        final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/");
		ProcessBuilder process = new ProcessBuilder();
        int port;
        if(process.environment().get("PORT")!=null){
            port = Integer.parseInt(process.environment().get("PORT")) ;
        }else{
            port=4567;
        }

        Spark.port(port);

        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

        Spark.get("/", (request, response) -> {
            Template plantillaPaginaInicio = configuration.getTemplate("Plantillas/index.ftl");
            StringWriter writer = new StringWriter();

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("estudiantes", estudiantes);
            attributes.put("tamano", estudiantes.size() > 0);
            plantillaPaginaInicio.process(attributes, writer);
            return writer;
        });

        Spark.get("/Crear", (request, response) -> {
            Template plantillaPaginaInicio = configuration.getTemplate("Plantillas/crear.ftl");
            return plantillaPaginaInicio;
        });

        Spark.post("/Guardar", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                String matricula = request.queryParams("matricula");
                String nombre = request.queryParams("nombre");
                String apellido = request.queryParams("apellido");
                String telefono = request.queryParams("telefono");
                estudiantes.add(new Estudiante(Integer.parseInt(matricula), nombre, apellido, telefono));
                response.redirect("/");
            }catch (Exception e){
                System.out.println(e);
                response.redirect("/Agregar");
            }
            return writer;
        });

        Spark.get("/Borrar/:id", (request, response) -> {
            StringWriter writer = new StringWriter();
            int id = Integer.parseInt(request.params("id"));

            estudiantes.remove(id);
            response.redirect("/");
            return writer;
        });

        Spark.get("/Editar/:id", (request, response) -> {
            Template resultTemplate = configuration.getTemplate("Plantillas/editar.ftl");
            StringWriter writer = new StringWriter();

            int id = Integer.parseInt(request.params("id"));

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("Estudiante", estudiantes.get(id));

            resultTemplate.process(atributos, writer);
            return writer;
        });

        Spark.post("/Editado", (request, response) -> {
            StringWriter writer = new StringWriter();

            try {
                String matricula = request.queryParams("matricula");
                String nombre = request.queryParams("nombre");
                String apellido = request.queryParams("apellido");
                String telefono = request.queryParams("telefono");
                Estudiante estudianteModificado = new Estudiante(Integer.parseInt(matricula), nombre, apellido, telefono);
                for (Estudiante estudiante: estudiantes)
                {
                    if(estudiante.getMatricula() == estudianteModificado.getMatricula())
                    {
                        estudiante.setNombre(estudianteModificado.getNombre());
                        estudiante.setApellido(estudianteModificado.getApellido());
                        estudiante.setMatricula(estudianteModificado.getMatricula());
                        estudiante.setTelefono(estudianteModificado.getTelefono());
                        break;
                    }
                }
                response.redirect("/");
            }catch (Exception e){
                System.out.println(" " + e.toString());
                response.redirect("/");
            }
            return writer;
        });

        Spark.get("/Detalle/:id", (request, response) -> {
            Template resultTemplate = configuration.getTemplate("Plantillas/detalle.ftl");
            StringWriter writer = new StringWriter();

            int id = Integer.parseInt(request.params("id"));

            Map<String, Object> atributos = new HashMap<>();
            atributos.put("Estudiante", estudiantes.get(id));

            resultTemplate.process(atributos, writer);
            return writer;
        });
    }
}
