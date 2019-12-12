
|Refranes | ![Metrica](https://github.com/metricalab/refranes/blob/master/src/main/resources/static/metricaLogo.jpg) |
|-------|--------|

Refranes es un proyecto tipo CRUD que permite consultar los refranes guardados en memoria así como en base de datos. Es un proyecto que tiene fundamentalmente una misión didáctica. 

## Tecnologías/librerías

Existen multitud de tecnologías y conceptos incluidos en el código entre las que destacan:

 * Proyecto Maven para Java 8/11 ó superior
 * Spring 5 con SpringBoot 2.2
 * Multitud de notaciones Spring (Autowired, Qualifier, RestController...)
 * Spring Data
 * Excepciones mediante ExceptionHandler
 * Expresiones lambda en Java
 * Optional Java
 * Patrones de diseño y de arquitectura (DAO, DTO...)
 * Generación de logs

## Descarga del proyecto

Para descargar el proyecto la forma más cómoda es disponer de git (con git bash instalado). Es necesario abrir una consola y lanzar el siguiente comando:

```
git clone https://github.com/metricalab/refranes.git
```

Una vez descargado el código se podrá importar este mediante el IDE Eclipse o STS (Spring Tool Suite). 

## Importar el proyecto

Es un proyecto maven luego es necesario utilizar la opción: **Import -> Existing Maven Projects** y seleccionar la carpeta **refranes** que es la carpeta contiene todo el código del proyecto.

## Antes de lanzar el proyecto

Es necesario disponer de una base de datos para MariaDB que corra en localhost. La aplicación intentará conectar a la base de datos con las siguientes credenciales: 

| Propiedad | Valor |
|-------------------------------|-------------------------------------------------|
| Cadena de conexión utilizada: | jdbc:mysql://localhost/db_refranes?useSSL=false |
| Nombre de base de datos:      | db_refranes                                     |
| Usuario de la bbdd:           | root                                            |
| Password de la bbdd:          | metrica123                                      |

Si se desea cambiar alguno de estos datos es necesario modificar el fichero **application.yml** . Este fichero se encuentra en la ruta:
```
../refranes/src/main/resources
```
En la sección
```
spring:
  datasource:
```
Se encuentran los valores de conexión.



