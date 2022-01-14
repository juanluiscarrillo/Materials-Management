# Gestión de materiales

Se trata de una aplicación para la gestión de las compras de una empresa de instalaciones eléctricas y telecomunicaciones. Está especialmente diseñada para el control de los precios de los proveedores. 

Los materiales se gestionan de manera separada por cada vendedor-fabricante. Esto facilita enormemente la introducción de datos, ya que el código del material coincide con el código que el vendedor pone en los albaranes. No obstante, para facilitar las búsquedas, cada material está asociado a una categoría.

Los datos se almacenan en una base de datos Access, aunque se pueden migrar fácilmente a otra base de datos.


## Diseño de la aplicación

Se trata de una aplicación visual. Cuando se lanza el programa, aparece una pantalla princial con las siguentes opciones:
- Material
- Proveedor
- Albaranes
- Factura
- Historial precio

El primer paso es crearse los proveedores. A continuación, los materiales. En ese momento, ya es posible introducir los albaranes. A partir de los albaranes, se genera la factura. 

# Utilización de la aplicación

La explicación presupone que se está utilizando linux. No obstante, para otros sistemas operativos los pasos serán similares. Además, se cuenta con que se tienen correctamente instalados: git y un JDK actualizado de Java.

En el repositorio se guardan los ficheros fuentes (dentro de la carpeta *src*), los *.jar* necesarios, la base de datos y los *scripts* de compilación y ejecución. A continuación, se detallan todos los pasos que se ha de seguir:
1. Clonación del proyecto: `git clone https://github.com/juanluiscarrillo/Materials-Management.git`
2. Acceso a la carpeta del proyecto: `cd Materials-Management/`
3. Concesión permiso de ejecución de los *scripts*: `chmod +x *.sh`
4. Compilación: `./compile.sh`
5. Ejecución: `./exe.sh`
