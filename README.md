# UmbrelaCorporation
 
link al repositorio: https://github.com/mbp4/UmbrelaCorporation.git 

Integrantes del proyecto: Miriam Blanco Ponce, Daniel Sousa Escudero y Sonia Tejero Recio 

## Proyecto 

El proyecto cuenta con los siguientes archivos:

   -> Dataset: el proyecto cuenta con un dataset relacionado con el cancer de mama y todos sus factores
    
   -> Html: el proyecto contiene un html donde se mostrará una visualización del gráfico obtenido del procesamiento de datos.

   -> Servicio: esta clase será la encargada de leer los datos del dataset, inciar los hilos de proceso y detener los mismos.

   -> Controlador: esta clase es la encargada de pasar mas funciones, haciendo uso de decoradores, al html.

   -> SpringBoot Application: será la clase inicial donde se ejecutará la aplicación.

## Servicio 

Esta clase se inicia con un "@Service" para que sea detectado por Spring.

Esta formada por los siguientes métodos:

   -> LoadDataset: se encarga de leer línea por línea el dataset y extraer los datos de la columna deseada, en este caso se ha elegido la columna 1 que representa el tamaño del tumor.

   -> StartDataGeneration: haciendo uso de executors y schedulers (multihilo) se inicia la generación de hilos con los hilos.

   -> StopDataGeneration: detiene la generación de hilos.

   -> getData: devuelve los datos leídos.

## Controlador

Esta clase se inicia con un "@Controller" para pasarlo al principal: 

Se encarga de hacer que los métodos sean detectados más tarde en el html a la hora de visualizarlo, esto lo hace haciendo uso de decoradores y de un objeto de la clase servicio.

## SpringBoot Application

Esta clase simplemente inicializa el programa pero para que muestre el html cuando esta se pone en funcionamiento hacemos un método, este se queda en espera hasta que se detecta que se ha iniciado la aplicación y redirige al usuario a la web.
