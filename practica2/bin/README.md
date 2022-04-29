# API-REST

## Descripción

Proyecto de spring boot 4 que consume diferentes servicios desde la base de datos. 

# Versión (2.0.0) 

Versión  disponible 2.0.0 

# Entorno de desarrollo

Considerando que la conexión se hará a la base de datos __*ORACLE*__ ya existente, entonces solo queda la previa instalación de las siguientes herramientas : 

- __*Java 8*__ 
- __*Spring Boot 4*__ 
- __*Docker v.20 o superior*__
- __*Postman*__

# Instalación y funcionamiento

### __*Pasos para la instalción :*__ 

Desde la carpeta del proyecto abrimos una nueva terminal desde donde ejecutamos:

```yml 
mvn clean install package
```

Esto con la finalidad de compilar el jar de la aplicación. 

Luego desde el mismo terminal generamos la imagen de docker de nuestra aplicación : 

```yml
docker build -t  practica2-wid
```

Por ultimo para poder poner en marcha el servicio utilizamos el siguiente comando, exponiendo el puerto __9595__: 

```yml
docker run -p  9595:9595 --name p2-container-wid practica2-wid
```

### __*Uso*__

Para probar el servicio solo necesita ingresar a su navegador web  y escribir la siguiente direccion 

` localhost:9595/cliente/buscar`

De esta manera el servicio obtendra todos los clientes que existen en la base de datos. 

Para fasilitar al usuario final se tienen un archivo llamado : __*Practica2.endpoints.json*__ que 
puede cargar en Postman para poder tener acceso a todos los endpoints de la API. 


# Desarrollo 

Desarrollado por @widjos  febrero del 2022