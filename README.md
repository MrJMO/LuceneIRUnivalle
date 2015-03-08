# LuceneIRUnivalle
Implementación de Lucene Básico para TG - Univalle

#Funcionamiento
##Recuperación de información con Lucene
La recuperación de información con lucene funciona, en este proyecto, con dos clases separadas 
Indexador y Searcher.

###Indexador
Con el `Indexador` lo deseado es generar una librería de búsqueda de 
la base de documentos existente. Ya que esto es una unidad con una única función sin retorno (o retorno void) 
lo ideal es ejecutarlo para la tarea requerida por tanto se genera un ejecutable .jar el cual recibe dos 
parametros `indexPath` y `docsPath` (en este orden).

- `indexPath` hace referencia al directorio donde se desean guardar las los archivos de indexación.
- `docsPath` hace referencia al directorio donde están contenidos los documentos que se desean indexar y 
en un futuro buscar.

El indexador utiliza todas las dependencias que se encuentran en la carpeta LuceneJar.

###Searcher
Con el `Searcher` se desea realizar una búsqueda sobre lo ya previamente indexado. Esta funcionalidad retorna 
una colección ArrayList y consiste en un método para realizar la búsqueda por tanto se genera un ejecutable .jar 
que recibe dos parametros `busqueda` y `indexPath` (en este orden).

- `busqueda` hace referencia a la cadena de texto que se desea buscar.
- `indexPath` hace referencia al directorio donde se desean guardar las los archivos de indexación.

El Searcher utiliza las siguientes dependencias:

- lucene-analyzers-common-4.9.0
- lucene-core-4.9.0
- lucene-highlighter-4.9.0
- lucene-queryparser-4.9.0