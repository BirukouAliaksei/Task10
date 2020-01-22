package com.github.mytestuser;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleTest {

    @Step("Check parameters are:")
    public void checkParametersAre(int a, int b) {

    }

    @Step("Do another step {variable}")
    public void doAnotherStep(String variable) {

    }

    @Test
    public void simpleTest() {
        checkParametersAre(1, 2);
        doAnotherStep("some string variable");
        assertThat(2, is(2));
    }

    @Test
    public void throwAnExceptionTest() {
        throw new IllegalStateException("Test exception");
    }

    @Step
    public void checkThat2is2() {
        assertThat(2, is(2));
    }

    @Test
    public void simpleTestWithSteps() {
        checkThat2is2();
    }

    @Attachment
    public String makeAttach() {
        return "Simple attachment, the method returns a string";
    }

    @Attachment
    public byte[] takeScreenShot() throws AWTException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(
                new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())),
                "png",
                baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

    @Test
    public void simpleTestWithAttachments() throws Exception {
        assertThat(2, is(2));
        makeAttach();
        takeScreenShot();
    }

    @Step("Add link to Allure Report resource")
    public static void addLinkAllure() {
        String link = "http://allure.qatools.ru/";
        Allure.addAttachment("Result", "text/plain", link);
    }

    @Test
    public void simpleTestWithAddingAttachments() {
        addLinkAllure();
    }

    @Test
    @Description(value = "The test checks the equivalence of the number 1 to the number 1")
    public void equivalenceTest() {
        assertTrue(1 == 1);
    }

    @Epic(value = "Mathematics")
    @Feature(value = "Simple operations")
    @Story(value = "Addition")
    @Test
    public void sumTest() {
        assertThat(5 + 4, is(9));
    }

    @Epic(value = "Mathematics")
    @Feature(value = "Simple operations")
    @Story(value = "Subtraction")
    @Test
    public void subTest() {
        assertThat(8 - 2, is(6));
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "Geometry")})
    @Features(value = {@Feature(value = "Trigonometry"), @Feature(value = "Simple operations")})
    @Stories(value = {@Story(value = "Sinus"), @Story(value = "Sinusoid")})
    @Test
    public void checkSinTest() {
        assertThat(Math.sin(0), is(0));
    }

    @Test
    @Flaky
    public void testFlaky() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
        if (randomNum == 0) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    @Link(name = "Link", url = "http://yandex.ru")
    public void checkSumWithLinkTest() {
        assertThat(5 + 4, is(9));
    }

    @Test
    @Links(value = {@Link(name = "Link1", url = "http://allure.qatools.ru/"),
            @Link(name = "Link2", url = "http://yandex.ru")})
    public void checkSumWithLinksTest() {
        assertThat(5 + 4, is(9));
    }

    @Test
    @Owner(value = "Ivanov Ivan Ivanovich")
    public void testOwner() {
        assertThat(5 + 4, is(9));
    }

    @Test
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSeverity() {
        assertThat(5 + 4, is(9));
    }

    @Test
    public void failedTest() {
        fail("This test should be failed");
    }

    @Test
    @Issue("ITX-123")
    @TmsLink("TRITX-123")
    public void issueTmsTest1() {
        assertTrue(1 == 1);
    }

    @Test
    @Issues({@Issue("ITX-123"), @Issue("ITX-456"), @Issue("ITX-789")})
    @TmsLink("TRITX-123")
    public void issueTmsTest2() {
        assertTrue(1 == 1);
    }

    @Test()
    public void csvAttachmentTest() throws Exception {
        saveCsvAttachment();
    }

    @Test()
    public void svgAttachmentTest() throws Exception {
        saveSvgAttachment();
    }

    @Attachment(value = "Sample csv attachment", type = "text/csv")
    public byte[] saveCsvAttachment() throws URISyntaxException, IOException {
        return getSampleFile("sample.csv");
    }

    @Attachment(value = "Sample svg attachment", type = "image/svg+xml")
    public byte[] saveSvgAttachment() throws URISyntaxException, IOException {
        return getSampleFile("sample.svg");
    }

    private byte[] getSampleFile(String fileName) throws IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            fail(format("Couldn't find resource '%s'", fileName));
        }
        return Files.readAllBytes(Paths.get(resource.toURI()));
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "addition")})
    @Severity(value = SeverityLevel.TRIVIAL)
    @Test
    public void mathTest1(){
        assertEquals(5, 2+3);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "addition")})
    @Test
    public void mathTest2(){
        assertEquals(4, 2+2);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "addition")})
    @Test
    public void mathTest3(){
        assertEquals(3, 2+1);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "addition")})
    @Test
    public void mathTest4(){
        assertEquals(7, 2+5);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "addition")})
    @Test
    public void mathTest5(){
        assertEquals(10, 5+5);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "subtraction")})
    @Test
    public void mathTest6(){
        assertEquals(7, 10-3);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "subtraction")})
    @Test
    public void mathTest7(){
        assertEquals(6, 10-4);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "subtraction")})
    @Test
    public void mathTest8(){
        assertEquals(3, 10-7);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "subtraction")})
    @Test
    public void mathTest9(){
        assertEquals(3, 9-6);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "subtraction")})
    @Test
    public void mathTest10(){
        assertEquals(1, 5-4);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "division")})
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    public void mathTest11(){
        assertEquals(2, 4/2);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "division")})
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    public void mathTest12(){
        assertEquals(6, 36/6);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "division")})
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    public void mathTest13(){
        assertEquals(5, 25/5);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "division")})
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    public void mathTest14(){
        assertEquals(10, 100/10);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "division")})
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    public void mathTest15(){
        assertEquals(3, 9/3);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "multiplication")})
    @Severity(value = SeverityLevel.MINOR)
    @Test
    public void mathTest16(){
        assertEquals(9, 3*3);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "multiplication")})
    @Severity(value = SeverityLevel.MINOR)
    @Test
    public void mathTest17(){
        assertEquals(4, 2*2);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "multiplication")})
    @Severity(value = SeverityLevel.MINOR)
    @Test
    public void mathTest18(){
        assertEquals(36, 6*6);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "multiplication")})
    @Severity(value = SeverityLevel.MINOR)
    @Owner(value = "Petr Petrovich")
    @Test
    public void mathTest19(){
        assertEquals(25, 5*5);
    }

    @Epics(value = {@Epic(value = "Mathematics"), @Epic(value = "multiplication")})
    @Severity(value = SeverityLevel.MINOR)
    @Test
    public void mathTest20(){
        assertEquals(15, 3*5);
    }





}

