package automatizacionTestCases;

import java.util.concurrent.TimeUnit;

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

public class Prueba2 {
	public static WebDriver driver;
	public int precioProducto;
	

	@BeforeTest
	public void planprueba2() {
		System.setProperty("webdriver.chrome.driver", "chromeDriver/bin/windows/googlechrome/64bit/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "chromeDriver/bin/windows/marionette/64bit/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	
	@DataProvider(name="busquedaData")
	public Object[][] searchData() {
		return new Object[][] {{"reloj"},};
	}
	@DataProvider(name="indexElementData")
	public Object[][] positionData() {
		return new Object[][] {{0},};
	}
	
	@Test(priority = 1)
	public void abrir_Falabella_Page() {
		String url = "https://www.falabella.com/falabella-cl/";
		PruebaPage prueba2 = new PruebaPage(driver);
		driver.get(url);
		Assert.assertTrue(prueba2.existElement("Pagina Home"), "Pagina no existe");
	}

	@Test(priority = 2)
	public void validar_Existencia_Elemetos_Home() {

		PruebaPage prueba2 = new PruebaPage(driver);
		prueba2.existElement("Buscador de Productos");
		prueba2.existElement("Boton Buscar Productos");
	}

	@Test(priority = 3, dataProvider = "busquedaData")
	public void realizar_Busqueda_Producto(String busqueda) {

		PruebaPage prueba2 = new PruebaPage(driver);
		prueba2.setText("Buscador de Productos", busqueda);
		prueba2.clickElement("Boton Buscar Productos");
	}

	@Test(priority = 4, dataProvider = "indexElementData")
	public void obtengo_Precio_Producto_encontrado(int index) {
		PruebaPage prueba2 = new PruebaPage(driver);
		prueba2.existElement("Lista Productos Encontrados");	
	    prueba2.seleccionarProductoLista(index);
	    precioProducto =  prueba2.guardarPrecioProducto();
		
	}
	
	@Test(priority = 5)
	public void agregar_producto_carro() {
		PruebaPage prueba2 = new PruebaPage(driver);
		prueba2.existElement("Boton Agregar Bolsa");
		prueba2.elemntIsEnable("Boton Agregar Bolsa");
		prueba2.clickElement("Boton Agregar Bolsa");
	}
	
	@Test(priority = 6)
	public void ver_producto_carro() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		PruebaPage prueba2 = new PruebaPage(driver);
	    prueba2.existElement("Boton ver Bolsa");
		prueba2.elemntIsEnable("Boton ver Bolsa");
		prueba2.clickElement("Boton ver Bolsa");


	}
	
	@Test(priority = 7)
	public void validar_precio_producto_carro() {
		PruebaPage prueba2 = new PruebaPage(driver);
		prueba2.existElement("Precio Producto Carro");
		prueba2.compararPrecioCarro(precioProducto);
	}
	
	 @AfterTest
	 public void cerrar_Navegador() throws Exception { 
	   driver.quit();
	  } 

}