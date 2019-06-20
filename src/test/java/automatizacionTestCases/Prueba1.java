/**
 * 
 */
package automatizacionTestCases;

import java.util.concurrent.TimeUnit;

import javax.xml.ws.Action;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automatizacionPages.PruebaPage;

/**
 * @author yalmarza
 *
 */

public class Prueba1 {
	public static WebDriver driver;
	public int precioProducto;
	

	@BeforeTest
	public void planPrueba1() {
		System.setProperty("webdriver.chrome.driver", "chromeDriver/bin/windows/googlechrome/64bit/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "chromeDriver/bin/windows/marionette/64bit/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	
	@DataProvider(name="busquedaData")
	public Object[][] searchData() {
		return new Object[][] {{"6993328"},};
	}
	
	
	@Test(priority = 1)
	public void abrir_Falabella_Page() {
		String url = "https://www.falabella.com/falabella-cl/";
		PruebaPage prueba1 = new PruebaPage(driver);
		driver.get(url);
		Assert.assertTrue(prueba1.existElement("Pagina Home"), "Pagina no existe");
	}

	@Test(priority = 2)
	public void validar_Existencia_Elemetos_Home() {

		PruebaPage prueba1 = new PruebaPage(driver);
		prueba1.existElement("Buscador de Productos");
		prueba1.existElement("Boton Buscar Productos");
	}

	@Test(priority = 3, dataProvider = "busquedaData")
	public void realizar_Busqueda_Producto(String busqueda) {

		PruebaPage prueba1 = new PruebaPage(driver);
		prueba1.setText("Buscador de Productos", busqueda);
		prueba1.clickElement("Boton Buscar Productos");
	}

	@Test(priority = 4)
	public void obtengo_Precio_Producto_encontrado() {
		PruebaPage prueba1 = new PruebaPage(driver);
		prueba1.existElement("Precio Producto Buscado");
		precioProducto =  prueba1.guardarPrecioProducto();
		
	}
	
	@Test(priority = 5)
	public void agregar_producto_carro() {

		PruebaPage prueba1 = new PruebaPage(driver);
		prueba1.existElement("Boton Agregar Bolsa");
		prueba1.elemntIsEnable("Boton Agregar Bolsa");
		prueba1.clickElement("Boton Agregar Bolsa");
	}
	
	@Test(priority = 6)
	public void ver_producto_carro() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		PruebaPage prueba1 = new PruebaPage(driver);
	    prueba1.existElement("Boton ver Bolsa");
		prueba1.elemntIsEnable("Boton ver Bolsa");
		prueba1.clickElement("Boton ver Bolsa");

	}
	
	@Test(priority = 7)
	public void validar_precio_producto_carro() {
		PruebaPage prueba1 = new PruebaPage(driver);
		prueba1.existElement("Precio Producto Carro");
		prueba1.compararPrecioCarro(precioProducto);
	}
	
	 @AfterTest
	 public void cerrar_Navegador() throws Exception { 
	   driver.quit();
	  } 

}
