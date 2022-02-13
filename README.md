# Tarea Reservas de Aulas
## Profesor: Francisco Enrique Balsalobre Salinas
## Alumno:	José Antonio Del Rey Martínez

Desde el IES Al-Ándalus nos comentan que debemos tener en cuenta los siguientes aspectos:

- Las aulas se pueden reservar para una permanencia por tramo (de mañana o de tarde) o para una permanencia por horas. La permanencia por horas se hará por horas en punto y no serán anteriores a las 8:00h ni posteriores a las 22:00h.
- Si para un día se realiza una reserva con permanencia por tramo, para ese día no se podrán hacer reservas por horas y viceversa.
- Las aulas deben tener información sobre el número de puestos de cada una.
- Las reservas no se pueden realizar para el mes en curso (sólo para el mes que viene o posteriores).
- Tampoco podemos anular una reserva a no ser que sea para el mes siguiente o posteriores.
- En el centro se lleva un sistema de puntos que cada profesor gasta al hacer una reserva:
    - Una permanencia por tramo restará 10 puntos.
    - Una permanencia por hora restará 3 puntos.
    - Un aula restará 0,5 puntos por el número de puestos del aula.
    - Una reserva restará la suma del número de puntos de la permanencia más el número de puntos del aula.
    - Un profesor tiene disponibles cada mes 200 puntos por lo que si cuando va a realizar una reserva el número de puntos gastados ese mes más el número de puntos de la reserva que quiere realizar supera ese límite no dejará realizar la reserva.
    
Por tanto, en este **tercer spring** abarcaremos todos estos requisitos. Para ello se propone seguir el siguiente diagrama de clases:   

![Diagrama de clases para reservasaulas](https://github.com/andresrubiodelrio/ReservasAulas-v2/blob/main/src/main/resources/reservasaulas.png)

He subido a GitHub un esqueleto de proyecto gradle que ya lleva incluidos todos los test necesarios que el programa debe pasar. Dichos test están todos comentados y deberás ir descomentándolos conforme vayas avanzando con la tarea. La URL del repositorio es en la que te encuentras.

Por tanto, tu tarea va a consistir en completar los siguientes apartados:

1. Lo primero que debes hacer es realizar un **fork** del repositorio donde he colocado el proyecto gradle con la estructura del proyecto y todos los test necesarios.
2. Clona tu repositorio remoto recién copiado en github a un repositorio local que será donde irás realizando lo que a continuación se te pide. Añade tu nombre al fichero `README.md` en el apartado "Alumno". Haz tu primer commit.
3. Haz las modificaciones necesarias en la clase `Aula` para incluir el atributo puestos e implementar el método **getPuntos**. Haz un commit.
4. Haz las modificaciones necesarias en la clase `Profesor` para que dos profesores sean iguales si tienen el mismo correo. Además, añade un nuevo método llamado **getProfesorFicticio** que devuelve un profesor a partir de un correo del mismo.
5. Modifica la clase `Reserva` para añadir un método **getReservaFicticia** que a través de un aula y de una permanencia recibidas como parámetros, obtenga un profesor ficticio y devuelve una reserva.
6. Convierte la clase `Permanencia` en una clase abstracta y haz que el método **getPuntos** sea abstracto. Esta clase sólo tendrá como atributo el día de la permanencia. Haz un commit.
7. Crea la clase `PermanenciaPorTramo` que herede de `Permanencia`, que implemente el método **getPuntos** y que posea el atributo **tramo**. Haz un commit.
8. Crea la clase `PermanenciaPorHora` que herede de `Permanencia`, que implemente el método **getPuntos** y que posea el atributo **hora**. Esta clase debe contemplar los requisitos expuestos en el enunciado para las horas. Haz un commit.
9. Haz las modificaciones necesarias en la clase Reserva para que
    - Un aula se pueda reservar por un profesor para una permanencia por tramo o por horas y que implemente el método **getPuntos**. Haz un commit.
    - Se tengan en cuenta las restricciones comentadas en el enunciado sobre no poder reservar aulas para el mes en curso y que no se sobrepase el límite de puntos de un profesor para el mes en el que quiere realizar la reserva. Haz un commit.
10. Haz las modificaciones necesarias en la clase `Consola` para que se incluyan los métodos descritos a continuación:
    - **leerProfesorFicticio**, que a partir de un correo obtendrá un profesor. Haz un commit.
    - **leerNumeroPuestos**, que lee el número de puestos que va a tener un aula. Haz un commit.
    - **leerAulaFicticia**, que a partir de un nombre obtendrá un objeto de tipo Aula. Haz un commit.
    - **leerHora**, que pedirá al usuario una hora en formato HH:mm de la que se hará uso en el caso de leer una permanencia por hora. Haz un commit.
    - **elegirPermanencia**, que permite elegir entre una permanencia por tramo o por hora. Haz un commit.
    - **leerPermanencia**, que en función de la permanencia elegida  por el usuario, devolverá una permanencia de tipo tramo o de tipo hora, pidiendo toda la información necesaria al usuario para tal fin. Haz un commit.
    - **leerReserva**, que a partir de un aula ficticia, de un profesor ficticio y de una permanencia, devuelve una reserva. Haz un commit.
    - **leerReservaFicticia**, que a partir de un aula ficticia y de una permanencia, devuelve reserva ficticia. Haz un commit.
11. Haz las modificaciones necesarias en la clase `Vista` para que:
    - Desde el método **realizarReserva** se haga uso del método **leerReserva** de la clase `Consola`, eliminando el método **leerReserva** de la clase Vista. Haz un commit.
    - Desde el método **anularReserva** se haga uso del método **leerReservaFicticia** de la clase `Consola`. Haz un commit.
    - Elimina el método **listarReservasPermanencia** ya que esta información puede ser obtenida a través de **listarReservas**. Haz un commit.
12. Modifica el Enum `Opcion` ya que la opción **listarReservasPermanencia** no estará disponible. Haz un commit.
13. Crea las interfaces para el **controlador, la vista y el modelo** y haz que se utilicen desde la aplicación principal. Haz un commit.

###### Se valorará:
- La nomenclatura del repositorio de GitHub y del archivo entregado sigue las indicaciones de entrega.
- La indentación debe ser correcta en cada uno de los apartados.
- El nombre de las variables debe ser adecuado.
- Se debe utilizar la clase `Entrada` para realizar la entrada por teclado.
- El proyecto debe pasar todas las pruebas que van en el esqueleto del mismo y toda entrada del programa será validada para evitar que el programa termine abruptamente debido a una excepción.
- Se deben utilizar los comentarios adecuados.
- Se valorará la corrección ortográfica tanto en los comentarios como en los mensajes que se muestren al usuario.
