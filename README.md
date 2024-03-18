# Turnera Médica en Java

no pensada para uso profesional, esto es una demostracion tecnica

## Descripción
Este proyecto es una aplicación de turnera médica desarrollada en Java, diseñada para estudiar y aplicar conceptos de programación orientada a objetos, interfaces, clases abstractas, y desarrollo de interfaces gráficas con Swing. Utiliza una base de datos H2 para gestionar la información localmente, permitiendo la creación de turnos médicos y la gestión de pacientes, así como el manejo de sesiones de usuarios.

## Consideraciones
Aunque demuestra capacidades funcionales y técnicas, debe considerarse como un prototipo o ejemplo de desarrollo y no está destinado para uso profesional en entornos de producción.


## Características
- **Programación Orientada a Objetos**: El proyecto está estructurado en torno a principios de OOP, lo que facilita la organización, modularidad y escalabilidad del código.
- **Arquitectura en tres capas modular**: El proyecto emplea la arquitectura de tres capas, utilizando entidades para modelar los datos, DAOs (Objetos de Acceso a Datos) para la interacción con la base de datos, y servicios que facilitan la comunicación entre los DAOs y los paneles de la interfaz de usuario de Swing. Esta estructura asegura una clara separación de responsabilidades, mejorando la organización del código y facilitando su mantenimiento y escalabilidad.
- **Interfaces y Clases Abstractas**: Se utilizan para definir estructuras comunes que permiten reciclar codigo y comunicacion servicios.
- **Interfaz Gráfica con Swing**: Proporciona una interfaz de usuario basica como para la demostracion.
- **Base de datos H2**: Utiliza H2 para el almacenamiento local de datos
- **Gestión de Sesiones**: Permite el manejo de sesiones de usuarios, asegurando una experiencia segura y personalizada.

## Demo
- para iniciar la demo es necesario ejecutar primero la base de datos mediante el archivo situado en h2\bin\h2.bat luego de ejecutar ese archivo puede correr el programa normalmente (es necesario para el inicio de sesion)
el usuario administrador tiene **dni 1 y contraseña admin**
- en src/misc esta situada la clase dbcreate que contiene el codigo para crear la base de datos en caso de que no este cargada

## Imagenes
- Manejo de sesiones mediante un login simple
![Manejo de sesiones mediante un login simple](/imagenes/login.png)
- administracion de turnos, con validadores correspondientes
![administracion de turnos, con validadores correspondientes](/imagenes/turno.png)
- arbol de archivos que demuestra el modelo de las 3 capas para aplicaciones
![arbol de archivos que demuestra el modelo de las 3 capas para aplicaciones](/imagenes/archivos.png)

