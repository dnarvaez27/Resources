package com.dnarvaez27.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Clase que permite el acceso a recursos del archivo
 * 
 * @author dnarvaez27
 */
public class Resources
{
	/**
	 * Clase principal por donde se accederan a los recursos
	 */
	private Class<?> mainClass;
	
	/**
	 * Ruta del folder dentro de la carpeta de recursos
	 */
	private String mainResFolder;
	
	/**
	 * Construye un objeto manejador de archivos de recursos.<br>
	 * 
	 * @param clasePrincipal metodo <b><u>.getClassLoader( )</u></b> o <b><u>.class</u></b> de la clase principal contenedora del metodo main
	 * @param mainRscFolder Directorio (Folder) donde se encuentran todos los resources. No incluir slash
	 */
	public Resources( Class<?> mainClass, String mainRscFolder )
	{
		this.mainClass = mainClass;
		this.mainResFolder = mainRscFolder;
	}
	
	/**
	 * Devuelve el URL del resource cuyo nombre es dado por parámetro
	 * 
	 * @param txt Nombre del recurso
	 * @return URL del recurso ubicado en el ClassPath
	 */
	public URL getPathFile( String txt )
	{
		URL url = mainClass.getResource( File.pathSeparator + mainResFolder + File.pathSeparator + txt );
		return url;
	}
	
	/**
	 * Devuelve el texto alojado en el recurso cuyo nombre es dado por parámetro
	 * 
	 * @param txt Nombre del recurso
	 * @return Cadena de texto con el contenido del recurso
	 */
	public String getTextFromResource( String txt )
	{
		StringBuilder texto = new StringBuilder( );
		try
		{
			InputStream is = mainClass.getResourceAsStream( File.pathSeparator + mainResFolder + File.pathSeparator + txt );
			BufferedReader bf = new BufferedReader( new InputStreamReader( is, StandardCharsets.UTF_8.name( ) ) );
			String linea = bf.readLine( );
			while( linea != null )
			{
				texto.append( linea + System.lineSeparator( ) );
				linea = bf.readLine( );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		return texto.toString( );
	}
	
	/**
	 * Devuelve el BufferedReader cargado con la información del recurso con el nombre dado por parámetro
	 * 
	 * @param txt Nombre del recurso
	 * @return BufferedReader con la información del recurso cargado
	 */
	public BufferedReader getScannerFromResource( String txt )
	{
		BufferedReader bf = null;
		try
		{
			InputStream is = mainClass.getClass( ).getResourceAsStream( File.pathSeparator + mainResFolder + File.pathSeparator + txt );
			bf = new BufferedReader( new InputStreamReader( is, StandardCharsets.UTF_8.name( ) ) );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		return bf;
	}
	
	/**
	 * Devuelve el InputStream cargado con la información del recurso con el nombre dado por parámetro
	 * 
	 * @param txt Nombre del recurso
	 * @return InputStream con la información del recurso
	 */
	public InputStream getResourceAsStream( String txt )
	{
		return mainClass.getClass( ).getResourceAsStream( File.pathSeparator + mainResFolder + File.pathSeparator + txt );
	}
}