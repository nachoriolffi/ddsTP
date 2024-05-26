# Entrega 1
### Colaboradores
Decidimos crear una clase persona universal y dejar la distinción de "tipoPersona" a un enum, ya que en términos de comportamiento son muy similares.
Su método principal "colaborar", es el mismo para ambos casos; la única diferencia está en las formas de colaboración.

### Formas de Colaboración
Pensamos que sería apropiado tener una clase por cada forma de colaborar, dándoles un método en común para poder aplicar polimorfismo.
De esta forma estaríamos aplicando el patrón Strategy, según su forma de colaborar (estrategia) cambia su colaboración (comportamiento).

### Viandas
Decidimos crear una clase Vianda la cual no tendrá comportamiento, solo guardará información importante.

### Persona Vulnerable
Al igual que en Vianda, solo buscamos almacenar información importante, no agregamos comportamiento a la clase.

### Heladera y Local
Decidimos implementar una clase que por ahora solo se encarga de agregar Viandas.
Estas heladeras serán instancias que guarden los locales para los cuales guardamos las heladeras que tiene dentro de una lista del tipo heladeras y la dirección del local.

### Dirección y Coordenadas
Decidimos que sean clases separadas ya que una dirección asumimos que más adelante se puede expandir más en otras clases como Provincia, Localidad y demás.
Entonces, para no tener que estar usando la dirección con las coordenadas lo separamos para poder tener más GRANULARIDAD con los datos latitud y longitud en una clase aparte y sea más rápido para acceder.

### Cuestionario
Para los cuestionarios hicimos varias clases para resolverlo, donde los cuestionarios contienen las preguntas que lo forman (pudiendo agregarlas, quitarlas o modificarlas).
Cada pregunta tiene sus opciones (en caso de que sea una pregunta de opción múltiple) y tiene el tipo de pregunta, pudiendo ser que la respuesta sea un bool, un string o de respuesta simple o múltiple.
Para las respuestas lo pensamos que cada respuesta debe contener las opciones elegidas y además de qué pregunta son estas opciones.

### Personas a Cargo en Persona Vulnerable
Decidimos separarlo para poder tener TRAZABILIDAD de cuando la persona vulnerable comienza o deja de tener personas a cargo, además decidimos que los datos cantidad también deberían ir en la clase RegistroPersonaACargo.

### Adapter
Decidimos usar el patron adapter para los medios de comunicacion para poder integrarlos con algun otro servicio a ver mas adelante. Para esto tambien definimos la clase mensaje que se toma como parametro en enviar mensaje para darle un foramato al mismo y ademas decimos quien es el destinatario que seria otro colaborador.
# Entrega 2

### Tarjetas
<Justificación>

### Heladera
<Justificación>

### Técnicos
<Justificación>

### Ofrecer productos (forma de colaboración)
<Justificación>

### Intercambiar puntos
<Justificación>

### Carga masiva de colaboraciones
<Justificación>
