package automatizacionPages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import bsh.Console;
import exception.qaTestException;
import util.WebElementUtil;

/**
 * @author yalmarza
 *
 */
public class PruebaPage {

	WebDriver driver;
   
	//prueba1
	@FindBy(how = How.ID, using = "searchQuestionSolr")
	public WebElement buscadorProductos;
	@FindBy(how = How.ID, using = "pageControlParameters")
	public WebElement paginaHome;
	@FindBy(how = How.XPATH, using = "//*[@id=\"searchFormSolr\"]/a/i")
	private WebElement btnBuscarProducto;
	@FindBy(how = How.XPATH, using = "//*[@id=\"searchFormSolr\"]/a/i")
	private WebElement titPro;
	@FindBy(how = How.CLASS_NAME, using = "fb-price")
	private WebElement precioProductoBuscado;
	@FindBy(how = How.XPATH, using = "//*[@id=\"fbra_browseMainProduct\"]/div/div/div[2]/div/div[7]/button")
	private WebElement btnAgregarBolsa;
	@FindBy(how = How.CSS, using = ".fb-overlay__inject > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1)")
	private WebElement btnVerBolsa;
	@FindBy(how = How.ID, using = "fb-added-to-basket__footer")
	private WebElement modalBolsa;		
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/main/div/div[2]/div[2]/div[1]/div[3]/section/section/form/section/div[1]/div/div/span[1]")
	private WebElement precioProductoCarro;
    
	
	//prueba2
	@FindBy(how = How.ID, using = "all-pods")
	private WebElement listaProductosEncontrados;
	
	
	public PruebaPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean existElement(String element) {
		switch(element) {
		    case "Buscador de Productos"       : return WebElementUtil.isElementPresent(buscadorProductos);
		    case "Boton Buscar Productos"      : return WebElementUtil.isElementPresent(btnBuscarProducto);	
		    case "Pagina Home"                 : return WebElementUtil.isElementPresent(paginaHome);
		    case "Precio Producto Buscado"     : return WebElementUtil.isElementPresent(precioProductoBuscado);
		    case "Boton Agregar Bolsa"         : return WebElementUtil.isElementPresent(btnAgregarBolsa);
		    case "Boton ver Bolsa"             : return WebElementUtil.isElementPresent(btnVerBolsa);
		    case "Precio Producto Carro"       : return WebElementUtil.isElementPresent(precioProductoCarro);
		    case "Lista Productos Encontrados" : return WebElementUtil.isElementPresent(listaProductosEncontrados);
		    default: throw new qaTestException("Nombre de elemeto no valido!");
		}
	}
	
	public void clickElement(String element) {
		switch(element) {
			case "Boton Buscar Productos"           : btnBuscarProducto.click();   break;
			case "Boton Agregar Bolsa"              : btnAgregarBolsa.click();     break;
			case "Boton ver Bolsa"                  : btnVerBolsa.click();         break;
	    	 default: throw new qaTestException("Nombre de elemento no valido!");
		}
	}
	

	public void setText(String element, String word) {
		switch(element) {
		    case "Buscador de Productos"	   : WebElementUtil.ingresaTexto(buscadorProductos, word); break;
	
	    	default: throw new qaTestException("Nombre de elemento no valido!");
		}
	}
	
	public void seleccionarProductoLista(int index) {
			List<WebElement> productos = listaProductosEncontrados.findElements(By.className("pod-item"));
			productos.get(index).findElement(By.className("image")).click();
	}	
	
	public int guardarPrecioProducto() {
		int precioProducto = 0;
			String precioProductoString = getTextElement("Precio Producto Obtenido");
			precioProductoString = precioProductoString.replaceAll("\\D+","");
			precioProducto = Integer.valueOf(precioProductoString);
		return precioProducto;
		
	}
	public String getTextElement(String element) {
		switch (element) {
		case "Precio Producto Obtenido": return precioProductoBuscado.getText().trim();
		case "Precio Producto Carro"   : return precioProductoCarro.getText();
		default:
			throw new qaTestException("Nombre de elemento no valido!");
		}
	}
	
	public boolean elemntIsEnable(String element) {
		switch(element) {
			case "Buscador de Productos"                 : return buscadorProductos.isEnabled();
			case "Boton Buscar Productos" 		         : return btnBuscarProducto.isEnabled();
			case "Boton Agregar Bolsa"                   : return btnAgregarBolsa.isEnabled();   
			case "Boton ver Bolsa"                       : return btnVerBolsa.isEnabled();
			
			default: throw new qaTestException("Nombre de elemento no valido!");
		}
	}
	
	public void compararPrecioCarro(int precioProducto){		
		int precioCarroInt = 0;
		String precioCarroString = getTextElement("Precio Producto Carro");
		       precioCarroString = precioCarroString.replaceAll("\\D+","");
		       precioCarroInt = Integer.valueOf(precioCarroString);
		   	   Assert.assertEquals(precioCarroInt, precioProducto, "Los Precios son Distintos");
	}
	
	
}
