import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    @Test
    @Order(1)
    public void runTest() {
//        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 6000;
        open("http://open-eshop.stqa.ru/oc-panel/auth/login");
    }

    @Test
    @Order(2)
    public void login(){
        $(byXpath("//*[@id='page']//*[@name='email']")).setValue("demo@open-eshop.com");
        $(byXpath("//*[@id='page']//*[@name='password']")).setValue("demo").pressEnter();
    }

    @Test
    @Order(3)
    public void createCoupone(){
        $(byText("eShop")).click();
//        $(byXpath("//div[@id='accordion']//span[text()='eShop']")).click();
        $(byXpath("//*[@title='Coupons']")).click();
        $(byXpath("//*[@class='btn btn-primary pull-right']")).click();
        $(byId("name")).setValue("Gold");
        $(byId("discount_amount")).setValue("34");
        $(byName("valid_date")).setValue("2020-01-09");
        $(byName("number_coupons")).setValue("21").pressEnter();
//        $(".alert-heading").shouldHave(text("Success"));
    }
    @Test
    @Order(4)
    public void searchCoupone(){
        $(byName("name")).setValue("Gold").pressEnter();
//        $(byId("tr4174")).shouldBe(visible);
    }

    @Test
    @Order(5)
    public void couponDelete(){
        $(byXpath("//*[@class='glyphicon glyphicon-trash']")).click();
        $(byXpath("//*[@class='confirm']")).click();
        $(byId("tr4174")).shouldNotBe(visible);
    }

    @Test
    @Order(6)
    public void logOut(){
        $(byXpath("//*[@calss='btn dropdown-toggle btn-success navbar-btn']")).click();
        $(byText("Logout")).click();
    }
}
